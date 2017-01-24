package src.views.panels.propietary;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;
import java.awt.*;
import src.controllers.propietary.*;
import src.dto.gestion.inventary.*;

public class SideBarPanel extends JPanel
{
    private JTree tree;
    public static DefaultTreeModel model_tree;
    public static DefaultMutableTreeNode root;
    public static List<Integer> parent_elements;
    public static List<Integer> excluidos = new ArrayList<Integer>();

    public static HashMap<Integer,List<Integer>> parent_childs;
    private TreeController controller;

    public static SideBarPanel instance;

    public SideBarPanel(TreeController model)
    {
        super(new BorderLayout());
        controller = model;
        //root = new DefaultMutableTreeNode("Room with ID "+controller.getID());
        root = new DefaultMutableTreeNode(controller.getNameRoom());
        parent_elements = getParents();
        parent_childs = getParentChilds();

        model_tree = new DefaultTreeModel(root);
        tree = new JTree(model_tree);
        /* Solo permite un nodo a seleccionar */
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) 
            {
                DefaultMutableTreeNode selected = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (selected == null) { return; }
                if (selected.isRoot()) {
                    /* controller.getID() retorna el id del room */
                    BodyController co = new BodyController(controller.getID());
                    BodyPanel v = new BodyPanel(co);
                    RoomPanel.body.removeAll();
                    RoomPanel.body.add(v);
                    RoomPanel.body.updateUI();
                } else {
                    Integer id = Integer.parseInt(selected.getUserObject().toString());
                    controller.drawDataWithElementID(id);
                }
            }
        });
        JScrollPane treeSP = new JScrollPane(tree);

        rePaint();

        this.add(treeSP);
    }

    private List<Integer> getParents()
    {
        List<Integer> response = new ArrayList<Integer>();
        List<ElementDTO> elements = controller.getData();
        if (elements.size() != 0 || elements != null) {
            for (ElementDTO e : elements) {
                if (controller.hasChilds(e)) {
                    response.add(e.getElementId()); 
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No Elements in getParents() function");
        }
        return response;
    }

    private HashMap<Integer,List<Integer>> getParentChilds()
    {
        HashMap<Integer,List<Integer>> response = new HashMap<Integer,List<Integer>>();
        List<ElementDTO> elements = controller.getData();
        if (elements.size() != 0) {
            for (Integer id : parent_elements) {
                List<Integer> value = new ArrayList<Integer>();
                for (ElementDTO e : elements) {
                    if (id == e.getElementParent()) {
                        value.add(e.getElementId());
                    }
                }
                response.put(id, value);
            }
        } else {
            JOptionPane.showMessageDialog(null,"No Elements in getParentChild() function");
        }
        return response;
    }

    private List<Integer> getChilds(Integer id)
    {
        return parent_childs.get(id);
    }

    private boolean isParent(Integer id)
    {
        boolean response = false;
        for (Integer i : parent_elements) {
            if (id == i) {
                response = true;
            }
        }
        return response;
    }

    public void rePaint()
    {
        rootLeafs();
        if (parent_elements.size() != 0 || parent_elements != null) {
            draw(parent_elements);
        } else {
            JOptionPane.showMessageDialog(null, "No Elements");
        }
    }

    /*public static void updateModel()
    {
        rePaint();
    }*/

    private void rootLeafs()
    {
        List<ElementDTO> els = controller.getData();
        for (ElementDTO e : els) {
            if (!(controller.hasChilds(e)) && e.getElementParent() == 0) {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(e.getElementId());
                model_tree.insertNodeInto(node, root, root.getChildCount());
            } 
        } 
    }

    private void draw(List<Integer> parents)
    {
        for (Integer i : parents) {
            if (!estaExcluido(i)) {
              DefaultMutableTreeNode node = new DefaultMutableTreeNode(i);
              List<Integer> childs = getChilds(i);
              if (childs.size() != 0) {
                recursive(node, childs);
              }
              if (controller.isRootChild(i)) {
                model_tree.insertNodeInto(node, root, root.getChildCount());
              }
            }
        }
    }

    private void recursive(DefaultMutableTreeNode node, List<Integer> childs)
    {
        for (Integer c : childs) {
            if (isParent(c)) {
                DefaultMutableTreeNode other = new DefaultMutableTreeNode(c);
                model_tree.insertNodeInto(other, node, node.getChildCount());
                List<Integer> cc = getChilds(c);
                excluidos.add(c);
                //parent_elements.remove(c);
                recursive(other, cc); 
            } else {
                DefaultMutableTreeNode other = new DefaultMutableTreeNode(c);
                model_tree.insertNodeInto(other, node, node.getChildCount());
            }
        }
    }

    private boolean estaExcluido(Integer i)
    {
        boolean response = false;
        for (Integer d : excluidos) {
            if (d == i) {
               response = true; 
            }
        }
        return response;
    }
} 
