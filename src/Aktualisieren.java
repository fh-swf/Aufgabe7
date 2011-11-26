import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;

public class Aktualisieren implements Action
{

   MainWindow parent;
   NoteTableModel model;
   double schnitt, ects, note;
   int ectsSum;
   
   public Aktualisieren( MainWindow parent, NoteTableModel model)
   {
      this.parent = parent;
      this.model = model;
      this.schnitt = this.ects = this.note = 0.0;
      this.ectsSum = 0;
       
   }

   @Override
   public void addPropertyChangeListener(PropertyChangeListener listener)
   {
      // TODO Auto-generated method stub

   }

   @Override
   public Object getValue(String key)
   {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public boolean isEnabled()
   {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public void putValue(String key, Object value)
   {
      // TODO Auto-generated method stub

   }

   @Override
   public void removePropertyChangeListener(PropertyChangeListener listener)
   {
      // TODO Auto-generated method stub

   }

   @Override
   public void setEnabled(boolean b)
   {
      // TODO Auto-generated method stub

   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
	   schnitt = ects = note = 0.0;
	   ectsSum = 0;
	   
	   parent.getFachMap().elementAt(0).setNote1(parent.getBachelor());
	   parent.getFachMap().elementAt(0).setNote2(parent.getColo());
	   
	   for(Fach fach : parent.getFachMap())
	     {
		   if (fach.getFach().equalsIgnoreCase("Abschlussarbeit"))
		   {
			   note = Double.parseDouble(parent.noteStrings[fach.getNote1()]);   // Bachelor Note
			   ects = Double.parseDouble(parent.ectsStrings[fach.getEcts()]);
			   if (note != 0.0)
			   {
				   ectsSum += Integer.parseInt(parent.ectsStrings[fach.getEcts()]);
				   schnitt += note * ects;
			   }
			   
			   note = Double.parseDouble(parent.noteStrings[fach.getNote2()]);   // Kolloquium Note
			   ects = Double.parseDouble(parent.ectsStrings[fach.getNote3()]);
			   if (note != 0.0)
			   {
				   ectsSum += Integer.parseInt(parent.ectsStrings[fach.getNote3()]);
				   schnitt += note * ects;
			   }
			   
		   }
		   else
		   {
			   if (fach.getNote3() != 0)
			      {
				   note = Double.parseDouble(parent.noteStrings[fach.getNote3()]);   // Versuch3
			      }
			      else if (fach.getNote2() != 0)
			      {
			       note = Double.parseDouble(parent.noteStrings[fach.getNote2()]);   // Versuch1
			      }
			      else if (fach.getNote1() != 0)
			      {
			       note = Double.parseDouble(parent.noteStrings[fach.getNote1()]);   // Versuch2
			      }

			   ects = Double.parseDouble(parent.ectsStrings[fach.getEcts()]);
			   ectsSum += Integer.parseInt(parent.ectsStrings[fach.getEcts()]);
			   schnitt += note * ects;
		   }
		   		   
	     }
	   schnitt = schnitt / ectsSum;
	   parent.setSchnitt(String.valueOf(schnitt));
	   parent.setEctsSum(String.valueOf(ectsSum));
   }
}
