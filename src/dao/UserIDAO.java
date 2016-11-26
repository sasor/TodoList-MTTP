package src.dao;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.dao.models.UserDAO;
import src.queries.UserQueries;
import src.stuff.DAOException;
import src.dto.UserDTO;

public class UserIDAO implements UserDAO
{
    private final String INSERT = UserQueries.create();
    private final String UPDATE = UserQueries.update();
    private final String UPDATEACTIVE = UserQueries.update_active();
    private final String DELETE = UserQueries.delete();
    private final String GETONE = UserQueries.read();
    private final String GETALL = UserQueries.all();

    private Connection db;

    public UserIDAO()
    {}

    public UserIDAO(Connection db)
    {
        this.db = db;
    }

    public boolean create(UserDTO user) throws DAOException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        try {
            stmt = db.prepareStatement(INSERT);
            stmt.setString(1, user.getUserNickname());
            stmt.setString(2, user.getUserName());
            stmt.setString(3, user.getUserLastname());
            stmt.setString(4, user.getUserPassword());
            if(stmt.executeUpdate() <= 0) {
                response = false;
                throw new DAOException("Error execute method in INSERT user"); 
            }
        } catch (SQLException e) {
            throw new DAOException("Error Insert User", e);
        } catch (Exception e) {
            throw new DAOException("Error en Exception class", e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    throw new DAOException("Error stmt close in create user");
                }
            }
        }
        return response;
    }

    public UserDTO get(Integer key) throws DAOException
    {
        UserDTO response = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = db.prepareStatement(GETONE);
            stmt.setInt(1, key);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                throw new DAOException("Error ResultSet user get");
            }
            response = set(rs);
        } catch (SQLException e) {
            throw new DAOException("Error get user", e);
        } catch (Exception e) {
            throw new DAOException("Error Exception class get user", e);
        } finally {
            if (rs != null ) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException("Error rs close in get user");
                }
            }
          
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    throw new DAOException("Error stmt close in get user");
                }
            }
        }
        return response;
    }
    
    public boolean update(UserDTO user) throws DAOException
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
                throw new DAOException("Error execute method in update user");
            }
        } catch (SQLException e) {
            throw new DAOException("Error update user", e);
        } catch (Exception e) {
            throw new DAOException("Error Exception class in update user", e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close(); 
                } catch (SQLException e) {
                    throw new DAOException("Error close statement");
                }
            }
        }
        return response;
    }

    public boolean update_active(UserDTO user) throws DAOException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        try {
            stmt = db.prepareStatement(UPDATEACTIVE);
            /* setByte convert to SQL TINYINT value */
            stmt.setByte(1, user.getUserActive());
            stmt.setInt(2, user.getUserId());
            if (stmt.executeUpdate() <= 0) {
                response = false;
                throw new DAOException("Error execute method in update_active");
            }
        } catch (SQLException e) {
            throw new DAOException("Error update_active in users");
        } catch (Exception e) {
            throw new DAOException("Error Exception class in update_active u");
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println(new DAOException("Error"));
                } 
            }
        }
        return response;
    }

    public boolean delete(UserDTO user) throws DAOException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        try {
            stmt = db.prepareStatement(DELETE);
            stmt.setInt(1, user.getUserId());
            if (stmt.executeUpdate() <= 0) {
                response = false;
                throw new DAOException("Error execute method DELETE user");
            }
        } catch (SQLException e) {
            throw new DAOException("Error delete user", e);
        } catch (Exception e) {
            throw new DAOException("Error Exception general", e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    throw new DAOException("Error close stmt in update_active");
                }
            }
        }
        return response;
    }

    public List<UserDTO> all() throws DAOException
    {
        List<UserDTO> response = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = db.prepareStatement(GETALL);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                throw new DAOException("Error listar users"); 
            }
            while (rs.next()) {
                response.add(set(rs)); 
            }
        } catch (SQLException e) {
            throw new DAOException("Error", e);
        } catch (Exception e) {
            throw new DAOException("Error", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException("Error ResultSet close users"); 
                }
            }
     
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    throw new DAOException("Error stmt close users");
                }
            }
        }
        return response;
    }

    /**
     * @return UserDTO
     */
    public UserDTO set(ResultSet rs) throws SQLException
    {
        String nick = rs.getString("user_nickname");
        String name = rs.getString("user_name");
        String lastname = rs.getString("user_lastname");
        String password = rs.getString("user_password");
  
        UserDTO user = new UserDTO(nick, name, lastname, password);
        user.setUserId(rs.getInt("user_id"));
        return user;
    }
}
