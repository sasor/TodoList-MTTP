package src.controllers;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import src.stuff.DB;
import src.dto.gestion.users.UserDTO;
import src.dao.gestion.users.UserIDAO;
import src.views.panels.users.*;

public class AllUserController
{
    private DB db;
    private All view;
    private List<UserDTO> data;

    public AllUserController()
    {
        db = DB.instance();
        data = new ArrayList<UserDTO>();
        populateData(db.openConnection());

        view = new All(this);
        UsersPanel.content.removeAll();
        UsersPanel.content.add(view);         
        UsersPanel.content.updateUI();
    }

    private void populateData(Connection db)
    {
        UserIDAO user_dao = new UserIDAO(db);
        try {
            List<UserDTO> users = user_dao.all(); 
            if (users.size() != 0) {
                data = users;
            } else {
                JOptionPane.showMessageDialog(null, "No users in Database"); 
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<UserDTO> getData()
    {
        return data;
    }
}
