package PopupMenuActions;

import java.awt.event.ActionEvent;

import javax.swing.JMenuItem;
import javax.swing.text.JTextComponent;


public class UndoAction extends AbstractPopupMenuAction
{
   public UndoAction(JTextComponent source, JMenuItem item)
   {
      super(source, item);
   }

   @Override
   boolean isEnabled()
   {
      return source != null && source.isEditable();
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      if(isEnabled())
         throw new RuntimeException("Function UNDO not implemented yet!");
   }
}
