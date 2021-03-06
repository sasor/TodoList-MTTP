package src.views.panels.users;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import java.util.List;
import src.controllers.AddUserController;

public class Add extends JPanel
{
    private JTextField tfNick;
    private JTextField tfName;
    private JTextField tfLastname;
    private JPasswordField tfPwd;
    private JComboBox cbProfiles;
    private JButton bSignUp; 

    private GridBagConstraints gbc;
    private AddUserController controller;

    public Add(AddUserController model)
    {
        super(new GridBagLayout());
        controller = model; 
        gbc = new GridBagConstraints();

        tfNick = new JTextField();
        tfNick.setPreferredSize(new Dimension(200, 20));

        tfName = new JTextField();
        tfName.setPreferredSize(new Dimension(200, 20));

        tfLastname = new JTextField();
        tfLastname.setPreferredSize(new Dimension(200, 20));

        tfPwd = new JPasswordField();
        tfPwd.setPreferredSize(new Dimension(200, 20));
     
        cbProfiles = new JComboBox();
        cbProfiles.setPreferredSize(new Dimension(200, 20));

        bSignUp = new JButton("ADD");
        bSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                controller.entry();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(5, 0, 5, 10);
        this.add(new JLabel("Nickname: "), gbc);
        
        gbc.gridy++;
        this.add(new JLabel("Name: "), gbc);

        gbc.gridy++;
        this.add(new JLabel("Lastname: "), gbc);

        gbc.gridy++;
        this.add(new JLabel("Password: "), gbc);

        gbc.gridy++;
        this.add(new JLabel("Profiles: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 10, 5, 0);
        this.add(tfNick, gbc);

        gbc.gridy++;
        this.add(tfName, gbc);

        gbc.gridy++;
        this.add(tfLastname, gbc);
 
        gbc.gridy++;
        this.add(tfPwd, gbc);
   
        gbc.gridy++;
        this.add(cbProfiles, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(20, 0, 0, 0);
        this.add(bSignUp, gbc); 
    }

    public String getValueOfNick()
    {
        return tfNick.getText();
    }

    public String getValueOfName()
    {
        return tfName.getText();
    }

    public String getValueOfLastname()
    {
        return tfLastname.getText();
    }

    public String getValueOfPassword()
    {
        String pwd = new String(tfPwd.getPassword());
        return pwd;
    }

    public String getSelectedProfile()
    {
        return (String) cbProfiles.getSelectedItem();
    }
   
    public JComboBox getProfileCB()
    {
        return cbProfiles;
    } 

    public void setValueOfNick()
    {
        tfNick.setText("");
    }

    public void setValueOfName()
    {
        tfName.setText("");
    }

    public void setValueOfLastname()
    {
        tfLastname.setText("");
    }

    public void setValueOfPassword()
    {
        tfPwd.setText(""); 
    }
}
