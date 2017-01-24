package src.controllers;

import java.text.SimpleDateFormat;
import src.stuff.DB;
import java.sql.SQLException;
import src.views.panels.users.*;
import javax.swing.*;
import src.dao.gestion.users.*;
import src.dto.gestion.users.*;

public class DeleteUserController
{
    private DB db;

    public DeleteUserController()
    {
        db = DB.instance();
        init(); 
    }
  
    public boolean verify()
    {
        boolean response = false;
        if (All.user_table.getSelectedRow() != -1) {
            response = true;
        }
        return response;
    }

    public String pullNickname()
    {
        String nick = String.valueOf(All.user_table.getValueAt(All.user_table.getSelectedRow(), 0));
        return nick;
    }

    public void init()
    {
        if (verify()) {
            String nickToDelete = pullNickname();
            if (deleteUser(nickToDelete)) {
                //All.table_model.removeRow(All.user_table.getSelectedRow());
                MainController.inform.action.setAction("DELETE USER");
                java.util.Date d = new java.util.Date();
                SimpleDateFormat f = new SimpleDateFormat("hh:mm:ss a"); 
                MainController.inform.action.setDate(d);
                MainController.inform.action.setTimes(f.format(d));
                MainController.inform.instance.registAction();

                JOptionPane.showMessageDialog(null, "OK, User Deleted");
                All.user_table.updateUI();
            }
        } else {
            JOptionPane.showMessageDialog(null, "DAMM No Row Selected");
        }
    }

    public boolean deleteUser(String nick)
    {
        boolean response = false;
        UserIDAO u_dao = new UserIDAO(db.openConnection());
        try {
            if(u_dao.deleteByNickname(nick)) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return response; 
    } 
}
