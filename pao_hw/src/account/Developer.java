package account;

import game.Game;
import java.util.ArrayList;

public class Developer extends User {
    private ArrayList<Game> developedGames = new ArrayList<>();
    private double rating;

    public Developer() {
    }

    public Developer(String username, String password, String nickname, String email, double accountBalance) {
        super(username, password, nickname, email, accountBalance);
    }

    public void addDevelopedGame(Game game) {
        developedGames.add(game);
        rating = calculateRating();
    }

    public double calculateRating() {
        double s = 0;
        for (Game game: developedGames) {
            s = s + game.getRating();
        }
        return s/developedGames.size();
    }

    @Override
    public String toString() {
        return "Developer " + super.toString() +
                " developedGames = " + developedGames +
                ", rating = " + rating;
    }

    public ArrayList<Game> getDevelopedGames() {
        return developedGames;
    }

    public double getRating() {
        return rating;
    }
}
