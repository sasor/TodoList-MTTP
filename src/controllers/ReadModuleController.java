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

public class ReadModuleController
{
    private DB db;
    private Read view;
    private ModuleDTO data;

    public ReadModuleController()
    {
        db = DB.instance();
        populateData(); 
        view = new Read(this);
        ModulesPanel.content.removeAll();
        ModulesPanel.content.add(view);
        ModulesPanel.content.updateUI();
    }

    public void populateData()
    {
        ModuleIDAO mo_dao = new ModuleIDAO(db.openConnection()); 
        Integer id = pullModuleId();
        try {
            data = mo_dao.get(id); 
        } catch (SQLException e) {
           System.err.println(e.getMessage());
        }
    }

    public Integer pullModuleId()
    {
        String id = String.valueOf(All.module_table.getValueAt(All.module_table.getSelectedRow(), 0));
        Integer i = Integer.parseInt(id);
        return i;
    }

    public ModuleDTO getData()
    {
        return data;
    }
}
