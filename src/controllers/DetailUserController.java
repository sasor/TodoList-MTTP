package src.controllers;

import java.util.List;
import java.util.ArrayList;
import src.stuff.DB;
import java.sql.SQLException;
import src.views.panels.users.*;
import javax.swing.*;
import src.dao.gestion.users.*;
import src.dto.gestion.users.*;

public class DetailUserController
{
    private DB db;
    private Integer user_id;
    private UserDTO data;
    private List<ActionDTO> items;
    public static Detail view;

    public DetailUserController()
    {
        db = DB.instance();
        user_id = getUserID();
        populateData();
        fixActions();
        if (items.size() == 0) {JOptionPane.showMessageDialog(null,"No Details");return;}
        view = new Detail(this);
        UsersPanel.content.removeAll();
        UsersPanel.content.add(view);         
        UsersPanel.content.updateUI();
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

    public void fixActions()
    {
        List<ActionDTO> actions = null;
        ActionIDAO dao = new ActionIDAO(db.openConnection());
        try {
            actions = dao.all();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        List<ActionDTO> tmp = new ArrayList<ActionDTO>();
        if (actions.size() != 0 || actions != null) {
            for (ActionDTO a : actions) {
                if (a.getUserId() == user_id) {
                    tmp.add(a);
                }
            }
            items = tmp;
        } else {
            JOptionPane.showMessageDialog(null, "No");
        }
    }

    public List<ActionDTO> getItems()
    {
        return items;
    }
}
