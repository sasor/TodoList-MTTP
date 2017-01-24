package src.views.panels.propietary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import src.controllers.propietary.Users;

public class UserPanel extends JPanel
{
    public static TableModel table_model;
    public static JTable table_users;
    private JButton bottom_click;

    private Users controller;

    public UserPanel(Users model)
    {
        super(new BorderLayout());
        controller = model;

        table_model = new TableModel(controller.getData());
        table_users = new JTable(table_model);
        JScrollPane wrapper = new JScrollPane(table_users);
   
        JPanel operations_panel = new JPanel(); 
        bottom_click = new JButton("READ");
        bottom_click.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if (table_users.getSelectedRow() != -1) {
                    //new UpdateUserController();
                    controller.doAction();
                } else {
                    JOptionPane.showMessageDialog(null, "DAMM No Row Selected");
                }
            }
        });
        operations_panel.add(bottom_click);

        this.add(wrapper, BorderLayout.CENTER);
        this.add(operations_panel, BorderLayout.SOUTH);
    }
}
