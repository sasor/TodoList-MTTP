package src.views.tables;

import javax.swing.*;
import src.views.tables.models.DetailTableModel;

public class DetailTable extends JTable
{
    public DetailTable(DetailTableModel model)
    {
        super(model);
    }
}
