import java.lang.Integer;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;


/**
 * Hauptfenster der Notenverwaltung
 * 
 *
 * @author Michael Rockstein & Philipp Schäfer
 * @version 1.0
 */
public class MainWindow extends JFrame
{
   /** Version. */
   private static final long serialVersionUID = 1L;

   public static Map<String, KeyWord> keyWordMap = new HashMap<String, KeyWord>();
   public static Vector<Fach> fachMap = new Vector<Fach>();
   public static  NoteTableModel tableData = new NoteTableModel();
   
   private JComboBox bachelor;   
   private JComboBox colo;
   
   private JPopupTextField noteText;
   private JPopupTextField ectsText;
   
   public String[] noteStrings = { "0", "1.0", "1.3", "1.7", "2.0", "2.3", "2.7", "3.0", "3.3", "3.7", "4.0", "5.0" };
   public String[] ectsStrings = { "0", "2", "3", "4", "5", "6", "7", "9", "12" };
   
   MainWindow frame;
   /**
    * Bastelt die GUI fürs Hauptfenster.
    */
   public MainWindow()
   {
      super("MainWindow");

      final int width = 1024;
      final int height = 700;
      frame = this;
      JFrame.setDefaultLookAndFeelDecorated(true);

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.addWindowListener(new WindowAdapter(){
         public void windowClosing(WindowEvent we){
            frame.saveAllDatabases();
            System.exit(0);
         }
       });
      
      
      JMenuBar menuBar;
      JMenu menuFile;
      JMenu menuHelp;
      JMenuItem actionExit;
      JMenuItem actionInfo;
      JMenuItem actionNewNote;
      JMenuItem actionKwList;
      
      menuBar = new JMenuBar();
      menuFile = new JMenu("Datei");
      menuFile.setMnemonic(KeyEvent.VK_D);
      menuFile.getAccessibleContext().setAccessibleDescription("Datei-Menü");
      menuBar.add(menuFile);

      menuHelp = new JMenu("Hilfe");
      menuHelp.setMnemonic(KeyEvent.VK_H);
      menuHelp.getAccessibleContext().setAccessibleDescription("Hilfe-Menü");
      menuBar.add(menuHelp);

      actionNewNote = new JMenuItem("Neues Fach", KeyEvent.VK_N);
      actionNewNote.getAccessibleContext().setAccessibleDescription(
            "Neues Fach hinzufügen");
      actionNewNote.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
              new NoteDialog("Fach hinzufügen", frame, tableData).setRow(-1);
         }
      });
      menuFile.add(actionNewNote);

      
      actionKwList = new JMenuItem("Aktualisieren", KeyEvent.VK_L);
      actionKwList.getAccessibleContext().setAccessibleDescription(
            "Aktualisiert Notendurchschnitt und ECTS");
      actionKwList.addActionListener(new Aktualisieren(frame, tableData));
      menuFile.add(actionKwList);
      
      actionExit = new JMenuItem("Beenden", KeyEvent.VK_E);
      actionExit.getAccessibleContext().setAccessibleDescription(
            "Beendet die Anwendung");
      actionExit.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            frame.saveAllDatabases();
            System.exit(0);
         }
      });
      menuFile.add(actionExit);

      actionInfo = new JMenuItem("Info", KeyEvent.VK_I);
      actionInfo.getAccessibleContext().setAccessibleDescription(
            "About Dialog");
      actionInfo.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            JDialog aboutDialog = new JDialog();
            JLabel aboutDialogLabel = new JLabel(
                  "Notenverwaltung - Version 1.0", JLabel.CENTER);
            aboutDialog.add(aboutDialogLabel);
            aboutDialog.setSize(320, 200);
            aboutDialog.setVisible(true);
         }
      });
      menuHelp.add(actionInfo);

      setJMenuBar(menuBar);
      
      Container c = getContentPane();

      JTable table = new JTable();
      table.setModel(tableData);
      table.addMouseListener(new TableClickListener(table, frame, tableData));
      JScrollPane tableScrollPane = new JScrollPane(table);
      tableScrollPane
            .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      tableScrollPane.setPreferredSize(new Dimension(320, 400));
      tableScrollPane.setBorder(BorderFactory
            .createCompoundBorder(BorderFactory.createCompoundBorder(
                  BorderFactory.createTitledBorder("Notenübersicht"), BorderFactory
                        .createEmptyBorder(10, 10, 10, 10)), tableScrollPane
                  .getBorder()));
      
      
      JPanel hBox = new JPanel();
      hBox.setLayout(new BoxLayout(hBox, BoxLayout.PAGE_AXIS));
      
      hBox.add(tableScrollPane);
            
      JPanel vBox = new JPanel();
      
      vBox = new JPanel();
      vBox.setLayout(new BoxLayout(vBox, BoxLayout.LINE_AXIS));
      JLabel bachelorLabel = new JLabel(" Bachelor Arbeit:  ");
      bachelorLabel.setPreferredSize(new Dimension(100, 30));
      vBox.add(bachelorLabel);
	  bachelor = new JComboBox(noteStrings);
	  bachelor.setEditable(false);
	  bachelor.setSelectedIndex(0);
	  bachelor.setPreferredSize(new Dimension(10, 10));
	  bachelor.setMaximumSize(new Dimension(50, 30));
