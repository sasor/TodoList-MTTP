package src.views.panels.admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import src.controllers.admin.*;
import src.dao.gestion.inventary.*;
import src.dto.gestion.inventary.*;
import src.views.AdminJTP;

public class HomePanel extends JPanel
{
    private static HomePanel instance;
    private JPanel panel;
    private GridBagConstraints gbc;
    private HomeController controller;

    public HomePanel(HomeController model)
    {
        super(new BorderLayout());
        instance = this;
        controller = model;

        panel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();

        JButton back = new JButton("BACK");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                InventaryController model = new InventaryController(); 
                InventaryPanel view = new InventaryPanel(model); 
                AdminJTP.inventaryP.removeAll();
                AdminJTP.inventaryP.add(view);
                AdminJTP.inventaryP.updateUI();
            }
        });

        this.add(panel, BorderLayout.CENTER);
        this.add(back, BorderLayout.SOUTH);

        paintRooms();
    }

    public void paintRooms()
    {
        List<RoomDTO> rooms = controller.getRooms();
        if (rooms.size() != 0 || rooms == null) {
            for (RoomDTO r : rooms) {
                JButton b = new JButton(r.getRoomName()); 
                b.setActionCommand(String.valueOf(r.getRoomId()));
                b.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        JButton btn = (JButton) e.getSource();
                        Integer id = Integer.parseInt(btn.getActionCommand());
                        //new RoomController(id, controller.getHomeID());
                        new RoomController(id);
                    }
                });
                 
                gbc.gridx++;
                gbc.gridy = 0;
                panel.add(b, gbc); 
            }
        } else {
            JOptionPane.showMessageDialog(null, "No Rooms");
        }
    }
}
