package src.controllers;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import src.stuff.DB;
import src.views.panels.licenses.*;
import src.dto.gestion.users.LicenseDTO;
import src.dao.gestion.users.LicenseIDAO;

public class DeleteLicenseController
{
    private DB db;
    private Integer license_id;

    public DeleteLicenseController()
    {
        db = DB.instance();
        license_id = pullLicenseId();
        init();
    }

    public Integer pullLicenseId()
    {
        String id = String.valueOf(All.license_table.getValueAt(All.license_table.getSelectedRow(), 0));
        Integer i = Integer.parseInt(id);
        return i;
    }

    public void init()
    {
        LicenseIDAO dao = new LicenseIDAO(db.openConnection());
        try {
            if (dao.deleteByKey(license_id)) {
                JOptionPane.showMessageDialog(null, "OK License Deleted");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
