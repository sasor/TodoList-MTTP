package src.controllers.resident;

import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import src.controllers.MainController;
import java.sql.SQLException;
import src.stuff.DB;
import src.dao.gestion.users.UserIDAO;
import src.dto.gestion.users.UserDTO;
import src.views.panels.resident.*;

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
}
