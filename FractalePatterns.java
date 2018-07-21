import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.PrintStream;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.BosseBL.JuliaSet;






























































































































public class FractalePatterns
  extends JPanel
  implements MouseListener
{
  int centerX;
  int centerY;
  int width;
  int height;
  double scale;
  BufferedImage mandelbrotImg;
  BufferedImage juliaImg;
  JPanel juliaPattern;
  double im;
  double re;
  JuliaSet js;
  float juliaValue;
  float colorScale;
  int mouseX;
  int mouseY;
  int cIm;
  int cRe;
  JLabel message;
  
  public FractalePatterns()
  {
    initValues();
    mouseX = centerX;
    mouseY = centerY;
    
    mandelbrotImg = new BufferedImage(width, height, 2);
    juliaImg = new BufferedImage(width, height, 2);
    
    js = new JuliaSet();
    colorScale = (1.0F / js.getRecursionLimit());
    
    drawMandelbrot();
    
    super.addMouseListener(this);
    
    message = new JLabel((mouseX - centerX) * scale + "i, " + (mouseY - centerY) * scale);
    super.add(message);
    
    super.setBounds(0, 0, width * 2, height * 2);
  }
  
  public void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D)g;
    
    g2.drawImage(mandelbrotImg, 0, 0, this);
    g2.drawImage(juliaImg, 700, 0, this);
  }
  
  private void drawJulia() {
    Graphics2D g2 = juliaImg.createGraphics();
    
    js.setC((mouseX - centerX) * scale, (mouseY - centerY) * scale);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        im = ((y - centerY) * scale);
        re = ((x - centerX + 150) * scale);
        
        juliaValue = js.calculateJuliaPoint(im, re);
        
        if (juliaValue == 0.0F) { g2.setColor(Color.BLACK);
        } else {
          g2.setColor(new Color(juliaValue * 1.0F, (float)(juliaValue * 0.7D), (float)(juliaValue * 0.45D)));
        }
        g2.drawLine(x, y, x, y);
      }
    }
    g2.dispose();
  }
  
  private void drawMandelbrot() {
    Graphics2D g2 = mandelbrotImg.createGraphics();
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        im = ((y - centerY) * scale);
        re = ((x - centerX) * scale);
        js.setC(im, re);
        
        juliaValue = js.calculateJuliaPoint(0.0D, 0.0D);
        
        if (juliaValue == 0.0F) { g2.setColor(Color.BLACK);
        } else {
          g2.setColor(new Color(1.0F - juliaValue * 1.0F, (float)(1.0D - juliaValue * 0.7D), (float)(1.0D - juliaValue * 0.45D)));
        }
        g2.drawLine(x, y, x, y);
      }
    }
    g2.dispose();
  }
  
  public void mouseClicked(MouseEvent e)
  {
    if (e.getButton() == 1) {
      mouseX = e.getX();
      mouseY = e.getY();
      if (mouseX < width) {
        initValues();
        message.setText(String.format("%.2f", new Object[] { Double.valueOf((mouseX - centerX) * scale) }) + "i, " + String.format("%.2f", new Object[] { Double.valueOf((mouseY - centerY) * scale) }));
      }
      else if (mouseX < width * 2) {
        zoomValues();
      }
      long time = System.currentTimeMillis();
      drawJulia();
      System.out.println(System.currentTimeMillis() - time);
      super.repaint();
    }
  }
  
  public void zoomValues()
  {
    centerX = (width / 2 + 150 + mouseX - width);
    centerY = (height / 2 - mouseY);
    scale *= 0.8D;
  }
  
  public void initValues() {
    width = 700;
    height = 700;
    centerX = (width / 2 + 150);
    centerY = (height / 2);
    
    scale = (3.0D / width);
  }
  
  public void mouseEntered(MouseEvent arg0) {}
  
  public void mouseExited(MouseEvent arg0) {}
  
  public void mousePressed(MouseEvent arg0) {}
  
  public void mouseReleased(MouseEvent arg0) {}
}
