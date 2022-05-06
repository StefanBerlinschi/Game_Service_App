package service;

import domain.items.Inventory;
import other.Pair;

import java.io.*;
import java.util.ArrayList;

public class InventoryCSV implements GenericCSVService<Inventory>{
    private static final InventoryCSV INSTANCE = new InventoryCSV();

    private InventoryCSV() {}

    @Override
    public ArrayList<Inventory> load(String file) throws FileNotFoundException {
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(file));
        ArrayList<Inventory> inventories = new ArrayList<>();

        try {
            String line = reader.readLine();
            while (line != null) {
                String[] data = line.split(";;");
                String[] list = data[1].split("::");
                ArrayList<Pair<String, String>> pairs = new ArrayList<>();

                for (String str: list) {
                    pairs.add(Pair.parseStrStrPair(str));
                }

                Inventory inventory = new Inventory(data[0], pairs);
                inventories.add(inventory);
                line = reader.readLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return inventories;
    }

    @Override
    public void add(String file, Inventory it) {
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

    public static InventoryCSV getInstance() {
        return INSTANCE;
    }
}
