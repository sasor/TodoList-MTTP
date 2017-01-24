package src.views.tables.models;

import java.util.List;
import javax.swing.table.*;

import src.dto.gestion.users.ModuleDTO;

public class ModuleTableModel extends AbstractTableModel
{
    private List<ModuleDTO> modules;
    private final String[] COLUMNS = {"ID", "Name", "Description"};

    public ModuleTableModel(List<ModuleDTO> modules)
    {
        this.modules = modules;
    }

    public String getColumnName(int column)
    {
        return COLUMNS[column];
    }
   
    public int getRowCount()
    {
        return modules.size();
    }

    public int getColumnCount()
    {
        return COLUMNS.length;
    }

    public Object getValueAt(int row, int column)
    {
        ModuleDTO module = modules.get(row);
        switch (column) {
            case 0: return module.getModuleId();
            case 1: return module.getModuleName(); 
            case 2: return module.getModuleDescription(); 
            default: return null;
        }
    }
}
