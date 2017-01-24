package src.views.tables;

import javax.swing.*;
import src.views.tables.models.LicenseTableModel;

public class LicenseTable extends JTable
{
    public LicenseTable(LicenseTableModel model)
    {
        super(model);
    }
}
