import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

/**
 * Hauptfenster der Notenliste
 * 
 *
 * @author Michael Rockstein & Philipp Schäfer
 * @version 1.0
 */
public class ListDialog extends JDialog
{
   /** Version. */
   private static final long serialVersionUID = 1L;

   private JList list = new JList();
   /**
    * Bastelt die GUI fürs Hauptfenster.
    */
   public ListDialog(MainWindow parent)
   {
      super();      
      setTitle("Notenliste");

      final int width = 380;
      final int height = 320;

      JFrame.setDefaultLookAndFeelDecorated(true);
      KeyWordListModel model = new KeyWordListModel();
      model.setList(parent.getKeyWords());

      list.setModel(model);
      
      JScrollPane listScrollPane = new JScrollPane(list);
      listScrollPane
            .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      listScrollPane.setPreferredSize(new Dimension(250, 250));
      listScrollPane.setBorder(BorderFactory
            .createCompoundBorder(BorderFactory.createCompoundBorder(
                  BorderFactory.createTitledBorder("Notenliste"), BorderFactory
                        .createEmptyBorder(10, 10, 10, 10)), listScrollPane
                  .getBorder()));
      list.addListSelectionListener(new NoteListSelectionListener(parent, list));
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      Container c = getContentPane();
      c.add(listScrollPane);

      setSize(width, height);
      setVisible(true);
   }
}
