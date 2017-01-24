package src.views.panels.modules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

import src.controllers.ModulePanelController;

public class ModuleSidebarPanel extends JPanel
{
    private JRadioButton rB_all;
    private JRadioButton rB_add;
    private JRadioButton rB_update;
    private JRadioButton rB_delete;
    private JRadioButton rB_read;

    private ButtonGroup actions;
    private GridBagConstraints gbc;

    private ModulePanelController controller;

    public ModuleSidebarPanel(ModulePanelController model)
    {
        super(new GridBagLayout());
        controller = model;
        this.setBorder(BorderFactory.createTitledBorder("Operations"));

        gbc = new GridBagConstraints();
        actions = new ButtonGroup();
   
        rB_all = new JRadioButton("ALL");
        rB_all.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                controller.all();
            }
        });

        rB_add = new JRadioButton("ADD");
        rB_add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                controller.add();
            }
        });

        rB_update = new JRadioButton("UPDATE");
        rB_update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                controller.update();
            } 
        });

        rB_delete = new JRadioButton("DELETE");
        rB_delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                controller.delete();
            } 
        });

        rB_read = new JRadioButton("READ");
        rB_read.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                controller.read();
            }
        });

        actions.add(rB_all);
        actions.add(rB_add);
        actions.add(rB_update);
        actions.add(rB_delete);
        actions.add(rB_read);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        //gbc.insets = new Insets(15, 10, 15, 0); // top,left,bottom,right
        this.add(rB_all, gbc);

        gbc.gridy++;
        this.add(rB_add, gbc);

        gbc.gridy++;
        this.add(rB_update, gbc);

        gbc.gridy++;
        this.add(rB_delete, gbc);

        gbc.gridy++;
        this.add(rB_read, gbc);
    }
}
