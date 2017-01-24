package src.controllers.propietary;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import src.stuff.DB;
import src.dto.gestion.users.UserDTO;
import src.dao.gestion.users.UserIDAO;
import src.views.panels.propietary.*;
import src.controllers.LoadViewController;

public class Users
{
    private DB db;
    private UserPanel view;
    private List<UserDTO> data;

    public Users()
    {
        db = DB.instance();
        data = new ArrayList<UserDTO>();
        populateData(db.openConnection());

        view = new UserPanel(this);
    }

    private void populateData(Connection db)
    {
        UserIDAO user_dao = new UserIDAO(db);
        List<UserDTO> users = null;
        try {
            users = user_dao.all(); 
            if (users.size() != 0) {
                //data = users;
                filterAdmins(users);
            } else {
                JOptionPane.showMessageDialog(null, "No users in Database"); 
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private void filterAdmins(List<UserDTO> users)
    {
        List<UserDTO> response = new ArrayList<UserDTO>();
        for (UserDTO u : users) {
            List<Integer> tmp = new ArrayList<Integer>();
            tmp.add(u.getUserId());
            if (!(LoadViewController.instance.isAdmin(tmp))) {
                response.add(u);
            }
            tmp = null;
        }
        data = response;
    }

    public List<UserDTO> getData()
    {
        return data;
    }
   
    public void doAction()
    {
        Integer id = getUserID();
        new DetailController(id);
    }

    public String pullNickname()
    {
        String nick = String.valueOf(UserPanel.table_users.getValueAt(UserPanel.table_users.getSelectedRow(), 0));
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
}
