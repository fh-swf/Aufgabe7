import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import PopupMenuActions.CopyAction;
import PopupMenuActions.CutAction;
import PopupMenuActions.DeleteAction;
import PopupMenuActions.PasteAction;
import PopupMenuActions.SelectAllAction;
import PopupMenuActions.UndoAction;

/*
 * $RCSFile$
 *
 * Created on 01.11.2011
 * for Project: Notenverwaltung
 * by Michael Rockstein & Philipp Schäfer
 *
 * (C) 2005-2006 by 
 */

public class JPopupTextArea extends JTextArea
{
   /** Version. */
   private static final long serialVersionUID = 1L;

   /** 
    * Standard Konstruktor. Fügt Popup-Menü hinzu.
    */
   public JPopupTextArea()
   {
      super();
      addMouseListener(new PopupListener(this));
   }

   class PopupListener extends MouseAdapter
   {
      JTextArea source;
      JPopupMenu popupMenu;
      
      public PopupListener(JTextArea source)
      {
         super();
         if(source == null)
            throw new IllegalArgumentException("component must not be null!");
         
         this.source = source;
      }

      private void setupPopup()
      {
         popupMenu = new JPopupMenu();

         // Rückgängig
         JMenuItem menuUndo = new JMenuItem("Rückgängig");
         menuUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_UNDO, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
         menuUndo.addActionListener(new UndoAction(source, menuUndo));
         menuUndo.setEnabled(false);
         popupMenu.add(menuUndo);
         
         // Ausschneiden
         JMenuItem menuCut = new JMenuItem("Ausschneiden");
         menuCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_CUT, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
         menuCut.addActionListener(new CutAction(source, menuCut));
         popupMenu.add(menuCut);

         // Kopieren
         JMenuItem menuCopy = new JMenuItem("Kopieren");
         menuCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_COPY, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
         menuCopy.addActionListener(new CopyAction(source, menuCopy));
         popupMenu.add(menuCopy);
         
         // Einfügen
         JMenuItem menuPaste = new JMenuItem("Einfügen");
         menuPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PASTE, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
         menuPaste.addActionListener(new PasteAction(source, menuPaste));
         popupMenu.add(menuPaste);

         // Löschen
         JMenuItem menuDelete = new JMenuItem("Löschen");
         menuDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
         menuDelete.addActionListener(new DeleteAction(source, menuDelete));
         popupMenu.add(menuDelete);
         
         // Alles auswählen
         JMenuItem menuSelectAll = new JMenuItem("Alles markieren");
         menuSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ALL_CANDIDATES, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
         menuSelectAll.addActionListener(new SelectAllAction(source, menuSelectAll));
         popupMenu.add(menuSelectAll);
      }
      
      public void mousePressed(MouseEvent e)
      {
         maybeShowPopup(e);
      }
      
      public void mouseReleased(MouseEvent e)
      {
         maybeShowPopup(e);
      }
      
      void maybeShowPopup(MouseEvent e)
      {
         if(e.isPopupTrigger())
         {
            popupMenu = null; // drop old popup
            setupPopup();     // setup new one 
            popupMenu.show(e.getComponent(), e.getX(), e.getY()); // show it
         }
      }
   }
}

