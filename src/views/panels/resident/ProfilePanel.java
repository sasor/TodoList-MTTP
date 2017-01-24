package src.views.panels.resident;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import src.controllers.resident.ProfileController;
import src.dto.gestion.users.UserDTO;

public class ProfilePanel extends JPanel
{
    private GridBagConstraints gbc;
    private ProfileController controller;
    /* Extra panels main , side, bottom */
    public static JPanel main_panel;
    public static JPanel side_panel;
    public static JPanel bottom_panel;

    public static ProfilePanel instance;

    private JLabel nick;
    private JLabel name;
    private JLabel lastname;
    private JLabel pwd;

    public ProfilePanel(ProfileController model)
    {
        super(new BorderLayout());
        instance = this;
        controller = model;
        bottom_panel = new JPanel();

        main_panel = new JPanel(new GridBagLayout()); 
        main_panel.setBorder(BorderFactory.createTitledBorder("Profile"));
        gbc = new GridBagConstraints();
        nick = new JLabel();
        name = new JLabel();
        lastname = new JLabel();
        pwd = new JLabel();
        bindData();

        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.insets = new Insets(0,0,10,15);
        gbc.anchor = GridBagConstraints.LINE_START;
        main_panel.add(new JLabel("Nickname: "), gbc);
        gbc.gridy++; 
        main_panel.add(new JLabel("Name: "), gbc);
        gbc.gridy++; 
        main_panel.add(new JLabel("Lastname: "), gbc);
        gbc.gridy++; 
        main_panel.add(new JLabel("Password: "), gbc);

        gbc.gridx = 1; 
        gbc.gridy = 0; 
        gbc.insets = new Insets(0,15,10,0);
        gbc.anchor = GridBagConstraints.LINE_END;
        main_panel.add(nick, gbc);
        gbc.gridy++; 
        main_panel.add(name, gbc);
        gbc.gridy++; 
        main_panel.add(lastname, gbc);
        gbc.gridy++; 
        main_panel.add(pwd, gbc);
        this.add(main_panel, BorderLayout.CENTER); 

        side_panel = new JPanel(new GridBagLayout());
        side_panel.setPreferredSize(new Dimension(150, 0));
        side_panel.setBorder(BorderFactory.createTitledBorder("Operations"));
        ButtonGroup actions = new ButtonGroup();
        JRadioButton profile = new JRadioButton("Update Profile");  
        profile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                bottom_panel.removeAll();
                bottom_panel.add(new ProfileUpdate(controller));
                instance.add(bottom_panel, BorderLayout.SOUTH);
                instance.updateUI();
            }
        });

        actions.add(profile);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0,0,2,0);
        side_panel.add(profile, gbc);
        this.add(side_panel, BorderLayout.EAST);
    }

    public void bindData()
    {
        UserDTO user = controller.getUser();
        nick.setText(user.getUserNickname()); 
        name.setText(user.getUserName()); 
        lastname.setText(user.getUserLastname()); 
        pwd.setText(user.getUserPassword()); 
    }
}
