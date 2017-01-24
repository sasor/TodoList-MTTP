package src.controllers;

import src.stuff.DB;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import src.views.panels.users.*;
import src.dto.gestion.users.*;

public class AddUserController
{
    private Add view;
    private List<String> data;
    private DB db;

    public AddUserController()
    {
        data = new ArrayList<String>();
        db = DB.instance();
        view = new Add(this);
        populateProfiles();
        UsersPanel.content.removeAll();
        UsersPanel.content.add(view);         
        UsersPanel.content.updateUI();
    }

    public void entry()
    {
        String nick = view.getValueOfNick(); 
        String name = view.getValueOfName();
        String last = view.getValueOfLastname();
        String pwd = view.getValueOfPassword();
        String profile = view.getSelectedProfile();

        data.add(nick);
        data.add(name);
        data.add(last);
        data.add(pwd);
        data.add(profile);

        boolean validAllFields = checkFields();
        
        if (validAllFields) {
            boolean created = SignUpManager.registUser(db.openConnection(),nick,  name, last, pwd, profile);
            
            if (created) {
                cleanFields();
                JOptionPane.showMessageDialog(null, "OK User registrado"); 
            } else {
                data.clear();
                JOptionPane.showMessageDialog(null, "Hay un error");
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

    private List<ProfileDTO> loadProfiles()
    {
        return SignUpManager.getAllProfiles(db.openConnection());
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

    private void cleanFields()
    {
        view.setValueOfNick();
        view.setValueOfName();
        view.setValueOfLastname();
        view.setValueOfPassword();
    }
}
