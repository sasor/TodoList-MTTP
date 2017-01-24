package src.views.panels;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel
{
    public WelcomePanel()
    {
        super(new FlowLayout(FlowLayout.CENTER));
        this.add(new JLabel("Welcome To TodoList Inventary"));
    }
}
