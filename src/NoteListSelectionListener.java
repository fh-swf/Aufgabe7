import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class NoteListSelectionListener implements ListSelectionListener
{
   private static final long serialVersionUID = 1L;
   private MainWindow parent;
   private JList list;
   
   public NoteListSelectionListener(MainWindow parent, JList list)
   {
      this.parent = parent;
      this.list = list;
   }
   
   @SuppressWarnings("static-access")
   @Override
   public void valueChanged(ListSelectionEvent e)
   {
      String value = list.getSelectedValue().toString();
      if(value == null) return;
      parent.tableData.clear();
      for(int i=0;i<parent.fachMap.size();i++)
      {
         System.out.println(e.toString());
         Fach fach = parent.fachMap.get(i);
         if(fach.getNote1().equals(value) || fach.getNote2().equals(value) || fach.getNote3().equals(value)) 
         {
            System.out.print("fach:" + i + " " + fach.toString());
            parent.tableData.addRow(fach, parent);
         }
      }
   }
}
