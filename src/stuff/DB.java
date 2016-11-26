import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB
{
    private static DB instance;
    private Connection db; 
    private final String URL = "jdbc:mysql://127.0.0.1/mttp";
    private final String USERNAME = "root";
    private final String PASSWORD = "undici";

    private DB()
    {
        try {
            db = DriverManager.getConnection(URL, USERNAME, PASSWORD); 
        } catch (SQLException e) {
            System.out.println("Error connect to DB");
        } catch (Exception e) {
            System.out.println("Error connect to DB Exception block");
        }
    }

    public static synchronized DB instance()
    {
        if (instance == null) {
            instance = new DB();
        }
        return instance;
    }

    public Connection open()
    {
        return db;
    }

    public void close()
    {
        instance = null;
    }
}
