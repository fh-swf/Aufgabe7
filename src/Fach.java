import java.io.IOException;
import java.util.Date;

import com.csvreader.CsvWriter;

public class Fach
{
   private String Fach;
   private Integer Ects;
   private Boolean Wf;
   private Integer Key1;
   private Integer Key2;
   private Integer Key3;
   private Date Datum1;
   private Date Datum2;
   private Date Datum3;
   
   public Fach()
   {
      
   }
  
   public Fach(String fach, Integer ects, Boolean wf, Integer key1, Integer key2, Integer key3, Date datum1, Date datum2, Date datum3)
   {
      Fach = fach;
      Ects = ects;
      Wf = wf;
      Key1 = key1;
      Key2 = key2;
      Key3 = key3;
      Datum1 = datum1;
      Datum2 = datum2;
      Datum3 = datum3;
   }

   public String getFach() { return Fach; }
   public Integer getEcts() { return Ects; }
   public Boolean getWf() { return Wf; }
   public Integer getNote1() { return Key1; }
   public Integer getNote2() { return Key2; }
   public Integer getNote3() { return Key3; }
   public Date getDatum1() { return Datum1; }
   public Date getDatum2() { return Datum2; }
   public Date getDatum3() { return Datum3; }
   
   public void setFach(String fach) { Fach = fach; }
   public void setEcts(Integer ects) { Ects = ects; }
   public void setWf(Boolean wf) { Wf = wf; }
   public void setNote1(Integer key1) { Key1 = key1; }
   public void setNote2(Integer key2) { Key2 = key2; }
   public void setNote3(Integer key3) { Key3 = key3; }
   public void setDatum1(Date datum1) { Datum1 = datum1; }
   public void setDatum2(Date datum2) { Datum2 = datum2; }
   public void setDatum3(Date datum3) { Datum3 = datum3; }
   
   public void toCSV(CsvWriter writer)
   {
      String[] record = { Fach, String.valueOf(Ects), String.valueOf(Wf), String.valueOf(Key1), String.valueOf(Key2), String.valueOf(Key3), String.valueOf(Datum1.getTime()), String.valueOf(Datum2.getTime()), String.valueOf(Datum3.getTime()) };
      try
      {
         writer.writeRecord(record);
      }
      catch (IOException e)
      {
         System.err.println("Datei konnte nicht geschrieben werden. CsvWriter closed?!");
      }
   }
   
   public String toString()
   {
      return "Fach: " + Fach + " ECTS: " + Ects.toString() + " Wahlfach: " + Wf.toString() +
    		 " Versuch1: " + Key1.toString() + " Datum: " + Datum1.toString() +
    		 " Versuch2: " + Key2.toString() + " Datum: " + Datum2.toString() + 
             " Versuch3: " + Key3.toString() + " Datum: " + Datum3.toString();
   }
}
