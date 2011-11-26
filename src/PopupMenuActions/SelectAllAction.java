package PopupMenuActions;

import java.awt.event.ActionEvent;

import javax.swing.JMenuItem;
import javax.swing.text.JTextComponent;


public class SelectAllAction extends AbstractPopupMenuAction
{
   public SelectAllAction(JTextComponent source, JMenuItem item)
   {
      super(source, item);
   }

   @Override
   boolean isEnabled()
   {
      return source != null && source.isEditable() && source.getText().length() > 0;
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      if(isEnabled())
      {
         source.setSelectionStart(0);
         source.setSelectionEnd(source.getText().length());
      }
   }
}
