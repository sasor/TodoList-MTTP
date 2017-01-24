package src.dao.gestion.users;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import src.dao.gestion.users.models.ActionDAO;
import src.queries.ActionQueries;
import src.dto.gestion.users.ActionDTO;
import java.sql.Date;

public class ActionIDAO implements ActionDAO
{
    private final String INSERT = ActionQueries.create();
    private final String UPDATE = ActionQueries.update();
    private final String DELETE = ActionQueries.delete();
    private final String GETONE = ActionQueries.read();
    private final String GETALL = ActionQueries.all();

    private Connection db;

    public ActionIDAO()
    {}

    public ActionIDAO(Connection d)
    {
        db = d;
    }

    public boolean create(ActionDTO action) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = db.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, action.getUserId());
            stmt.setString(2, action.getNick());
            stmt.setString(3, action.getAction());
            stmt.setDate(4, new Date(action.getDate().getTime()));
            stmt.setString(5, action.getTimes());
            if(stmt.executeUpdate() <= 0) {
                response = false;
            } else {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    action.setID(rs.getInt(1));
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

    public ActionDTO get(Integer key) throws SQLException
    {
        ActionDTO response = null;
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

    public boolean update(ActionDTO action) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        try {
            stmt = db.prepareStatement(UPDATE);
            stmt.setInt(1, action.getUserId());
            stmt.setString(2, action.getNick());
            stmt.setString(3, action.getAction());
            stmt.setDate(4, new Date(action.getDate().getTime()));
            stmt.setString(5, action.getTimes());
            stmt.setInt(6, action.getID());
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

    public boolean delete(ActionDTO action) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        try {
            stmt = db.prepareStatement(DELETE);
            stmt.setInt(1, action.getID());
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

    public List<ActionDTO> all() throws SQLException
    {
        List<ActionDTO> response = new ArrayList<ActionDTO>();
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
     * @return ActionDTO
     */
    private ActionDTO set(ResultSet rs) throws SQLException
    {
        Integer user = rs.getInt("user_id");
        String nick = rs.getString("user_nick");
        String ac = rs.getString("action");
        java.util.Date d = rs.getDate("date_action");
        String ti = rs.getString("times");
        
        ActionDTO action = new ActionDTO(user,nick,ac,d, ti);
        action.setID(rs.getInt("_id"));
        return action;
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