//	  bachelor.addActionListener(this);
      vBox.add(bachelor);
      
      hBox.add(vBox);
      
      vBox = new JPanel();
      vBox.setLayout(new BoxLayout(vBox, BoxLayout.LINE_AXIS));
      JLabel cololabel = new JLabel(" Kolloquium:          ");
      cololabel.setPreferredSize(new Dimension(100, 30));
      vBox.add(cololabel);
	  colo = new JComboBox(noteStrings);
	  colo.setEditable(false);
	  colo.setSelectedIndex(0);
	  colo.setPreferredSize(new Dimension(10, 10));
	  colo.setMaximumSize(new Dimension(50, 30));
//	  colo.addActionListener(this);
      vBox.add(colo);
      
      hBox.add(vBox);
      
      JPanel hBox1 = new JPanel();
      hBox1.setLayout(new BoxLayout(hBox1, BoxLayout.PAGE_AXIS));
      
      JLabel qLabel = new JLabel(" Notendurchschnitt: ");
      qLabel.setPreferredSize(new Dimension(100, 10));
      hBox1.add(qLabel);
      
      noteText = new JPopupTextField();
      noteText.setPreferredSize(new Dimension(100, 30));
      noteText.setMaximumSize(new Dimension(100, 30));
      noteText.setEditable(false);
      hBox1.add(noteText);
      
      JLabel eLabel = new JLabel(" ECTS: ");
      eLabel.setPreferredSize(new Dimension(100, 10));
      hBox1.add(eLabel);
      
      ectsText = new JPopupTextField();
      ectsText.setPreferredSize(new Dimension(100, 30));
      ectsText.setMaximumSize(new Dimension(100, 30));
      ectsText.setEditable(false);
      hBox1.add(ectsText);
      
      hBox1.setSize(new Dimension(250, 100));
      hBox1.setMaximumSize(new Dimension(250, 250));
      
      vBox = new JPanel();
      vBox.setLayout(new BoxLayout(vBox, BoxLayout.LINE_AXIS));
      
      vBox.add(hBox);
      vBox.add(hBox1, BorderLayout.EAST);
      
      c.add(vBox);
      
      setSize(width, height);
      setVisible(true);
   }

   /**
    * Hauptfunktion des Programms.
    * 
    * @param args
    *           Argumente - derzeit ungenutzt.
    */
   public static void main(String[] args)
   {
      // Display the window in a thread safe way.
	   MainWindow frame = new MainWindow();
      new JFrameShower(frame);
      try
      {
         CsvReader reader = new CsvReader("noten.csv", ',', Charset.forName("UTF-8"));
         System.out.println("reading notes...");
         while(reader.readRecord())
         {
            Fach fach = new Fach(); // leerer Noten-Datensatz
            fach.setFach(reader.get(0));
            fach.setEcts(Integer.parseInt(reader.get(1)));
            fach.setWf(Boolean.parseBoolean(reader.get(2)));
            fach.setNote1(Integer.parseInt(reader.get(3)));
            fach.setNote2(Integer.parseInt(reader.get(4)));
            fach.setNote3(Integer.parseInt(reader.get(5)));
            fach.setDatum1(new Date(Long.parseLong(reader.get(6))));
            fach.setDatum2(new Date(Long.parseLong(reader.get(7))));
            fach.setDatum3(new Date(Long.parseLong(reader.get(8))));
            
            
            System.out.println(fach.toString());
            // zur Tabelle hinzufügen
            if ( reader.get(0).equalsIgnoreCase("Abschlussarbeit") )
            {
            	frame.setBachelor(Integer.parseInt(reader.get(3)));
            	frame.setColo(Integer.parseInt(reader.get(4)));
            }
            else
            {
            	tableData.addRow(fach, frame);
            }
            
            // Fach zur Map hinzufügen.
            fachMap.add(fach);
         }
         reader.close();
         
         CsvReader keyReader = new CsvReader("key.csv", ',', Charset.forName("UTF-8"));
         System.out.println("reading keys...");
         while(keyReader.readRecord())
         {
            KeyWord key = new KeyWord(Integer.parseInt(keyReader.get(1)), Integer.parseInt(keyReader.get(2)), Integer.parseInt(keyReader.get(3)));
            System.out.println(key.getKey());
            
            // Note zur Map hinzufügen.
            keyWordMap.put(key.getKey().toString(), key);
         }
         keyReader.close();
        
      }
      catch (FileNotFoundException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      catch (IOException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      catch (NumberFormatException nfe)
      {
         // ignore
      }
   }
   
  public void addFach(Fach fach)
  {
     fachMap.add(fach);
  }
  
  public void editFach(Fach fach, int row)
  {
	  fachMap.remove(row);
	  fachMap.insertElementAt(fach, row);
  }
  public void delFach(int row)
  {
	  fachMap.remove(row);
  }
  
  public Fach getFach(int index)
  {
     return fachMap.get(index);
  }
  
  public Vector<Fach> getFachMap()
  {
	  return fachMap;
  }

  public void addKey(KeyWord key)
  {
     if ( keyWordMap.containsKey(key.getKey()) )
     {
    	 key.setCount(key.getCount()+1);
    	 return;
     }
     keyWordMap.put(key.getKey().toString(), key);
  }

  public void delKey(KeyWord key)
  {
	  if ( keyWordMap.containsKey(key.getKey()) )
	     {
	    	 key.setCount(key.getCount()-1);
	    	 if ( key.getCount() == 0) keyWordMap.remove(key);
	    	 return;
	     }

  }

  public Set<String> getKeyWords()
  {
     return keyWordMap.keySet();
  }
  
  public void setBachelor(int index)
  {
	  bachelor.setSelectedIndex(index);
  }

  public int getBachelor()
  {
	  return bachelor.getSelectedIndex();
  }
  
  public void setColo(int index)
  {
	  colo.setSelectedIndex(index);
  }

  public int getColo()
  {
	  return colo.getSelectedIndex();
  }

  public void setSchnitt(String schnitt)
  {
	  noteText.setText(schnitt.substring(0, 3));
  }

  public double getSchnitt()
  {
	  return Double.parseDouble(noteText.getText());
  }

  public void setEctsSum(String ects)
  {
	  ectsText.setText(ects);
  }

  public int getEctsSum()
  {
	  return Integer.parseInt(noteText.getText());
  }

  public void saveAllDatabases()
  {
     CsvWriter writer = new CsvWriter("noten.csv", ',', Charset.forName("UTF-8"));  
     System.out.println("writing notes...");
     fachMap.elementAt(0).setNote1(frame.getBachelor());
     fachMap.elementAt(0).setNote2(frame.getColo());
     for(Fach fach : fachMap)
     {
        System.out.println(fach.toString());
        fach.toCSV(writer);
     }
     writer.close();
     
     CsvWriter keyWriter = new CsvWriter("key.csv", ',', Charset.forName("UTF-8"));
     System.out.println("writing keys...");
     for(KeyWord key : keyWordMap.values())
     {   
        System.out.println(key.getKey());
        key.toCSV(keyWriter);
     }
     keyWriter.close();
  }
}
