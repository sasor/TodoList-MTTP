package src.dto.gestion.users;

import java.util.Date;

public class ActionDTO
{
    private Integer _id;
    private Integer user_id;
    private String user_nick;
    private String action;
    private Date date; 
    private String times;

    public ActionDTO()
    {}

    public ActionDTO(Integer user,String nick,String use,Date fecha,String t)
    {
        user_id = user;
        user_nick = nick;
        action = use;
        date = fecha;
        times = t;
    }

    public Integer getID()
    {
        return _id;
    }

    public Integer getUserId()
    {
        return user_id;
    }

    public String getNick()
    {
        return user_nick;
    }

    public String getAction()
    {
        return action;
    }

    public Date getDate()
    {
        return date;
    }
 
    public String getTimes()
    {
        return times;
    }

    public void setTimes(String t)
    {
        times = t;
    }

    public void setID(Integer id)
    {
        _id = id;
    }

    public void setUser(Integer user)
    {
        user_id = user;
    }

    public void setUserNick(String nick)
    {
        user_nick = nick;
    }

    public void setAction(String a)
    {
        action = a;
    }

    public void setDate(Date fecha)
    {
        date = fecha;
    }
}
