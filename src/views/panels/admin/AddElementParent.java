package src.views.panels.admin;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import src.controllers.admin.*;
import src.dto.gestion.inventary.DataDTO;

public class AddElementParent extends JPanel
{
    private JPanel wrapper;

    private JTextField data_name_tf;
    private JTextArea data_place_ta;
    private JTextArea data_description_ta;
    private JSpinner data_expiration_sp;

    private GridBagConstraints gbc;
    private AddElementParentController controller;

    public AddElementParent(AddElementParentController model)
    {
        super(new BorderLayout());
        wrapper = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        controller = model;
        
        data_name_tf = new JTextField();
        data_name_tf.setPreferredSize(new Dimension(200, 20));
        /* JTextArea(int rows, int columns) */
        data_place_ta = new JTextArea(5, 20);
        data_description_ta = new JTextArea(5, 20);
        data_expiration_sp = new JSpinner(new SpinnerDateModel());
        Dimension size_sp = data_expiration_sp.getPreferredSize();
        size_sp.width = 100;
        data_expiration_sp.setPreferredSize(size_sp);
        JSpinner.DateEditor editor_sp = new JSpinner.DateEditor(data_expiration_sp, "dd-MM-yy");
        data_expiration_sp.setEditor(editor_sp);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        wrapper.add(new JLabel("Name"), gbc);
        gbc.gridx++;
        gbc.gridy = 0;
        wrapper.add(data_name_tf, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_START;
        wrapper.add(new JLabel("Place"), gbc);
        gbc.gridx++;
        wrapper.add(new JLabel("Description"), gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 10, 5);
        wrapper.add(data_place_ta, gbc);
        gbc.gridx++;
        gbc.insets = new Insets(0, 0, 10, 0);
        wrapper.add(data_description_ta, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        wrapper.add(new JLabel("Expiration Date"), gbc);
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.LINE_END;
        wrapper.add(data_expiration_sp, gbc);

        this.add(wrapper);

        JPanel operations = new JPanel();
        operations.setBorder(BorderFactory.createTitledBorder("Operations"));
        JButton add = new JButton("ADD");
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                controller.add();
            }
        });

        operations.add(add); 
        this.add(operations, BorderLayout.SOUTH);
    }

    public String getName()
    {
        return data_name_tf.getText();
    }

    public String getPlace()
    {
        return data_place_ta.getText();
    }

    public String getDescription()
    {
        return data_description_ta.getText();
    }

    public Object getExpiration()
    {
        return data_expiration_sp.getValue();
    }
}
