package domain.items;

import java.util.ArrayList;
import other.Pair;

public class Inventory {
    private String playerUsername;
    private ArrayList<Pair<String, String>> items = new ArrayList<>();

    public Inventory(String playerUsername) {
        this.playerUsername = playerUsername;
    }

    public Inventory(String playerUsername, ArrayList<Pair<String, String>> items) {
        this.playerUsername = playerUsername;
        this.items = items;
    }

    public void addItem(Pair<String, String> p) {
        items.add(p);
    }

    @Override
    public String toString() {
        String inventory = playerUsername + "'s inventory: ";

        for (Pair<String, String> pair: items) {
            inventory = inventory + pair.toString() + ", ";
        }

        return inventory;
    }

    public String StringCSV() {
        String inventory = playerUsername + ";;";

        for (Pair<String, String> pair: items) {
            inventory = inventory + pair.toString() + "::";
        }

        return inventory;
    }

    public String getPlayerUsername() {
        return playerUsername;
    }

    public void setPlayerUsername(String playerUsername) {
        this.playerUsername = playerUsername;
    }

    public ArrayList<Pair<String, String>> getItems() {
        return items;
    }

    public void setItems(ArrayList<Pair<String, String>> items) {
        this.items = items;
    }
}
