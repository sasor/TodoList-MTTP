package src.views.panels.modules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import src.controllers.ReadModuleController;
import src.dto.gestion.users.ModuleDTO;

public class Read extends JPanel
{
    private JTextField tf_module_name;
    private JTextArea ta_module_description;
    private GridBagConstraints gbc;
    private ReadModuleController controller;

    public Read(ReadModuleController model)
    {
        super(new GridBagLayout());
        gbc = new GridBagConstraints();
        controller = model;

        tf_module_name = new JTextField();
        tf_module_name.setPreferredSize(new Dimension(200, 20));
        ta_module_description = new JTextArea();
        ta_module_description.setPreferredSize(new Dimension(200, 100));

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

        bindData();
        disableFields();
    }

    public void bindData()
    {
        ModuleDTO obj = controller.getData();
        tf_module_name.setText(obj.getModuleName());
        ta_module_description.setText(obj.getModuleDescription());
    }

    public void disableFields()
    {
        tf_module_name.setEditable(false);
        ta_module_description.setEditable(false);
    }
}
