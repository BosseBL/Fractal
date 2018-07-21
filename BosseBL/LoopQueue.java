package org.BosseBL;

import java.util.ArrayList;

public class LoopQueue<T> extends ArrayList<T>
{
  int index;
  
  public LoopQueue()
  {
    index = 0;
  }
  
  public T last() {
    if (super.size() == 0) return null;
    if (index == 0) index = (super.size() - 1); else
      index -= 1;
    return super.get(index);
  }
  
  public T next() { if (super.size() == 0) return null;
    if (index == super.size() - 1) index = 0; else
      index += 1;
    return super.get(index);
  }
  
  public T current() { if (super.size() == 0) return null;
    return super.get(index);
  }
  
  public void addCurrent(T element) { super.add(index, element); }
  
  public void removeCurrent() {
    super.remove(index);
  }
  
  public void toStart() { index = 0; }
}
