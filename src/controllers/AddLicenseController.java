package src.controllers;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import src.stuff.DB;
import src.views.panels.licenses.*;
import src.dao.gestion.users.LicenseIDAO;
import src.dto.gestion.users.LicenseDTO;
import src.dao.gestion.users.ModuleIDAO;
import src.dto.gestion.users.ModuleDTO;

public class AddLicenseController
{
    private DB db;
    private Add view; 

    public AddLicenseController()
    {
        db = DB.instance();
        view = new Add(this);
        populateProfiles();
        LicensesPanel.content.removeAll();
        LicensesPanel.content.add(view);
        LicensesPanel.content.updateUI();
    }

    public void entry()
    {
        String action = view.getAction();
        String description = view.getDescription();
        String module = view.getModule();

        Integer will_id_module = findModuleId(module);
        LicenseIDAO li_dao = new LicenseIDAO(db.openConnection());
        LicenseDTO obj = new LicenseDTO(will_id_module, action, description);
        try {
            if (li_dao.create(obj)) {
                JOptionPane.showMessageDialog(null, "OK, License Created");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
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
