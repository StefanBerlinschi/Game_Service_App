package service;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface GenericCSVService<T> {
    ArrayList<T> load(String file) throws FileNotFoundException;

    void add(String file, T ob);
}
