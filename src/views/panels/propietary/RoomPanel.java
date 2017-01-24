package src.views.panels.propietary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import src.controllers.propietary.*;
import src.views.*;

public class RoomPanel extends JPanel
{
    public static SideBarPanel sidebar;
    public static BodyPanel body;

    private TreeController sidebar_controller;
    private BodyController body_controller;

    private Room controller;

    public RoomPanel(Room model)
    {
        super(new BorderLayout());
        controller = model;
        sidebar_controller = new TreeController(controller.getRoomId()); 
        body_controller = new BodyController(controller.getRoomId()); 

        sidebar = new SideBarPanel(sidebar_controller);
        sidebar.setPreferredSize(new Dimension(220, 0));
        body = new BodyPanel(body_controller);

        JButton back = new JButton("BACK");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                Integer parent_id = controller.getParentId();
                if (parent_id != null) {
                    Home model_back = new Home(parent_id);
                    HomePanel view_back = new HomePanel(model_back);
                    PropietaryJTP.inventary_panel.removeAll();
                    PropietaryJTP.inventary_panel.add(view_back);
                    PropietaryJTP.inventary_panel.updateUI();
                } else {
                    JOptionPane.showMessageDialog(null, "Parent ID null");
                }
            }
        });
        
      
        this.add(sidebar, BorderLayout.WEST);
        this.add(body, BorderLayout.CENTER);
        this.add(back, BorderLayout.SOUTH);
    }
}
