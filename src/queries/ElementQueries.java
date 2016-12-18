package src.queries;

public class ElementQueries
{
    private static final String KEY = "element_id";
    private static final String ROOM_KEY = "element_room_id"; 
    private static final String LEVEL = "element_level"; 
    private static final String PARENT = "have_parent"; 
    private static final String CHILD = "have_child"; 

    public static String create()
    {
        String fields = ROOM_KEY+","+LEVEL+","+PARENT+","+CHILD;
        return "INSERT INTO elements ("+fields+") VALUES (?, ?, ?, ?)";
    }

    public static String read()
    {
        return "SELECT * FROM elements WHERE "+KEY+" = ?";
    }

    public static String update()
    {
        String fields = ROOM_KEY+"=?,"+LEVEL+"=?,"+PARENT+"=?,"+CHILD+"=?";
        return "UPDATE elements SET "+fields+" WHERE "+KEY+"=?";
    }

    public static String delete()
    {
        return "DELETE FROM homes WHERE "+KEY+" = ?";
    }

    public static String all()
    {
        return "SELECT * FROM elements";
    }
}
