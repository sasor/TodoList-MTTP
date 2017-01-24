package src.views;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
{
    private JPanel mainPanel;
    public static JPanel panelN;
    public static JPanel panelC;
    public static JPanel panelS;
    public static JPanel panelW;
    public static JPanel panelE; 
    
    private static final int WIDTH = 700;
    private static final int HEIGHT = 350;
    private static final String TITLE = "ToDoList";

    private static MainFrame instance; 
  
    public MainFrame()
    {
        createView();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE); 
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        this.setResizable(false);
    }
  
    public static MainFrame getInstance()
    {
        return instance;
    }

    private void createView()
    {
        mainPanel = new JPanel(new BorderLayout());

        panelN = new JPanel(new BorderLayout());
        panelC = new JPanel(new BorderLayout());
        panelS = new JPanel(new BorderLayout());
        panelW = new JPanel(new BorderLayout());
        panelE = new JPanel(new BorderLayout());
   
        mainPanel.add(panelN, BorderLayout.NORTH);
        mainPanel.add(panelC, BorderLayout.CENTER);
        mainPanel.add(panelS, BorderLayout.SOUTH);
        mainPanel.add(panelW, BorderLayout.WEST);
        mainPanel.add(panelE, BorderLayout.EAST);

        getContentPane().add(mainPanel);
    }

    /*public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run()
            {
                instance = new MainFrame();
                instance.setVisible(true);
            } 
        });
    }*/
}
