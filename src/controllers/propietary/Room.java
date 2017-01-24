package src.controllers.propietary;

import java.sql.SQLException;
import src.stuff.DB;
import src.dao.gestion.inventary.*;
import src.dto.gestion.inventary.*;
import src.views.panels.propietary.*;
import src.views.*;

/* Todo Room tiene objectos */
public class Room
{
    /* id room para traer los objetos relacionados a este room */
    private Integer room_id;
    /* id del home al que pertenece */
    private Integer parent_id;
    private DB db;

    private RoomPanel view;

    public Room(Integer id)
    {
        room_id = id;
        db = DB.instance();
        findParentId();

        view = new RoomPanel(this);
        PropietaryJTP.inventary_panel.removeAll();
        PropietaryJTP.inventary_panel.add(view);
        PropietaryJTP.inventary_panel.updateUI();
    }

    public Integer getParentId()
    {
        return parent_id;
    } 
 
    public Integer getRoomId()
    {
        return room_id; 
    }

    private void findParentId()
    {
        RoomIDAO dao = new RoomIDAO(db.openConnection());
        try {
            RoomDTO tmp = dao.get(room_id);
            if (tmp != null) {
                parent_id = tmp.getHomeId();
            }
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        }
    }
}
