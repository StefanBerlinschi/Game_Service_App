package database;

import domain.account.Developer;
import domain.account.Player;
import domain.account.User;
import domain.game.Game;
import domain.items.Item;
import domain.items.ItemType;
import domain.review.Review;
import service.ReviewCSV;
import service.UserCSV;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class Database {
    private Scanner s = new Scanner(System.in);
    private static ArrayList<Game> games = new ArrayList<>();
    private static ArrayList<Item> items = new ArrayList<>();
//    private ArrayList<Inventory> inventories = new ArrayList<>();
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Review> rev = new ArrayList<>();

    private static GamesDB gamesDB = GamesDB.getInstance();

    public static void main(String[] args) {
        Database database = new Database();

//        UserCSV x = UserCSV.getInstance();
//        try {
//            ArrayList<User> ls = x.load("/home/stefan/Desktop/materii_facultate/pao/pao_hw/src/service_files/User.csv");
//            for (User user: ls) {
//                users.add(user);
//            }
//        }
//        catch (FileNotFoundException e) {
//            System.out.println("Users file not found");
//        }
//
//        ReviewCSV y = ReviewCSV.getInstance();
//        try {
//            ArrayList<Review> ls = y.load("/home/stefan/Desktop/materii_facultate/pao/pao_hw/src/service_files/Review.csv");
//            for (Review review: ls) {
//                rev.add(review);
//            }
//        }
//        catch (FileNotFoundException e) {
//            System.out.println("Review file not found");
//        }
//
//        System.out.println(rev);
//
//        String player = database.s.nextLine();
//        String game = database.s.nextLine();
//        Double rating = parseDouble(database.s.nextLine());
//        Double hrs = parseDouble(database.s.nextLine());
//        String revtext = database.s.nextLine();
//        Boolean recc = parseBoolean(database.s.nextLine());
//
//        Review r = new Review(player, game, rating, hrs, revtext, recc);
//        y.add("/home/stefan/Desktop/materii_facultate/pao/pao_hw/src/service_files/Review.csv", r);

//        GamesDB loader = new GamesDB();
        ArrayList<Game> g = gamesDB.read();
        for (Game game: g) {
            games.add(game);
        }

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
        System.out.println("16. Delete game");
        System.out.println("17. Update game");
        System.out.println("18. Exit");
        System.out.print("Option:");
    }

    private int readOption(){
        String line = s.nextLine();
        int option = parseInt(line);
        if (option >= 1 && option <= 18)
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
                returnToMenu();
                break;
            case 5:
                listAllGames();
                returnToMenu();
                break;
            case 6:
                listAllItems();
                returnToMenu();
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
                deleteGame();
                break;
            case 17:
                updateGame();
                break;
            case 18:
                System.exit(0);
        }
    }

    public void returnToMenu() {
        System.out.println("(press enter to return to menu)");
        s.nextLine();
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
            System.out.println("No such domain.game exists!");
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
        System.out.println("domain.account balance:");
        double accBalance = Double.parseDouble(s.nextLine());

        System.out.println("Is it a player or a developer? [p/d]");

        String line = s.nextLine();
        if (line.compareTo("p") == 0) {
            System.out.println("short biography:");
            String bio = s.nextLine();

            User p = new Player(username, password, nickname, email, accBalance, bio);

//            UserCSV x = UserCSV.getInstance();
//            x.add("/home/stefan/Desktop/materii_facultate/pao/pao_hw/src/service/User.csv", p);


            users.add(p);

        } else if (line.compareTo("d") == 0) {
            User d = new Developer(username, password, nickname, email, accBalance);

//            UserCSV x = UserCSV.getInstance();
//            x.add("/home/stefan/Desktop/materii_facultate/pao/pao_hw/src/service/User.csv", d);

            users.add(d);

        } else System.out.println("Invalid option");
    }

    // done
    public void addGame() {
        System.out.println("You have selected adding a game");
        System.out.println("What is the game called?");
        String name = s.nextLine();

        for (Game game: games) {
            if (game.getName().equals(name)) {
                System.out.println("Game already exists!");
                returnToMenu();
                return;
            }
        }

        try {
            System.out.println("How much disk space does it occupy? (in bytes)");
            int bytes = parseInt(s.nextLine());
            System.out.println("What rating would you give it? (out of 10)");
            double rating = Double.parseDouble(s.nextLine());

            if (rating < 0.0 || rating > 10.0) {
                System.out.println("You have entered an invalid rating!");
                returnToMenu();
                return;
            }

            Game g = new Game(name, bytes, rating);
            games.add(g);
            gamesDB.create(g);
        }
        catch (Exception e) {
            System.out.println("You have entered an invalid rating or storage size!");
            returnToMenu();
        }
    }

    public void addItem() {
        System.out.println("You have selected adding a new item in the database");
        System.out.println("What domain.game does the item belong to?");
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

        Item t = new Item(g.getName(), itemName, type, desc);
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
        System.out.println("Complete list of the domain.game.items in our database:");
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
        System.out.println("Which domain.game would you wish to see reviewed? (enter exact name)");
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

        System.out.println("What domain.game would you like to add? (enter exact name)");
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

        System.out.println("What domain.game did they work on?");
        Game g = searchGame();

        if (g == null) {
            System.out.println("Would you like to add it? [y/n]");
            String op = s.nextLine();
            if (op.compareTo("y") == 0) {
                addGame();

                g = games.get(games.size() - 1);
                d.addDevelopedGame(g);
                g.addDeveloper(d.getUsername());
            } else if (op.compareTo("n") == 0) {
                return;
            } else {
                System.out.println("Invalid option, assumed negative");
            }
        }
        else {
            d.addDevelopedGame(g);
            g.addDeveloper(d.getUsername());
        }
    }

    public void addToLibrary() {
        System.out.println("Whose library would you like to update? (enter a username)");
        Player p = searchPlayer();
        if (p == null) {
            return;
        }

        System.out.println("What domain.game would you like to add? (enter exact name)");
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
        System.out.println("What player created this domain.review?");
        Player p = searchPlayer();

        if (p == null) {
            return;
        }

        System.out.println("What domain.game is this domain.review for?");
        Game g = searchGame();

        if (g == null) {
            return;
        }

        System.out.println("What do you rate this domain.game?");
        double rt = Double.parseDouble(s.nextLine());
        System.out.println("How many hours have you played it for?");
        double hrs = Double.parseDouble(s.nextLine());
        System.out.println("Do you recommend it? [y/n]");

        String op = s.nextLine();
        boolean recc = (op.compareTo("y") == 0);

        System.out.println("Review text:");
        String rev = s.nextLine();

        Review r = new Review(p.getUsername(), g.getName(), rt, hrs, rev, recc);
        p.addReview(r);
//        g.addReview(r);

        ReviewCSV y = ReviewCSV.getInstance();
        y.add("/home/stefan/Desktop/materii_facultate/pao/pao_hw/src/service/Review.csv", r);
    }

    // done
    public void deleteGame() {
        System.out.println("Game name: ");
        String name = s.nextLine();

        for (Game game: games) {
            if (game.getName().equals(name)) {
                games.remove(game);
                gamesDB.delete(name);
                System.out.println("Delete successful!");
                returnToMenu();
                return;
            }
        }

        System.out.println("Game doesn't exist!");
        returnToMenu();
    }

    // done
    public void updateGame() {
        listAllGames();
        System.out.println("\nWhich game would you like to update? (enter the name)");
        String name = s.nextLine();

        for (Game game: games) {
            if (game.getName().equals(name)) {
                System.out.println("Enter a new rating or keep the same one:");
                try {
                    String rat = s.nextLine();
                    double rating = parseDouble(rat);
                    System.out.println("Enter a new storage size:");
                    String sz = s.nextLine();
                    int size = parseInt(sz);

                    game.setRating(rating);
                    game.setStorage_size(size);

                    gamesDB.update(game);
                    System.out.println("Update successful!");
                }
                catch (Exception e) {
                    System.out.println("You have entered and invalid rating or storage size!");
                    returnToMenu();
                }
                return;
            }
        }

        System.out.println("The game does not exist!");
        returnToMenu();
    }

}

