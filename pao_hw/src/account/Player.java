package account;

import game.Game;
import items.Inventory;
import items.Item;
import review.Review;

import java.util.ArrayList;

public class Player extends User {
    private ArrayList<Game> ownedGames = new ArrayList<>();
    private ArrayList<Review> reviews = new ArrayList<>();
    private ArrayList<Game> wishlist = new ArrayList<>();
    private String biography;
    private Inventory inventory = new Inventory(getUsername());

    public Player() {
    }

    public Player(String username, String password, String nickname, String email, double accountBalance, String biography) {
        super(username, password, nickname, email, accountBalance);
        this.biography = biography;
    }

    @Override
    public String toString() {
        return "Player " + super.toString() +
                " ownedGames = " + ownedGames +
                ", reviews = " + reviews +
                ", wishlist = " + wishlist +
                ", biography = '" + biography + '\'' +
                ", inventory = " + inventory;
    }

    public void addOwnedGame(Game game) {
        ownedGames.add(game);
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public void addToWishlist(Game game) {
        if (!ownedGames.contains(game)) {
            wishlist.add(game);
        }
    }

    public void addToInventory(Item item) {
        inventory.addItem(item);
    }

    public ArrayList<Game> getOwnedGames() {
        return ownedGames;
    }


    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public ArrayList<Game> getWishlist() {
        return wishlist;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
