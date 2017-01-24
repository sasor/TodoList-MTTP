package src.views;

import javax.swing.*;
import java.awt.*;
import src.views.panels.propietary.*;
import src.controllers.propietary.*;

public class PropietaryJTP extends JTabbedPane
{
    private ProfilePanel me_panel;  
    public static UserPanel user_panel;
    public static InventaryPanel inventary_panel;
    public static ExitPanel exit_panel;

    private Users user_panel_controller;
    private Inventary inventary_panel_controller;
    private ProfileController me_controller;

    public PropietaryJTP()
    {
        super();
        user_panel_controller = new Users();
        inventary_panel_controller = new Inventary();
        me_controller = new ProfileController();

        me_panel = new ProfilePanel(me_controller);
        user_panel = new UserPanel(user_panel_controller);
        inventary_panel = new InventaryPanel(inventary_panel_controller);
        exit_panel = new ExitPanel();

        this.addTab("My_Profile", me_panel); 
        this.addTab("Users", user_panel);
        this.addTab("Inventary", inventary_panel);
        this.addTab("Exit", exit_panel);
    }
}
