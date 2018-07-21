package org.BosseBL;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;






public class JuliaScreen
  extends BufferedImage
{
  private JuliaSet julia;
  private double scale;
  private double dx;
  private double dy;
  private double zoom;
  private int width;
  private int height;
  private int centerX;
  private int centerY;
  
  public JuliaScreen(int width, int height, JuliaSet j)
  {
    super(height, height, 2);
    
    this.width = width;
    this.height = height;
    
    centerX = (width / 2);
    centerY = (height / 2);
    
    dx = -1.5D;
    dy = -1.5D;
    
    julia = j;
    
    scale = 3.0D;
    

    zoom = 1.0D;
    
    drawJulia();
  }
  
  public void drawJulia()
  {
    Graphics2D g2 = super.createGraphics();
    
    double realX = 0.0D;
    double realY = 0.0D;
    double deltaX = (dx - centerX) / zoom;
    double deltaY = (dy - centerY) / zoom;
    for (int x = 0; x < width; x++) {
      realX = x * scale / width + dx;
      for (int y = 0; y < height; y++) {
        realY = y * scale / width + dy;
        float juliaValue = julia.calculateJuliaPoint(realY, realX);
        
        if (juliaValue == 0.0F) { g2.setColor(Color.BLACK);
        } else {
          g2.setColor(new Color((float)Math.abs(0.3D - juliaValue), (float)(Math.abs(1.0F - juliaValue) * 0.4D), (float)(Math.abs(0.7D - juliaValue) * 0.8D)));
        }
        g2.drawLine(x, y, x, y);
      }
    }
    g2.dispose();
  }
  
  public void zoom(int x, int y) {
    zoom = (1.0D / (1.0D + zoom));
    dx = (dx + x * scale / width - scale / 4.0D);
    dy = (dy + y * scale / height - scale / 4.0D);
    
    scale /= 2.0D;
  }
  
  public void resetZoom() {
    zoom = 1.0D;
    dx = -1.5D;
    dy = -1.5D;
    scale = 3.0D;
  }
  
  private double getRealX(int x) {
    return scale * x;
  }
  
  private double getRealY(int y) { return scale * y; }
}
