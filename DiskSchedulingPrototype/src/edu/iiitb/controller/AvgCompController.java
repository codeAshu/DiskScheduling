package edu.iiitb.controller;
import  static edu.iiitb.view.ScheduleGraphView.*;

import edu.iiitb.view.AvgBarGraphView;;
public class AvgCompController {

	int[] array=new int[8];
	ScheduleGraphController sg = new ScheduleGraphController();
	AvgBarGraphView dis = new AvgBarGraphView();
	
	int[] output = new int[201];
	int[] ans = new int[201];
	
	

	
	public void showComp(String trackInput, int starting)
	{
		
		
		total = count(trackInput);
		
		for (int i = 0; i < total; i++) {
			System.out.println("\n"+Input[i]);		
		}
	System.out.println("starting  "+starting);
		
		output = sg.fcfscal(Input,starting);
		array[0] = output[total];
		System.out.println("printing avg comparision values\n\n");
		System.out.println(array[0]);
		
		
		output = sg.sstfcal(Input,starting);
		array[1] = output[total];
		System.out.println(array[1]);
		
		output = sg.scancal(Input,starting);
		array[2] = output[total];
		System.out.println(array[2]);
		
		
		output = sg.cscancal(Input,starting);
		array[3] = output[total];
		System.out.println(array[3]);
		
		output = sg.lookcal(Input,starting);
		array[4] = output[total];
		System.out.println(array[4]);
		
		output = sg.clookcal(Input,starting);
		array[5] = output[total];
		System.out.println(array[5]);
		
		output = sg.stepcal(Input,starting);
		array[6] = output[total];
		System.out.println(array[6]);
		
		output = sg.pickupcal(Input,starting);
		array[7] = output[total];
		System.out.println(array[7]);
		
		dis.createchart(array[0], array[1], array[2], array[3], array[4], array[5],array[6],array[7],"Average Comparision");
	}
}
