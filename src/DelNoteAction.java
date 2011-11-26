import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;

public class DelNoteAction implements Action
{

   NoteDialog source;
   MainWindow parent;
   NoteTableModel model;
   
   public DelNoteAction(NoteDialog noteDialog, MainWindow parent, NoteTableModel model)
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
      model.removeRowAt(source.getRow());	// Tabelle
      parent.delFach(source.getRow());     // fachMap
      
      source.dispose();
   }
}
