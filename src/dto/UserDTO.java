package src.dto;

public class UserDTO
{
    private Integer user_id;
    private String user_nickname;
    private String user_name;
    private String user_lastname;
    private String user_password;
    private byte user_active;

    public UserDTO()
    {}

    public UserDTO(String nickname, String name, String lastname, String password)
    {
        user_nickname = nickname;
        user_name = name;
        user_lastname = lastname;
        user_password = password;
    }

    public UserDTO(String nickname, String name, String lastname, String password, byte active)
    {
        user_nickname = nickname;
        user_name = name;
        user_lastname = lastname;
        user_password = password;
        user_active = active;
    }

    public Integer getUserId()
    {
        return user_id;
    }

    public String getUserNickname()
    {
        return user_nickname;
    }

    public String getUserName()
    {
        return user_name;
    }

    public String getUserLastname()
    {
        return user_lastname;
    }

    public String getUserPassword()
    {
        return user_password;
    }

    public byte getUserActive()
    {
        return user_active;
    }

    public String toString()
    {
        String SS = " ,";
        String id = "ID: " + getUserId() + SS;
        String name = "NAME: " + getUserName() + SS;
        String lastname = "LASTNAME: " + getUserLastname() + SS;
        String active = "ACTIVE: " + getUserActive();
        return "User { " + id + name + lastname + active + " }";
    }

    public void setUserId(Integer id)
    {
        user_id = id;
    }

    public void setUserNickname(String nickname)
    {
        user_nickname = nickname;
    }

    public void setUserName(String name)
    {
        user_name = name;
    }

    public void setUserLastname(String lastname)
    {
        user_lastname = lastname;
    }

    public void setUserPassword(String password)
    {
        user_password = password;
    }

    public void setUserActive(byte active)
    {
        user_active = active;
    }
}
