package src.controllers.propietary;

import java.util.List;
import java.util.ArrayList;
import src.stuff.DB;
import java.sql.SQLException;
import src.views.panels.users.*;
import javax.swing.*;
import src.dao.gestion.users.*;
import src.dto.gestion.users.*;
import src.views.panels.propietary.Detail;
import src.views.PropietaryJTP;

public class DetailController
{
    private DB db;
    private Integer user_id;
    private UserDTO data;
    private List<ActionDTO> items;
    private Detail view;

    public DetailController(Integer id)
    {
        db = DB.instance();
        //user_id = getUserID();
        user_id = id;
        populateData();
        fixActions();
        if (items.size() == 0) {JOptionPane.showMessageDialog(null,"No Details");return;}
        view = new Detail(this);
        PropietaryJTP.user_panel.removeAll();
        PropietaryJTP.user_panel.add(view);         
        PropietaryJTP.user_panel.updateUI();
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
