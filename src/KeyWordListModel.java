import java.util.Set;
import java.util.Vector;

import javax.swing.AbstractListModel;


public class KeyWordListModel extends AbstractListModel
{
   private static final long serialVersionUID = 1L;

   Vector<String> listData = new Vector<String>();

   public KeyWordListModel()
   {
      super();
   }
   
   @Override
   public Object getElementAt(int index)
   {
      return listData.get(index);
   }

   @Override
   public int getSize()
   {
      return listData.size();
   }

   public void setList(Set<String> list)
   {
      if(list == null) return;

      for(String key : list)
      {
         listData.add(key);
      }
   }
}
