package service;

import domain.items.Item;
import domain.items.ItemType;

import java.io.*;
import java.util.ArrayList;

public class ItemCSV implements GenericCSVService<Item> {
    private static final ItemCSV INSTANCE = new ItemCSV();

    private ItemCSV() {}

    @Override
    public ArrayList<Item> load(String file) throws FileNotFoundException {
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(file));
        ArrayList<Item> items = new ArrayList<>();

        try {
            String line = reader.readLine();
            while (line != null) {
                String[] data = line.split(",");
                Item item = new Item(data[0], data[1], ItemType.valueOf(data[2]), data[3]);
                items.add(item);
                line = reader.readLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }
    @Override
    public void add(String file, Item it) {
        FileWriter writer = null;

        try {
            writer = new FileWriter(file, true);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writer.append(it.StringCSV());
            writer.flush();
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ItemCSV getInstance() {
        return INSTANCE;
    }
}