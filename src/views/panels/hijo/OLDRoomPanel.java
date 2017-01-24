package src.views.panels.hijo;

import javax.swing.*;
import java.util.List;
import src.controllers.hijo.*;

public class RoomPanel extends JPanel
{
    private RoomController controller;

    public RoomPanel(RoomController model)
    {
        super();
        controller = model; 
    }
}
