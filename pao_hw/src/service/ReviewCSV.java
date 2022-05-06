package service;

import domain.review.Review;

import java.io.*;
import java.util.ArrayList;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Double.parseDouble;

public class ReviewCSV implements GenericCSVService<Review>{
    private static final ReviewCSV INSTANCE = new ReviewCSV();

    private ReviewCSV() {}

    @Override
    public ArrayList<Review> load(String file) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        ArrayList<Review> reviews = new ArrayList<>();

        try {
            String line = reader.readLine();
            while (line != null) {
                String[] data = line.split(";;");
                Review review = new Review(data[0], data[1], parseDouble(data[2]), parseDouble(data[3]), data[4],  parseBoolean(data[5]));
                reviews.add(review);
                line = reader.readLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return reviews;
    }

    @Override
    public void add(String file, Review rv) {
        FileWriter writer = null;

        try {
            writer = new FileWriter(file, true);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writer.append(rv.StringCSV());
            writer.flush();
            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ReviewCSV getInstance() {
        return INSTANCE;
    }
}
