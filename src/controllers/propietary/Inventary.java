package src.controllers.propietary;

import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import src.stuff.DB;
import src.dao.gestion.inventary.*;
import src.dto.gestion.inventary.*;
import src.views.panels.propietary.*;

public class Inventary 
{
    private DB db;
    private List<HomeDTO> data;
    private InventaryPanel view;
  
    public Inventary()
    {
        db = DB.instance();
        populateData();

        view = new InventaryPanel(this); 
        //view.rePaint();
    }

    public void populateData()
    {
        HomeIDAO home_dao = new HomeIDAO(db.openConnection());
        List<HomeDTO> homes = null;
        try {
            homes = home_dao.all();
            if (homes.size() != 0) {
                data = homes;
            } else {
                data = new ArrayList<HomeDTO>();
                JOptionPane.showMessageDialog(null, "No Homes in Database"); 
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /* Este metodo es utilizado por la vista para recuperar cuando homes
       existen 
     */
    public List<HomeDTO> getData()
    {
        return data;
    }

    public void entryHome(Integer id_something_home)
    {
        new Home(id_something_home);
    }
}
