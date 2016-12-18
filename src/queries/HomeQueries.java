package src.queries;

public class HomeQueries
{
    private static final String KEY = "home_id"; 
    private static final String HOME_NAME = "home_name";
    private static final String HOME_PLACE = "home_place_id"; 

    public static String create()
    {
        String fields = HOME_NAME+","+HOME_PLACE;
        return "INSERT INTO homes ("+fields+") VALUES (?, ?)";
    } 

    public static String read()
    {
        return "SELECT * FROM homes WHERE "+KEY+"= ?";
    }

    public static String update()
    {
        String fields = HOME_NAME+"= ?,"+HOME_PLACE+"= ?";
        return "UPDATE homes SET "+fields+" WHERE "+KEY+" = ?";
    }

    public static String delete()
    {
        return "DELETE FROM homes WHERE "+KEY+" = ?";
    }

    public static String all()
    {
        return "SELECT * FROM homes";
    } 
}
