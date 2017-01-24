package src.controllers.resident;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import src.stuff.DB;
import src.views.panels.resident.*;
import src.views.*;
import src.dao.gestion.inventary.*;
import src.dto.gestion.inventary.*;

public class HomeController
{
    private DB db;
    private Integer id;
    public static HomePanel view;

    public HomeController(Integer id)
    {
        this.id = id;
        db = DB.instance();
        view = new HomePanel(this);
        ResidentJTP.panel.removeAll();
        ResidentJTP.panel.add(view);
        ResidentJTP.panel.updateUI();
    }

    /* Method que trae todos los rooms que pertenecen a un Home */
    public List<RoomDTO> getRooms()
    {
        RoomIDAO r_dao = new RoomIDAO(db.openConnection());
        List<RoomDTO> list = null;
        List<RoomDTO> response = null;
        try {
            list = r_dao.all();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        if (list.size() != 0 || list != null) {
            response = filterRooms(list); 
        }
        return response;
    }
 
    private List<RoomDTO> filterRooms(List<RoomDTO> list)
    {
        List<RoomDTO> response = new ArrayList<RoomDTO>();
        for (RoomDTO r : list) {
            /* Comparamos is el room pertenece a este home */
            if (id == r.getHomeId()) {
                response.add(r);
            }
        }
        return response;
    }

    public Integer getHomeID()
    {
        return id;
    }
}
