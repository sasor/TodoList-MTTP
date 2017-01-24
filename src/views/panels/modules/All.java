package src.views.panels.modules;

import javax.swing.*;
import java.awt.*;

import src.views.tables.ModuleTable;
import src.views.tables.models.ModuleTableModel;

import src.controllers.AllModuleController;

public class All extends JPanel
{
    public static ModuleTable module_table;
    public static ModuleTableModel table_model;

    private AllModuleController controller;

    public All(AllModuleController model)
    {
        super(new BorderLayout());
        controller = model;

        table_model = new ModuleTableModel(controller.getData());
        module_table = new ModuleTable(table_model); 
 
        JScrollPane wrapper = new JScrollPane(module_table);
        this.add(wrapper);
    }
}
