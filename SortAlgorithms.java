import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import org.BosseBL.BarChart;
import org.BosseBL.DataElement;
import org.BosseBL.DataElementList;
import org.BosseBL.SortMonitor;










public class SortAlgorithms
  extends JPanel
  implements ActionListener
{
  JSpinner numberOfElements;
  JButton generateElementsButton;
  JButton flipOrder;
  JButton sort;
  JButton nextItteration;
  JButton lastItteration;
  JTextField elementInput;
  JButton setInputValue;
  JRadioButton almostSorted;
  JRadioButton notSorted;
  ButtonGroup initialSorting;
  JLabel message;
  JComboBox algorithms;
  BarChart chart;
  JPanel resultPanel;
  JPanel elementGeneratingPanel;
  JPanel sortPanel;
  JPanel messagePanel;
  DataElementList elements;
  ArrayList<DataElementList> sortLog;
  int chartIndex;
  SortMonitor sortingMachine;
  
  SortAlgorithms()
  {
    super.setName("SortAlgorithms");
    super.setLayout(new GridBagLayout());
    
    GridBagConstraints c = new GridBagConstraints();
    
    nextItteration = new JButton(">>");
    nextItteration.addActionListener(this);
    lastItteration = new JButton("<<");
    lastItteration.addActionListener(this);
    chart = new BarChart("Data");
    resultPanel = new JPanel();
    
    gridx = 0;
    gridy = 0;
    gridheight = 2;
    super.add(resultPanel, c);
    
    gridx = 1;
    gridheight = 2;
    ipady = 20;
    super.add(chart, c);
    
    gridx = 2;
    gridheight = 1;
    ipady = 0;
    super.add(nextItteration, c);
    gridy = 1;
    anchor = 11;
    super.add(lastItteration, c);
    

    message = new JLabel("...");
    messagePanel = new JPanel();
    messagePanel.setBackground(Color.WHITE);
    messagePanel.add(message);
    
    gridy = 2;
    gridx = 0;
    gridwidth = 3;
    anchor = 10;
    super.add(messagePanel, c);
    


    elementInput = new JTextField(40);
    setInputValue = new JButton("set values");
    setInputValue.addActionListener(this);
    
    gridx = 0;
    gridy = 3;
    gridwidth = 1;
    super.add(setInputValue, c);
    gridx = 1;
    gridwidth = 2;
    anchor = 17;
    super.add(elementInput, c);
    


    almostSorted = new JRadioButton("sortish");
    notSorted = new JRadioButton("unsorted");
    ButtonGroup initialSorting = new ButtonGroup();
    initialSorting.add(almostSorted);
    initialSorting.add(notSorted);
    initialSorting.setSelected(notSorted.getModel(), true);
    flipOrder = new JButton("Flip order");
    flipOrder.addActionListener(this);
    generateElementsButton = new JButton("Generate Elements");
    generateElementsButton.addActionListener(this);
    SpinnerModel model = new SpinnerNumberModel(new Integer(10), 
      new Integer(1), 
      new Integer(100), 
      new Integer(1));
    numberOfElements = new JSpinner(model);
    

    elementGeneratingPanel = new JPanel();
    
    elementGeneratingPanel.add(almostSorted);
    elementGeneratingPanel.add(notSorted);
    elementGeneratingPanel.add(flipOrder);
    elementGeneratingPanel.add(generateElementsButton);
    elementGeneratingPanel.add(numberOfElements);
    
    gridx = 0;
    gridy = 4;
    gridwidth = 3;
    anchor = 10;
    super.add(elementGeneratingPanel, c);
    


    String[] algorithmsText = { "QuickSort", "MergeSort", "HeapSort", "InsertionSort", "SelectionSort" };
    algorithms = new JComboBox(algorithmsText);
    sort = new JButton("Sort");
    sort.addActionListener(this);
    sortPanel = new JPanel();
    sortPanel.add(sort);
    sortPanel.add(algorithms);
    
    gridy = 5;
    gridx = 0;
    gridwidth = 3;
    anchor = 10;
    super.add(sortPanel, c);
    


    elements = new DataElementList();
    sortLog = new ArrayList();
    chartIndex = 0;
    

    sortingMachine = new SortMonitor();
  }
  


  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() == nextItteration) {
      if (chartIndex < sortLog.size()) {
        chartIndex += 1;
        chart.display((DataElementList)sortLog.get(chartIndex), "chart " + String.valueOf(chartIndex));
      }
      
    }
    else if (e.getSource() == lastItteration) {
      if (chartIndex > 0) {
        chartIndex -= 1;
        String name = chartIndex > 0 ? "chart " + String.valueOf(chartIndex) : "original chart";
        chart.display((DataElementList)sortLog.get(chartIndex), name);

      }
      

    }
    else if (e.getSource() == setInputValue) {
      sortLog.clear();
      String[] str = elementInput.getText().split(" ");
      int value = 0;
      DataElementList tempElementList = new DataElementList();
      try {
        for (int i = 0; i < str.length; i++) {
          value = Integer.parseInt(str[i]);
          if ((value < 0) && (value > 100)) throw new NumberFormatException();
          tempElementList.add(new DataElement(value, "element", i));
        }
      } catch (NumberFormatException exception) {
        message.setText("Check the input format. Only numbers between 0 - 100 is allowed ");
      }
      elements = tempElementList;
      chart.display(elements, "unsorted");

    }
    else if (e.getSource() == flipOrder) {
      sortLog.clear();
      elements.reverseOrder();
      chart.display(elements, "unsorted");

    }
    else if (e.getSource() == generateElementsButton) {
      sortLog.clear();
      
      int n = ((Number)numberOfElements.getValue()).intValue();
      
      elements.clear();
      for (int i = 0; i < n; i++) {
        elements.add(new DataElement((int)(100.0D * Math.random()), "element", i));
      }
      

      if (notSorted.isSelected()) {
        chart.display(elements, "unsorted");

      }
      else if (almostSorted.isSelected()) {
        int i;
        for (; elements.getSortLevel() > 0.2D; 
            i < n - 1) { i = 0; continue;
          int comp = elements.compare(i, i + 1);
          if (comp > 0) elements.swap(i, i + 1);
          i++;
        }
        


        chart.display(elements, "almost sorted");


      }
      



    }
    else if (e.getSource() == sort) {
      String alg = (String)algorithms.getSelectedItem();
      if (alg.compareTo("QuickSort") == 0) {
        sortingMachine.quickSort(elements, sortLog);
      }
      if (alg.compareTo("MergeSort") == 0) {
        sortingMachine.mergeSort(elements, sortLog);
      }
      
      if (alg.compareTo("HeapSort") == 0) {
        sortingMachine.heapSort(elements, sortLog);
      }
      
      if (alg.compareTo("InsertionSort") == 0) {
        sortingMachine.insertionSort(elements, sortLog);
      }
      
      if (alg.compareTo("SelectionSort") == 0) {
        sortingMachine.selectionSort(elements, sortLog);
      }
    }
  }
}
