package src.dao;

import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;

import src.dao.models.ModuleDAO;
import src.queries.ModuleQueries;
import src.dto.ModuleDTO;

public class ModuleIDAO implements ModuleDAO
{
    private final String INSERT = ModuleQueries.create();
    private final String UPDATE = ModuleQueries.update();
    private final String DELETE = ModuleQueries.delete();
    private final String GETONE = ModuleQueries.read();
    private final String GETALL = ModuleQueries.all();

    private Connection db;

    public ModuleIDAO()
    {}
 
    public ModuleIDAO(Connection db)
    {
        this.db = db;
    }

    public boolean create(ModuleDTO module) throws DAOException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        try {
            stmt = db.prepareStatement(INSERT);
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

    public ModuleDTO get(Integer key) throws DAOException
    {

    }

    public boolean update(ModuleDTO module) throws DAOException
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
 
    public boolean delete(ModuleDTO module) throws DAOException
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
  
    public List<ModuleDTO> all() throws DAOException
    {
 
    }
}
