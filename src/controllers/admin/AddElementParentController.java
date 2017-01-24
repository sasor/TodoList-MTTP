package src.controllers.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import src.stuff.DB;
import src.views.panels.admin.*;
import src.dao.gestion.inventary.*;
import src.dto.gestion.inventary.*;
import src.controllers.MainController;

public class AddElementParentController
{
    private DB db;
    private Integer room_id;
    private AddElementParent view;
    
    private Integer will_place_id;
    private Integer will_element_id;

    public AddElementParentController(Integer room)
    {
        db = DB.instance();
        room_id = room;
        view = new AddElementParent(this);
        RoomPanel.body.removeAll();
        RoomPanel.body.add(view);
        RoomPanel.body.updateUI();
    }

    public void add()
    {
        String name = view.getName();
        String place = view.getPlace();
        String description = view.getDescription();
        Date storage = new Date();
        Date expiration = (Date) view.getExpiration();

        boolean a_insertadoElement = insertElement();
        boolean a_insertadoPlace = insertPlace(place);
        if (a_insertadoElement) {
            if (a_insertadoPlace) {
                insertData(name,description,storage,expiration);

                MainController.inform.action.setAction("CREATE ELEMENT IN ROOM");
                java.util.Date d = new java.util.Date();
                SimpleDateFormat f = new SimpleDateFormat("hh:mm:ss a");
                MainController.inform.action.setDate(d);
                MainController.inform.action.setTimes(f.format(d));
                MainController.inform.instance.registAction();

                SideBarPanel.model_tree.reload();
            } else {
                JOptionPane.showMessageDialog(null, "Error Insert Data");
            } 
        } 
    }

    private boolean insertElement()
    {
        boolean response = false;
        ElementIDAO dao = new ElementIDAO(db.openConnection());
        ElementDTO obj = new ElementDTO(room_id);
        try {
            if (dao.createByRoom(obj)) {
                will_element_id = obj.getElementId();
                response = true;
            } 
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return response;
    }

    private boolean insertPlace(String string)
    {
        /* Code Here for new Place insert */
        boolean response = false;
        PlaceIDAO place = new PlaceIDAO(db.openConnection());
        PlaceDTO _p = new PlaceDTO(string);
        try {
            if(place.create(_p)) {
                will_place_id = _p.getPlaceId(); 
                response = true;
            } 
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return response;
    }

    private boolean insertData(String name,String description,Date storage,Date expiration)
    {
        boolean response = false;
        DataIDAO dao = new DataIDAO(db.openConnection());
        DataDTO obj = new DataDTO(will_place_id,will_element_id,name,description,storage,expiration);
        try {
            if (dao.create(obj)) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return response;
    }

}
