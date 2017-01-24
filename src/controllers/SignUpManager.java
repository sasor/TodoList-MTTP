package src.controllers;

import java.sql.SQLException;
import java.sql.Connection;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.*;

import src.dao.gestion.users.ProfileIDAO;
import src.dao.gestion.users.UserIDAO;
import src.dto.gestion.users.ProfileDTO;
import src.dto.gestion.users.UserDTO;
import src.dto.gestion.users.ProfileUserDTO;
import src.dao.gestion.users.ProfileUserIDAO;

public class SignUpManager
{
    public SignUpManager()
    {}

    public static List<ProfileDTO> getAllProfiles(Connection db)
    {
        List<ProfileDTO> response = null;
        ProfileIDAO dao_profile = new ProfileIDAO(db); 

        try {
            List<ProfileDTO> profiles = dao_profile.all();
            if (profiles.size() != 0) {
                response = profiles;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage()); 
        }
        return response;
    }

    public static boolean registUser(Connection db,   String nick, String name, String lastname,  String pwd, String profile)
    {
        boolean response = false;
        UserIDAO dao_user = new UserIDAO(db); 

        UserDTO user = new UserDTO();
        user.setUserNickname(nick);
        user.setUserName(name);
        user.setUserLastname(lastname);
        user.setUserPassword(pwd);

        Integer for_session = null;
        try {
            if (dao_user.create(user)) {
                Integer id = user.getUserId();
                for_session = id;
                boolean created = registProfile(db, id, profile);
                if (created) {
                    response = true;
                }
            } 
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        if (response) {
            MainController.inform.action.setUser(for_session);
            MainController.inform.action.setUserNick(nick);
            MainController.inform.action.setAction("SIGNUP");
            java.util.Date d = new java.util.Date();
            SimpleDateFormat f = new SimpleDateFormat("hh:mm:ss a");
            MainController.inform.action.setDate(d);
            MainController.inform.action.setTimes(f.format(d));
            MainController.inform.instance.registAction();
        }
        return response;
    }

    private static boolean registProfile(Connection db, Integer user, String profile)
    {
        boolean response = false;
        ProfileUserIDAO dao = new ProfileUserIDAO(db);       
        Integer profile_id = findProfileId(profile, db);

        ProfileUserDTO object = new ProfileUserDTO(profile_id, user);
        try {
            if (dao.create(object)) {
                response = true;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return response;
    }

    private static Integer findProfileId(String profile_response, Connection db)
    {
        Integer response = null;

        List<ProfileDTO> profiles = getAllProfiles(db);
        for (ProfileDTO profile : profiles) {
            if (profile_response.equals(profile.getProfileName())) {
                response = profile.getProfileId();
            }
        }
        return response;
    }
}
