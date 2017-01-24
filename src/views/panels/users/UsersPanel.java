package src.views.panels.users;

import javax.swing.*;
import java.awt.*;

import src.controllers.UserPanelController;

public class UsersPanel extends JPanel
{
    public static UserSidebarPanel sidebar;
    public static JPanel content;

    public UsersPanel(UserPanelController model)
    {
        super(new BorderLayout());
        
        sidebar = new UserSidebarPanel(model);
        content = new JPanel();

        this.add(sidebar, BorderLayout.WEST);
        this.add(content, BorderLayout.CENTER);
    }
}
