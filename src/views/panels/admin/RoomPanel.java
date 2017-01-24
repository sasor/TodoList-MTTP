package src.views.panels.admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import src.controllers.admin.*;
import src.views.*;

public class RoomPanel extends JPanel
{
    public static SideBarPanel sidebar;
    public static BodyPanel body;

    private TreeController sidebar_controller;
    private BodyController body_controller;

    private RoomController controller;

    public RoomPanel(RoomController model)
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
                    HomeController model_back = new HomeController(parent_id);
                    HomePanel view_back = new HomePanel(model_back);
                    AdminJTP.inventaryP.removeAll();
                    AdminJTP.inventaryP.add(view_back);
                    AdminJTP.inventaryP.updateUI();
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
