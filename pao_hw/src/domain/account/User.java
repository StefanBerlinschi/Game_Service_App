package domain.account;

public class User {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private double accountBalance;

    public User() {
    }

    public User(String username, String password, String nickname, String email, double accountBalance) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return  "username: " + username +
                ", nickname: " + nickname +
                ", email: " + email +
                ", accountBalance: " + accountBalance + "$";
    }

    public String StringCSV() {
        return  username +
                ',' + nickname +
                ',' + email +
                ',' + accountBalance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
}
