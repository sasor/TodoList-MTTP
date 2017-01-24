package src.views.panels.propietary;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import src.controllers.propietary.ProfileController;
import src.dao.gestion.inventary.*;
import src.dto.gestion.inventary.*;

public class RoomAdd extends JPanel
{
    private JTextField name;
    private JComboBox homes;
    private JTextField place;
    private JButton add;
    private JRadioButton cancel;
    
    private ProfileController controller;
    private GridBagConstraints gbc;

    public RoomAdd(ProfileController model)
    {
        super(new GridBagLayout());
        gbc = new GridBagConstraints();
        controller = model;

        name = new JTextField();
        name.setPreferredSize(new Dimension(140,25));
        place = new JTextField();
        place.setPreferredSize(new Dimension(250,25));
        homes = new JComboBox();
        homes.setPreferredSize(new Dimension(120,25));
        loadHomes();    

        add = new JButton("ADD");
        add.setPreferredSize(new Dimension(60,25));
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if (!(isEmptyFields())) {
                    String n = name.getText();
                    String p = place.getText();
                    String h = String.valueOf(homes.getSelectedItem());
                    controller.addRoom(n, p, h);
                } else {
                    JOptionPane.showMessageDialog(null, "DAMM Fields Empty");
                }
            }
        });
        cancel = new JRadioButton("C");
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                ProfilePanel.instance.remove(ProfilePanel.bottom_panel);
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(new JLabel("Room Name"), gbc);
        gbc.gridx++;
        this.add(new JLabel("Room Place"), gbc);
        gbc.gridx++;
        this.add(new JLabel("Home Belong"), gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        this.add(name, gbc);
        gbc.gridx++;
        this.add(place, gbc);
        gbc.gridx++;
        this.add(homes, gbc);
        gbc.gridx++;
        this.add(add, gbc);
        gbc.gridx++;
        this.add(cancel, gbc);
    }

    private boolean isEmptyFields()
    {
        boolean response = false;
        String n = name.getText();
        String p = place.getText();
        if (n.isEmpty() || p.isEmpty()) {
            return true;
        }
        return response;
    }

    public void loadHomes()
    {
        List<HomeDTO> data = controller.getHomes();
        if (data != null) {
            int length = data.size();
            for (int index = 0; index < length; index++) {
                HomeDTO current = data.get(index);
                homes.addItem(current.getHomeName());
            }
        }
    }
}
