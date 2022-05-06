package domain.items;

import domain.game.Game;

public class Item {
    private String game;
    private String name;
    private ItemType type;
    private String description;

    public Item(String game, String name, ItemType type, String description) {
        this.game = game;
        this.name = name;
        this.type = type;
        this.description = description;
    }

    @Override
    public String toString() {
        return "game: " + game +
                ", name: " + name +
                ", item type: " + type +
                ", description: " + description;
    }

    public String StringCSV() {
        return  game +
                ";;" + name +
                ";;" + type +
                ";;" + description;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
