package game;
import account.Developer;
import review.Review;
import java.util.ArrayList;

public class Game {
    private String name;
    private ArrayList<Developer> dev = new ArrayList<>();
    private int storage_size;   // in bytes
    private double rating;      // out of 5
    private ArrayList<Review> reviews = new ArrayList<>();

    public Game() {
    }

    public Game(String name, int storage_size, double rating) {
        this.name = name;
        this.storage_size = storage_size;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return name;
    }

    public void addDeveloper(Developer develop) {
        dev.add(develop);
    }

    public void addReview(Review rev) {
        reviews.add(rev);
        rating = calculateRating();
    }

    public double calculateRating() {
        double s = 0;
        for (Review rev: reviews) {
            s = s + rev.getRating();
        }
        return s/ reviews.size();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Developer> getDev() {
        return dev;
    }

    public int getStorage_size() {
        return storage_size;
    }

    public double getRating() {
        return rating;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }
}
