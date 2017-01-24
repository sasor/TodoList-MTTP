package src.views.panels.propietary;

import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import src.controllers.propietary.*;
import src.dto.gestion.inventary.*;

public class InventaryPanel extends JPanel
{
    private Inventary controller;
    private JPanel secondary_panel;
    private GridBagConstraints gbc;

    private static InventaryPanel instance;

    public InventaryPanel()
    {
        super();
        instance = this; 
    }

    public InventaryPanel(Inventary model)
    {
        super(new BorderLayout());
        instance = this;
        secondary_panel = new JPanel(new GridBagLayout());
        JButton btn_update = new JButton("UPDATE");
        btn_update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                controller.populateData();
                rePainter(); 
                secondary_panel.updateUI();
                instance.updateUI(); 
                JOptionPane.showMessageDialog(null, "UPDATED");
            }
        });

        this.add(secondary_panel, BorderLayout.CENTER);
        this.add(btn_update, BorderLayout.SOUTH);
        
        //super(new GridBagLayout());
        gbc = new GridBagConstraints();
        controller = model;
        rePainter();
    }

    public void rePainter()
    {
        int extra = 0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        List<HomeDTO> homes = controller.getData();
        if (homes.size() != 0 || homes == null) {
            for (HomeDTO home : homes) {
                JButton btn = new JButton(home.getHomeName());
                /* lo siguente va a guardar en Id del Home*/
                String id_home = ""+home.getHomeId();
                btn.setActionCommand(id_home);
                btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        JButton btn_selected = (JButton) e.getSource();
                        Integer id = Integer.parseInt(btn_selected.getActionCommand());
                        controller.entryHome(id);
                    }
                });
                /* Add a button to panel */
                /*if (extra != 0 && (extra%5) == 0) {
                    gbc.gridy++;
                    gbc.gridx = 0;
                    secondary_panel.add(btn, gbc); 
                } else {
                    secondary_panel.add(btn, gbc); 
                    gbc.gridx++;
                }
                extra++;*/
                gbc.gridx++;
                gbc.gridy = 0;
                secondary_panel.add(btn, gbc);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Sorry no Data");
        }
    }
}
