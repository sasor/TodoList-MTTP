package src.dao;

import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;

import src.dao.models.ProfileDAO;
import src.queries.ProfileQueries;
import src.dto.ProfileDTO;

public class ProfileIDAO implements ProfileDAO
{
    private final String INSERT = ProfileQueries.create();
    private final String UPDATE = ProfileQueries.update();
    private final String UPDATEACTIVE = ProfileQueries.update_active();
    private final String DELETE = ProfileQueries.delete();
    private final String GETONE = ProfileQueries.read();
    private final String GETALL = ProfileQueries.all();

    private Connection db;

    public ProfileIDAO()
    {}
   
    public ProfileIDAO(Connection db)
    {
        this.db = db;
    }

    public boolean create(ProfileDTO profile) throws DAOException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        try {
            stmt = db.prepareStatement(INSERT);
            stmt.setString(1, profile.getProfileName());
            if (stmt.executeUpdate() <= 0) {
                response = false;
                throw new DAOException("Error in executed method in Profile");
            }
        } catch (SQLException e) {
            throw new DAOException("Error Insert Profile", e);
        } catch (Exception e) {
            throw new DAOException("Error Exception class in Profile", e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return response;
    }
  
    public ProfileDTO get(Integer key) throws DAOException
    {
        ProfileDTO response = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

        } catch () {
    
        } catch () {

        } finally {
            if (true) {

            }
    
            if (true) {

            }
        }
    }
   
    public boolean update(ProfileDTO profile) throws DAOException
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
 
    public boolean delete(ProfileDTO profile) throws DAOException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        try {
            stmt = db.prepareStatement(DELETE);
        } catch (SQLException e) {
            throw new DAOException("Error delete Profile", e);
        } catch (Exception e) {
            throw new DAOException("Error Exception class delete Profile", e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return response;
    }

    public List<ProfileDTO> all() throws DAOException
    {

    }
}
