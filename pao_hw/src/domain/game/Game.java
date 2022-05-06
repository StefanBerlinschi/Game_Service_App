package domain.game;
import domain.account.Developer;
import domain.review.Review;
import other.Pair;

import java.util.ArrayList;

public class Game {
    private String name;
    private ArrayList<String> dev = new ArrayList<>();  // developer usernames
    private int storage_size;   // in bytes
    private double rating;      // out of 5
    private ArrayList<Pair<String, Double>> reviews = new ArrayList<>();  // player usernames and ratings

    public Game(String name, int storage_size, double rating) {
        this.name = name;
        this.storage_size = storage_size;
        this.rating = rating;
    }

    @Override
    public String toString() {
        String game = "Game name: " + name +
                "; storage size: " + String.valueOf(storage_size) +
                "; rating: " + String.valueOf(rating) +
                "; developers: ";
        for (String developer: dev) {
            game = game + developer + ',';
        }
        game = game + "; reviews: ";
        for (Pair<String, Double> pair: reviews) { // (player | 4.5)
            game = game + pair.toString() + ", ";
        }
        return game;
    }

    public String StringCSV() {
        String game = name +
                ";;" + String.valueOf(storage_size) +
                ";;" + String.valueOf(rating) +
                ";;";
        for (String developer: dev) {
            game = game + developer + "::";
        }
        game = game + ";;";
        for (Pair<String, Double> pair: reviews) { // (player | 4.5)
            game = game + pair.toString() + "::";
        }
        
        return game;
    }

    public void addDeveloper(String developer) {
        dev.add(developer);
    }

    public void addReview(Pair<String, Double> rev) {
        reviews.add(rev);
        rating = calculateRating();
    }

    public double calculateRating() {
        double s = 0;
        for (Pair<String, Double> rev: reviews) {
            s = s + rev.getSecond();
        }
        return s / reviews.size();
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getDev() {
        return dev;
    }

    public int getStorage_size() {
        return storage_size;
    }

    public double getRating() {
        return rating;
    }

    public ArrayList<Pair<String, Double>> getReviews() {
        return reviews;
    }
}
