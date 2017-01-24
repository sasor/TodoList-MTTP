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

public class AddModuleController
{
    private DB db;
    private Add view;
    private List<String> data;

    public AddModuleController()
    {
        data = new ArrayList<String>();
        db = DB.instance();
        view = new Add(this);
        ModulesPanel.content.removeAll();
        ModulesPanel.content.add(view);
        ModulesPanel.content.updateUI();
    }

    public void entry()
    {
        String name = view.getName();
        String description = view.getDescription();
   
        data.add(name);
        data.add(description);

        boolean validAllFields = checkFields();
        if (validAllFields) {
            ModuleIDAO m_dao = new ModuleIDAO(db.openConnection());
            ModuleDTO mo = new ModuleDTO(name, description); 
            try {
                if (m_dao.create(mo)) {
                    data.clear();                    
                    view.cleanFields();
                    JOptionPane.showMessageDialog(null, "Ok, Module created");
                } 
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            } 
        } else {
            data.clear();
            JOptionPane.showMessageDialog(null, "DAMM, Fields Empty");
        }
    }

    private boolean checkFields()
    {
        boolean response = true;
        for (String i : data) {
            if (i.isEmpty() || i == null) {
                return response = false; 
            }
        }
        return response;
    }
}
