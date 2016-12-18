package src.dao.gestion.users;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import src.dao.gestion.users.models.ProfileDAO;
import src.queries.ProfileQueries;
import src.dto.gestion.users.ProfileDTO;

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

    public boolean create(ProfileDTO profile) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        ResultSet rs = null; 
        try {
            stmt = db.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, profile.getProfileName());
            if (stmt.executeUpdate() <= 0) {
                response = false;
            } else {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    profile.setProfileId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(stmt, rs);
        }
        return response;
    }
  
    public ProfileDTO get(Integer key) throws SQLException
    {
        ProfileDTO response = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = db.prepareStatement(GETONE);
            stmt.setInt(1, key);
            rs = stmt.executeQuery();
            if (rs.next()) {
                response = set(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(stmt, rs);
        }
        return response;
    }
   
    public boolean update(ProfileDTO profile) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        try {
            stmt = db.prepareStatement(UPDATE);
            stmt.setString(1, profile.getProfileName());
            stmt.setInt(2, profile.getProfileId());
            if (stmt.executeUpdate() <= 0) {
                response = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(stmt);
        }
        return response;
    }

    public boolean update_active(ProfileDTO profile) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        try {
            stmt = db.prepareStatement(UPDATEACTIVE);
            stmt.setByte(1, (byte) profile.getProfileActive());
            stmt.setInt(2, profile.getProfileId());
            if (stmt.executeUpdate() <= 0) {
                response = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(stmt);
        }
        return response;
    }
 
    public boolean delete(ProfileDTO profile) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        try {
            stmt = db.prepareStatement(DELETE);
            stmt.setInt(1, profile.getProfileId());
            if (stmt.executeUpdate() <= 0) {
                response = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(stmt);
        }
        return response;
    }

    public List<ProfileDTO> all() throws SQLException
    {
        List<ProfileDTO> response = new ArrayList<>();    
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = db.prepareStatement(GETALL);
            rs = stmt.executeQuery(); 
            if (rs.first()) {
                do {
                    response.add(set(rs));
                } while (rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(stmt, rs);
        }
        return response;
    }

    /**
     * @return ProfileDTO
     */
    private ProfileDTO set(ResultSet rs) throws SQLException
    {
        String name = rs.getString("profile_name");
        byte active = rs.getByte("profile_active");
        ProfileDTO profile = new ProfileDTO(name, active);
        profile.setProfileId(rs.getInt("profile_id"));
        return profile;
    }

    private void close(PreparedStatement stmt) throws SQLException
    {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
   
    private void close(PreparedStatement stmt, ResultSet rs) throws SQLException
    {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        close(stmt);
    } 
}
