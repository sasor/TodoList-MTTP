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

public class ReadLicenseController
{
    private DB db;
    private Read view;
    private LicenseDTO data;
    private Integer license_id;

    public ReadLicenseController()
    {
        db = DB.instance();
        license_id = pullLicenseId();
        populateData();
        view = new Read(this);
        populateProfiles();
        settingModule();
        view.disableFields();
        LicensesPanel.content.removeAll();
        LicensesPanel.content.add(view);
        LicensesPanel.content.updateUI();
    }

    public LicenseDTO getData()
    {
        return data;
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
} 
