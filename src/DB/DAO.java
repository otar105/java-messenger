package DB;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    //add
    public int create(T item) throws SQLException;
    //getAll
    public List<T> get() throws SQLException;
    //edit
    int edit(T item) throws SQLException;
    //remove
    int remove(T item) throws SQLException;

}