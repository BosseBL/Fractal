package org.BosseBL;

public class DataElement implements Comparable<DataElement>
{
  int value;
  int index;
  String type;
  
  public DataElement(int v, String str, int i) {
    value = v;
    type = new String(str);
    index = i;
  }
  
  public int getValue() { return value; }
  public String getType() { return type; }
  public int getIndex() { return index; }
  
  public void setValue(int v) { value = v; }
  public void setType(String t) { type = t; }
  
  public void set(int v, String t) { value = v;
    type = t;
  }
  
  public int compareTo(DataElement o)
  {
    if (getValue() > o.getValue()) return 1;
    if (getValue() > o.getValue()) return -1;
    return 0;
  }
  
  public DataElement clone() {
    return new DataElement(value, type, index);
  }
}
