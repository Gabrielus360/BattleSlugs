import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Table extends JPanel {
    private boolean DEBUG = false;

    public Table() 
    {
        super(new GridLayout(1,0));

        String[] columnNames = {"Player Name", "Highscores"};

        Object[][] sampleData = {
            {"Mary",  new Integer(5)},
            {"Alison", new Integer(3)},
            {"Kathy", new Integer(2)},
            {"Sharon", new Integer(20)},
            {"Philip", new Integer(10)}
        };

        final JTable table = new JTable(sampleData, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        
        //Makes table uneditable
        table.setEnabled(false);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }
    
    public void addToTable()
    {
    	
    }

    /**
     * Create the GUI and show it.
     */
    
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("SimpleTableDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        Table highscoresTable = new Table();
        highscoresTable.setOpaque(true); //content panes must be opaque
        frame.setContentPane(highscoresTable);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    
    public static void main(String[] args)
    {
    	 createAndShowGUI();
	}
    
    
//    public static void main(String[] args) {
//        //Schedule a job for the event-dispatching thread:
//        //creating and showing this application's GUI.
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGUI();
//            }
//        });
//    }
}
