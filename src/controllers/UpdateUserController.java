package src.controllers;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import src.stuff.DB;
import java.sql.SQLException;
import src.views.panels.users.*;
import javax.swing.*;
import src.dao.gestion.users.*;
import src.dto.gestion.users.*;

public class UpdateUserController
{
    private DB db;
    public static Update view;
    private Integer user_id;
    private UserDTO data;

    public UpdateUserController()
    {
        db = DB.instance();
        user_id = getUserID();
        populateData();
        view = new Update(this);
        populateProfiles();
        view.setProfileUser();
        UsersPanel.content.removeAll();
        UsersPanel.content.add(view);         
        UsersPanel.content.updateUI();
        //init();
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

    public UserDTO getData()
    {
        return data;
    }

    public void entry()
    {
        String nick = view.getValueOfNick();
        String name = view.getValueOfName();
        String lastname = view.getValueOfLastname();
        String pwd = view.getValueOfPassword();
        byte active = view.getValueOfActive();
        String profile = view.getSelectedProfile();
        //JOptionPane.showMessageDialog(null,"NICK "+nick+" NAME "+name+" LASTNAME "+lastname+" PWD "+pwd+" ACTIVE "+""+active+" PROFILE "+profile);
        data.setUserNickname(nick);
        data.setUserName(name);
        data.setUserLastname(lastname);
        data.setUserPassword(pwd);
        data.setUserActive(active);
        UserIDAO u_dao = new UserIDAO(db.openConnection());
        boolean u = false;
        try {
            if(u_dao.update(data) && u_dao.update_active(data)) {
                u = true;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        ProfileIDAO p_dao = new ProfileIDAO(db.openConnection()); 
        ProfileDTO p_dto = null;
        try {
            p_dto = p_dao.getByName(profile);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        ProfileUserIDAO pu_dao = new ProfileUserIDAO(db.openConnection());
        List<ProfileUserDTO> list = null;
        try {
            list = pu_dao.all(); 
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        for (ProfileUserDTO em : list) {
            if (user_id == em.getUserId()) {
                if (p_dto.getProfileId() == em.getProfileId()) {
                    JOptionPane.showMessageDialog(null, "OK User Updated");
                } else {
                    updatePU(user_id,p_dto.getProfileId(),em.getID());
                    JOptionPane.showMessageDialog(null, "OK User Updated");
                }
                MainController.inform.action.setAction("UPDATE USER");
                java.util.Date d = new java.util.Date();
                SimpleDateFormat f = new SimpleDateFormat("hh:mm:ss a"); 
                MainController.inform.action.setDate(d);
                MainController.inform.action.setTimes(f.format(d));
                MainController.inform.instance.registAction();
            }
        }
        
    }

    public void updatePU(Integer user, Integer profile, Integer pu)
    {
        ProfileUserIDAO pu_dao = new ProfileUserIDAO(db.openConnection());
        ProfileUserDTO pu_obj = new ProfileUserDTO(profile, user);
        pu_obj.setID(pu); 
        try {
            pu_dao.update(pu_obj);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
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
