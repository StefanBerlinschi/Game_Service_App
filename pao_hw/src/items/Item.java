package items;

import game.Game;

public class Item {
    private Game game;
    private String name;
    private ItemType type;
    private String description;

    public Item(Game game, String name, ItemType type, String description) {
        this.game = game;
        this.name = name;
        this.type = type;
        this.description = description;
    }

    @Override
    public String toString() {
        return "game = " + game +
                ", name = '" + name + '\'' +
                ", type = " + type +
                ", description = '" + description + '\'';
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
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
