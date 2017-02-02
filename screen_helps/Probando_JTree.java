import java.awt.BorderLayout; 
import java.awt.EventQueue;
import javax.swing.JFrame; 
import javax.swing.JOptionPane; 
import javax.swing.JPanel; 
import javax.swing.border.EmptyBorder; 
import javax.swing.JTree; 
import javax.swing.tree.DefaultTreeModel; 
import javax.swing.tree.DefaultMutableTreeNode; 
import javax.swing.tree.TreeSelectionModel; 
import javax.swing.JLabel; 
import javax.swing.SwingConstants; 
import java.awt.Font; 
import javax.swing.JButton; 
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import javax.swing.event.TreeExpansionListener; 
import javax.swing.event.TreeExpansionEvent; 
import javax.swing.event.TreeSelectionListener; 
import javax.swing.event.TreeSelectionEvent;   

public class Probando_JTree extends JFrame 
{      
     private JPanel contentPane;       
     JTree arbol = new JTree();
     public static void main(String[] args)     
     {         
         EventQueue.invokeLater(new Runnable() {             
             public void run()             
             {                 
                 try {                     
                    Probando_JTree frame = new Probando_JTree();
                    frame.setVisible(true);                 
                 } catch (Exception e) {                     
                    e.printStackTrace();                 
                 }             
             }         
        });     
     }       

     private void Expandir()
     {
        for (int x = 0; x<arbol.getRowCount();x++) {
           arbol.expandRow(x);
        }
     }

    public Probando_JTree() {         
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setBounds(100, 100, 450, 300);         
         contentPane = new JPanel();         
         contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));         
         contentPane.setLayout(new BorderLayout(0, 0));
         setContentPane(contentPane);
         arbol.setEditable(true);
         arbol.addTreeSelectionListener(new TreeSelectionListener() {                      public void valueChanged(TreeSelectionEvent arg0)
           {                 
              TreeSelectionModel x=arbol.getSelectionModel();
              int[] pag=x.getSelectionRows();                 
              String xw=arbol.getSelectionModel().getLeadSelectionPath().toString();
              JOptionPane.showMessageDialog(null, pag[0]);
           }
        });
        arbol.addTreeExpansionListener(new TreeExpansionListener() {
           public void treeCollapsed(TreeExpansionEvent arg0)
           { 
               Expandir();             
           }           
           public void treeExpanded(TreeExpansionEvent arg0)
           {                 
              Expandir();             
           }         
        });         
        arbol.setModel(new DefaultTreeModel(
             new DefaultMutableTreeNode("Computrachos") {                
              {        
                  DefaultMutableTreeNode node_1;
                  node_1 = new DefaultMutableTreeNode("Java"); 
                  node_1.add(new DefaultMutableTreeNode("java1"));
                  node_1.add(new DefaultMutableTreeNode("Java2"));
                  add(node_1); 
                  node_1 = new DefaultMutableTreeNode("Php");
                  node_1.add(new DefaultMutableTreeNode("php1"));
                  node_1.add(new DefaultMutableTreeNode("php2"));
                  add(node_1);  
                  node_1 = new DefaultMutableTreeNode("Html");
                  node_1.add(new DefaultMutableTreeNode("html1"));
                  node_1.add(new DefaultMutableTreeNode("html2"));
                  add(node_1); 
              }
            }
        ));
        contentPane.add(arbol, BorderLayout.CENTER);
        JLabel lblProbandoJtreeEn = new JLabel("Probando JTree en Java");
        lblProbandoJtreeEn.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblProbandoJtreeEn.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblProbandoJtreeEn, BorderLayout.NORTH);
        JPanel panel = new JPanel(); 
        contentPane.add(panel, BorderLayout.SOUTH);
        JButton btnNewButton = new JButton("Salir");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0)
                {System.exit(0);}
        });
        panel.setLayout(new BorderLayout(0, 0));
        btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(btnNewButton, BorderLayout.EAST);
        Expandir();
    } 
}

