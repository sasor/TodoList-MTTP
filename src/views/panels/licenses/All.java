package src.views.panels.licenses;

import javax.swing.*;
import java.awt.*;

import src.views.tables.LicenseTable;
import src.views.tables.models.LicenseTableModel;

import src.controllers.AllLicenseController;

public class All extends JPanel
{
    public static LicenseTable license_table;
    public static LicenseTableModel table_model; 
 
    private AllLicenseController controller;

    public All(AllLicenseController model)
    {
        super(new BorderLayout());
        controller = model;
        table_model = new LicenseTableModel(controller.getData());
        license_table = new LicenseTable(table_model);

        JScrollPane wrapper = new JScrollPane(license_table);
        this.add(wrapper);
    }
}
