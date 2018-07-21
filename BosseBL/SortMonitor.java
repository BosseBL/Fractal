package org.BosseBL;

import java.util.ArrayList;

public class SortMonitor
{
  int nOfSwap;
  int nOfComparison;
  int maxRecursionLevel;
  DataElementList data;
  
  public SortMonitor()
  {
    data = new DataElementList();
  }
  
  public void quickSort(DataElementList d, ArrayList<DataElementList> log)
  {
    reset(d, log);
  }
  


  public void mergeSort(DataElementList d, ArrayList<DataElementList> log)
  {
    reset(d, log);
  }
  
  public void insertionSort(DataElementList d, ArrayList<DataElementList> log)
  {
    reset(d, log);
  }
  
  public void heapSort(DataElementList d, ArrayList<DataElementList> log) {
    reset(d, log);
  }
  
  public void selectionSort(DataElementList d, ArrayList<DataElementList> log)
  {
    reset(d, log);
  }
  


  private void reset(DataElementList d, ArrayList<DataElementList> log)
  {
    nOfSwap = 0;
    nOfComparison = 0;
    maxRecursionLevel = 0;
    log.clear();
    data = d.clone();
  }
}
