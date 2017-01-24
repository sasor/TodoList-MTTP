package src.controllers;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import src.stuff.DB;
import src.views.panels.modules.*;
import src.dto.gestion.users.ModuleDTO;
import src.dao.gestion.users.ModuleIDAO;

public class AllModuleController
{
    private DB db;
    private All view;
    private List<ModuleDTO> data;

    public AllModuleController()
    {
        db = DB.instance();
        data = new ArrayList<ModuleDTO>();
        populateData(db.openConnection());

        view = new All(this);
        ModulesPanel.content.removeAll();
        ModulesPanel.content.add(view);
        ModulesPanel.content.updateUI();
    }

    private void populateData(Connection db)
    {
        ModuleIDAO module_dao = new ModuleIDAO(db);
        try {
            List<ModuleDTO> modules = module_dao.all(); 
            if (modules.size() != 0) {
                data = modules;
            } else {
                JOptionPane.showMessageDialog(null, "No Modules in Database"); 
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<ModuleDTO> getData()
    {
        return data;
    }
}
