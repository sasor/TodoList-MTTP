package src.views.panels.users;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import src.controllers.*;
import src.dao.gestion.users.*;
import src.dto.gestion.users.*;

public class Update extends JPanel
{
    private JTextField tfNick;
    private JTextField tfName;
    private JTextField tfLastname;
    private JPasswordField tfPwd;
    private JComboBox cbProfiles;
    private JRadioButton yes;
    private JRadioButton no;
    private ButtonGroup actives;

    private JButton bUpdate; 
    private GridBagConstraints gbc;

    private UpdateUserController controller;

    public Update(UpdateUserController model)
    {
        super(new GridBagLayout());
        controller = model;
        gbc = new GridBagConstraints();

        JPanel wra = new JPanel();
        actives = new ButtonGroup();
        yes = new JRadioButton("Yes");
        no = new JRadioButton("No");
        actives.add(yes);
        actives.add(no);
        wra.add(yes);
        wra.add(no);
        wra.setBorder(BorderFactory.createTitledBorder("Active"));

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

        bUpdate = new JButton("UPDATE");
        bUpdate.addActionListener(new ActionListener() {
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
        this.add(wra, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(20, 0, 0, 0);
        this.add(bUpdate, gbc); 
    
        bindDataView();
    }

    public void bindDataView()
    {
        UserDTO user = controller.getData();        
        tfNick.setText(user.getUserNickname());        
        tfName.setText(user.getUserName());
        tfLastname.setText(user.getUserLastname());
        tfPwd.setText(user.getUserPassword());
        byte isActive = user.getUserActive();
        if (isActive == 1) {
            yes.setSelected(true);
        } else {
            no.setSelected(true);
        }
    }

    public void setProfileUser()
    {
        ProfileUserDTO pu = controller.getProfileBelong();
        ProfileDTO p = controller.getProfile(pu.getProfileId());

        for (int i=0; i<cbProfiles.getItemCount();i++) {
            if (p.getProfileName().equals((String)cbProfiles.getItemAt(i))) {
                cbProfiles.setSelectedIndex(i);
            } 
        }
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

    public JComboBox getProfileCB()
    {
        return cbProfiles;
    } 

    public String getSelectedProfile()
    {
        return (String) cbProfiles.getSelectedItem();
    }

    public byte getValueOfActive()
    {
        if(yes.isSelected()) {
            return (byte) 1;
        } else {
            return (byte) 0;
        }
    }
}
