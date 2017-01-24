package src.stuff;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB
{
    private static DB instance;
    private Connection db; 
    private final String URL = "jdbc:mysql://127.0.0.1/mttp";
    private final String USERNAME = "mttp";
    private final String PASSWORD = "mttp";

    private DB()
    {
        try {
            db = DriverManager.getConnection(URL, USERNAME, PASSWORD); 
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error DataBase Connected");
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized DB instance()
    {
        if (instance == null) {
            instance = new DB();
        }
        return instance;
    }

    public Connection openConnection()
    {
        return db;
    }

    public void disconnect()
    {
        if (db != null) {
            try {
                db.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                instance = null;
            }
        }
    }
}
