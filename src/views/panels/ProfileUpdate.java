package src.views.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import src.dto.gestion.users.UserDTO;
import src.controllers.admin.ProfileController;

public class ProfileUpdate extends JPanel
{
    private JTextField nick;
    private JTextField name;
    private JTextField last;
    private JTextField pwd;
    private JButton update;
    private JRadioButton cancel;

    private GridBagConstraints gbc;
    private ProfileController controller;

    public ProfileUpdate(ProfileController model)
    {
        super(new GridBagLayout());
        gbc = new GridBagConstraints();
        controller = model;

        nick = new JTextField();        
        nick.setPreferredSize(new Dimension(120,25));
        name = new JTextField();        
        name.setPreferredSize(new Dimension(120,25));
        last = new JTextField();        
        last.setPreferredSize(new Dimension(120,25));
        pwd = new JPasswordField();        
        pwd.setPreferredSize(new Dimension(120,25));
        update = new JButton("UPDATE");
        update.setPreferredSize(new Dimension(90,20));
        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if (!(isEmptyFields())) {
                    controller.updateUser(nick.getText(),name.getText(),last.getText(),pwd.getText());
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
        this.add(new JLabel("Nickname"), gbc);
        gbc.gridx++;
        this.add(new JLabel("Name"), gbc);
        gbc.gridx++;
        this.add(new JLabel("Lastname"), gbc);
        gbc.gridx++;
        this.add(new JLabel("Password"), gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        this.add(nick, gbc);
        gbc.gridx++;
        this.add(name, gbc);
        gbc.gridx++;
        this.add(last, gbc);
        gbc.gridx++;
        this.add(pwd, gbc);
        gbc.gridx++;
        this.add(update, gbc);
        gbc.gridx++;
        this.add(cancel, gbc);

        bindData();
    }

    private boolean isEmptyFields()
    {
        boolean response = false;
        String ni = nick.getText();
        String n = name.getText();
        String l = last.getText();
        String p = pwd.getText();
        if (ni.isEmpty() || n.isEmpty() || l.isEmpty() || p.isEmpty()) {
            return true;
        }
        return response;
    }

    public void bindData()
    {
        UserDTO user = controller.getUser();
        nick.setText(user.getUserNickname()); 
        name.setText(user.getUserName()); 
        last.setText(user.getUserLastname()); 
        pwd.setText(user.getUserPassword()); 
    }
}
