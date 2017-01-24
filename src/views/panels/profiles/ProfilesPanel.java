package src.views.panels.profiles;

import javax.swing.*;
import java.awt.*;

import src.controllers.ProfilePanelController;

public class ProfilesPanel extends JPanel
{
    public static ProfileSidebarPanel sidebar;
    public static JPanel content;

    public ProfilesPanel(ProfilePanelController model)
    {
        super(new BorderLayout());
        
        sidebar = new ProfileSidebarPanel(model);
        content = new JPanel();

        this.add(sidebar, BorderLayout.WEST);
        this.add(content, BorderLayout.CENTER);
    }
}
