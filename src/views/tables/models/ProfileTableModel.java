package src.views.tables.models;

import java.util.List;

import javax.swing.table.*;

import src.dto.gestion.users.ProfileDTO;

public class ProfileTableModel extends AbstractTableModel
{
    private List<ProfileDTO> profiles;
    private final String[] COLUMNS = {"ID", "Name", "Active"};

    public ProfileTableModel(List<ProfileDTO> profiles)
    {
        this.profiles = profiles;
    }

    public String getColumnName(int column)
    {
        return COLUMNS[column];
    }

    public int getRowCount()
    {
        return profiles.size();
    }

    public int getColumnCount()
    {
        return COLUMNS.length;
    }
  
    public Object getValueAt(int row, int column)
    {
        ProfileDTO profile = profiles.get(row);
        switch (column) {
            case 0: return profile.getProfileId();
            case 1: return profile.getProfileName();
            case 2: return profile.getProfileActive(); 
            default: return null;
        }
    }
}
