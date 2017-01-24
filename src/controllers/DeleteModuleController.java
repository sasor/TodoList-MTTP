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

public class DeleteModuleController
{
    private DB db;

    public DeleteModuleController()
    {
        db = DB.instance();
        init();
    }

    public Integer pullModuleId()
    {
        String id = String.valueOf(All.module_table.getValueAt(All.module_table.getSelectedRow(), 0));
        Integer i = Integer.parseInt(id);
        return i;
    }

    public void init()
    {
        Integer id = pullModuleId();
        ModuleIDAO m_dao = new ModuleIDAO(db.openConnection());
        try {
            if(m_dao.deleteByKey(id)) {
                All.module_table.updateUI();
                JOptionPane.showMessageDialog(null, "OK Module Deleted");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
