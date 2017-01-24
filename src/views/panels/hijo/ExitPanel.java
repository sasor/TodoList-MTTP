package src.views.panels.hijo;

import java.text.SimpleDateFormat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import src.controllers.MainController;

public class ExitPanel extends JPanel
{
    private JRadioButton out;
    private JRadioButton signout;
    private GridBagConstraints gbc;

    public ExitPanel()
    {
        super(new GridBagLayout());
        this.setBorder(BorderFactory.createTitledBorder("Actions"));
        gbc = new GridBagConstraints();

        ButtonGroup actions = new ButtonGroup();
        out = new JRadioButton("Exit");
        out.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                MainController.inform.action.setAction("EXIT-SIGN-OUT");
                java.util.Date d = new java.util.Date();
                SimpleDateFormat f = new SimpleDateFormat("hh:mm:ss a"); 
                MainController.inform.action.setDate(d);
                MainController.inform.action.setTimes(f.format(d));
                MainController.inform.instance.registAction();
                System.exit(1);
            }
        });
        actions.add(out);
        
        gbc.anchor = GridBagConstraints.LINE_START;
        this.add(out, gbc);
    }
}
