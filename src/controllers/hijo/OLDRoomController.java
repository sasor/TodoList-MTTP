package src.controllers.hijo;

import java.util.List;
import java.util.ArrayList;
import src.stuff.DB;
import java.sql.*;
import javax.swing.JOptionPane;
import src.views.HijoJTP;
import src.views.panels.hijo.*;

public class RoomController
{
    private DB db;
    private Integer room_id;
    private Integer home_id;
    private RoomPanel view;

    public RoomController(Integer room, Integer home)
    {
        room_id = room;
        home_id = home;
        db = DB.instance();
        view = new RoomPanel(this);
        HijoJTP.objects_panel.removeAll();
        HijoJTP.objects_panel.add(view);
        HijoJTP.objects_panel.updateUI();
    }
}
