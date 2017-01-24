package src.controllers;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import src.views.panels.profiles.*;
import src.stuff.DB;
import src.dto.gestion.users.ProfileDTO;
import src.dao.gestion.users.ProfileIDAO;

public class AllProfileController
{
    private DB db;
    private All view;
    private List<ProfileDTO> data;

    public AllProfileController()
    {
        db = DB.instance();
        data = new ArrayList<ProfileDTO>();
        populateData(db.openConnection());

        view = new All(this);
        ProfilesPanel.content.removeAll();
        ProfilesPanel.content.add(view);
        ProfilesPanel.content.updateUI();
    }

    private void populateData(Connection db)
    {
        ProfileIDAO profile_dao = new ProfileIDAO(db);
        try {
            List<ProfileDTO> profiles = profile_dao.all(); 
            if (profiles.size() != 0) {
                data = profiles;
            } else {
                JOptionPane.showMessageDialog(null, "No Profiles in Database"); 
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<ProfileDTO> getData()
    {
        return data;
    }
}
