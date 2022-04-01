package review;

import account.Player;
import game.Game;

import java.util.Objects;

public class Review {
    private Player player;
    private Game game;
    private double rating;
    private double hoursPlayed;
    private String reviewText;
    private boolean isRecommended;

    public Review() {
    }

    public Review(Player player, Game game, double rating, double hoursPlayed, String reviewText, boolean isRecommended) {
        this.player = player;
        this.game = game;
        this.rating = rating;
        this.hoursPlayed = hoursPlayed;
        this.reviewText = reviewText;
        this.isRecommended = isRecommended;
    }

    @Override
    public String toString() {
        return  player.getUsername() +
                ", game =" + game +
                ", rating =" + rating +
                ", hoursPlayed =" + hoursPlayed +
                ", reviewText ='" + reviewText + '\'' +
                ", isRecommended = " + isRecommended;
    }


    public Player getPlayer() {
        return player;
    }

    public Game getGame() {
        return game;
    }

    public double getRating() {
        return rating;
    }

    public double getHoursPlayed() {
        return hoursPlayed;
    }

    public String getReviewText() {
        return reviewText;
    }

    public boolean isRecommended() {
        return isRecommended;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setHoursPlayed(double hoursPlayed) {
        this.hoursPlayed = hoursPlayed;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public void setRecommended(boolean recommended) {
        isRecommended = recommended;
    }
}
