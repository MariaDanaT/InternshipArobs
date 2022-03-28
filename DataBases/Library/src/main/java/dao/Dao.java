package dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> get(int id) throws SQLException;

    Collection<T> getAll() throws SQLException;

    int save(T t);

    void update(T t);

    void delete(T t);
}
