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

public class DeleteProfileController
{
    private DB db;
    private Integer profile_id;

    public DeleteProfileController()
    {
        db = DB.instance();
        profile_id = pullProfileId();
        init(); 
    }

    public Integer pullProfileId()
    {
        String id = String.valueOf(All.profile_table.getValueAt(All.profile_table.getSelectedRow(), 0));
        Integer i = Integer.parseInt(id);
        return i;
    }

    public void init()
    {
        ProfileIDAO dao = new ProfileIDAO(db.openConnection());
        try {
            if (dao.deleteByKey(profile_id)) {
                JOptionPane.showMessageDialog(null, "OK, Profile Deleted");
            } 
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
