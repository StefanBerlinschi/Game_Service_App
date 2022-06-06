package database;

import domain.game.Game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class GamesDB {
    private static final GamesDB INSTANCE = new GamesDB();

    private GamesDB() {}

    public static GamesDB getInstance() {
        return INSTANCE;
    }

    public void create(Game g) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/game_service_app", "root", "RootPassword453029.");
            Statement stmt = connection.createStatement();
            String update = "INSERT INTO games VALUES(\"" + g.getName() + "\", \"" + g.getStorage_size() + "\", \"" + g.getRating() +"\")";
            stmt.executeUpdate(update);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Game> read() {
        ArrayList<Game> games = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/game_service_app", "root", "RootPassword453029.");
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM games";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Game g = new Game(rs.getString("name"), rs.getInt("storage_size"), rs.getDouble("rating"));
                games.add(g);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return games;
    }

    public void update(Game g) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/game_service_app", "root", "RootPassword453029.");
            Statement stmt = connection.createStatement();
            String update = "UPDATE games SET ";
            update = update + "storage_size = " + g.getStorage_size() + ", rating = " + g.getRating() + " ";
            update = update + "WHERE name = \"" + g.getName() + "\";";
            stmt.executeUpdate(update);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String name) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/game_service_app", "root", "RootPassword453029.");
            Statement stmt = connection.createStatement();
            String update = "DELETE FROM games WHERE name=\"" + name + "\"";
            stmt.executeUpdate(update);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
