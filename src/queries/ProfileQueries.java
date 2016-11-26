package src.queries;

public class ProfileQueries
{
    private static final String KEY = "profile_id";
    private static final String NAME = "profile_name";
    private static final String ACTIVE = "profile_active";

    public static String create()
    {
        String fields = NAME;
        return "INSERT INTO profiles ("+fields+") VALUES (?)";
    }

    public static String read()
    {
        return "SELECT * FROM profiles WHERE "+KEY+" = ?";
    }
  
    public static String update()
    {
        String fields = NAME+" = ?";
        return "UPDATE profiles SET "+fields+" WHERE "+KEY+" = ?";
    }

    public static String update_active()
    {
        return "UPDATE profiles SET "+ACTIVE+" = ? WHERE "+KEY+" = ?";
    }

    public static String delete()
    {
        return "DELETE FROM profiles WHERE "+KEY+" = ?";
    }

    public static String all()
    {
        return "SELECT * FROM profiles";
    }
}
