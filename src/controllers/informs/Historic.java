package src.controllers.informs;

import java.sql.*;
import src.dao.gestion.users.ActionIDAO;
import src.dto.gestion.users.ActionDTO;
import src.stuff.DB;

public class Historic
{
    private DB db;
    public static ActionDTO action;

    public static Historic instance;

    public Historic()
    {
        db = DB.instance();
        action = new ActionDTO();
        instance = this;
    } 

    public void registAction()
    {
        ActionIDAO dao = new ActionIDAO(db.openConnection()); 
        try {
            dao.create(action);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
   
    public void registAction(Integer u,String n,String a,java.util.Date d,String ti)
    {
        action.setUser(u);
        action.setUserNick(n);
        action.setAction(a);
        action.setDate(d);
        action.setTimes(ti);
        
        ActionIDAO dao = new ActionIDAO(db.openConnection()); 
        try {
            dao.create(action);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void reload()
    {
        action = new ActionDTO();
    }
}
