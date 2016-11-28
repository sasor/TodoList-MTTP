package src.dao;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.dao.models.ModuleDAO;
import src.queries.ModuleQueries;
import src.dto.ModuleDTO;

public class ModuleIDAO implements ModuleDAO
{
    private final String INSERT = ModuleQueries.create();
    private final String UPDATE = ModuleQueries.update();
    private final String DELETE = ModuleQueries.delete();
    private final String GETONE = ModuleQueries.read();
    private final String GETALL = ModuleQueries.all();

    private Connection db;

    public ModuleIDAO()
    {}
 
    public ModuleIDAO(Connection db)
    {
        this.db = db;
    }

    public boolean create(ModuleDTO module) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        try {
            stmt = db.prepareStatement(INSERT);
            stmt.setString(1, module.getModuleName());
            stmt.setString(2, module.getModuleDescription());
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

    public ModuleDTO get(Integer key) throws SQLException
    {
        ModuleDTO response = null;
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

    public boolean update(ModuleDTO module) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        try {
            stmt = db.prepareStatement(UPDATE);
            stmt.setString(1, module.getModuleName());
            stmt.setString(2, module.getModuleDescription());
            stmt.setInt(3, module.getModuleId());
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
 
    public boolean delete(ModuleDTO module) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        try {
            stmt = db.prepareStatement(DELETE);
            stmt.setInt(1, module.getModuleId());
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
  
    public List<ModuleDTO> all() throws SQLException
    {
        List<ModuleDTO> response = new ArrayList<>(); 
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = db.prepareStatement(GETALL);
            rs = stmt.executeQuery();
            if (rs.next()) {
                while (rs.next()) {
                    response.add(set(rs));
                }
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
 
    private ModuleDTO set(ResultSet rs) throws SQLException
    {
        String name = rs.getString("module_name");
        String description = rs.getString("module_description");
        ModuleDTO module = new ModuleDTO(name, description);
        module.setModuleId(rs.getInt("module_id"));
        return module;
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
