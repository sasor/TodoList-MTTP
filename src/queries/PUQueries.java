package src.queries;

public class PUQueries
{
    private static final String KEY = "_id";  
    private static final String USER_KEY = "user_id"; 
    private static final String PROFILE_KEY = "profile_id";

    private static final String TABLE = "profile_user";

    public static String create()
    {
        String fields = PROFILE_KEY+","+USER_KEY; 
        return "INSERT INTO "+TABLE+" ("+fields+") VALUES (?, ?)";
    }

    public static String read()
    {
        return "SELECT * FROM "+TABLE+" WHERE "+KEY+" =?";
    }

    public static String update()
    {
        String fields = PROFILE_KEY+"= ?, "+USER_KEY+"= ?";
        return "UPDATE "+TABLE+" SET "+fields+" WHERE "+KEY+" = ?";
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
