package src.factories;

import java.sql.Connection;

import src.dao.models.DAO;
import src.dao.UserIDAO;
import src.dao.ProfileIDAO;
import src.dao.ModuleIDAO;
import src.dao.LicenseIDAO;

public class IDAOManager
{
    private Connection db;

    private UserIDAO userIDAO = null;
    private ProfileIDAO profileIDAO = null;
    private ModuleIDAO moduleIDAO = null;
    private LicenseIDAO licenseIDAO = null;

    public IDAOManager()
    {}

    public IDAOManager(Connection db)
    {
        this.db = db;
    }
 
    public UserIDAO makeUserIDAO()
    {
        if (userIDAO == null) {
            userIDAO = new UserIDAO(db);
        } 
        return userIDAO;
    }

    public ProfileIDAO makeProfileIDAO()
    {
        if (profileIDAO == null) {
            profileIDAO = new ProfileIDAO(db);
        }
        return profileIDAO;
    }

    public ModuleIDAO makeModuleIDAO()
    {
        if (moduleIDAO == null) {
            moduleIDAO = new ModuleIDAO(db);
        }
        return moduleIDAO;
    }

    public LicenseIDAO makeLicenseIDAO()
    {
        if (licenseIDAO == null) {
            licenseIDAO = new LicenseIDAO(db);
        }
        return licenseIDAO;
    }
}
