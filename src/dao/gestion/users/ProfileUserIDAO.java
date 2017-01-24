package src.dao.gestion.users;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.dto.gestion.users.ProfileUserDTO;
import src.dao.gestion.users.models.PUDAO;
import src.queries.PUQueries;

public class ProfileUserIDAO implements PUDAO
{
    private final String INSERT = PUQueries.create();
    private final String UPDATE = PUQueries.update();
    private final String DELETE = PUQueries.delete();
    private final String GETONE = PUQueries.read();
    private final String GETALL = PUQueries.all();
    private Connection db; 

    public ProfileUserIDAO()
    {}

    public ProfileUserIDAO(Connection d)
    {
        db = d; 
    }  

    public boolean create(ProfileUserDTO object) throws SQLException
    { 
        boolean response = true;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = db.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, object.getProfileId());
            stmt.setInt(2, object.getUserId());
            if(stmt.executeUpdate() <= 0) {
                response = false;
            } else {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    object.setID(rs.getInt(1));
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

    public ProfileUserDTO get(Integer key) throws SQLException
    {
        ProfileUserDTO response = null;
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

    public boolean update(ProfileUserDTO object) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        try {
            stmt = db.prepareStatement(UPDATE);
            stmt.setInt(1, object.getProfileId()); 
            stmt.setInt(2, object.getUserId());
            stmt.setInt(3, object.getID());
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

    public boolean delete(ProfileUserDTO object) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        try {
            stmt = db.prepareStatement(DELETE);
            stmt.setInt(1, object.getID());
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

    public List<ProfileUserDTO> all() throws SQLException
    {
        List<ProfileUserDTO> response = new ArrayList<ProfileUserDTO>();
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

    private ProfileUserDTO set(ResultSet rs) throws SQLException
    {
        Integer profile_id = rs.getInt("profile_id");
        Integer user_id = rs.getInt("user_id");
        ProfileUserDTO object = new ProfileUserDTO(profile_id, user_id);
        object.setID(rs.getInt("_id"));
        return object;
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
