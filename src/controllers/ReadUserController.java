package src.controllers;

import java.util.List;
import java.util.ArrayList;
import src.stuff.DB;
import java.sql.SQLException;
import src.views.panels.users.*;
import javax.swing.*;
import src.dao.gestion.users.*;
import src.dto.gestion.users.*;

public class ReadUserController
{
    private DB db;
    private Integer user_id;
    private Read view;
    private UserDTO data;

    public ReadUserController()
    {
        db = DB.instance();
        user_id = getUserID();
        populateData();
        view = new Read(this);
        populateProfiles();
        view.setProfileUser();
        view.disableFields();
        UsersPanel.content.removeAll();
        UsersPanel.content.add(view);         
        UsersPanel.content.updateUI();
    }

    public UserDTO getData()
    {
        return data;
    }

    public void populateData()
    {
        UserIDAO u_dao = new UserIDAO(db.openConnection());
        try {
            data = u_dao.get(user_id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public ProfileUserDTO getProfileBelong()
    {
        ProfileUserDTO response = null;
        ProfileUserIDAO pu_dao = new ProfileUserIDAO(db.openConnection());        
        List<ProfileUserDTO> all = null;
        try {
            all = pu_dao.all();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        for (ProfileUserDTO pu : all) {
            if (pu.getUserId() == user_id) {
                return pu;
            }
        }
        return response;
    }

    public String pullNickname()
    {
        String nick = String.valueOf(All.user_table.getValueAt(All.user_table.getSelectedRow(), 0));
        return nick;
    }

    public Integer getUserID()
    {
        Integer response = null;
        String nick = pullNickname();
        UserIDAO u_dao = new UserIDAO(db.openConnection());
        try {
            response = u_dao.getByNickname(nick); 
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return response;
    }

    private void populateProfiles()
    {
        JComboBox profileCB = view.getProfileCB();

        List<ProfileDTO> profiles = loadProfiles();
        if (profiles != null) {
            int length = profiles.size();
            for (int index = 0; index < length; index++) {
                ProfileDTO current = profiles.get(index);
                if (current.getProfileActive() == 1) {
                    profileCB.addItem(current.getProfileName());
                }
            }
        }
    }

    private List<ProfileDTO> loadProfiles()
    {
        return SignUpManager.getAllProfiles(db.openConnection());
    }

    public ProfileDTO getProfile(Integer key)
    {
        ProfileDTO response = null;
        ProfileIDAO p_dao = new ProfileIDAO(db.openConnection());
        try {
            response = p_dao.get(key); 
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return response;
    }
} 
