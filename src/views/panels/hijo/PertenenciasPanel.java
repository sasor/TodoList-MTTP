package src.views.panels.hijo;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import src.controllers.hijo.*;
import src.dao.gestion.inventary.*;
import src.dto.gestion.inventary.*;

public class PertenenciasPanel extends JPanel
{
    private static PertenenciasPanel instance;
    private JPanel secondary_panel;
    private GridBagConstraints gbc;

    private InventaryController controller;

    public PertenenciasPanel(InventaryController model)
    {
        super(new BorderLayout());
        instance = this;
        controller = model;

        secondary_panel = new JPanel(new GridBagLayout());
        JButton btn_update = new JButton("UPDATE");
        btn_update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                instance.secondary_panel.removeAll();
                instance.paintHomes();
                instance.secondary_panel.updateUI();
            }
        });

        this.add(secondary_panel, BorderLayout.CENTER);
        this.add(btn_update, BorderLayout.SOUTH);
        
        gbc = new GridBagConstraints();
        paintHomes();
    }

    public void paintHomes()
    {
        int extra = 0;
        List<HomeDTO> homes = controller.getHomes();
        if (homes.size() != 0 || homes == null) {
            for (HomeDTO h : homes) {
                JButton b = new JButton(h.getHomeName()); 
                b.setActionCommand(String.valueOf(h.getHomeId()));
                b.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        JButton btn = (JButton) e.getSource();
                        Integer id = Integer.parseInt(btn.getActionCommand());
                        new HomeController(id);
                    }
                });
                 
                /*if (extra != 0 && (extra%5) == 0) {
                    gbc.gridy++;
                    gbc.gridx = 0;
                    secondary_panel.add(b, gbc); 
                } else {
                    secondary_panel.add(b, gbc); 
                    gbc.gridx++;
                }
                extra++;*/
                gbc.gridx++;
                gbc.gridy = 0;
                secondary_panel.add(b, gbc);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No Homes");
        }
    }
}
