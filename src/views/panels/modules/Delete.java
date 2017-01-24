package src.views.panels.modules;

import javax.swing.*;
import src.controllers.DeleteModuleController;

public class Delete extends JPanel
{
    private DeleteModuleController controller;

    public Delete(DeleteModuleController model)
    {
        super();
        controller = model;
    }
}
