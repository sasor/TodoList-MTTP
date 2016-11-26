package src.dao.models;

import java.util.List;

import src.stuff.DAOException;

public interface DAO<T,K>
{
    public boolean create(T param) throws DAOException;
    public T get(K key) throws DAOException;
    public boolean update(T param) throws DAOException;
    public boolean delete(T param) throws DAOException;
    public List<T> all() throws DAOException;
}
