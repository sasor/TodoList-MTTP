package src.queries;

public class MPQueries
{
    private static final String KEY = "_id";
    private static final String MODULE_KEY = "module_id";
    private static final String PROFILE_KEY = "profile_id";

    private static final String TABLE = "module_profile";

    public static String create()
    {
        String fields = PROFILE_KEY+","+MODULE_KEY;
        return "INSERT INTO "+TABLE+" ("+fields+") VALUES (?, ?)";
    }

    public static String read()
    {
        return "SELECT * FROM "+TABLE+" WHERE "+KEY+"= ?";
    }

    public static String update()
    {
        String fields = PROFILE_KEY+"= ?, "+MODULE_KEY+"= ?";
        return "UPDATE "+TABLE+" SET "+fields+" WHERE "+KEY+"= ?";
    }

    public static String delete()
    {
        return "DELETE FROM "+TABLE+" WHERE "+KEY+"= ?";
    }

    public static String all()
    {
        return "SELECT * FROM "+TABLE; 
    }  
}
