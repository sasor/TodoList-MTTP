package src.views.panels.access;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import java.util.List;

import src.controllers.*;
import src.dto.gestion.users.ProfileDTO;

public class SignUpPanel extends JPanel
{
    private JTextField tfNick;
    private JTextField tfName;
    private JTextField tfLastname;
    private JPasswordField tfPwd;
    private JComboBox cbProfiles;
    private JButton bSignUp; 

    private GridBagConstraints gbc;
    private LoginRegisterController controller;

    public static Vector<String> profiles;

    public SignUpPanel(LoginRegisterController model)
    {
        super(new GridBagLayout());
   
        gbc = new GridBagConstraints();
        profiles = new Vector<String>();
        controller = model;

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

        bSignUp = new JButton("SignUp");
        bSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                controller.checkSignUp();
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

        //loadProfiles();
    }

    /*private void loadProfiles()
    {
        List<ProfileDTO> profiles = controller.loadProfiles();
        if (profiles != null) {
            int length = profiles.size();
            for (int index = 0; index < length; index++) {
                ProfileDTO currentProfile = profiles.get(index);
                if (currentProfile.getProfileActive() == 1) {
                    cbProfiles.addItem(currentProfile.getProfileName());
                }
            }
        }
    }*/

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
