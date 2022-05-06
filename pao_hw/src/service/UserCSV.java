package service;

import domain.account.User;

import java.io.*;
import java.util.ArrayList;

import static java.lang.Double.parseDouble;

public class UserCSV implements GenericCSVService<User> {
    private static final UserCSV INSTANCE = new UserCSV();

    private UserCSV() {}

    @Override
    public ArrayList<User> load(String file) throws FileNotFoundException {
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(file));
        ArrayList<User> users = new ArrayList<>();

        try {
            String line = reader.readLine();
            while (line != null) {
                String[] data = line.split(",");
                User user = new User(data[0], data[1], data[2], data[3], parseDouble(data[4]));
                users.add(user);
                line = reader.readLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public void add(String file, User u) {
        FileWriter writer = null;

        try {
            writer = new FileWriter(file, true);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writer.append(u.toString());
            writer.append("\n");
            writer.flush();
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static UserCSV getInstance() {
        return INSTANCE;
    }
}
