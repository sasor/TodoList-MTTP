package src.views.panels.licenses;

import javax.swing.*;
import java.awt.*;

import src.views.panels.SidebarPanel;

public class LicensesPanel extends JPanel
{
    public static SidebarPanel sidebar;
    public static JPanel content;

    public LicensesPanel()
    {
        super(new BorderLayout());
        
        sidebar = new SidebarPanel();
        content = new JPanel(new BorderLayout());

        this.add(sidebar, BorderLayout.WEST);
        this.add(content, BorderLayout.CENTER);
    }
}
