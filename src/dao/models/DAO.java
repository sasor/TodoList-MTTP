package src.dao.models;

import java.util.List;

import java.sql.SQLException;

public interface DAO<T,K>
{
    public boolean create(T param) throws SQLException;
    public T get(K key) throws SQLException;
    public boolean update(T param) throws SQLException;
    public boolean delete(T param) throws SQLException;
    public List<T> all() throws SQLException;
}
