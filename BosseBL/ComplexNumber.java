package org.BosseBL;


public class ComplexNumber
{
  public double im;
  
  public double re;
  

  public ComplexNumber(double im, double re)
  {
    this.im = im;
    this.re = re;
  }
  
  public ComplexNumber() { im = 0.0D;
    re = 0.0D;
  }
  



  public static ComplexNumber add(ComplexNumber c, ComplexNumber d)
  {
    return new ComplexNumber(im + im, re + re);
  }
  
  public static ComplexNumber multiply(ComplexNumber c, double d) { return new ComplexNumber(im * d, re * d); }
  
  public static ComplexNumber multiply(ComplexNumber c, ComplexNumber d) {
    return new ComplexNumber(im * re + re * im, re * re - im * im);
  }
  
  public static ComplexNumber sub(ComplexNumber c, ComplexNumber d) { return new ComplexNumber(im - im, re - re); }
  
  public static ComplexNumber pow(ComplexNumber c, int i) {
    if (i > 1) {
      return multiply(c, pow(c, i - 1));
    }
    return c;
  }
  
  public static double abs(ComplexNumber c) { return Math.sqrt(Math.pow(im, 2.0D) + Math.pow(re, 2.0D)); }
  
  public double abs()
  {
    return Math.sqrt(Math.pow(im, 2.0D) + Math.pow(re, 2.0D));
  }
  
  public void addRe(double d) { re += d; }
  

  public void addIm(double d) { im += d; }
  
  public void add(double i, double r) {
    im += i;
    re += r;
  }
  


  public void set(double im, double re)
  {
    this.im = im;
    this.re = re;
  }
  
  public void setIm(double im) { this.im = im; }
  
  public void setRe(double re) {
    this.re = re;
  }
  


  public double getRe()
  {
    return re;
  }
  
  public double getIm() { return im; }
}
