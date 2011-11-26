import java.io.IOException;

import com.csvreader.CsvWriter;

/*
 * $RCSFile$
 *
 * Created on 01.11.2011
 * for Project: Notenverwaltung
 * by Michael Rockstein & Philipp Schäfer
 *
 * (C) 2005-2006 by 
 */

public class KeyWord
{
   private int Key;
   private int KeyID;
   static int KeyCount = 0;
   private int Count;
   
   public KeyWord(Integer keyWord)
   {
      KeyID = KeyCount; 
      KeyCount++;
      Key = keyWord;
      Count = 1;
   }
   
   public KeyWord(int keyID, Integer keyWord, int count)
   {
      this.KeyID = keyID;
      Key=keyWord;
      Count = count;
   }
   
   public Integer getKey()
   {
      return Key;
   }
   
   public int getCount() {
	return Count;
}

public void setCount(int count) {
	Count = count;
}

public void toCSV(CsvWriter writer)
   {
      String[] record = { String.valueOf(KeyID), String.valueOf(Key), String.valueOf(Count) };
      try
      {
         writer.writeRecord(record);
      }
      catch (IOException e)
      {
         System.err.println("Datei konnte nicht geschrieben werden. CsvWriter closed?!");
      }
   }
}
