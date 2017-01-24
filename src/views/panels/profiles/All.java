package src.views.panels.profiles;

import javax.swing.*;
import java.awt.*;

import src.views.tables.ProfileTable;
import src.views.tables.models.ProfileTableModel;

import src.controllers.AllProfileController;

public class All extends JPanel
{
    public static ProfileTable profile_table;
    public static ProfileTableModel table_model;

    private AllProfileController controller; 

    public All(AllProfileController model)
    {
        super(new BorderLayout());
        controller = model;
        table_model = new ProfileTableModel(controller.getData());
        profile_table = new ProfileTable(table_model); 

        JScrollPane wrapper = new JScrollPane(profile_table);
        this.add(wrapper);
    }
}
