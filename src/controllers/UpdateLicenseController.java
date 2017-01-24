package src.controllers;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import src.stuff.DB;
import src.views.panels.licenses.*;
import src.dto.gestion.users.LicenseDTO;
import src.dao.gestion.users.LicenseIDAO;
import src.dto.gestion.users.ModuleDTO;
import src.dao.gestion.users.ModuleIDAO;

public class UpdateLicenseController
{
    private DB db;
    private Update view;
    private LicenseDTO data;
    private Integer license_id;

    public UpdateLicenseController()
    {
        db = DB.instance();
        license_id = pullLicenseId();
        populateData();
        view = new Update(this);
        populateProfiles();
        settingModule();
        LicensesPanel.content.removeAll();
        LicensesPanel.content.add(view);
        LicensesPanel.content.updateUI();
    } 

    public void populateProfiles()
    {
        JComboBox cb = view.getModuleCB();
        List<ModuleDTO> modules = getAllModules();
        if (modules != null) {
            for (ModuleDTO m : modules) {
                cb.addItem(m.getModuleName()); 
            }
        }
    } 

    public List<ModuleDTO> getAllModules()
    {
        List<ModuleDTO> response = null;
        ModuleIDAO m_dao = new ModuleIDAO(db.openConnection());
        try {
            response = m_dao.all();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return response;
    }

    public Integer pullLicenseId()
    {
        String id = String.valueOf(All.license_table.getValueAt(All.license_table.getSelectedRow(), 0));
        Integer i = Integer.parseInt(id);
        return i;
    }
  
    public void populateData()
    {
        LicenseIDAO l_dao = new LicenseIDAO(db.openConnection());
        try {
            data = l_dao.get(license_id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void settingModule()
    {
        ModuleIDAO m_dao = new ModuleIDAO(db.openConnection());
        ModuleDTO module = null;
        try {
            module = m_dao.get(data.getModuleId());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        view.setModuleInView(module.getModuleName());
    }

    public LicenseDTO getData()
    {
        return data;
    }

    public void entry()
    {
        String action = view.getAction();
        String description = view.getDescription();
        String module = view.getModule();
        Integer module_id = findModuleId(module);
         
        data.setLicenseAction(action);
        data.setLicenseDescription(description);
        data.setModuleId(module_id);

        LicenseIDAO l_dao = new LicenseIDAO(db.openConnection());
        try {
            if (l_dao.update(data)) {
                JOptionPane.showMessageDialog(null, "OK, License Updated");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public Integer findModuleId(String module_name)
    {
        ModuleIDAO m_dao = new ModuleIDAO(db.openConnection());
        List<ModuleDTO> all = null;
        try {
            all = m_dao.all();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        for (ModuleDTO module : all) {
            if (module_name.equals(module.getModuleName())) {
                return module.getModuleId();
            }
        }
        return null;
    }
}
