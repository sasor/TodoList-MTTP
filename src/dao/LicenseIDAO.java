package src.dao;

import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;

import src.dao.models.LicenseDAO;
import src.queries.LicenseQueries;
import src.dto.LicenseDTO; 

public class LicenseIDAO implements LicenseDAO
{
    private final String INSERT = LicenseQueries.create();
    private final String UPDATE = LicenseQueries.update();
    private final String DELETE = LicenseQueries.delete();
    private final String GETONE = LicenseQueries.read();
    private final String GETALL = LicenseQueries.all();

    private Connection db;

    public LicenseIDAO()
    {}

    public LicenseIDAO(Connection db)
    {
        this.db = db;
    }

    public boolean create(LicenseDTO license) throws DAOException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        try {
            stmt = db.prepareStatement(INSERT);
        } catch (SQLException e) {
            throw new DAOException();
        } catch(Exception e) {
            throw new DAOException();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    throw new DAOException("Error close stmt license"); 
                }
            }
        }
        return response;
    }

    public LicenseDTO get(Integer key) throws DAOException
    {

    }

    public boolean update(LicenseDTO license) throws DAOException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        try {
            stmt = db.prepareStatement(UPDATE);
        } catch (SQLException e) {
            throw new DAOException();
        } catch (Exception e) {
            throw new DAOException();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        } 
        return response;
    }

    public boolean delete(LicenseDTO license) throws DAOException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        try {
            stmt = db.prepareStatement(DELETE);
        } catch (SQLException e) {
            throw new DAOException();
        } catch (Exception e) {
            throw new DAOException();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return response;
    }
 
    public List<LicenseDTO> all() throws DAOException
    {

    }
}
