import java.util.Date;


class RowEntry
{
   String fach;
   String ects;
   Boolean wf;
   String note1;
   String note2;
   String note3;
   Date datum1;
   Date datum2;
   Date datum3;

   public RowEntry(String fach, String ects, Boolean wf, String note1, String note2, String note3, Date datum1, Date datum2, Date datum3 )
   {
      this.fach = fach;
      this.ects = ects;
      this.wf = wf;
      this.note1 = note1;
      this.note2 = note2;
      this.note3 = note3;
      this.datum1 = datum1;
      this.datum2 = datum2;
      this.datum3 = datum3;
   }

   public String getFach()
   {
      return fach;
   }

   public String getEcts()
   {
      return ects;
   }

   public Boolean getWf()
   {
      return wf;
   }

   public String getNote1()
   {
      return note1;
   }
   
   public String getNote2()
   {
      return note2;
   }
   
   public String getNote3()
   {
      return note3;
   }
   
   public Date getDate1()
   {
      return datum1;
   }
   
   public Date getDate2()
   {
      return datum2;
   }
   
   public Date getDate3()
   {
      return datum3;
   }
   
}
