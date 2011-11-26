import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;

public class AddNoteAction implements Action
{

   NoteDialog source;
   MainWindow parent;
   NoteTableModel model;
   
   public AddNoteAction(NoteDialog noteDialog, MainWindow parent, NoteTableModel model)
   {
      this.source = noteDialog;
      this.parent = parent;
      this.model = model;
   }

   @Override
   public void addPropertyChangeListener(PropertyChangeListener listener)
   {
      // TODO Auto-generated method stub

   }

   @Override
   public Object getValue(String key)
   {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public boolean isEnabled()
   {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public void putValue(String key, Object value)
   {
      // TODO Auto-generated method stub

   }

   @Override
   public void removePropertyChangeListener(PropertyChangeListener listener)
   {
      // TODO Auto-generated method stub

   }

   @Override
   public void setEnabled(boolean b)
   {
      // TODO Auto-generated method stub

   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      Fach fach = new Fach(source.fachText.getText(),
    		               source.ects.getSelectedIndex(),
    		               source.wfButton.isSelected(),
                           source.key1.getSelectedIndex(), 
                           source.key2.getSelectedIndex(), 
                           source.key3.getSelectedIndex(),
                           source.datum1.getSelectedDate(),
                           source.datum2.getSelectedDate(),
                           source.datum3.getSelectedDate()
                           );
      if (source.getRow() != -1)
      {
          model.editRowAt(fach, parent, source.getRow());	// Tabelle
          parent.editFach(fach, source.getRow());     // fachMap
      }
      else
      {
    	  model.addRow(fach, parent);          // Tabelle
          parent.addFach(fach);                // fachMap
      }
      
      
      source.dispose();
   }
}
