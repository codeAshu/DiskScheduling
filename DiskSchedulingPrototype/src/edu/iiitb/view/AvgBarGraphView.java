/******************************************************************************************************
 * AvgBarGraphView.java
 * This File contains the code for showing bar chart for different algorithms and their seek time.
 * It gives a comparative analysis on the results. X axis shows Algorithms and Y axis show the respective seek Time
 *******************************************************************************************************/



package edu.iiitb.view;
import javax.swing.JFrame;

import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.*;
import java.awt.*;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.CategoryPlot;

public class AvgBarGraphView extends JFrame {
	Color color = new Color(0,102,204);
	private static final long serialVersionUID = 1L;

	/******************************************************************************************************
	 * This function takes the values to show on bar charts and output the Bar chart for each Algorithms
	 * @param h 
	 * @param g 
	 *******************************************************************************************************/
	public void createchart(int a, int b,int c,int d,int e,int f,int g, int h, String str){
	
	
	  DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	  dataset.setValue(a, "Marks", "FCFS");							//naming on X axis
	  dataset.setValue(b, "Marks", "SSTF");
	  dataset.setValue(c, "Marks", "SCAN");
	  dataset.setValue(d, "Marks", "CSCAN");
	  dataset.setValue(e, "Marks", "C-LOOK");
	  dataset.setValue(f, "Marks", "LOOK");
	  dataset.setValue(g, "Marks", "3-Step Scan");
	  dataset.setValue(h, "Marks", "Pickup");
	  	  
	  JFreeChart chart = ChartFactory.createBarChart			//create Bar chart with headings and labels
	  (str,"Algorithms", "Average Access Time", dataset, 
	  PlotOrientation.VERTICAL, false,true, false);
	  
	
	//trying to change bar colour
	  

	  chart.setBackgroundPaint(Color.GRAY);
	  chart.getTitle().setPaint(Color.black); 
	 
	  CategoryPlot p = chart.getCategoryPlot();
	  
	  BarRenderer renderer = (BarRenderer) p.getRenderer();
	  
	Paint gp0 = new GradientPaint(
	            0.0f, 0.0f, color, 
	            0.0f, 0.0f, Color.lightGray
	        );
	  
	renderer.setSeriesPaint(0, gp0);
	
	  //renderer.setFillPaint(gp0);
	   
	  chart.setBackgroundPaint(Color.orange);
	  
	  p.setRangeGridlinePaint(Color.BLACK); 
	  
	  ChartFrame frame1=new ChartFrame("Bar Chart",chart);		//setting the Bar Chart
	  frame1.setVisible(true);
	  frame1.setSize(400,350);									//size of the chart
	  }
	

	
}
