package src.controllers.hijo;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;
import src.stuff.DB;
import src.views.panels.hijo.*;
import src.dao.gestion.inventary.*;
import src.dto.gestion.inventary.*;

public class TreeController
{
    private DB db;
    private Integer room_id;
    private List<ElementDTO> data;

    public static SideBarPanel view;

    public TreeController(Integer id)
    {
        db = DB.instance();
        room_id = id;
        populateData();

        view = new SideBarPanel(this);
    }

    private void populateData()
    {
        ElementIDAO dao = new ElementIDAO(db.openConnection()); 
        List<ElementDTO> elements = null;
        try {
            elements = dao.all();
            List<ElementDTO> elsBelongToRoom = filter(elements);
            if (elsBelongToRoom.size() != 0) {
                data = elsBelongToRoom;
            } else {
                data = new ArrayList<ElementDTO>();
                JOptionPane.showMessageDialog(null,"No Elements in this Room");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<ElementDTO> filter(List<ElementDTO> els)
    {
        List<ElementDTO> response = new ArrayList<ElementDTO>();
        for (ElementDTO e : els) {
            if (room_id == e.getRoomId()) {
                response.add(e);
            }
        }
        return response;
    }

    public List<ElementDTO> getData()
    {
        return data;
    }

    public Integer getID()
    {
        return room_id;
    }

    public boolean hasChilds(ElementDTO e)
    {
        boolean response = false;
        if (e.getHaveChild() != 0) {
            response = true;
        }
        return response;
    }

    /* Metodo que lo usa la vista , para comprobar si hijo directo del root
       del arbol o algo asi */
    public boolean isRootChild(Integer id)
    {
        boolean response = false;
        for (ElementDTO e : data) {
            if (id == e.getElementId() && e.getElementParent() == 0) {
                response = true; 
            }
        }
        return response; 
    }

    public void drawDataWithElementID(Integer element_id)
    {
        new ElementDataController(element_id);
    }

    public String getNameRoom()
    {
        String response = "";
        RoomIDAO dao = new RoomIDAO(db.openConnection());
        try {
            RoomDTO dto = dao.get(room_id);        
            response = dto.getRoomName();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return response;
    }
}
