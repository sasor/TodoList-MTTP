package src.views.panels.propietary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import src.controllers.propietary.ProfileController;

public class HomeAdd extends JPanel
{
    private JTextField name;
    private JTextField place;
    private JButton add;
    private JRadioButton cancel;

    private ProfileController controller;
    private GridBagConstraints gbc;

    public HomeAdd(ProfileController model)
    {
        super(new GridBagLayout());
        gbc = new GridBagConstraints();
        controller = model;

        name = new JTextField();        
        name.setPreferredSize(new Dimension(150,25));
        place = new JTextField();
        place.setPreferredSize(new Dimension(300,25));
        add = new JButton("ADD");
        add.setPreferredSize(new Dimension(80,25));
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if (!(isEmptyFields())) {
                    controller.addHome(name.getText(),place.getText());
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
        this.add(new JLabel("Home Name"), gbc);
        gbc.gridx++;
        this.add(new JLabel("Home Place"), gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        this.add(name, gbc);
        gbc.gridx++;
        this.add(place, gbc);
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
}
