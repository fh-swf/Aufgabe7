import java.util.Date;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;


class NoteTableModel extends AbstractTableModel {
   private static final long serialVersionUID = 1L;
   private String[] headers = {"Fach", "ECTS", "Wahlfach", "Note1", "Note2", "Note3", "Datum1", "Datum2", "Datum3" };

   @SuppressWarnings({ "rawtypes" })
   Class[] columnClasses = { String.class, String.class, Boolean.class, String.class, String.class, String.class, Date.class, Date.class, Date.class };

   Vector<RowEntry> data = new Vector<RowEntry>();

   @Override
   public int getColumnCount() {
       return headers.length;
   }

   @Override
   public int getRowCount() {
       return data.size();
   }

   @SuppressWarnings({ "unchecked", "rawtypes" })
   public Class getColumnClass(int c) {
       return columnClasses[c];
   }

   public String getColumnName(int c) {
       return headers[c];
   }

   public boolean isCellEditable(int r, int c) {
       return false;
   }

   @Override
   public Object getValueAt(int r, int c) {
       RowEntry e = data.get(r);
       switch (c) {
       case 0:
           return e.getFach();
       case 1:
           return e.getEcts();
       case 2:
           return e.getWf();
       case 3:
           return e.getNote1();
       case 4:
    	   return e.getNote2();
       case 5:
    	   return e.getNote3();
       case 6:
    	   return e.getDate1();
       case 7:
    	   return e.getDate2();
       case 8:
    	   return e.getDate3();
       default:
           return null;
       }
   }

   public void setValueAt(Object value, int r, int c) {
   }

   public void addRow(Fach fach, MainWindow frame) {
       int ID = getRowCount();
    
       data.add(ID, new RowEntry(fach.getFach(), frame.ectsStrings[fach.getEcts()], fach.getWf(), frame.noteStrings[fach.getNote1()], frame.noteStrings[fach.getNote2()], frame.noteStrings[fach.getNote3()], fach.getDatum1(), fach.getDatum2(), fach.getDatum3() ));
       fireTableRowsInserted(ID, ID); 
   }

   public void removeRowAt(int r) {
	   r--;
       data.removeElementAt(r);
       fireTableRowsDeleted(r, r);
   }

   public void editRowAt(Fach fach, MainWindow frame, int r) {
	   r--;
	   data.removeElementAt(r);
       fireTableRowsDeleted(r, r);
	   data.insertElementAt(new RowEntry(fach.getFach(), frame.ectsStrings[fach.getEcts()], fach.getWf(), frame.noteStrings[fach.getNote1()], frame.noteStrings[fach.getNote2()], frame.noteStrings[fach.getNote3()], fach.getDatum1(), fach.getDatum2(), fach.getDatum3() ), r);
       fireTableRowsInserted(r, r);
   }
   
   
   public void clear() {
      int rowCount = data.size();
      data.clear();
      fireTableRowsDeleted(0, rowCount);
   }
}

