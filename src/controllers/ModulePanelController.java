package src.controllers;

import src.views.panels.modules.*;
import javax.swing.*;

public class ModulePanelController
{
    public ModulePanelController()
    {}

    public void all()
    {
        new AllModuleController();
    }

    public void add()
    {
        new AddModuleController(); 
    }
 
    public void update()
    {
        if (All.module_table.getSelectedRow() != -1) {
            new UpdateModuleController();
        } else {
            JOptionPane.showMessageDialog(null, "DAMM No Row Selected");
        }
    }

    public void delete()
    {
        if (All.module_table.getSelectedRow() != -1) {
            new DeleteModuleController();
        } else {
            JOptionPane.showMessageDialog(null, "DAMM No Row Selected");
        }
    }

    public void read()
    {
        if (All.module_table.getSelectedRow() != -1) {
            new ReadModuleController();
        } else {
            JOptionPane.showMessageDialog(null, "DAMM No Row Selected");
        }
        /*ModulesPanel.content.removeAll();
        ModulesPanel.content.add(new Read());         
        ModulesPanel.content.updateUI();*/
    }
}
