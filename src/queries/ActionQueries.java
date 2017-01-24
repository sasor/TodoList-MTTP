package src.queries;

public class ActionQueries
{
    private static final String TABLE = "historic_actions"; 
    private static final String KEY = "_id"; 
    private static final String USER = "user_id"; 
    private static final String NICK = "user_nick"; 
    private static final String ACTION = "action"; 
    private static final String DATE = "date_action"; 
    private static final String TIME = "times"; 
    
    public static String create()
    {
        String fields = USER+","+NICK+","+ACTION+","+DATE+","+TIME; 
        return "INSERT INTO "+TABLE+" ("+fields+") VALUES (?,?,?,?,?)"; 
    }
   
    public static String read()
    {
        return "SELECT * FROM "+TABLE+" WHERE "+KEY+" =?"; 
    } 

    public static String update()
    {
        String fields=USER+"=?,"+NICK+"=?,"+ACTION+"=?,"+DATE+"=?,"+TIME+"=?"; 
        return "UPDATE "+TABLE+" SET "+fields+" WHERE "+KEY+" =?"; 
    }
   
    public static String delete()
    {
        return "DELETE FROM "+TABLE+" WHERE "+KEY+" =?";
    }

    public static String all()
    {
        return "SELECT * FROM "+TABLE;
    }
}

