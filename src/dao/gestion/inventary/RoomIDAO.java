package src.dao.gestion.inventary;

import java.util.List;
import java.util.ArrayList;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import src.dao.gestion.inventary.models.RoomDAO;
import src.dto.gestion.inventary.RoomDTO;
import src.queries.RoomQueries;

public class RoomIDAO implements RoomDAO
{
    private final String INSERT = RoomQueries.create();
    private final String UPDATE = RoomQueries.update();
    private final String DELETE = RoomQueries.delete();
    private final String GETONE = RoomQueries.read();
    private final String GETALL = RoomQueries.all();

    private Connection db;

    public RoomIDAO()
    {}

    public RoomIDAO(Connection db)
    {
        this.db = db;
    }

    public boolean create(RoomDTO room) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        ResultSet rs = null;
   
        try {
            stmt = db.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, room.getHomeId());
            stmt.setInt(2, room.getPlaceId());
            stmt.setString(3, room.getRoomName());
            if(stmt.executeUpdate() <= 0) {
                response = false;
            } else {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    room.setRoomId(rs.getInt(1));
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

    public RoomDTO get(Integer key) throws SQLException
    {
        RoomDTO response = null;
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

    public boolean update(RoomDTO room) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
   
        try {
            stmt = db.prepareStatement(UPDATE);
            stmt.setInt(1, room.getHomeId()); 
            stmt.setInt(2, room.getPlaceId());
            stmt.setString(3, room.getRoomName());
            stmt.setInt(4, room.getRoomId());
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

    public boolean delete(RoomDTO room) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
   
        try {
            stmt = db.prepareStatement(DELETE);
            stmt.setInt(1, room.getRoomId());
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

    public List<RoomDTO> all() throws SQLException
    {
        List<RoomDTO> response = new ArrayList<RoomDTO>();
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

    public RoomDTO set(ResultSet rs) throws SQLException
    {
        Integer home = rs.getInt("room_home_id");
        Integer place = rs.getInt("room_place_id");
        String name = rs.getString("room_name");
        RoomDTO room = new RoomDTO(name, home, place);
        room.setRoomId(rs.getInt("room_id"));
        return room;
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
