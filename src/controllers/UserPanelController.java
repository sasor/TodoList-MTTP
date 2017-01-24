package src.controllers;

import javax.swing.*;

import src.views.panels.users.*;

public class UserPanelController
{
    public UserPanelController()
    {}

    public void all()
    {
        new AllUserController();
    }

    public void add()
    {
        new AddUserController();
    }

    public void update()
    {
        if (All.user_table.getSelectedRow() != -1) {
            new UpdateUserController();
        } else {
            JOptionPane.showMessageDialog(null, "DAMM No Row Selected");
        }
    }

    public void delete()
    {
        new DeleteUserController();
    }
 
    public void read()
    {
        if (All.user_table.getSelectedRow() != -1) {
            new ReadUserController();
        } else {
            JOptionPane.showMessageDialog(null, "DAMM No Row Selected");
        }
    }

    public void detail()
    {
        if (All.user_table.getSelectedRow() != -1) {
            new DetailUserController();
        } else {
            JOptionPane.showMessageDialog(null, "DAMM No Row Selected");
        }
    }
}
