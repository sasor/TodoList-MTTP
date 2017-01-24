package src.views.tables.models;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import src.dto.gestion.users.UserDTO;

public class UserTableModel extends AbstractTableModel
{
    private List<UserDTO> users;
    private final String[] COLUMNS = {"Nickname", "Name", "Lastname", "Active"};
    
    public UserTableModel(List<UserDTO> users)
    {
        this.users = users;
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
            case 3: return user.getUserActive();
            default: return null;
        }
    }

    public void setUsers(List<UserDTO> data)
    {
        users = data;
    }
}
