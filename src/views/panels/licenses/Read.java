package src.views.panels.licenses;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import src.controllers.ReadLicenseController;
import src.dto.gestion.users.LicenseDTO;

public class Read extends JPanel
{
    private JTextField tf_license_action;
    private JTextArea ta_license_description; 
    private JComboBox cb_modules_belong;
    private ReadLicenseController controller;
    private GridBagConstraints gbc;

    public Read(ReadLicenseController model)
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
    
        bindData();
    }

    public JComboBox getModuleCB()
    {
        return cb_modules_belong;
    }

    public void bindData()
    {
        LicenseDTO license = controller.getData();
        tf_license_action.setText(license.getLicenseAction());
        ta_license_description.setText(license.getLicenseDescription());
    }

    /* Este es llamado desde el controller */
    public void setModuleInView(String module)
    {
        for (int i=0; i<cb_modules_belong.getItemCount(); i++) {
            if (module.equals((String) cb_modules_belong.getItemAt(i))) {
                cb_modules_belong.setSelectedIndex(i);
            } 
        }
    }
    
    public void disableFields()
    {
        tf_license_action.setEditable(false);
        ta_license_description.setEditable(false);
        cb_modules_belong.setEnabled(false);
    }
}
