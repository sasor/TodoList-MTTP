package src.views;

import javax.swing.*;
import java.awt.*;
import src.views.panels.hijo.*;
import src.controllers.hijo.*;

public class HijoJTP extends JTabbedPane
{
    public static ProfilePanel me_panel;
    public static PertenenciasPanel objects_panel;
    public static ExitPanel exit_panel;

    private ProfileController me_controller;
    private InventaryController object_controller;

    public HijoJTP()
    {
        super();
        me_controller = new ProfileController();
        me_panel = new ProfilePanel(me_controller);

        object_controller = new InventaryController();
        objects_panel = new PertenenciasPanel(object_controller);
        exit_panel = new ExitPanel();

        this.addTab("My_Profile", me_panel); 
        this.addTab("My_Stuffs", objects_panel); 
        this.addTab("Exit", exit_panel);
    }
}
