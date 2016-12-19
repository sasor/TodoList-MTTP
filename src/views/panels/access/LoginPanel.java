package src.views.panels.access;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import src.controllers.*;

public class LoginPanel extends JPanel
{
    private JTextField tfNick;
    private JPasswordField tfPwd;
    private JButton bLogin;

    private GridBagConstraints gbc;
    private LoginRegisterController controller;

    public LoginPanel(LoginRegisterController model)
    {
        super(new GridBagLayout());

        gbc = new GridBagConstraints();
        controller = model;

        tfNick = new JTextField(20);
        tfNick.setPreferredSize(new Dimension(200, 30));
        tfPwd = new JPasswordField(20);
        tfPwd.setPreferredSize(new Dimension(200, 30));
        bLogin = new JButton("Login");
        bLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                controller.checkLogin();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 10); // top, left, bottom, right
        this.add(new JLabel("Nickname: "), gbc); 
        
        gbc.gridy++;
        this.add(new JLabel("Password: "), gbc);

        gbc.gridx++;    
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0); 
        this.add(tfNick, gbc);

        gbc.gridy++;
        this.add(tfPwd, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(10, 0, 0, 0);
        this.add(bLogin, gbc);
    }

    public String getValueOfNick()
    {
        return tfNick.getText();
    }

    public String getValueOfPassword()
    {
        String pwd = new String(tfPwd.getPassword());
        return pwd;
    }
}
