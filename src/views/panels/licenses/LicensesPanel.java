package src.views.panels.licenses;

import javax.swing.*;
import java.awt.*;

import src.controllers.LicensePanelController;

public class LicensesPanel extends JPanel
{
    public static LicenseSidebarPanel sidebar;
    public static JPanel content;

    public LicensesPanel(LicensePanelController model)
    {
        super(new BorderLayout());
        
        sidebar = new LicenseSidebarPanel(model);
        content = new JPanel();

        this.add(sidebar, BorderLayout.WEST);
        this.add(content, BorderLayout.CENTER);
    }
}
