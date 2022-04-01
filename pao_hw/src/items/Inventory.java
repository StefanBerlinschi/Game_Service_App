package items;

import account.Player;

import java.util.ArrayList;

public class Inventory {
    private String playerUsername;
    private ArrayList<Item> items = new ArrayList<Item>();

    public Inventory() {
    }

    public Inventory(String playerUsername) {
        this.playerUsername = playerUsername;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public String toString() {
        return playerUsername + "'s inventory " +
                ", items = " + items +
                '}';
    }

    public String getPlayerUsername() {
        return playerUsername;
    }

    public void setPlayerUsername(String playerUsername) {
        this.playerUsername = playerUsername;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
