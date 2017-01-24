package src.controllers;

import javax.swing.*;

import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import src.views.*;
import src.views.panels.access.*;
import src.dto.gestion.users.ProfileDTO;
import src.stuff.DB;
import src.controllers.informs.*;

public class LoginRegisterController
{
    private LoginRegisterForm view;
    private DB db;

    private LoadViewController load;
    private SessionController session;
    
    public LoginRegisterController(DB connection)
    {
        db = connection;
        view = new LoginRegisterForm(this);
        load = new LoadViewController(db);
        populateProfiles();
        MainFrame.panelC.add(view);
    }

    public void checkLogin()
    {
        LoginPanel loginP = view.getLoginView();
        String nick = loginP.getValueOfNick();        
        String pwd = loginP.getValueOfPassword();

        Integer id = LoginManager.checkEntryNick(db.openConnection(), nick);
        if (id == null) {
            JOptionPane.showMessageDialog(null, "NO Existe este Nick: "+nick);
        } else {
            boolean matchPwd = LoginManager.checkEntryPwd( db.openConnection(),             id, pwd ); 
            if (matchPwd) {
                if (LoginManager.isActive(db.openConnection(), id)) {
                    MainController.session.user.setUserId(id);
                    MainController.session.user.setUserNickname(nick);
                    MainController.inform.action.setUser(id);
                    MainController.inform.action.setUserNick(nick);
                    MainController.inform.action.setAction("LOGIN");
                    java.util.Date d = new java.util.Date();
                    SimpleDateFormat f = new SimpleDateFormat("hh:mm:ss a"); 
                    MainController.inform.action.setDate(d);
                    MainController.inform.action.setTimes(f.format(d));
                    MainController.inform.instance.registAction();

                    load.principal(id);  
                } else {
                    JOptionPane.showMessageDialog(null,"You have been Blocked");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Password: "+pwd); 
            }
        }
    }

    public void checkSignUp()
    {
        SignUpPanel signP = view.getSignUpView(); 
        JComboBox profileCB = signP.getProfileCB();

        String nick = signP.getValueOfNick();
        String name = signP.getValueOfName();
        String lastname = signP.getValueOfLastname();
        String pwd = signP.getValueOfPassword();
        String profileSelected = (String)profileCB.getSelectedItem();

        String[] fields = {nick, name, lastname, pwd, profileSelected};
   
        boolean isValidate = checkFields(fields);
      
        if (isValidate) {
            boolean valid = SignUpManager.registUser(db.openConnection(), nick, name, lastname, pwd, profileSelected);
            if (valid) {
                cleanFields();
                JOptionPane.showMessageDialog(null, "OK User registrado"); 
            } else {
                JOptionPane.showMessageDialog(null, "Hay un error");
            }
        } else {
           JOptionPane.showMessageDialog(null, "DAMM, Fields Empty");
        }

        //String todo = nick+ " | "+name+" | "+lastname+" | "+pwd;

        //JOptionPane.showMessageDialog(null, profileSelected);
    }

    private List<ProfileDTO> loadProfiles()
    {
        return SignUpManager.getAllProfiles(db.openConnection());
    }

    private void populateProfiles()
    {
        SignUpPanel signP = view.getSignUpView();
        JComboBox profileCB = signP.getProfileCB();

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
        SignUpPanel signP = view.getSignUpView(); 
        signP.setValueOfNick();
        signP.setValueOfName();
        signP.setValueOfLastname();
        signP.setValueOfPassword();
    }

    private boolean checkFields(String[] campos)
    {
        boolean response = true;
        for (String i : campos) {
            if (i.isEmpty() || i == null) {
                return response = false; 
            }
        }
        return response;
    }
}
