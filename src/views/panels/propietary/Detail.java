package src.views.panels.propietary;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import src.controllers.*;
import src.controllers.propietary.*;
import src.dao.gestion.users.*;
import src.dto.gestion.users.*;
import src.views.PropietaryJTP;

import src.views.tables.DetailTable;
import src.views.tables.models.DetailTableModel;

public class Detail extends JPanel
{
    private DetailController controller;   

    public static DetailTable detail_table;
    public static DetailTableModel table_model;

    public Detail(DetailController model)
    {
        super(new BorderLayout());
        controller = model;
        table_model = new DetailTableModel(controller.getItems());
        detail_table = new DetailTable(table_model); 
 
        JScrollPane wrapper = new JScrollPane(detail_table);
        JButton back = new JButton("BACK");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                Users model_back = new Users(); 
                UserPanel view_back = new UserPanel(model_back);
                PropietaryJTP.user_panel.removeAll();
                PropietaryJTP.user_panel.add(view_back);
                PropietaryJTP.user_panel.updateUI();
            }
        });
        this.add(wrapper);
        this.add(back, BorderLayout.SOUTH);
    }
}
