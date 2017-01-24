package src.views.panels.propietary;

import javax.swing.table.*;
import java.util.List;
import java.util.ArrayList;
import src.dto.gestion.users.UserDTO;

public class TableModel extends AbstractTableModel
{
    private final String[] COLUMNS = {"Nickname", "Name", "Lastname"}; 
    private List<UserDTO> users;

    public TableModel()
    {
        users = new ArrayList<UserDTO>();
    }

    public TableModel(List<UserDTO> u)
    {
        users = u;
    }

    public String getColumnName(int column)
    {
        return COLUMNS[column];
    }

    public int getRowCount()
    {
        return users.size();
    }

    public int getColumnCount()
    {
        return COLUMNS.length;
    }

    public Object getValueAt(int row, int column)
    {
        UserDTO user = users.get(row);
        switch (column) {
            case 0: return user.getUserNickname();
            case 1: return user.getUserName(); 
            case 2: return user.getUserLastname();
            default: return null;
        }
    }
}
