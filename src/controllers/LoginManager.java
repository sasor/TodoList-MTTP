package src.controllers;

import java.sql.SQLException;
import java.sql.Connection;

import src.dao.gestion.users.UserIDAO;
import src.dto.gestion.users.UserDTO;

public class LoginManager
{
    public LoginManager()
    {}

    public static Integer checkEntryNick(Connection db, String nick_request)
    {
        Integer response = null;

        UserIDAO user_dao = new UserIDAO(db);
        
        if (nick_request != "") {
            try { 
                Integer id_user = user_dao.getByNickname(nick_request);
                if (id_user != null) {
                    response = id_user; 
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage()); 
                e.printStackTrace();
            }
        }
        return response;
    }

    public static boolean checkEntryPwd(Connection db, Integer id, String pwd_request)
    {
        boolean response = false;
        UserIDAO user_dao = new UserIDAO(db);
      
        try {
            UserDTO user = user_dao.get(id); 
            if (user != null) {
                if (pwd_request.equals(user.getUserPassword())) {
                    response = true;
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }       
        return response;
    }

    public static boolean isActive(Connection db, Integer id)
    {
        UserIDAO dao = new UserIDAO(db);
        boolean response = true;
        try {
             UserDTO u = dao.get(id);
             if (u != null) {
                 if (u.getUserActive() == 0) {
                     return false;
                 }
             }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return response;
    }
}
