package src.controllers;

import javax.swing.JOptionPane;
import src.views.panels.profiles.*;

public class ProfilePanelController
{
    public ProfilePanelController()
    {}

    public void all()
    {
        new AllProfileController();
    }

    public void add()
    {
        ProfilesPanel.content.removeAll();
        ProfilesPanel.content.add(new Add());         
        ProfilesPanel.content.updateUI();
    }

    public void update()
    {
        if (All.profile_table.getSelectedRow() != -1) {
            new UpdateProfileController();
        } else {
            JOptionPane.showMessageDialog(null, "DAMM No Row Selected");
        }
    }

    public void delete()
    {
        if (All.profile_table.getSelectedRow() != -1) {
            new DeleteProfileController();
        } else {
            JOptionPane.showMessageDialog(null, "DAMM No Row Selected");
        }
    }

    public void read()
    {
        if (All.profile_table.getSelectedRow() != -1) {
            new ReadProfileController();
        } else {
            JOptionPane.showMessageDialog(null, "DAMM No Row Selected");
        }
        /*ProfilesPanel.content.removeAll();
        ProfilesPanel.content.add(new Read());         
        ProfilesPanel.content.updateUI();*/
    }
}
