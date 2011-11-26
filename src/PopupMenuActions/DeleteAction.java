package PopupMenuActions;

import java.awt.event.ActionEvent;

import javax.swing.JMenuItem;
import javax.swing.text.JTextComponent;


public class DeleteAction extends AbstractPopupMenuAction
{
   public DeleteAction(JTextComponent textField, JMenuItem item)
   {
      super(textField, item);
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
         source.setText("");
   }
}
