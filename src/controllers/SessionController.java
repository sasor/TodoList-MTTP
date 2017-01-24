package src.controllers;

import src.dto.gestion.users.UserDTO;

public class SessionController
{
    public static UserDTO user;
    
    public SessionController()
    {
        user = new UserDTO();
    } 

    public void reload()
    {
        user = new UserDTO();
    }
}
