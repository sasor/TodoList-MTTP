package src.controllers;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import src.controllers.informs.Historic;
import src.views.*;
import src.stuff.DB;

public class MainController
{
    private static MainFrame main_view;
    private static LoginRegisterController access;
 
    public static MainController instance;
    public static SessionController session;
    public static Historic inform;

    public MainController()
    {
        main_view = new MainFrame(); 
        session = new SessionController();
        inform = new Historic();
        DB instance = DB.instance();
        access = new LoginRegisterController(instance);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run()
            {
                instance = new MainController();
                instance.main_view.setVisible(true);
            }
        }); 
    }
}
