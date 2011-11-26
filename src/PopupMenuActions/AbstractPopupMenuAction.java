package PopupMenuActions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.text.JTextComponent;


public abstract class AbstractPopupMenuAction implements ActionListener
{
   protected JTextComponent source;

   public AbstractPopupMenuAction(JTextComponent source, JMenuItem item)
   {
      super();
      if(source == null)
         throw new IllegalArgumentException("component must not be null");
      this.source = source;
      if(!isEnabled() && item != null)
         item.setEnabled(false);
   }

   abstract boolean isEnabled();
 
   @Override
   public void actionPerformed(ActionEvent e)
   {
    // do nothing ;)
   }
}
