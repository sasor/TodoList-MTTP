package src.queries;

public class DataQueries
{
    private static final String TABLE = "data";
    private static final String KEY = "data_id";
    private static final String KEY_P = "data_place_id";
    private static final String KEY_E = "data_element_id";
    private static final String NAME = "data_name";
    private static final String DESCRIPTION = "data_description";
    private static final String STORAGE = "data_storage";
    private static final String EXPIRATION = "data_expiration";

    public static String create()
    {
        String fields = "";
        fields = KEY_P+","+KEY_E+","+NAME+","+DESCRIPTION+","+STORAGE+","+EXPIRATION; 
        return "INSERT INTO "+TABLE+" ("+fields+") VALUES (?,?,?,?,?,?)";
    }

    public static String read()
    {
        return "SELECT * FROM "+TABLE+" WHERE "+KEY+" =?";
    }

    public static String readbyElement()
    {
        return "SELECT * FROM "+TABLE+" WHERE data_element_id =?";
    }

    public static String update()
    {
        String fields = KEY_P+" =?,"+KEY_E+" =?,"+NAME+" =?,"+DESCRIPTION+" =?, "+STORAGE+" =?,"+EXPIRATION+" =?"; 
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
