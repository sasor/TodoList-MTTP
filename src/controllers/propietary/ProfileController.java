package src.controllers.propietary;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import src.controllers.MainController;
import java.sql.SQLException;
import src.stuff.DB;
import src.dao.gestion.users.UserIDAO;
import src.dto.gestion.users.UserDTO;
import src.dao.gestion.inventary.*;
import src.dto.gestion.inventary.*;
import src.views.panels.propietary.*;

public class ProfileController
{
    private DB db;
    private UserDTO user;

    public ProfileController()
    {
        db = DB.instance(); 
        upUser();
    }

    private void upUser()
    {
        Integer current = MainController.session.user.getUserId();
        UserIDAO dao = new UserIDAO(db.openConnection());
        try {
            user = dao.get(current);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public UserDTO getUser()
    {
        return user;
    }

    public void updateUser(String nick,String name,String last,String pwd)
    {
        UserIDAO dao = new UserIDAO(db.openConnection());
        user.setUserNickname(nick); 
        user.setUserName(name); 
        user.setUserLastname(last); 
        user.setUserPassword(pwd); 

        try {
            if (dao.update(user)) {
                /* Save Regist */
                MainController.inform.action.setAction("UPDATE PROFILE");
                java.util.Date d = new java.util.Date();
                SimpleDateFormat f = new SimpleDateFormat("hh:mm:ss a");
                MainController.inform.action.setDate(d);
                MainController.inform.action.setTimes(f.format(d));
                MainController.inform.instance.registAction();

                JOptionPane.showMessageDialog(null, "OK, User Updated");
                ProfilePanel.bottom_panel.removeAll();
                ProfilePanel.instance.remove(ProfilePanel.bottom_panel);
                ProfilePanel.instance.bindData();
                ProfilePanel.instance.updateUI();
            }                      
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void addHome(String name_home, String place_home)
    {
        PlaceIDAO p_dao = new PlaceIDAO(db.openConnection());
        PlaceDTO p_dto = new PlaceDTO(place_home);
        Integer place_id = null;
        try {
            if (p_dao.create(p_dto)) {
                place_id = p_dto.getPlaceId();
            }         
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        HomeIDAO h_dao = new HomeIDAO(db.openConnection());
        HomeDTO h_dto = new HomeDTO(name_home, place_id);
        try {
            if (h_dao.create(h_dto)) {
                /* Save Regist */
                MainController.inform.action.setAction("ADD HOME");
                java.util.Date d = new java.util.Date();
                SimpleDateFormat f = new SimpleDateFormat("hh:mm:ss a");
                MainController.inform.action.setDate(d);
                MainController.inform.action.setTimes(f.format(d));
                MainController.inform.instance.registAction();

                JOptionPane.showMessageDialog(null,"OK, Home has been created");
                ProfilePanel.bottom_panel.removeAll();
                ProfilePanel.instance.remove(ProfilePanel.bottom_panel);
                ProfilePanel.instance.updateUI();
            }         
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<HomeDTO> getHomes()
    {
        List<HomeDTO> homes = null;
        HomeIDAO dao = new HomeIDAO(db.openConnection());
        try {
            homes = dao.all();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return homes;
    }

    public void addRoom(String name, String lugar, String pertenece)
    {
        PlaceIDAO p_dao = new PlaceIDAO(db.openConnection());
        PlaceDTO p_dto = new PlaceDTO(lugar);
        Integer place_id = null;
        try {
            if (p_dao.create(p_dto)) {
                place_id = p_dto.getPlaceId();
            } 
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        HomeIDAO h_dao = new HomeIDAO(db.openConnection()); 
        List<HomeDTO> list = null;
        try {
            list = h_dao.all();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        Integer home_id = null;
        for (HomeDTO h : list) {
            if (pertenece.equals(h.getHomeName())) {
                home_id = h.getHomeId();
            }
        }

        //JOptionPane.showMessageDialog(null, "P_ID"+place_id+"HO_ID"+home_id); 

        RoomIDAO r_dao = new RoomIDAO(db.openConnection());
        RoomDTO r_dto = new RoomDTO(name, home_id, place_id);
        try {
            if (r_dao.create(r_dto)) {
                /* Save Regist */
                MainController.inform.action.setAction("ADD ROOM");
                java.util.Date d = new java.util.Date();
                SimpleDateFormat f = new SimpleDateFormat("hh:mm:ss a");
                MainController.inform.action.setDate(d);
                MainController.inform.action.setTimes(f.format(d));
                MainController.inform.instance.registAction();

                JOptionPane.showMessageDialog(null,"OK, Room has been created");
                ProfilePanel.bottom_panel.removeAll();
                ProfilePanel.instance.remove(ProfilePanel.bottom_panel);
                ProfilePanel.instance.updateUI();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
