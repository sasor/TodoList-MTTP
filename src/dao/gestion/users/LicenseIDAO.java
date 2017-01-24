package src.dao.gestion.users;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

import src.dao.gestion.users.models.LicenseDAO;
import src.queries.LicenseQueries;
import src.dto.gestion.users.LicenseDTO; 

public class LicenseIDAO implements LicenseDAO
{
    private final String INSERT = LicenseQueries.create();
    private final String UPDATE = LicenseQueries.update();
    private final String DELETE = LicenseQueries.delete();
    private final String DELETEBYKEY = LicenseQueries.delete();
    private final String GETONE = LicenseQueries.read();
    private final String GETALL = LicenseQueries.all();

    private Connection db;

    public LicenseIDAO()
    {}

    public LicenseIDAO(Connection db)
    {
        this.db = db;
    }

    public boolean create(LicenseDTO license) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = db.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, license.getModuleId());
            stmt.setString(2, license.getLicenseAction());
            stmt.setString(3, license.getLicenseDescription());
            if (stmt.executeUpdate() <= 0) {
                response = false;
            } else {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    license.setLicenseId(rs.getInt(1)); 
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            close(stmt, rs);
        }
        return response;
    }

    public LicenseDTO get(Integer key) throws SQLException
    {
        LicenseDTO response = null;
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

    public boolean update(LicenseDTO license) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        try {
            stmt = db.prepareStatement(UPDATE);
            stmt.setInt(1, license.getModuleId());
            stmt.setString(2, license.getLicenseAction());
            stmt.setString(3, license.getLicenseDescription());
            stmt.setInt(4, license.getLicenseId());
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

    public boolean delete(LicenseDTO license) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        try {
            stmt = db.prepareStatement(DELETE);
            stmt.setInt(1, license.getLicenseId());
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

    public boolean deleteByKey(Integer key) throws SQLException
    {
        boolean response = true;
        PreparedStatement stmt = null;
        try {
            stmt = db.prepareStatement(DELETEBYKEY);
            stmt.setInt(1, key);
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
 
    public List<LicenseDTO> all() throws SQLException
    {
        List<LicenseDTO> response = new ArrayList<LicenseDTO>();
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

    private LicenseDTO set(ResultSet rs) throws SQLException
    {
        Integer m_id = rs.getInt("module_id");
        String action = rs.getString("license_action");
        String description = rs.getString("license_description"); 
        LicenseDTO license = new LicenseDTO(m_id, action, description);
        license.setLicenseId(rs.getInt("license_id"));
        return license;
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
