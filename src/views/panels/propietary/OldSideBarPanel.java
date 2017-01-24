package src.views.panels.propietary;

import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import src.controllers.propietary.*;
import src.dto.gestion.inventary.*;

public class SideBarPanel extends JPanel
{
    private JTree tree;
    private DefaultTreeModel model_tree;
    private DefaultMutableTreeNode root;
    public static List<DefaultMutableTreeNode> nodes;

    private TreeController controller;

    public SideBarPanel(TreeController model)
    {
        super(new BorderLayout());
        controller = model;
        root = new DefaultMutableTreeNode("Room with ID "+controller.getID());
        nodes = new ArrayList<>();

        model_tree = new DefaultTreeModel(root);
        tree = new JTree(model_tree);
        rePaint();

        this.add(tree);
    }

    public void rePaint()
    {
        List<ElementDTO> els = controller.getData();
        if (els.size() != 0 || els == null) {
            draw(els);
        } else {
            JOptionPane.showMessageDialog(null, "No Elements");
        }
    }

    private List<ElementDTO> findChilds(Integer parent_id)
    {
        List<ElementDTO> response = new ArrayList<>();
        List<ElementDTO> els = controller.getData();
        for (ElementDTO e : els) {
            if (parent_id == e.getElementParent()) {
                response.add(e); 
            } 
        }
        return response;
    }

    private void draw(List<ElementDTO> els)
    {
        for (ElementDTO e : els) { 
            if (e.getElementParent() == 0) {
                drawRootChilds(e);
            } else if (controller.hasChilds(e)) {
                drawAParentNode(e);
            } else {
                drawNode(e); 
            }
        }
    }

    private void drawRootChilds(ElementDTO e)
    {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(); 
        node.setUserObject(e);
        nodes.add(node);
        model_tree.insertNodeInto(node, root, root.getChildCount());
    }

    private void drawAParentNode(ElementDTO e)
    {
        /* Como es parent crea primero el padre */
        DefaultMutableTreeNode parent = new DefaultMutableTreeNode(); 
        parent.setUserObject(e);
        nodes.add(parent);
        /* findChilds busca los elements hijos del element parent*/
        List<ElementDTO> childs = findChilds(e.getElementId()); 
        for (ElementDTO child : childs) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode();
            node.setUserObject(child);
            model_tree.insertNodeInto(node, parent, parent.getChildCount());
            if (controller.hasChilds(child)) {
                drawAParentNode(child);
            }
        }
    }

    private DefaultMutableTreeNode findParentNode(Integer id)
    {
        DefaultMutableTreeNode response = null;
        for (DefaultMutableTreeNode node : nodes) {
            //ElementDTO e = node.getUserObject();
            String obj = node.toString();
            Integer e_id = Integer.parseInt(obj);
            /*if (id == e.getElementId()) {
                response = node;
            }*/
            if (id == e_id) {
                response = node;
            }
        }
        return response;
    }

    private void drawNode(ElementDTO e)
    {
        DefaultMutableTreeNode c_node = new DefaultMutableTreeNode(); 
        c_node.setUserObject(e);
        nodes.add(c_node);
        /* busca el nodo padre de c_node */
        DefaultMutableTreeNode parent = findParentNode(e.getElementParent()); 
        if (parent == null) {
            JOptionPane.showMessageDialog(null, "PARENT NULL");
        }
        model_tree.insertNodeInto(c_node, parent, parent.getChildCount());
    }
} 
