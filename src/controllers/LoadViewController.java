package src.controllers;

import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import src.views.*;
import src.stuff.DB;

import src.dao.gestion.users.*;
import src.dto.gestion.users.*;

public class LoadViewController
{
    private DB db; 
    public static LoadViewController instance;

    public LoadViewController(DB d)
    {
        db = d; 
        instance = this;
    }

    public void principal(Integer user_id)
    {
        List<Integer> ids_profiles = getProfilesBelongToUser(user_id);
        if (isAdmin(ids_profiles)) {
            JOptionPane.showMessageDialog(null, "Welcome User Administrator");
            MainFrame.panelC.removeAll();
            //MainFrame.panelC.add(new WelcomePanel());
            MainFrame.panelC.add(new AdminJTP());
            MainFrame.panelC.updateUI();
        }
        
        if (isPropietary(ids_profiles)) {
            JOptionPane.showMessageDialog(null, "Welcome User Propietary");
            MainFrame.panelC.removeAll();
            //MainFrame.panelC.add(new WelcomePanel());
            MainFrame.panelC.add(new PropietaryJTP());
            MainFrame.panelC.updateUI();
        }
        
        if (isHijo(ids_profiles)) {
            JOptionPane.showMessageDialog(null, "Welcome");
            MainFrame.panelC.removeAll();
            //MainFrame.panelC.add(new WelcomePanel());
            MainFrame.panelC.add(new HijoJTP());
            MainFrame.panelC.updateUI();
        }
    
        if (isResident(ids_profiles)) {
            JOptionPane.showMessageDialog(null, "Welcome");
            MainFrame.panelC.removeAll();
            //MainFrame.panelC.add(new WelcomePanel());
            MainFrame.panelC.add(new ResidentJTP());
            MainFrame.panelC.updateUI();
        }
    }

    private List<Integer> getProfilesBelongToUser(Integer id)
    {
        List<Integer> response = new ArrayList<Integer>();
        ProfileUserIDAO dao = new ProfileUserIDAO(db.openConnection());
        List<ProfileUserDTO> pus = null; 
        try {
            pus = dao.all();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        for (ProfileUserDTO p : pus) {
            if (p.getUserId() == id) {
                response.add(p.getProfileId()); 
            }
        } 
        return response;
    }

    public boolean isAdmin(List<Integer> ids)
    {
        boolean response = false;
        List<ProfileDTO> profiles = null; 
        ProfileIDAO dao_profile = new ProfileIDAO(db.openConnection());
        try {
            profiles = dao_profile.all(); 
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        for (int i=0; i<ids.size(); i++) {
            for (ProfileDTO p : profiles) {
                if (ids.get(i) == p.getProfileId() && "administrador".equals(p.getProfileName())) {
                    response = true;
                }
            }
        }
        return response;
    }

    private boolean isPropietary(List<Integer> ids)
    {
        boolean response = false;
        List<ProfileDTO> profiles = null; 
        ProfileIDAO dao_profile = new ProfileIDAO(db.openConnection());
        try {
            profiles = dao_profile.all(); 
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        for (int i=0; i<ids.size(); i++) {
            for (ProfileDTO p : profiles) {
                if (ids.get(i) == p.getProfileId() && "propietario".equals(p.getProfileName())) {
                    response = true;
                }
            }
        }
        return response;
    }

    private boolean isHijo(List<Integer> ids)
    {
        boolean response = false;
        List<ProfileDTO> profiles = null; 
        ProfileIDAO dao_profile = new ProfileIDAO(db.openConnection());
        try {
            profiles = dao_profile.all(); 
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        for (int i=0; i<ids.size(); i++) {
            for (ProfileDTO p : profiles) {
                if (ids.get(i) == p.getProfileId() && "hijo".equals(p.getProfileName())) {
                    response = true;
                }
            }
        }
        return response;
    }
    private boolean isResident(List<Integer> ids)
    {
        boolean response = false;
        List<ProfileDTO> profiles = null; 
        ProfileIDAO dao_profile = new ProfileIDAO(db.openConnection());
        try {
            profiles = dao_profile.all(); 
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        for (int i=0; i<ids.size(); i++) {
            for (ProfileDTO p : profiles) {
                if (ids.get(i) == p.getProfileId() && "residente".equals(p.getProfileName())) {
                    response = true;
                }
            }
        }
        return response;
    }
}
