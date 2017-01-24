package src.controllers;

import java.text.SimpleDateFormat;
import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import src.stuff.DB;
import src.dao.gestion.users.*;
import src.dto.gestion.users.*;
import src.views.panels.profiles.*;

public class UpdateProfileController
{
    private DB db;
    private ProfileDTO data;
    private Update view;
    private Integer profile_id;

    public UpdateProfileController()
    {
        db = DB.instance();
        profile_id = pullProfileId();
        populateData();
        view = new Update(this);
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

    public void entry()
    {
        String name = view.getName();
        byte value = view.getActive();

        data.setProfileName(name);
        data.setProfileActive(value);

        ProfileIDAO dao = new ProfileIDAO(db.openConnection());
        try {
            if (dao.update(data) && dao.update_active(data)) {
                MainController.inform.action.setAction("UPDATE PROFILE ACTIVE");
                java.util.Date d = new java.util.Date();
                SimpleDateFormat f = new SimpleDateFormat("hh:mm:ss a"); 
                MainController.inform.action.setDate(d);
                MainController.inform.action.setTimes(f.format(d));
                MainController.inform.instance.registAction();

                JOptionPane.showMessageDialog(null, "OK, Profile Updated");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
