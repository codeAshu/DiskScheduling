

package edu.iiitb.controller;
import  static edu.iiitb.view.ScheduleGraphView.*;

import java.util.ArrayList;

import edu.iiitb.model.legacyModel;

/************************************************************************************************************************
 * LegacyController.java
 * 
 * This File contains the code for all the algorithms It take trackInput as input and return
 * access sequence and seek time and final header positions for all the algorithms implemented for legacy performance.
 * Previous Head position is retrieved from Table for each Algorithms. Than it will call the AvgBarGraphView to show the 
 * performance of each algorithm on all the inputs till now
 ******************************************************************************************************************************/

public class LegacyController {

	DbController db = new DbController();
	ScheduleGraphController sg = new ScheduleGraphController();
	
	ArrayList<legacyModel> legacyList = new ArrayList<legacyModel>();
	
	int[] array=new int[6];
	int[] output = new int[201];

	public void process(String trackInput)
	{
		total = count(trackInput);					//getting the number of inputs from count function
		
		array = db.fetch_head();					//fetching the previous head positions for each algorithms
		System.out.println("array "+array[0]);

		output = sg.fcfscal(Input,array[0]);		//FCFS
		legacyModel lm = new legacyModel(0, output[total], total, output[total-1]);
		legacyList.add(lm);							//add the output to the list which will again update the existing data	
		
		for(int o:output)
			System.out.print(o+" ");				
		System.out.println("");
		
		
		
		output = sg.sstfcal(Input,array[1]);							//SSTF legacy calculation
		lm = new legacyModel(1, output[total], total, output[total-1]);
		legacyList.add(lm);
		
		for(int o:output)
			System.out.print(o+" ");
		System.out.println("");
		
		output = sg.scancal(Input,array[2]);							//S-SCAN legacy calculation
		lm = new legacyModel(2, output[total], total, output[total-1]);
		legacyList.add(lm);
		
		for(int o:output)
			System.out.print(o+" ");
		System.out.println("");
		
		
		output = sg.cscancal(Input,array[3]);							//C-SCAN legacy calculation
		lm = new legacyModel(3, output[total], total, output[total-1]);
		legacyList.add(lm);
		
		for(int o:output)
			System.out.print(o+" ");
		System.out.println("");
		
		
		output = sg.lookcal(Input,array[4]);							//LOOK legacy calculation
		 lm = new legacyModel(4, output[total], total, output[total-1]);
		legacyList.add(lm);
		
		for(int o:output)
			System.out.print(o+" ");
		System.out.println("");
		
		
		output = sg.clookcal(Input,array[5]);							//C-LOOK Calculation
		 lm = new legacyModel(5, output[total], total, output[total-1]);
		legacyList.add(lm);
		
		for(int o:output)
			System.out.print(o+" ");
		System.out.println("");
		
		

		db.leg_insert(legacyList);									//update Legacy data
		
		
		
		

	}

}
