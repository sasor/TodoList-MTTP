package src.controllers.resident;

import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import src.stuff.DB;
import src.views.panels.resident.*;
import src.dao.gestion.inventary.*;
import src.dto.gestion.inventary.*;
import java.util.Date;

public class ElementDataController
{
    private DB db;
    private DataDTO data;
    private ElementDataPanel view;
    /* Este va a almacenar el id del elemento para asi traer su data */
    private Integer id;

    public ElementDataController(Integer element_id)
    {
        db = DB.instance();
        id = element_id;
        getInformationOfElementData();
        view = new ElementDataPanel(this);
        RoomPanel.body.removeAll();
        RoomPanel.body.add(view);
        RoomPanel.body.updateUI();
    }

    public void getInformationOfElementData()
    {
        DataIDAO dao = new DataIDAO(db.openConnection());
        try {
            List<DataDTO> tmp = dao.all();
            /* controla que tmp no este vacio */
            if (tmp.size() == 0 || tmp == null) {return;}
            DataDTO tmp1 = filter(tmp);
            if (tmp1 != null) {
                data = tmp1;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private DataDTO filter(List<DataDTO> list)
    {
        DataDTO response = null;
        for (DataDTO d : list) {
            if (d.getDataElementId() == id) {
                return d;
            }
        }
        return response;
    }

    public DataDTO getData()
    {
        if (data != null) {
            return data;
        } else {
           return new DataDTO();
        }
    }

    public void add()
    {
        /* Creamos el controller que cargara la vista como parametro
           le otorgamos el id del element actual */
        new DataController(id);
    }

    public void update()
    {
        String name = view.getName();
        String place = view.getPlace();         
        String description = view.getDescription();
        Date expiration = (Date) view.getExpiration();

        DataIDAO dao = new DataIDAO(db.openConnection());
        DataDTO o = null;
        try {
             o = dao.getByElement(id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        //JOptionPane.showMessageDialog(null, ""+o.getDataPlaceId());

        if(updatePlace(place, o.getDataPlaceId())) {
            o.setDataName(name);
            o.setDataDescription(description);
            o.setDataExpiration(expiration);
            try {
                dao.update(o); 
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
            JOptionPane.showMessageDialog(null, "OK, Element was Updated");
        }
    }
    
    private boolean updatePlace(String place, Integer i)
    {
        PlaceIDAO dao = new PlaceIDAO(db.openConnection());
        PlaceDTO dto = null; 
        try {
            dto = dao.get(i);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } 
        if (dto != null) {
            dto.setPlaceObject(place);
        }
        try {
            if(dao.update(dto)) {
                return true; 
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public void delete()
    {
        if (!(hasChilds())) {
            deleteElement();
        } else {
            JOptionPane.showMessageDialog(null, "Error You cant erase Element");
        }
    }

    public boolean hasChilds()
    {
        boolean response = false;
        ElementIDAO e_dao = new ElementIDAO(db.openConnection());
        ElementDTO e_dto = null;
        try {
            e_dto = e_dao.get(id); 
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        /* 0 si no tiene hijos || distinto de 0 si tiene hijos */
        if (e_dto.getHaveChild() != 0) {
           response = true; 
        }
        return response;
    }

    public void deleteElement()
    {
        if (deleteData()) {
            if (oneChild()) {
                JOptionPane.showMessageDialog(null, "ENTRO AQUI");
                updateParent();
                eraseElement(); 
            } else {
                eraseElement();
            }
            JOptionPane.showMessageDialog(null, "OK, Element was Deleted");
        }
    }

    private boolean deleteData()
    {
        boolean response = false;
        if (deletePlace(data.getDataPlaceId())) {
            DataIDAO d_dao = new DataIDAO(db.openConnection());
            try {
                if(d_dao.deleteByKey(data.getDataId())) {
                    return true;
                }    
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return response;
    }

    private boolean deletePlace(Integer place)
    {
        boolean response = false;
        PlaceIDAO p_dao = new PlaceIDAO(db.openConnection());
        try {
            if (p_dao.deleteByKey(place)) {
                response = true;
            } 
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return response;
    }

    private boolean oneChild()
    {
        boolean response = false;
        ElementIDAO dao = new ElementIDAO(db.openConnection());
        ElementDTO dto = null;
        try {
            dto = dao.get(id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        if (dto != null) {
            Integer parent = dto.getElementParent();
            List<ElementDTO> list = null;
            try {
                list = dao.allByParent(parent); 
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            } 
            if (list.size() == 1) {
                return true;
            }
        }
        return response;
    }

    private void eraseElement()
    {
        ElementIDAO dao = new ElementIDAO(db.openConnection()); 
        try {
            dao.deleteByKey(id); 
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private void updateParent()
    {
        ElementIDAO dao = new ElementIDAO(db.openConnection());
        ElementDTO dto = null;
        try {
            dto = dao.get(id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        Integer will_parent_element = dto.getElementParent();
        JOptionPane.showMessageDialog(null, ""+will_parent_element);
        byte value = (byte) 0; 
        try {
            dao.updateParent(will_parent_element, value);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /* Metodo que la vista llama para recuperar el lugar del objeto */
    public String giveMePlace(Integer place_id)
    {
        PlaceIDAO p_dao = new PlaceIDAO(db.openConnection());
        try {
            PlaceDTO p_dto = p_dao.get(place_id);
            if (p_dto != null) {
                return p_dto.getPlaceObject();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return "";
    }
}
