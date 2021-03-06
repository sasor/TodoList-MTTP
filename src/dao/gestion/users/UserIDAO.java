package src.dao.gestion.users;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.dao.gestion.users.models.UserDAO;
import src.queries.UserQueries;
import src.dto.gestion.users.UserDTO;

public class UserIDAO implements UserDAO
{
    private final String INSERT = UserQueries.create();
    private final String UPDATE = UserQueries.update();
    private final String UPDATEACTIVE = UserQueries.update_active();
    private final String DELETE = UserQueries.delete();
    private final String DELETEBYNICK = UserQueries.deleteByNick();
    private final String GETONE = UserQueries.read();
    private final String GETONEBYNICK = UserQueries.readByNickname();
    private final String GETALL = UserQueries.all();

    private Connection db;

    public UserIDAO()
    {}

    public UserIDAO(Connection db)
    {
        this.db = db;
    }

    public boolean create(UserDTO user) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = db.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUserNickname());
            stmt.setString(2, user.getUserName());
            stmt.setString(3, user.getUserLastname());
            stmt.setString(4, user.getUserPassword());
            if(stmt.executeUpdate() <= 0) {
                response = false;
            } else {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    user.setUserId(rs.getInt(1));
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

    public UserDTO get(Integer key) throws SQLException
    {
        UserDTO response = null;
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

    public Integer getByNickname(String nickname) throws SQLException
    {
        Integer response = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = db.prepareStatement(GETONEBYNICK);
            stmt.setString(1, nickname);
            rs = stmt.executeQuery();
            if (rs.next()) {
                response = rs.getInt(1);
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
    
    public boolean update(UserDTO user) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        try {
            stmt = db.prepareStatement(UPDATE);
            stmt.setString(1, user.getUserNickname()); 
            stmt.setString(2, user.getUserName());
            stmt.setString(3, user.getUserLastname());
            stmt.setString(4, user.getUserPassword());
            stmt.setInt(5, user.getUserId());
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

    public boolean update_active(UserDTO user) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        try {
            stmt = db.prepareStatement(UPDATEACTIVE);
            /* setByte convert to SQL TINYINT value
             * user.getUserActive() method returns type byte value
             */
            stmt.setByte(1, (byte) user.getUserActive());
            stmt.setInt(2, user.getUserId());
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

    public boolean delete(UserDTO user) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        try {
            stmt = db.prepareStatement(DELETE);
            stmt.setInt(1, user.getUserId());
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

    public boolean deleteByNickname(String nick) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        try {
            stmt = db.prepareStatement(DELETEBYNICK);
            stmt.setString(1, nick);
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

    public List<UserDTO> all() throws SQLException
    {
        List<UserDTO> response = new ArrayList<UserDTO>();
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
     * @return UserDTO
     */
    private UserDTO set(ResultSet rs) throws SQLException
    {
        String nick = rs.getString("user_nickname");
        String name = rs.getString("user_name");
        String lastname = rs.getString("user_lastname");
        String password = rs.getString("user_password");
        byte active = rs.getByte("user_active");
        UserDTO user = new UserDTO(nick, name, lastname, password, active);
        user.setUserId(rs.getInt("user_id"));
        return user;
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
