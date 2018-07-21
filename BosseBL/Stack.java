package org.BosseBL;

import java.util.ArrayList;

public class Stack<T>
{
  ArrayList<T> stack;
  int topPointer;
  
  public Stack() {
    stack = new ArrayList();
    topPointer = -1;
  }
  
  public void push(T element) {
    stack.add(element);
    topPointer += 1;
  }
  
  public boolean pop() {
    if (topPointer >= 0) {
      stack.remove(topPointer);
      topPointer -= 1;
      return true;
    }
    return false;
  }
  
  public T peek() {
    if (topPointer >= 0) {
      return stack.get(topPointer);
    }
    return null;
  }
  
  public boolean isEmpty() {
    if (topPointer == -1) return true;
    return false;
  }
  
  public int getTopIndex() {
    return topPointer;
  }
  
  public void flush() {
    stack.clear();
    topPointer = -1;
  }
}
