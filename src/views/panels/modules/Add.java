package src.views.panels.modules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import src.controllers.AddModuleController;

public class Add extends JPanel
{
    private JTextField tf_module_name;
    private JTextArea ta_module_description;
    private JButton b_module;
    private GridBagConstraints gbc;
    private AddModuleController controller;

    public Add(AddModuleController model)
    {
        super(new GridBagLayout());
        gbc = new GridBagConstraints();
        controller = model;

        tf_module_name = new JTextField();
        tf_module_name.setPreferredSize(new Dimension(200, 20));
        ta_module_description = new JTextArea();
        ta_module_description.setPreferredSize(new Dimension(200, 100));
        b_module = new JButton("ADD");
        b_module.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                controller.entry();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(new JLabel("Name: "), gbc);

        gbc.gridy++;
        this.add(new JLabel("Description: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 0); // top, left, bottom, right
        this.add(tf_module_name, gbc);
      
        gbc.gridy++;
        this.add(ta_module_description, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 10, 0); // top, left, bottom, right
        this.add(b_module, gbc);
    }

    public String getName()
    {
        return tf_module_name.getText();
    }

    public String getDescription()
    {
        return ta_module_description.getText();
    }

    public void cleanFields()
    {
        tf_module_name.setText("");
        ta_module_description.setText("");
    }
}
