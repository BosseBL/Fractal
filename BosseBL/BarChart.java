package org.BosseBL;

import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;








public class BarChart
  extends JPanel
{
  private static final long serialVersionUID = 1L;
  ChartPanel chartPanel;
  
  public BarChart(String chartTitle)
  {
    DefaultCategoryDataset dataset = createDataset(new int[0]);
    
    JFreeChart chart = createChart(dataset, chartTitle);
    
    chartPanel = new ChartPanel(chart);
    
    chartPanel.setPreferredSize(new Dimension(500, 270));
    
    add(chartPanel);
  }
  



  private DefaultCategoryDataset createDataset(int[] elements)
  {
    DefaultCategoryDataset result = new DefaultCategoryDataset();
    for (int i = 0; i < elements.length; i++) {
      result.setValue(elements[i], "value", String.valueOf(i));
    }
    return result;
  }
  



  private JFreeChart createChart(DefaultCategoryDataset dataset, String title)
  {
    JFreeChart chart = ChartFactory.createBarChart(title, "index", "value", 
      dataset, PlotOrientation.VERTICAL, 
      true, 
      true, 
      false);
    
    return chart;
  }
  
  public void display(DataElementList datalist, String title) {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    
    for (int i = 0; i < datalist.size(); i++) {
      DataElement dataelement = (DataElement)datalist.get(i);
      dataset.addValue(dataelement.getValue(), dataelement.getType(), String.valueOf(dataelement.getIndex()));
    }
    chartPanel.setChart(createChart(dataset, title));
  }
}
