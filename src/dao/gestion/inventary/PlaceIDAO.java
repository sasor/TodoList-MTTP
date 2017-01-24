package src.dao.gestion.inventary;

import java.util.List;
import java.util.ArrayList;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import src.dao.gestion.inventary.models.PlaceDAO;
import src.dto.gestion.inventary.PlaceDTO;
import src.queries.PlaceQueries;

public class PlaceIDAO implements PlaceDAO
{
    private final String INSERT = PlaceQueries.create();
    private final String UPDATE = PlaceQueries.update();
    private final String DELETE = PlaceQueries.delete();
    private final String GETALL = PlaceQueries.all();
    private final String GETONE = PlaceQueries.read();
   
    private Connection db;
    
    public PlaceIDAO()
    {}
  
    public PlaceIDAO(Connection db)
    {
        this.db = db;
    }

    public boolean create(PlaceDTO place) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        ResultSet rs = null;
 
        try {
            stmt = db.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, place.getPlaceObject());
            if(stmt.executeUpdate() <= 0) {
                response = false;
            } else {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    place.setPlaceId(rs.getInt(1));
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
  
    public PlaceDTO get(Integer key) throws SQLException
    {
        PlaceDTO response = null;
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

    public boolean update(PlaceDTO place) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;

        try {
            stmt = db.prepareStatement(UPDATE);
            stmt.setString(1, place.getPlaceObject()); 
            stmt.setInt(2, place.getPlaceId());
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

    public boolean delete(PlaceDTO place) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;

        try {
            stmt = db.prepareStatement(DELETE);
            stmt.setInt(1, place.getPlaceId());
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

    public boolean deleteByKey(Integer key) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;

        try {
            stmt = db.prepareStatement(DELETE);
            stmt.setInt(1, key);
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

    public List<PlaceDTO> all() throws SQLException
    {
        List<PlaceDTO> response = new ArrayList<PlaceDTO>();
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

    private PlaceDTO set(ResultSet rs) throws SQLException
    {
        String object = rs.getString("place_object");
        PlaceDTO place = new PlaceDTO(object);
        place.setPlaceId(rs.getInt("place_id"));
        return place;
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
