import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.PrintStream;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.BosseBL.JuliaScreen;
import org.BosseBL.JuliaSet;

public class FractaleTestClass
  extends JPanel implements MouseListener
{
  int centerX;
  int centerY;
  int width;
  int height;
  double scale;
  BufferedImage mandelbrotImg;
  JuliaScreen juliaImg;
  JPanel juliaPattern;
  JuliaSet js;
  float juliaValue;
  float colorScale;
  int mouseX;
  int mouseY;
  int cIm;
  int cRe;
  JLabel message;
  JuliaScreen juliaImage;
  
  public FractaleTestClass()
  {
    initValues();
    
    mouseX = centerX;
    mouseY = centerY;
    
    js = new JuliaSet();
    
    mandelbrotImg = new BufferedImage(width, height, 2);
    juliaImg = new JuliaScreen(width, height, js);
    

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
    g2.drawImage(juliaImg, 650, 0, this);
  }
  



  private void drawMandelbrot()
  {
    Graphics2D g2 = mandelbrotImg.createGraphics();
    for (int x = 0; x < width; x++) {
      double re = (x - centerX) * scale;
      for (int y = 0; y < height; y++) {
        double im = (y - centerY) * scale;
        re = (x - centerX) * scale;
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
      System.out.println(mouseX + ", " + mouseY);
      if (mouseX <= width) {
        initValues();
        juliaImg.resetZoom();
        js.setC((mouseX - centerX) * scale, (mouseY - centerY) * scale);
        message.setText(String.format("%.2f", new Object[] { Double.valueOf((mouseY - centerY) * scale) }) + "i, " + String.format("%.2f", new Object[] { Double.valueOf((mouseX - centerX) * scale) }));
      }
      else if (mouseX > width) {
        juliaImg.zoom(mouseX - width, mouseY);
      }
      long time = System.currentTimeMillis();
      juliaImg.drawJulia();
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
    width = 650;
    height = 650;
    centerX = (width / 2 + 150);
    centerY = (height / 2);
    
    scale = (3.0D / width);
  }
  
  public void mouseEntered(MouseEvent arg0) {}
  
  public void mouseExited(MouseEvent arg0) {}
  
  public void mousePressed(MouseEvent arg0) {}
  
  public void mouseReleased(MouseEvent arg0) {}
}
