package src.views.panels.modules;

import javax.swing.*;
import java.awt.*;

import src.controllers.ModulePanelController;

public class ModulesPanel extends JPanel
{
    public static ModuleSidebarPanel sidebar;
    public static JPanel content;

    public ModulesPanel(ModulePanelController model)
    {
        super(new BorderLayout());
        
        sidebar = new ModuleSidebarPanel(model);
        content = new JPanel();

        this.add(sidebar, BorderLayout.WEST);
        this.add(content, BorderLayout.CENTER);
    }
}
