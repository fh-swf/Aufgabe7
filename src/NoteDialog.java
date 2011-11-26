import java.lang.Integer;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.standbysoft.component.date.swing.JDatePicker;

/**
 * Hauptfenster der Notenverwaltung
 * 
 *
 * @author Michael Rockstein & Philipp Schäfer
 * @version 1.0
 */
public class NoteDialog extends JDialog
							implements ActionListener{
   /** Version. */
   private static final long serialVersionUID = 1L;

   protected JPopupTextField fachText;
   
   protected JCheckBox wfButton;
   
   protected JComboBox ects;   
   protected JComboBox key1;   
   protected JComboBox key2;   
   protected JComboBox key3;
   
   protected JDatePicker datum1;
   protected JDatePicker datum2;
   protected JDatePicker datum3;
   
   
   private JButton deleteButton;
   private JButton saveButton;
   private JButton exitButton;
   
   private String[] noteStrings;
   private String[] ectsStrings;
   
   private int row = -1;
   
   private JDialog dialog;
   /**
    * Bastelt die GUI fürs Hauptfenster.
    */
   public NoteDialog(String title, MainWindow parent, NoteTableModel model)
   {
      super();
      if (title == null)
         throw new IllegalArgumentException("Title must not be null!");
      
      noteStrings = parent.noteStrings;
      ectsStrings = parent.ectsStrings;

      setTitle(title);
      final int width = 480;
      final int height = 280;
      dialog = this;
      
      JFrame.setDefaultLookAndFeelDecorated(true);

      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      Container c = getContentPane();

      JPanel hBox = new JPanel();
      hBox.setLayout(new BoxLayout(hBox, BoxLayout.PAGE_AXIS));
            
      JLabel label = new JLabel();
      label.setPreferredSize(new Dimension(100, 10));

      JPanel vBox = new JPanel();
      
      vBox = new JPanel();
      vBox.setLayout(new BoxLayout(vBox, BoxLayout.LINE_AXIS));
      JLabel fachLabel = new JLabel(" Fach: ");
      fachLabel.setPreferredSize(new Dimension(100, 10));
      vBox.add(fachLabel);
      fachText = new JPopupTextField();
      vBox.add(fachText);
      hBox.add(vBox);

       
      deleteButton = new JButton("Löschen");
      deleteButton.addActionListener(new DelNoteAction(this, parent, model));
      deleteButton.setEnabled(false);

      saveButton = new JButton("Speichern");
      saveButton.addActionListener(new AddNoteAction(this, parent, model));
      saveButton.setEnabled(true);

      exitButton = new JButton("Abbrechen");
      exitButton.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            dialog.dispose();
         }
      });
      exitButton.setEnabled(true);

      JPanel buttonBox = new JPanel();
      buttonBox.setLayout(new BoxLayout(buttonBox, BoxLayout.LINE_AXIS));
      buttonBox.add(deleteButton);
      buttonBox.add(saveButton);
      buttonBox.add(exitButton);

      vBox = new JPanel();
      vBox.setLayout(new BoxLayout(vBox, BoxLayout.LINE_AXIS));
      JLabel ectslabel = new JLabel(" ECTS Punkte: ");
      ectslabel.setPreferredSize(new Dimension(100, 10));
      vBox.add(ectslabel);
	  ects = new JComboBox(ectsStrings);
	  ects.setEditable(false);
      ects.setSelectedIndex(0);
      ects.addActionListener(this);
      vBox.add(ects);
      hBox.add(vBox);
      
      vBox = new JPanel();
      vBox.setLayout(new BoxLayout(vBox, BoxLayout.LINE_AXIS));
      JLabel wflabel = new JLabel(" Wahlfach: ");
      ectslabel.setPreferredSize(new Dimension(100, 10));
      vBox.add(wflabel);
      wfButton = new JCheckBox("Wahlfach");
      wfButton.setMnemonic(KeyEvent.VK_C);
      wfButton.setSelected(false);
      vBox.add(wfButton);
      hBox.add(vBox);
    
      vBox = new JPanel();
      vBox.setLayout(new BoxLayout(vBox, BoxLayout.LINE_AXIS));
      JLabel kw1label = new JLabel(" Note Versuch1: ");
      kw1label.setPreferredSize(new Dimension(100, 10));
      vBox.add(kw1label);
      key1 = new JComboBox(noteStrings);
      key1.setEditable(false);
      key1.setSelectedIndex(0);
      key1.addActionListener(this);
      vBox.add(key1);
      JLabel datelabel1 = new JLabel(" Datum: ");
      datelabel1.setPreferredSize(new Dimension(100, 10));
      vBox.add(datelabel1);
      datum1 = new JDatePicker();
      vBox.add(datum1);
      hBox.add(vBox);

      vBox = new JPanel();
      vBox.setLayout(new BoxLayout(vBox, BoxLayout.LINE_AXIS));
      JLabel kw2label = new JLabel(" Note Versuch2: ");
      kw2label.setPreferredSize(new Dimension(100, 10));
      vBox.add(kw2label);
      key2 = new JComboBox(noteStrings);
      key2.setEditable(false);
      key2.setSelectedIndex(0);
      key2.addActionListener(this);
      vBox.add(key2);
      JLabel datelabel2 = new JLabel(" Datum: ");
      datelabel2.setPreferredSize(new Dimension(100, 10));
      vBox.add(datelabel2);
      datum2 = new JDatePicker();
      vBox.add(datum2);
      hBox.add(vBox);

      vBox = new JPanel();
      vBox.setLayout(new BoxLayout(vBox, BoxLayout.LINE_AXIS));
      JLabel kw3label = new JLabel(" Note Versuch3: ");
      kw3label.setPreferredSize(new Dimension(100, 10));
      vBox.add(kw3label);
      key3 = new JComboBox(noteStrings);
      key3.setEditable(false);
      key3.setSelectedIndex(0);
      key3.addActionListener(this);
      vBox.add(key3);
      JLabel datelabel3 = new JLabel(" Datum: ");
      datelabel3.setPreferredSize(new Dimension(100, 10));
      vBox.add(datelabel3);
      datum3 = new JDatePicker();
      vBox.add(datum3);
      hBox.add(vBox);

      hBox.add(buttonBox, BorderLayout.SOUTH);
      
      // Noten und Buttons
      c.add(hBox, BorderLayout.NORTH);
      
      setSize(width, height);
      setVisible(true);
   }

   public void setDeleteEnabled(boolean state)
   {
      deleteButton.setEnabled(state);
   }
   
   public void setSaveEnabled(boolean state)
   {
      saveButton.setEnabled(state);
   }
   
   public void setExitEnabled(boolean state)
   {
      exitButton.setEnabled(state);
   }  
 
   public void loadFach(Fach fach)
   {
      fachText.setText(fach.getFach());
      ects.setSelectedIndex(fach.getEcts());
      wfButton.setSelected(fach.getWf());
      key1.setSelectedIndex(fach.getNote1());
      key2.setSelectedIndex(fach.getNote2());
      key3.setSelectedIndex(fach.getNote3());
      datum1.setSelectedDate(fach.getDatum1());
      datum2.setSelectedDate(fach.getDatum2());
      datum3.setSelectedDate(fach.getDatum3());
   }

public Integer getRow() {
	return row;
}

public void setRow(int row) {
//	System.out.println(row.toString());
	this.row = row;
}

@Override
public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	
}
}
