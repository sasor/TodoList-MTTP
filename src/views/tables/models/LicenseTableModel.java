package src.views.tables.models;

import java.util.List;
import javax.swing.table.*;

import src.dto.gestion.users.LicenseDTO;

public class LicenseTableModel extends AbstractTableModel
{
    private List<LicenseDTO> licenses;
    private final String[] COLUMNS = {"ID", "Action", "Description"};

    public LicenseTableModel(List<LicenseDTO> licenses)
    {
        this.licenses = licenses;
    }

    public String getColumnName(int column)
    {
        return COLUMNS[column];
    }

    public int getRowCount()
    {
        return licenses.size();
    }

    public int getColumnCount()
    {
        return COLUMNS.length;
    }

    public Object getValueAt(int row, int column)
    {
        LicenseDTO license = licenses.get(row);
        switch (column) {
            case 0: return license.getLicenseId();
            case 1: return license.getLicenseAction();
            case 2: return license.getLicenseDescription(); 
            default: return null;
        }
    }
}
