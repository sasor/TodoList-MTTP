package src.views.tables.models;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import src.dto.gestion.users.ActionDTO;

public class DetailTableModel extends AbstractTableModel
{
    private List<ActionDTO> actions;
    private final String[] COLUMNS = {"Nickname", "Action", "Date", "Time"};

    public DetailTableModel(List<ActionDTO> actions)
    {
        this.actions = actions;
    }

    public String getColumnName(int column)
    {
        return COLUMNS[column];
    }

    public int getRowCount()
    {
        return actions.size();
    }

    public int getColumnCount()
    {
        return COLUMNS.length;
    }

    public Object getValueAt(int row, int column)
    {
        ActionDTO action = actions.get(row);
        switch (column) {
            case 0: return action.getNick();
            case 1: return action.getAction(); 
            case 2: return String.valueOf(action.getDate());
            case 3: return action.getTimes();
            default: return null;
        }
    }

    public void setAction(List<ActionDTO> data)
    {
        actions = data;
    }
}
