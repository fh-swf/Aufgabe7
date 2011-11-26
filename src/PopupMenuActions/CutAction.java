package PopupMenuActions;

import java.awt.event.ActionEvent;

import javax.swing.JMenuItem;
import javax.swing.text.JTextComponent;


public class CutAction extends AbstractPopupMenuAction
{
   public CutAction(JTextComponent source, JMenuItem item)
   {
      super(source, item);
   }

   @Override
   boolean isEnabled()
   {
      return source != null && source.isEditable() && source.getSelectedText() != null;
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      if(isEnabled())
         source.cut();
   }
}
