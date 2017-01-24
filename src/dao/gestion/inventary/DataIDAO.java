package src.dao.gestion.inventary;

import java.util.List;
import java.util.ArrayList;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;

import src.dao.gestion.inventary.models.DataDAO;
import src.dto.gestion.inventary.DataDTO;
import src.queries.DataQueries;

public class DataIDAO implements DataDAO
{
    private final String INSERT = DataQueries.create();
    private final String UPDATE = DataQueries.update();
    private final String DELETE = DataQueries.delete();
    private final String GETONE = DataQueries.read();
    private final String GETBYELEMENT = DataQueries.readbyElement();
    private final String GETALL = DataQueries.all();
    private Connection db;

    public DataIDAO(Connection d)
    {
        db = d;
    }

    public boolean create(DataDTO data) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = db.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, data.getDataPlaceId());
            stmt.setInt(2, data.getDataElementId());
            stmt.setString(3, data.getDataName());
            stmt.setString(4, data.getDataDescription());
            stmt.setDate(5, new Date(data.getDataStorage().getTime()));
            stmt.setDate(6, new Date(data.getDataExpiration().getTime()));
            if(stmt.executeUpdate() <= 0) {
                response = false;
            } else {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    data.setDataId(rs.getInt(1));
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

    public DataDTO get(Integer key) throws SQLException
    {
        DataDTO response = null;
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

    public DataDTO getByElement(Integer key) throws SQLException
    {
        DataDTO response = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = db.prepareStatement(GETBYELEMENT);
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

    public boolean update(DataDTO data) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        
        try {
            stmt = db.prepareStatement(UPDATE);
            stmt.setInt(1, data.getDataPlaceId()); 
            stmt.setInt(2, data.getDataElementId());
            stmt.setString(3, data.getDataName());
            stmt.setString(4, data.getDataDescription());
            stmt.setDate(5, new Date(data.getDataStorage().getTime()));
            stmt.setDate(6, new Date(data.getDataExpiration().getTime()));
            stmt.setInt(7, data.getDataId());
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

    public boolean delete(DataDTO data) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        
        try {
            stmt = db.prepareStatement(DELETE);
            stmt.setInt(1, data.getDataId());
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

    public List<DataDTO> all() throws SQLException
    {
        List<DataDTO> response = new ArrayList<DataDTO>();
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

    public DataDTO set(ResultSet rs) throws SQLException
    {
        Integer place = rs.getInt("data_place_id");
        Integer element = rs.getInt("data_element_id");
        String name = rs.getString("data_name");
        String description = rs.getString("data_description");
        java.util.Date storage = rs.getDate("data_storage");
        java.util.Date expiration = rs.getDate("data_expiration");

        DataDTO data = new DataDTO(place, element, name, description, storage, expiration);
        data.setDataId(rs.getInt("data_id"));
        return data;
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
