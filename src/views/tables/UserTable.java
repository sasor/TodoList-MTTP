package src.views.tables;

import javax.swing.*;
import src.views.tables.models.UserTableModel;

public class UserTable extends JTable
{
    public UserTable(UserTableModel model)
    {
        super(model);
    }
}
