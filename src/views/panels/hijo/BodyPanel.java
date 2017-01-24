package src.views.panels.hijo;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import src.controllers.hijo.*;

public class BodyPanel extends JPanel
{
    private BodyController controller;

    public BodyPanel(BodyController model)
    {
        super(new BorderLayout());
        controller = model;
        JButton add = new JButton("ADD"); 
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                controller.add();
            }
        });
        this.add(add, BorderLayout.SOUTH);
    }
} 
