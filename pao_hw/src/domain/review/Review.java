package domain.review;

public class Review {
    private String player_username;
    private String game_title;
    private double rating;
    private double hoursPlayed;
    private String reviewText;
    private boolean isRecommended;

    public Review(String username, String game_title, double rating, double hoursPlayed, String reviewText, boolean isRecommended) {
        this.player_username = username;
        this.game_title = game_title;
        this.rating = rating;
        this.hoursPlayed = hoursPlayed;
        this.reviewText = reviewText;
        this.isRecommended = isRecommended;
    }

    @Override
    public String toString() {
        return  "player: " + player_username +
                ", game: " + game_title +
                ", rating: " + rating + "/5" +
                ", hours played: " + hoursPlayed +
                ", review: " + reviewText +
                ", recommended: " + isRecommended;
    }

    public String StringCSV() {
        return  player_username +
                ";;" + game_title +
                ";;" + rating +
                ";;" + hoursPlayed +
                ";;" + reviewText +
                ";;" + isRecommended;
    }


    public String getPlayer_username() { return player_username; }

    public String getGame_title() {
        return game_title;
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

    public void setPlayer_username(String player_username) { this.player_username = player_username; }

    public void setGame_title(String game) {
        this.game_title = game;
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
