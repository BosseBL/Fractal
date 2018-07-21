package org.BosseBL;

import java.util.ArrayList;

public class DataElementList
  extends ArrayList<DataElement>
{
  public DataElementList(int[] v, String[] str)
  {
    if (v.length != str.length) { throw new RuntimeException("DataElementList constructor parameters unmatched lengths");
    }
    for (int i = 0; i < v.length; i++) {
      add(new DataElement(v[i], str[i], i));
    }
  }
  

  public DataElementList(int[] v, String s)
  {
    for (int i = 0; i < v.length; i++) {
      add(new DataElement(v[i], s, i));
    }
  }
  

  public DataElementList() {}
  

  public int compare(int i1, int i2)
  {
    return ((DataElement)get(i1)).compareTo((DataElement)get(i2));
  }
  
  public void swap(int i1, int i2) {
    DataElement d = (DataElement)get(i1);
    set(i1, (DataElement)get(i2));
    set(i2, d);
  }
  
  public void reverseOrder() {
    for (int i = 0; i < size() - i - 1; i++) {
      swap(i, size() - i - 1);
    }
  }
  
  public float getSortLevel() {
    int n = size();
    float missplaced = 0.0F;
    for (int i = 0; i < n - 1; i++) {
      if (((DataElement)get(i)).getValue() > ((DataElement)get(i + 1)).getValue()) missplaced += 1.0F;
    }
    return missplaced / n;
  }
  
  public DataElementList clone() {
    DataElementList listClone = new DataElementList();
    for (DataElement de : this) {
      listClone.add(de.clone());
    }
    return listClone;
  }
}
