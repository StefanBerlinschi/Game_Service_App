package service;

import domain.game.Game;
import domain.review.Review;

import java.io.*;
import java.util.ArrayList;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Double.parseDouble;

public class GameCSV implements GenericCSVService{
    private static final GameCSV INSTANCE = new GameCSV();

    private GameCSV() {}

    @Override
    public ArrayList<Game> load(String file) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        ArrayList<Game> games = new ArrayList<>();

        try {
            String line = reader.readLine();
            while (line != null) {
                String[] data = line.split(";;");
                Game game = new Game(data[0], data[1], parseDouble(data[2]), parseDouble(data[3]), data[4],  parseBoolean(data[5]));
                games.add(game);
                line = reader.readLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return games;
    }

    @Override
    public void add(String file, Game gm) {
        FileWriter writer = null;

        try {
            writer = new FileWriter(file, true);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writer.append(gm.StringCSV());
            writer.flush();
            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static GameCSV getInstance() {
        return INSTANCE;
    }
}
