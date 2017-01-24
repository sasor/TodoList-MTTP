package src.dao.gestion.users;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.dto.gestion.users.LicenseUserDTO;
import src.dao.gestions.users.models.LUDAO;

public class LicenseUserIDAO implements LUDAO
{
    private Connection db;

    public LicenseUserIDAO()
    {}

    public LicenseUserIDAO(Connection d)
    {
        db = d;
    }

    public boolean create(LicenseUserDTO object) throws SQLException
    {

    }

    public LicenseUserDTO get(Integer key) throws SQLException
    {

    }

    public boolean update(LicenseUserDTO object) throws SQLException
    {

    }

    public boolean delete(LicenseUserDTO object) throws SQLException
    {

    }

    public List<LicenseUserDTO> all() throws SQLException
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
