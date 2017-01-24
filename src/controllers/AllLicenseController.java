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

public class AllLicenseController
{
    private DB db;
    private All view;
    private List<LicenseDTO> data;

    public AllLicenseController()
    {
        db = DB.instance();
        data = new ArrayList<LicenseDTO>();
        populateData(db.openConnection());

        view = new All(this);
        LicensesPanel.content.removeAll();
        LicensesPanel.content.add(view);
        LicensesPanel.content.updateUI();
    }

    private void populateData(Connection db)
    {
        LicenseIDAO license_dao = new LicenseIDAO(db);
        try {
            List<LicenseDTO> licenses = license_dao.all(); 
            if (licenses.size() != 0) {
                data = licenses;
            } else {
                JOptionPane.showMessageDialog(null, "No Modules in Database"); 
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<LicenseDTO> getData()
    {
        return data;
    }
}
