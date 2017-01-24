package src.views.panels.profiles;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;
import src.controllers.UpdateProfileController;
import src.dao.gestion.users.ProfileIDAO; 
import src.dto.gestion.users.ProfileDTO; 

public class Update extends JPanel
{
    private JTextField tf_profile_name;
    private JRadioButton yes;
    private JRadioButton no;
    private JButton b_profile;

    private ButtonGroup actions;
    private GridBagConstraints gbc;
    private UpdateProfileController controller;

    public Update(UpdateProfileController model)
    {
        super(new GridBagLayout());
        gbc = new GridBagConstraints();
        controller = model;
        actions = new ButtonGroup();

        tf_profile_name = new JTextField();
        tf_profile_name.setEditable(false);
        tf_profile_name.setPreferredSize(new Dimension(200, 20));

        yes = new JRadioButton("Yes");
        no = new JRadioButton("No");

        actions.add(yes);
        actions.add(no);
        b_profile = new JButton("UPDATE");
        b_profile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                String a = tf_profile_name.getText();
                if (!(a.isEmpty())) {
                    controller.entry();
                } else {
                    JOptionPane.showMessageDialog(null, "DAMM Empty Fields");
                }
            }
        });

        /*Lo siguente guarda los radiobuttons en un panel*/
        JPanel rb_panel = new JPanel();
        rb_panel.add(yes);
        rb_panel.add(no);
        rb_panel.setBorder(BorderFactory.createTitledBorder("Active"));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 0); // top, left, bottom, right
        this.add(tf_profile_name, gbc);

        gbc.gridy++; 
        gbc.insets = new Insets(0, 0, 0, 0); // top, left, bottom, right
        this.add(rb_panel, gbc);
   
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 10, 0); // top, left, bottom, right
        this.add(b_profile, gbc);
       
        bindData();
    }

    public void bindData()
    {
        ProfileDTO obj = controller.getData();
        tf_profile_name.setText(obj.getProfileName());
        byte isActive = obj.getProfileActive();
        if (isActive == 1) {
            yes.setSelected(true);
        } else {
            no.setSelected(true);
        }
    }

    public String getName()
    {
        return tf_profile_name.getText();
    }

    public byte getActive()
    {
        if(yes.isSelected()) {
            return (byte) 1;
        } else {
            return (byte) 0;
        }
    }
}
