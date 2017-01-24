package src.views.tables;

import javax.swing.*;
import src.views.tables.models.ProfileTableModel;

public class ProfileTable extends JTable
{
    public ProfileTable(ProfileTableModel model)
    {
        super(model);
    }
}
