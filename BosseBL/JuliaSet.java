package org.BosseBL;


public class JuliaSet
{
  private ComplexNumber c;
  
  private ComplexNumber tempC;
  
  private ComplexNumber point;
  private int recursionLimit;
  private double escapeDist;
  private int recursionCounter;
  
  public JuliaSet(ComplexNumber c)
  {
    this.c = c;
    recursionLimit = 30;
    escapeDist = 30.0D;
    point = new ComplexNumber(0.0D, 0.0D);
    tempC = new ComplexNumber();
  }
  
  public JuliaSet() { c = new ComplexNumber(0.0D, 0.0D);
    recursionLimit = 100;
    escapeDist = 80.0D;
    point = new ComplexNumber(0.0D, 0.0D);
    tempC = new ComplexNumber();
  }
  


  public int calculateJuliaPointRec(double im, double re)
  {
    point.set(im, re);
    recursionCounter = 0;
    
    point = juliaFunction(point, c);
    
    if (point.abs() > escapeDist) return recursionCounter;
    return 0;
  }
  
  public float calculateJuliaPoint(double im, double re) { point.set(im, re);
    recursionCounter = 0;
    
    while ((point.abs() < escapeDist) && (recursionCounter < recursionLimit)) {
      recursionCounter += 1;
      point = ComplexNumber.add(ComplexNumber.pow(point, 2), c);
    }
    
    if (point.abs() > escapeDist) return recursionCounter / recursionLimit;
    return 0.0F;
  }
  
  public int calculateJuliaPointRec(ComplexNumber d) {
    return calculateJuliaPointRec(d.getIm(), d.getRe());
  }
  
  public float calculateJuliaPoint(ComplexNumber d) { return calculateJuliaPoint(d.getIm(), d.getRe()); }
  

  public boolean isBounded()
  {
    if (calculateJuliaPointRec(0.0D, 0.0D) == 0) return true;
    return false;
  }
  
  private ComplexNumber juliaFunction(ComplexNumber p, ComplexNumber bigC) {
    if ((recursionCounter < recursionLimit) && (p.abs() < escapeDist)) {
      recursionCounter += 1;
      return juliaFunction(ComplexNumber.add(ComplexNumber.pow(p, 2), bigC), bigC);
    }
    return p;
  }
  
  public float calculateMandelbrotPoint(double im, double re) { tempC.set(c.getIm(), c.getRe());
    c.set(im, re);
    float mandelbrotValue = calculateJuliaPoint(0.0D, 0.0D);
    c = tempC;
    return mandelbrotValue;
  }
  


  public void setEscapeDist(double d)
  {
    escapeDist = d;
  }
  
  public void setRecursionLimit(int i) { recursionLimit = i; }
  
  public void setC(ComplexNumber c) {
    this.c = c;
  }
  
  public void setC(double im, double re) { c.set(im, re); }
  



  public int getRecursionLimit()
  {
    return recursionLimit;
  }
  
  public double getEscapeDist() { return escapeDist; }
  
  public ComplexNumber getC() {
    return c;
  }
}
