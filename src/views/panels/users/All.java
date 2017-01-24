package src.views.panels.users;

import javax.swing.*;
import java.awt.*;

import src.views.tables.UserTable;
import src.views.tables.models.UserTableModel;

import src.controllers.AllUserController;

public class All extends JPanel
{
    public static UserTable user_table;
    public static UserTableModel table_model;

    private AllUserController controller;

    public All(AllUserController model)
    {
        super(new BorderLayout());
        controller = model; 
        table_model = new UserTableModel(controller.getData());
        user_table = new UserTable(table_model); 
 
        JScrollPane wrapper = new JScrollPane(user_table);
        this.add(wrapper, BorderLayout.WEST);
    }

    public boolean have_been_seletedRow()
    {
        boolean response = false;
        if (user_table.getSelectedRow() != -1) {
            response = true;
        }
        return response;
    }
}
