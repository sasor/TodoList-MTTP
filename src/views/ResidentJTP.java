package src.views;

import javax.swing.*;
import java.awt.*;
import src.views.panels.resident.*;
import src.controllers.resident.*;

public class ResidentJTP extends JTabbedPane
{
    public static ProfilePanel me_profile;
    private ProfileController me_controller;
 
    public static InventaryPanel panel;
    private InventaryController panel_controller;

    public static ExitPanel exit_panel;

    public ResidentJTP()
    {
        super();
        me_controller = new ProfileController();
        me_profile = new ProfilePanel(me_controller);

        panel_controller = new InventaryController();
        panel = new InventaryPanel(panel_controller);

        exit_panel = new ExitPanel();
     
        this.addTab("My_Profile", me_profile);
        this.addTab("Stuff", panel);
        this.addTab("Exit", exit_panel);
    }
}
