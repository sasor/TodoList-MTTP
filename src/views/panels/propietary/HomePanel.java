package src.views.panels.propietary;

import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import src.controllers.*;
import src.controllers.propietary.*;
import src.dto.gestion.inventary.*;
import src.views.*;

/* Todo Home tiene rooms, method rePaint() hace consulta 
   a la DB para traer los rooms que belongs a this HOME */
public class HomePanel extends JPanel
{
    public static JPanel main_panel;
    private JButton back;
    private GridBagConstraints gbc;

    private Home controller;

    public HomePanel(Home model)
    {
        super(new BorderLayout());
        controller = model;
        gbc = new GridBagConstraints();
        main_panel = new JPanel(new GridBagLayout());
        back = new JButton("BACK"); 
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                Inventary model_back = new Inventary();
                InventaryPanel view_back = new InventaryPanel(model_back);
                PropietaryJTP.inventary_panel.removeAll();
                PropietaryJTP.inventary_panel.add(view_back);
                PropietaryJTP.inventary_panel.updateUI();
            }
        });
        rePaint();

        this.add(main_panel, BorderLayout.CENTER);
        this.add(back, BorderLayout.SOUTH);
    }

    public void rePaint()
    {
        List<RoomDTO> rooms = controller.getData();
        if (rooms.size() != 0 || rooms == null) {
            gbc.gridx = 0;
            gbc.gridy = 0;
            for (RoomDTO room : rooms) {
                JButton btn = new JButton(room.getRoomName());
                //JButton btn = new JButton("Room");
                String id_room = ""+room.getRoomId();
                btn.setActionCommand(id_room);
                btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        JButton btn_selected = (JButton) e.getSource();
                        Integer id = Integer.parseInt(btn_selected.getActionCommand());
                        controller.entryRoom(id);
                    }
                });
                gbc.gridx++;
                gbc.gridy = 0;
                main_panel.add(btn, gbc);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No rooms");
        }
    }
}
