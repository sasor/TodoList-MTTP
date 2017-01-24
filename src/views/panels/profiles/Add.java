package src.views.panels.profiles;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class Add extends JPanel
{
    private JTextField tf_profile_name;
    private JRadioButton rb_active_yes;
    private JRadioButton rb_active_no;
    private JButton b_profile;

    private ButtonGroup actions;
    private GridBagConstraints gbc;

    public Add()
    {
        super(new GridBagLayout());

        gbc = new GridBagConstraints();
        actions = new ButtonGroup();

        tf_profile_name = new JTextField();
        tf_profile_name.setPreferredSize(new Dimension(200, 20));

        rb_active_yes = new JRadioButton("Yes");
        rb_active_no = new JRadioButton("No");

        actions.add(rb_active_yes);
        actions.add(rb_active_no);
        b_profile = new JButton("ADD");

        /*Lo siguente guarda los radiobuttons en un panel*/
        JPanel rb_panel = new JPanel();
        rb_panel.add(rb_active_yes);
        rb_panel.add(rb_active_no);
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
    }
}
