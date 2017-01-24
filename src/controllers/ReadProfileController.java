package src.controllers;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import src.stuff.DB;
import src.views.panels.profiles.*;
import src.dto.gestion.users.ProfileDTO;
import src.dao.gestion.users.ProfileIDAO;

public class ReadProfileController
{
    private DB db;
    private Read view;
    private ProfileDTO data;
  
    public ReadProfileController()
    {
        db = DB.instance();
        populateData();
        view = new Read(this);
        ProfilesPanel.content.removeAll();
        ProfilesPanel.content.add(view);
        ProfilesPanel.content.updateUI();
    }

    public Integer pullProfileId()
    {
        String id = String.valueOf(All.profile_table.getValueAt(All.profile_table.getSelectedRow(), 0));
        Integer i = Integer.parseInt(id);
        return i;
    }

    public void populateData()
    {
        ProfileIDAO p_dao = new ProfileIDAO(db.openConnection()); 
        Integer id = pullProfileId();
        try {
            data = p_dao.get(id); 
        } catch (SQLException e) {
           System.err.println(e.getMessage());
        }
    }

    public ProfileDTO getData()
    {
        return data;
    }
}
