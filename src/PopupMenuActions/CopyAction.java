package PopupMenuActions;

import java.awt.event.ActionEvent;

import javax.swing.JMenuItem;
import javax.swing.text.JTextComponent;


public class CopyAction extends AbstractPopupMenuAction
{
   public CopyAction(JTextComponent source, JMenuItem item)
   {
      super(source, item);
   }

   @Override
   boolean isEnabled()
   {
      return source != null && source.getSelectedText() != null;
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      if(isEnabled())
         source.copy();
   }
}
