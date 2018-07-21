import javax.swing.JFrame;
import javax.swing.JPanel;

public class Laboration1 extends JFrame
{
  Laboration1(JPanel panel)
  {
    super(panel.getName());
    super.setDefaultCloseOperation(3);
    super.setSize(1300, 650);
    super.add(panel);
    super.setVisible(true);
  }
  





  public static void main(String[] args)
  {
    FractaleTestClass fts = new FractaleTestClass();
    
    Laboration1 labb = new Laboration1(fts);
  }
}
