import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import org.BosseBL.LoopQueue;





public class BlobAnalysis
  extends JPanel
  implements ActionListener
{
  JFileChooser fileChooser;
  JButton openButton;
  JButton saveButton;
  JLabel img1;
  JLabel img2;
  JButton nextButton;
  JButton lastButton;
  JRadioButton rainbowRadio;
  JRadioButton largestRadio;
  JRadioButton orderedRadio;
  ButtonGroup radioGroup;
  JButton genButton;
  LoopQueue<Image> image2;
  Image image1;
  
  BlobAnalysis()
  {
    super.setName("BlobAnalysis");
    super.setLayout(new GridBagLayout());
    

    fileChooser = new JFileChooser();
    JButton openButton = new JButton("open");
    openButton.addActionListener(this);
    JButton saveButton = new JButton("save");
    saveButton.addActionListener(this);
    
    img1 = new JLabel();
    image1 = null;
    img2 = new JLabel();
    image2 = new LoopQueue();
    
    nextButton = new JButton(">>");
    nextButton.addActionListener(this);
    lastButton = new JButton("<<");
    lastButton.addActionListener(this);
    
    rainbowRadio = new JRadioButton("Rainbow");
    largestRadio = new JRadioButton("Largest");
    orderedRadio = new JRadioButton("Order");
    radioGroup = new ButtonGroup();
    radioGroup.add(largestRadio);
    radioGroup.add(rainbowRadio);
    radioGroup.add(orderedRadio);
    rainbowRadio.setSelected(true);
    
    genButton = new JButton("Paint");
    genButton.addActionListener(this);
    

    GridBagConstraints c = new GridBagConstraints();
    
    gridx = 0;gridy = 0;gridwidth = 2;gridheight = 2;
    super.add(img1, c);
    gridy = 2;gridheight = 3;
    super.add(img2);
    gridy = 5;gridwidth = 1;gridheight = 1;
    super.add(lastButton);
    gridx = 0;
    super.add(nextButton);
    gridx = 2;
    super.add(genButton);
    gridy = 4;
    super.add(rainbowRadio);
    gridy = 3;
    super.add(orderedRadio);
    gridy = 2;
    super.add(largestRadio);
    gridy = 1;
    super.add(saveButton);
    gridy = 0;
    super.add(openButton);
  }
  

  public void actionPerformed(ActionEvent e)
  {
    if ((e.getSource() == nextButton) && 
      (image2.size() > 1)) {
      img2.setIcon(new ImageIcon((Image)image2.next()));
    }
    
    if ((e.getSource() == lastButton) && 
      (image2.size() > 1)) {
      img2.setIcon(new ImageIcon((Image)image2.next()));
    }
    
    if ((e.getSource() == genButton) && 
      (img1 != null)) {
      if (rainbowRadio.isSelected()) {
        paintRainbow();
      }
      if (largestRadio.isSelected()) {
        paintLargest();
      }
      if (orderedRadio.isSelected()) {
        paintOrdered();
      }
    }
    
    e.getSource();
    

    e.getSource();
  }
  
  private void paintOrdered() {}
  
  private void paintLargest() {}
  
  private void paintRainbow() {}
}
