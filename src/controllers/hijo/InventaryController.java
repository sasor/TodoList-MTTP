package src.controllers.hijo;

import java.util.List;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import src.controllers.MainController;
import src.stuff.DB;
import src.dao.gestion.inventary.*;
import src.dto.gestion.inventary.*;

public class InventaryController 
{
    private DB db;

    public InventaryController()
    {
        db = DB.instance();  
    }

    public List<HomeDTO> getHomes()
    {
        HomeIDAO h_dao = new HomeIDAO(db.openConnection());
        List<HomeDTO> list = null;
        try {
            list = h_dao.all();
            return list;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
}
