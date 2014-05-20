package edu.iiitb.controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import edu.iiitb.model.legacyModel;
import edu.iiitb.view.AvgBarGraphView;


/*********************************************************************************
 *  This class have all the methods to get the connection from database and
 * fetch method to get the value and Write method to insert into database
 ***********************************************************************************/
public class DbController {

	static Connection con;
	//Set up the connection with the database
	public static Connection getConnection(){

		try{
			Class.forName("com.mysql.jdbc.Driver");

			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/godam","root","qwe123"); 	//connection string
		}
		catch(Exception ex)
		{
			ex.printStackTrace();

		}
		return con;
	}

	public static ArrayList<legacyModel> l;								//Legacy Model object used in updating the table

	/******************************************************************************************************
	 *This Function will fetch the data from the legacy table for getting the previous head position
	 *******************************************************************************************************/
	public void fetch(){
		try{
			con=getConnection();

			String str="Select * from legacy;";
			PreparedStatement qu=con.prepareStatement(str);
			ResultSet re=qu.executeQuery();

			l=new ArrayList<legacyModel>();
			while(re.next())
			{

				int type=re.getInt("type");
				int avg=re.getInt("avg");
				int no=re.getInt("no");
				int head=re.getInt("head");

				legacyModel q=new legacyModel(type,avg,no,head);

				l.add(q);


			}

			con.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

	}

	/******************************************************************************************************
	 *This Function will get the avg and and call the create chart function to create chart
	 *******************************************************************************************************/
	public void disp_data(){

		fetch();
		int[] array=new int[8];
		int i=0;
		for (legacyModel dm: l){
			System.out.print(dm.getAvg());
			array[i]=dm.getAvg();						//get the avg from the Legacy model
			i++;
		}

		AvgBarGraphView avg=new AvgBarGraphView();

		//call the create chart to create the bar Graph
		avg.createchart(array[0],array[1],array[2],array[3],array[4],array[5],array[6],array[7],"Legacy Time Comparision");


	}

	/******************************************************************************************************
	 *This Function will fetch the last head position for all the the Algorithms
	 *******************************************************************************************************/
	public int[] fetch_head(){
		fetch();
		int[] array=new int[8];
		int i=0;
		for (legacyModel dm: l){
			//System.out.print(dm.getHead());
			array[i]=dm.getHead();
			i++;
		}

		return array;

	}

	/******************************************************************************************************
	 *This Function will update the legacy table with current data.
	 *******************************************************************************************************/
	public void leg_insert(ArrayList<legacyModel> legacyList){

		try{
			con=getConnection();

			System.out.print("we are upadating legacy");
			for(int i = 0;i<legacyList.size();i++)
			{
				String str="update legacy SET no=no+?,avg=avg+?,head=? where type=?;";
				PreparedStatement qu=con.prepareStatement(str);

				qu.setInt(1, legacyList.get(i).getNo());
				qu.setInt(2, legacyList.get(i).getAvg());
				qu.setInt(3, legacyList.get(i).getHead());
				qu.setInt(4,legacyList.get(i).getType());
				qu.execute();
			}	



			con.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();

		}


	}

}
