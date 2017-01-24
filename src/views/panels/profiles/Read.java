package src.views.panels.profiles;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import src.controllers.ReadProfileController;
import src.dao.gestion.users.*;
import src.dto.gestion.users.*;

public class Read extends JPanel
{
    private JTextField tf_profile_name;
    private JRadioButton yes;
    private JRadioButton no;
    private JButton b_profile;

    private ButtonGroup actions;
    private GridBagConstraints gbc;
    private ReadProfileController controller;

    public Read(ReadProfileController model)
    {
        super(new GridBagLayout());
        gbc = new GridBagConstraints();
        controller = model;
        actions = new ButtonGroup();

        tf_profile_name = new JTextField();
        tf_profile_name.setPreferredSize(new Dimension(200, 20));

        yes = new JRadioButton("Yes");
        no = new JRadioButton("No");

        actions.add(yes);
        actions.add(no);

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
        
        bindData();
        disableFields();
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

    public void disableFields()
    {
        tf_profile_name.setEditable(false);
        yes.setEnabled(false);
        no.setEnabled(false);
    }
}
