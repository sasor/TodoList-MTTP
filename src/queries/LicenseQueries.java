package src.queries;

public class LicenseQueries
{
    private static final String KEY = "license_id";  
    private static final String MODULE = "module_id";
    private static final String ACTION = "license_action";
    private static final String DESC = "license_description";

    public static String create()
    {
        String fields = MODULE+","+ACTION+","+DESC;
        return "INSERT INTO licenses ("+fields+") VALUES (?,?,?)";
    }
   
    public static String read()
    {
        return "SELECT * FROM licenses WHERE "+KEY+" = ?"; 
    }
  
    public static String update()
    {
        String fields = MODULE+" = ?, "+ACTION+" = ?, "+DESC+" = ?";
        return "UPDATE licenses SET "+fields+" WHERE "+KEY+" = ?";
    }
   
    public static String delete()
    {
        return "DELETE FROM licenses WHERE "+KEY+" = ?";
    }
 
    public static String all()
    {
        return "SELECT * FROM licenses";
    }
} 
