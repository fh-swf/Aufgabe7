package PopupMenuActions;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.ActionEvent;

import javax.swing.JMenuItem;
import javax.swing.text.JTextComponent;

public class PasteAction extends AbstractPopupMenuAction
{
   public PasteAction(JTextComponent source, JMenuItem item)
   {
      super(source, item);
   }

   @Override
   boolean isEnabled()
   {
      return 
         source != null &&           
         source.isEditable() &&     
         Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null) != null &&  
         Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null).isDataFlavorSupported(DataFlavor.stringFlavor); 
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      if(isEnabled())
         source.paste();
   }
}
