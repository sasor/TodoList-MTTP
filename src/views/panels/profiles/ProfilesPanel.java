package src.views.panels.profiles;

import javax.swing.*;
import java.awt.*;

import src.views.panels.SidebarPanel;

public class ProfilesPanel extends JPanel
{
    public static SidebarPanel sidebar;
    public static JPanel content;

    public ProfilesPanel()
    {
        super(new BorderLayout());
        
        sidebar = new SidebarPanel();
        content = new JPanel(new BorderLayout());

        this.add(sidebar, BorderLayout.WEST);
        this.add(content, BorderLayout.CENTER);
    }
}
