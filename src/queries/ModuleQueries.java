package src.queries;

public class ModuleQueries
{
    private static final String KEY = "module_id";
    private static final String NAME = "module_name"; 
    private static final String DESC = "module_description";

    public static String create()
    {
        String fields = NAME+","+DESC;
        return "INSERT INTO modules ("+fields+") VALUES (?,?)";
    }
  
    public static String read()
    {
        return "SELECT * FROM modules WHERE "+KEY+" = ?";
    }

    public static String update()
    {
        String fields = NAME+" = ?, "+DESC+" = ?";
        return "UPDATE modules SET "+fields+" WHERE "+KEY+" = ?";
    }
   
    public static String delete()
    {
        return "DELETE FROM modules WHERE "+KEY+" = ?";
    }
   
    public static String all()
    {
        return "SELECT * FROM modules";
    }
}
