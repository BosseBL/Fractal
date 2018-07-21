package org.BosseBL;

public class StackUnderflowException extends Exception {
  public StackUnderflowException(String message) {
    super(message);
  }
  
  public StackUnderflowException() {}
}
