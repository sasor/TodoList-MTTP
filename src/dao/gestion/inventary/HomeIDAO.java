package src.dao.gestion.inventary;

import java.util.List;
import java.util.ArrayList;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import src.dao.gestion.inventary.models.HomeDAO;
import src.dto.gestion.inventary.HomeDTO;
import src.queries.HomeQueries;

public class HomeIDAO implements HomeDAO
{
    private final String INSERT = HomeQueries.create();
    private final String UPDATE = HomeQueries.update();
    private final String DELETE = HomeQueries.delete();
    private final String GETONE = HomeQueries.read();
    private final String GETALL = HomeQueries.all();

    private Connection db;

    public HomeIDAO()
    {}

    public HomeIDAO(Connection db)
    {
        this.db = db;
    }

    public boolean create(HomeDTO home) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        ResultSet rs = null;
 
        try {
            stmt = db.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, home.getHomeName());
            stmt.setInt(2, home.getPlaceId());
            if(stmt.executeUpdate() <= 0) {
                response = false;
            } else {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    home.setHomeId(rs.getInt(1));
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

    public HomeDTO get(Integer key) throws SQLException
    {
        HomeDTO response = null;
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
            System.err.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(stmt, rs);
        }

        return response;
    }

    public boolean update(HomeDTO home) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
       
        try {
            stmt = db.prepareStatement(UPDATE);
            stmt.setString(1, home.getHomeName()); 
            stmt.setInt(2, home.getPlaceId());
            stmt.setInt(3, home.getHomeId());
            if (stmt.executeUpdate() <= 0) {
                response = false;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(stmt);
        }

        return response;
    }

    public boolean delete(HomeDTO home) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
       
        try {
            stmt = db.prepareStatement(DELETE);
            stmt.setInt(1, home.getHomeId());
            if (stmt.executeUpdate() <= 0) {
                response = false;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(stmt);
        }

        return response;
    }

    public List<HomeDTO> all() throws SQLException   
    {
        List<HomeDTO> response = new ArrayList<HomeDTO>();
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
            System.err.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(stmt, rs);
        }

        return response;
    }

    private HomeDTO set(ResultSet rs) throws SQLException
    {
        String name = rs.getString("home_name");
        Integer place_id = rs.getInt("home_place_id");
        HomeDTO home = new HomeDTO(name, place_id);
        home.setHomeId(rs.getInt("home_id"));
        return home;
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
