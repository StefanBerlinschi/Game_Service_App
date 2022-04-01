package database;

import account.Developer;
import account.Player;
import account.User;
import game.Game;
import items.Inventory;
import items.Item;
import items.ItemType;
import review.Review;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Database {
    private Scanner s = new Scanner(System.in);
    private ArrayList<Game> games = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
//    private ArrayList<Inventory> inventories = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();

    public static void main(String args[]) {
        Database database = new Database();
        while (true) {
            database.menu();
            int option = database.readOption();
            database.apply(option);
        }
    }

    private void menu() {
        System.out.println("---------------------------");
        System.out.println("Game service app");
        System.out.println("1. Add user");
        System.out.println("2. Add game");
        System.out.println("3. Add item");
        System.out.println("4. List all users");
        System.out.println("5. List all games");
        System.out.println("6. List all items");
        System.out.println("7. Display user's library");
        System.out.println("8. Display user's wishlist");
        System.out.println("9. Display user's reviews");
        System.out.println("10. List all reviews for a game");
        System.out.println("11. Update user wishlist");
        System.out.println("12. Add dev as contributor to game");
        System.out.println("13. Add game to player's library");
        System.out.println("14. Add item to player's inventory");
        System.out.println("15. Add review");
        System.out.println("16. Exit");
        System.out.print("Option:");
    }

    private int readOption(){
        String line = s.nextLine();
        int option = Integer.parseInt(line);
        if (option >= 1 && option <= 16)
            return option;

        System.out.println("Invalid option. Try again");
        return readOption();
    }

    private void apply(int op) {
        switch (op) {
            case 1:
                addUser();
                break;
            case 2:
                addGame();
                break;
            case 3:
                addItem();
                break;
            case 4:
                listAllUsers();
                break;
            case 5:
                listAllGames();
                break;
            case 6:
                listAllItems();
                break;
            case 7:
                displayLibrary();
                break;
            case 8:
                displayWishlist();
                break;
            case 9:
                displayReviews();
                break;
            case 10:
                gameReviews();
                break;
            case 11:
                updateWishlist();
                break;
            case 12:
                addDevToGame();
                break;
            case 13:
                addToLibrary();
                break;
            case 14:
                addItemInventory();
                break;
            case 15:
                addReview();
                break;
            case 16:
                System.exit(0);
        }
    }


    public Player searchPlayer() {
        String username = s.nextLine();
        User u = null;

        for (User user: users) {
            if (user.getUsername().compareTo(username) == 0) {
                u = user;
                break;
            }
        }
        if (u == null) {
            System.out.println("No user with that name found");
            return null;
        } else if (u instanceof Developer) {
            System.out.println("This is a developer, not a player!");
            return null;
        }

        return ((Player)u);
    }

    public Developer searchDeveloper() {
        String username = s.nextLine();
        User u = null;

        for (User user: users) {
            if (user.getUsername().compareTo(username) == 0) {
                u = user;
                break;
            }
        }
        if (u == null) {
            System.out.println("No user with that name found");
            return null;
        } else if (u instanceof Player) {
            System.out.println("This is a Player, not a developer!");
            return null;
        }

        return ((Developer)u);
    }

    public Game searchGame() {
        String name = s.nextLine();
        Game g = null;

        for (Game game: games) {
            if (game.getName().compareTo(name) == 0) {
                g = game;
                break;
            }
        }
        if (g == null) {
            System.out.println("No such game exists!");
            return null;
        }

        return g;
    }

    public Item searchItem() {
        String str = s.nextLine();
        Item t = null;

        for (Item item: items) {
            if (item.getName().compareTo(str) == 0) {
                t = item;
                break;
            }
        }
        if (t == null) {
            System.out.println("No such item :(");
        }

        return t;
    }

    public void addUser() {
        System.out.println("You have selected adding a user");

        System.out.println("username:");
        String username = s.nextLine();
        System.out.println("password:");
        String password = s.nextLine();
        System.out.println("nickname:");
        String nickname = s.nextLine();
        System.out.println("email:");
        String email = s.nextLine();
        System.out.println("account balance:");
        double accBalance = Double.parseDouble(s.nextLine());

        System.out.println("Is it a player or a developer? [p/d]");

        String line = s.nextLine();
        if (line.compareTo("p") == 0) {
            System.out.println("short biography:");
            String bio = s.nextLine();

            User p = new Player(username, password, nickname, email, accBalance, bio);
            users.add(p);

        } else if (line.compareTo("d") == 0) {
            User d = new Developer(username, password, nickname, email, accBalance);
            users.add(d);

        } else System.out.println("Invalid option");
    }

    public void addGame() {
        System.out.println("You have selected adding a game");
        System.out.println("What is the game called?");
        String name = s.nextLine();
        System.out.println("How much disk space does it occupy? (in bytes)");
        int bytes = Integer.parseInt(s.nextLine());
        System.out.println("What rating would you give it? (out of 10)");
        double rating = Double.parseDouble(s.nextLine());

        if (rating < 0.0 || rating > 10.0) {
            System.out.println("Invalid rating");
            return;
        }

        Game g = new Game(name, bytes, rating);
        games.add(g);
    }

    public void addItem() {
        System.out.println("You have selected adding a new item in the database");
        System.out.println("What game does the item belong to?");
        String name = s.nextLine();
        Game g = null;

        for (Game game: games) {
            if (game.getName().compareTo(name) == 0) {
                g = game;
            }
        }
        if (g == null) {
            System.out.println("Game does not exist!");
            return;
        }

        System.out.println("What is the item called?");
        String itemName = s.nextLine();
        System.out.println("What is the item type?");
        System.out.println("KEY, CRATE, WEAPON, COSMETIC, OTHER (write it exactly as shown here)");
        ItemType type = ItemType.valueOf(s.nextLine());
        System.out.println("Short description:");
        String desc = s.nextLine();

        Item t = new Item(g, itemName, type, desc);
        items.add(t);
    }

    public void listAllUsers() {
        System.out.println("Complete list of users:");
        for (User user: users) {
            System.out.println(user);
        }
    }

    public void listAllGames() {
        System.out.println("Complete list of games in our database:");
        for (Game game: games) {
            System.out.println(game);
        }
    }

    public void listAllItems() {
        System.out.println("Complete list of the items in our database:");
        for (Item item: items) {
            System.out.println(item);
        }
    }

    public void displayLibrary() {
        System.out.println("Whose library would you like to see? (enter a username)");
        Player p = searchPlayer();
        if (p == null) {
            return;
        }

        System.out.println(p.getOwnedGames());
    }
    
    public void displayWishlist() {
        System.out.println("Whose wishlist would you like to see? (enter a username)");
        Player p = searchPlayer();
        if (p == null) {
            return;
        }

        System.out.println(p.getWishlist());
    }
    
    public void displayReviews() {
        System.out.println("Whose reviews would you like to see? (enter a username)");
        Player p = searchPlayer();
        if (p == null) {
            return;
        }

        System.out.println(p.getReviews());
    }
    
    public void gameReviews() {
        System.out.println("Which game would you wish to see reviewed? (enter exact name)");
        Game g = searchGame();
        if (g == null) {
            return;
        }
        
        System.out.println("Overall rating: " + g.getRating());
        System.out.println(g.getReviews());
    }
    
    public void updateWishlist() {
        System.out.println("Whose wishlist would you like to update? (enter a username)");
        Player p = searchPlayer();
        if (p == null) {
            return;
        }

        System.out.println("What game would you like to add? (enter exact name)");
        Game g = searchGame();
        if (g == null) {
            return;
        }

        p.addToWishlist(g);
    }

    public void addDevToGame() {
        System.out.println("What dev is creating/contributing to this game?");
        Developer d =searchDeveloper();
        if (d == null) {
            return;
        }

        System.out.println("What game did they work on?");
        Game g = searchGame();

        if (g == null) {
            System.out.println("Would you like to add it? [y/n]");
            String op = s.nextLine();
            if (op.compareTo("y") == 0) {
                addGame();

                g = games.get(games.size() - 1);
                d.addDevelopedGame(g);
                g.addDeveloper(d);
            } else if (op.compareTo("n") == 0) {
                return;
            } else {
                System.out.println("Invalid option, assumed negative");
            }
        }
        else {
            d.addDevelopedGame(g);
            g.addDeveloper(d);
        }
    }

    public void addToLibrary() {
        System.out.println("Whose library would you like to update? (enter a username)");
        Player p = searchPlayer();
        if (p == null) {
            return;
        }

        System.out.println("What game would you like to add? (enter exact name)");
        Game g = searchGame();
        if (g == null) {
            return;
        }

        p.addOwnedGame(g);
    }

    public void addItemInventory() {
        System.out.println("What is the name of the item?");
        Item t = searchItem();
        if (t == null) {
            return;
        }

        System.out.println("Whose inventory would you like to add it to? (enter a username)");
        Player p = searchPlayer();
        if (p == null) {
            return;
        }

        p.addToInventory(t);
    }

    public void addReview() {
        System.out.println("What player created this review?");
        Player p = searchPlayer();

        if (p == null) {
            return;
        }

        System.out.println("What game is this review for?");
        Game g = searchGame();

        if (g == null) {
            return;
        }

        System.out.println("What do you rate this game?");
        double rt = Double.parseDouble(s.nextLine());
        System.out.println("How many hours have you played it for?");
        double hrs = Double.parseDouble(s.nextLine());
        System.out.println("Do you recommend it? [y/n]");

        String op = s.nextLine();
        boolean recc = (op.compareTo("y") == 0);

        System.out.println("Review text:");
        String rev = s.nextLine();

        Review r = new Review(p, g, rt, hrs, rev, recc);
        p.addReview(r);
        g.addReview(r);
    }
}
