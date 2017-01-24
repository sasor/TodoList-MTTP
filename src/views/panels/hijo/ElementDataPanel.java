package src.views.panels.hijo;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import src.controllers.hijo.*;
import src.dto.gestion.inventary.DataDTO;

public class ElementDataPanel extends JPanel
{
    private JPanel wrapper;
    public static boolean isAllowed = false;

    private JTextField data_name_tf;
    private JTextArea data_place_ta;
    private JTextArea data_description_ta;
    private JSpinner data_expiration_sp;

    private GridBagConstraints gbc;
    private ElementDataController controller;

    public ElementDataPanel(ElementDataController model)
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
        ButtonGroup group = new ButtonGroup(); 
        JRadioButton add = new JRadioButton("ADD");
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                controller.add();
            }
        });

        JRadioButton update = new JRadioButton("UPDATE");
        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if (!(isAllowed)) {
                    isAllowed = true;
                    enableFields();
                } else {
                    controller.update();
                    isAllowed = false;
                    disableFields();
                }
            }
        });

        JRadioButton delete = new JRadioButton("DELETE");
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                controller.delete();
            }
        });

        group.add(add);
        group.add(update);
        group.add(delete);
        operations.add(add); 
        operations.add(update); 
        operations.add(delete); 
 
        this.add(operations, BorderLayout.SOUTH);
        bindDataView();
        disableFields();
    }

    private void bindDataView()
    {
        DataDTO data = controller.getData();
        data_name_tf.setText(data.getDataName());
        String tmp_place = controller.giveMePlace(data.getDataPlaceId());
        data_place_ta.setText(tmp_place);
        data_description_ta.setText(data.getDataDescription());
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
        /* El tipo de objeto que retorna es java.util.Date */
        return data_expiration_sp.getValue();
    }

    public void enableFields()
    {
        data_name_tf.setEditable(true);
        data_place_ta.setEditable(true);
        data_description_ta.setEditable(true);
    }

    public void disableFields()
    {
        data_name_tf.setEditable(false);
        data_place_ta.setEditable(false);
        data_description_ta.setEditable(false);
    }
} 
