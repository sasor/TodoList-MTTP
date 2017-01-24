package src.controllers.propietary;

import java.text.SimpleDateFormat;
import src.controllers.MainController;
import java.util.Date;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import src.stuff.DB;
import src.views.panels.propietary.*;

import src.dao.gestion.inventary.*;
import src.dto.gestion.inventary.*;

public class DataController
{
    private DB db;
    private AddDataToElement view;
    private Integer id;

    private Integer will_place_id;
    private Integer will_element_id;
    private Integer will_element_level;
    private Integer will_room_id;

    public DataController(Integer element_id)
    {
        db = DB.instance();
        id = element_id;
        view = new AddDataToElement(this);
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
        boolean isPrepared = prepareElement();
        boolean a_insertadoElement = insertElement();
        boolean a_insertadoPlace = insertPlace(place);
        if (isPrepared) {
            if (a_insertadoElement) {
                if (a_insertadoPlace) {
                    insertData(name,description,storage,expiration);

                    /* Save Action */
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

    private boolean insertElement()
    {
        boolean response = false;
        ElementIDAO dao = new ElementIDAO(db.openConnection());
        byte child = 0;
        ElementDTO obj = new ElementDTO(will_room_id,++will_element_level,id,child);
        try {
            if (dao.create(obj)) {
                will_element_id = obj.getElementId();
                response = true;
            } 
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return response;
    }

    private boolean prepareElement()
    {
        boolean response = false;
        ElementIDAO dao = new ElementIDAO(db.openConnection());
        ElementDTO dto = null;
        try {
            dto = dao.get(id);
            if (dto != null) {
                will_element_level = dto.getElementLevel();
                will_room_id = dto.getRoomId();
                boolean tmp = updateElement(dto);
                if (tmp) {
                    response = true;
                } 
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return response;
    }

    private boolean updateElement(ElementDTO obj) 
    {
        boolean response = false;
        if (obj.getHaveChild() == 0) {
            byte f = 1;
            obj.setHaveChild(f);
        }
        ElementIDAO dao = new ElementIDAO(db.openConnection());
        try {
            if (dao.update(obj)) {
                response = true;
            }    
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return response;
    }
}
