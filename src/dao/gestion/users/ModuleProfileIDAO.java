package src.dao.gestion.users;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import src.dto.gestion.users.ModuleProfileDTO;
import src.dao.gestion.users.models.MPDAO;

public class ModuleProfileIDAO implements MPDAO
{
    private Connection db; 

    public ModuleProfileIDAO()
    {}

    public ModuleProfileIDAO(Connection d)
    {
        db = d;
    }

    public boolean create(ModuleProfileDTO object) throws SQLException
    {

    }

    public ModuleProfileDTO get(Integer key)
    {

    }

    public boolean update(ModuleProfileDTO object) throws SQLException
    {

    }

    public boolean delete(ModuleProfileDTO object) throws SQLException
    {

    }

    public List<ModuleProfileDTO> all() throws SQLException
    {

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
