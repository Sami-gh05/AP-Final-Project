package database.java;

import java.util.List;

public interface DatabaseManager<T> {

    public void writeToDatabase(List<T> objects);
    public List<T> readFromDatabase();
}
