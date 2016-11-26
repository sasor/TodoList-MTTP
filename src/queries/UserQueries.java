package src.queries;

public class UserQueries
{
    private static final String KEY = "user_id";
    private static final String NICK = "user_nickname";
    private static final String NAME = "user_name";
    private static final String LASTNAME = "user_lastname";
    private static final String PASSWORD = "user_password";
    private static final String ACTIVE = "user_active";

    public static String create()
    {
        String fields = NICK +","+ NAME +","+ LASTNAME +","+ PASSWORD;
        String query = "";
        query = "INSERT INTO users ("+ fields +") VALUES (?, ?, ?, ?)";
        return query;
    }

    public static String read()
    {
        return "SELECT * FROM users WHERE user_id = ?";
    }
  
    public static String update()
    {
        String fields = "";
        fields = NICK+" = ?, "+NAME+" = ?, "+LASTNAME+" = ?, "+PASSWORD+" = ?";
        return "UPDATE users SET "+ fields + " WHERE user_id = ?";
    }

    public static String update_active()
    {
        return "UPDATE users SET user_active = ? WHERE user_id = ?";
    }

    public static String delete()
    {
        return "DELETE FROM users WHERE user_id = ?";
    }

    public static String all()
    {
        return "SELECT * FROM users";
    }
}
