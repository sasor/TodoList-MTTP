package src.views.panels.users;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import src.controllers.*;
import src.dao.gestion.users.*;
import src.dto.gestion.users.*;

import src.views.tables.DetailTable;
import src.views.tables.models.DetailTableModel;

public class Detail extends JPanel
{
    private DetailUserController controller;   

    public static DetailTable detail_table;
    public static DetailTableModel table_model;

    public Detail(DetailUserController model)
    {
        super(new BorderLayout());
        controller = model;
        table_model = new DetailTableModel(controller.getItems());
        detail_table = new DetailTable(table_model); 
 
        JScrollPane wrapper = new JScrollPane(detail_table);
        this.add(wrapper);
    }
}
