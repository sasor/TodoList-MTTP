package src.dao.gestion.inventary;

import java.util.List;
import java.util.ArrayList;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import src.dao.gestion.inventary.models.ElementDAO;
import src.dto.gestion.inventary.ElementDTO;
import src.queries.ElementQueries;

public class ElementIDAO implements ElementDAO
{
    private final String INSERT = ElementQueries.create();
    private final String INSERTBYROOM = ElementQueries.createByRoom();
    private final String UPDATE = ElementQueries.update();
    private final String UPDATEPARENT = ElementQueries.updateParent();
    private final String DELETE = ElementQueries.delete();
    private final String GETONE = ElementQueries.read();
    private final String GETALL = ElementQueries.all();
    private final String GETALLPARENT = ElementQueries.allParent();

    private Connection db;

    public ElementIDAO()
    {}

    public ElementIDAO(Connection db)
    {
        this.db = db;
    }

    public boolean createByRoom(ElementDTO element) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = db.prepareStatement(INSERTBYROOM, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, element.getRoomId());
            if(stmt.executeUpdate() <= 0) {
                response = false;
            } else {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    element.setElementId(rs.getInt(1));
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

    public boolean create(ElementDTO element) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = db.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, element.getRoomId());
            stmt.setInt(2, element.getElementLevel());
            stmt.setInt(3, element.getElementParent());
            stmt.setByte(4, element.getHaveChild());
            if(stmt.executeUpdate() <= 0) {
                response = false;
            } else {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    element.setElementId(rs.getInt(1));
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

    public ElementDTO get(Integer key) throws SQLException
    {
        ElementDTO response = null;
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

    public boolean update(ElementDTO element) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        
        try {
            stmt = db.prepareStatement(UPDATE);
            stmt.setInt(1, element.getRoomId()); 
            stmt.setInt(2, element.getElementLevel());
            stmt.setInt(3, element.getElementParent());
            stmt.setByte(4, element.getHaveChild());
            stmt.setInt(5, element.getElementId());
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

    public boolean updateParent(Integer key, byte value) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        
        try {
            stmt = db.prepareStatement(UPDATEPARENT);
            stmt.setByte(1, value); 
            stmt.setInt(2, key);
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

    public boolean delete(ElementDTO element) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        
        try {
            stmt = db.prepareStatement(DELETE);
            stmt.setInt(1, element.getElementId());
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

    public List<ElementDTO> all() throws SQLException
    {
        List<ElementDTO> response = new ArrayList<ElementDTO>();
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

    public List<ElementDTO> allByParent(Integer key) throws SQLException
    {
        List<ElementDTO> response = new ArrayList<ElementDTO>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = db.prepareStatement(GETALLPARENT);
            stmt.setInt(1, key);
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

    public ElementDTO set(ResultSet rs) throws SQLException
    {
        Integer room = rs.getInt("element_room_id");
        Integer level = rs.getInt("element_level");
        Integer parent = rs.getInt("element_parent");
        byte child = rs.getByte("element_has_child");
        ElementDTO element = new ElementDTO(room, level, parent, child);
        element.setElementId(rs.getInt("element_id"));
        return element;
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
