package src.queries;

public class PlaceQueries
{
    private static final String KEY = "place_id";
    private static final String DATA = "place_object"; 

    public static String create()
    {
        return "INSERT INTO places ("+DATA+") VALUES (?)";
    }

    public static String read()
    {
        return "SELECT * FROM places WHERE "+KEY+" = ?";
    }

    public static String update()
    {
        return "UPDATE places SET "+DATA+" = ? WHERE "+KEY+" = ?";
    }

    public static String delete()
    {
        return "DELETE FROM places WHERE "+KEY+" = ?";
    }

    public static String all()
    {
        return "SELECT * FROM places";
    }
}
