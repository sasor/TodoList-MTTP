package src.views.tables;

import javax.swing.*;
import src.views.tables.models.ModuleTableModel;

public class ModuleTable extends JTable
{
    public ModuleTable(ModuleTableModel model)
    {
        super(model);
    }
}
