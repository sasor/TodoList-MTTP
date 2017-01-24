package src.controllers.propietary;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import src.stuff.DB;
import java.sql.SQLException;
import src.dao.gestion.inventary.*;
import src.dto.gestion.inventary.*;
import src.views.panels.propietary.*;
import src.views.*;

public class Home 
{
    private DB db;
    private Integer home_id;
    private List<RoomDTO> data;
    public static HomePanel view;

    public Home(Integer id)
    {
        home_id = id;
        db = DB.instance();
        populateData();
        view = new HomePanel(this);
        PropietaryJTP.inventary_panel.removeAll();
        PropietaryJTP.inventary_panel.add(view);
        PropietaryJTP.inventary_panel.updateUI();
    }

    public void populateData()
    {
        RoomIDAO room_dao = new RoomIDAO(db.openConnection());
        List<RoomDTO> rooms = null;
        try {
            rooms = room_dao.all();
            List<RoomDTO> roomsBelongToHome = filter(rooms); 
            if (roomsBelongToHome.size() != 0) {
                data = roomsBelongToHome;
            } else {
                data = new ArrayList<RoomDTO>();
                JOptionPane.showMessageDialog(null, "No Rooms in this Home");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<RoomDTO> getData()
    {
        return data;
    } 

    public Integer getHomeId()
    {
        return home_id;
    }

    public List<RoomDTO> filter(List<RoomDTO> rooms)
    {
        List<RoomDTO> response = new ArrayList<RoomDTO>();
        for (RoomDTO room : rooms) {
            if (home_id == room.getHomeId()) {
                response.add(room);
            }
        }
        return response;
    }

    public void entryRoom(Integer id)
    {
        new Room(id); 
    }
}
