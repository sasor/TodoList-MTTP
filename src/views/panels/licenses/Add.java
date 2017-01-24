package src.views.panels.licenses;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import src.controllers.AddLicenseController;

public class Add extends JPanel
{
    private JTextField tf_license_action;
    private JTextArea ta_license_description; 
    private JComboBox cb_modules_belong;
    private JButton b_license; 

    private AddLicenseController controller;
    private GridBagConstraints gbc;
    
    public Add(AddLicenseController model)
    {
        super(new GridBagLayout());
        controller = model;
        gbc = new GridBagConstraints();

        tf_license_action = new JTextField();
        tf_license_action.setPreferredSize(new Dimension(200, 20));

        ta_license_description = new JTextArea();
        ta_license_description.setPreferredSize(new Dimension(200, 100));
  
        cb_modules_belong = new JComboBox(); 
        cb_modules_belong.setPreferredSize(new Dimension(200, 20));

        b_license = new JButton("ADD"); 
        b_license.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                String a = tf_license_action.getText();
                String b = ta_license_description.getText();
                if (!(a.isEmpty()) && !(b.isEmpty())) {
                    controller.entry();
                } else {
                    JOptionPane.showMessageDialog(null, "DAMM Empty Fields");
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(new JLabel("Action: "), gbc);
 
        gbc.gridy++;
        this.add(new JLabel("Module: "), gbc);

        gbc.gridy++;
        this.add(new JLabel("Description: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 0);
        this.add(tf_license_action, gbc);

        gbc.gridy++;
        this.add(cb_modules_belong, gbc);

        gbc.gridy++;
        this.add(ta_license_description, gbc);

        gbc.gridy++;
        this.add(b_license, gbc);
    }

    public String getAction()
    {
        return tf_license_action.getText();
    }
   
    public String getDescription()
    {
        return ta_license_description.getText();
    }

    public String getModule()
    {
        return String.valueOf(cb_modules_belong.getSelectedItem());
    }

    public JComboBox getModuleCB()
    {
        return cb_modules_belong;
    }
}
