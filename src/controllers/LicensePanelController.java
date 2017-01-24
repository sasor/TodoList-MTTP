package src.controllers;

import javax.swing.JOptionPane;
import src.views.panels.licenses.*;

public class LicensePanelController
{
    public LicensePanelController()
    {}

    public void all()
    {
        new AllLicenseController();
    }

    public void add()
    {
        new AddLicenseController();
    }

    public void update()
    {
        if (All.license_table.getSelectedRow() != -1) {
            new UpdateLicenseController();
        } else {
            JOptionPane.showMessageDialog(null, "DAMM No Row Selected");
        }
        /*LicensesPanel.content.removeAll();
        LicensesPanel.content.add(new Update());         
        LicensesPanel.content.updateUI();*/
    }

    public void delete()
    {
        if (All.license_table.getSelectedRow() != -1) {
            new DeleteLicenseController();
        } else {
            JOptionPane.showMessageDialog(null, "DAMM No Row Selected");
        }
        /*LicensesPanel.content.removeAll();
        LicensesPanel.content.add(new Delete());         
        LicensesPanel.content.updateUI();*/
    }

    public void read()
    {
        if (All.license_table.getSelectedRow() != -1) {
            new ReadLicenseController();
        } else {
            JOptionPane.showMessageDialog(null, "DAMM No Row Selected");
        }
        /*LicensesPanel.content.removeAll();
        LicensesPanel.content.add(new Read());         
        LicensesPanel.content.updateUI();*/
    }
}
