

package edu.iiitb.controller;

import static edu.iiitb.view.ScheduleGraphView.*;

import java.util.ArrayList;
import java.util.Arrays;
import edu.iiitb.model.ScheduleModel;
/******************************************************************************************************
 * ScheduleGraphController.java
 * This File contains the code for all the algorithms It take trackInput as input and return
 * access sequence and seek time and final header positions for all the algorithms implemented.
 *******************************************************************************************************/

public class ScheduleGraphController {

	public ArrayList<ScheduleModel> scheduleList;

	/********************************************************************************************
	 * This method will returns the track value nearest to the current header position
	 ********************************************************************************************/
	private int nearestReq(int request,int reqArray[])         
	{
		int min = 1000;
		int minin = -1;

		int i;
		for(i=0;i<total;i++)
		{
			if(Math.abs(reqArray[i]-request)<min && reqArray[i]>0)
			{
				min=Math.abs(reqArray[i]-request);
				minin=i;
			}
		}

		return minin;
	}


	/********************************************************************************************
	 * This method will Calculate the access sequence for FCFS Algorithm, The return array contains
	 * access sequence , seekTime, Final head Position
	 ********************************************************************************************/
	public int[] fcfscal(int[] In,int starting)
	{
		int[] ans = new int[total+1];
		int seek = 0;
		int point = starting;

		int i;
		for(i=0;i<total;i++)
		{
			ans[i] = In[i];
			seek = seek + Math.abs(point-In[i]);
			point=ans[i];
		}

		ans[total]=seek;

		return ans;

	}

	/********************************************************************************************
	 * This method will Calculate the access sequence for SSTF Algorithm, The return array contains
	 * access sequence , seekTime, Final head Position
	 ********************************************************************************************/
	public int[] sstfcal(int[] In,int starting)
	{
		int[] temp = new int[total];
		int i,pval,minin;

		for(i=0;i<total;i++)			//storing the values in temp array
			temp[i]=In[i];

		int[] ans = new int[total+1];
		int seek = 0;
		int point = starting;


		for(i=0;i<total;i++)
		{
			minin = nearestReq(point,temp);
			pval=temp[minin];
			temp[minin]=-1;
			ans[i]=pval;
			seek = seek + Math.abs(point-pval);
			point = pval;
		}

		ans[total]=seek;

		return ans;

	}
	/********************************************************************************************
	 * This method will Calculate the access sequence for S-Scan Algorithm, The return array contains
	 * access sequence , seekTime, Final head Position
	 ********************************************************************************************/

	public int[] scancal(int[] In,int starting)
	{
		int[] temp = new int[total];
		int i,min = In[0],max=In[0];

		for(i=0;i<total;i++)
			temp[i]=In[i];

		int[] ans = new int[total+1];
		int seek = 0;

		for(i=0;i<total;i++)
		{
			if(In[i]<min)
				min = In[i];

			if(In[i]>max)
				max = In[i];
		}

		if(min < starting)
		{
			seek = seek + 199 - starting;
			seek = seek + 199 - min;
		}
		else
			seek = seek + max - starting;

		Arrays.sort(temp);					//sort the sequence

		int j,k;
		for(i=0;i<total;i++)
		{
			if(temp[i] > starting)
				break;
		}

		j=i-1;
		k=i;
		int index=0;

		for(i=k;i<total;i++)
		{
			ans[index] = temp[i];
			index++;
		}

		for(i=j;i>=0;i--)
		{
			ans[index] = temp[i];
			index++;
		}

		ans[total]=seek;			//Seek Time is returned

		return ans;

	}
	/********************************************************************************************
	 * This method will Calculate the access sequence for C-Scan Algorithm, The return array contains
	 * access sequence , seekTime, Final head Position
	 ********************************************************************************************/

	public int[] cscancal(int[] In, int starting)
	{
		int[] temp = new int[total];
		int i,min = In[0],max=In[0];

		for(i=0;i<total;i++)
			temp[i]=In[i];

		int[] ans = new int[total+1];
		int seek = 0;

		for(i=0;i<total;i++)
		{
			if(In[i]<min)
				min = In[i];

			if(In[i]>max)
				max = In[i];
		}

		Arrays.sort(temp);

		int j,k;
		for(i=0;i<total;i++)
		{
			if(temp[i] > starting)
				break;
		}

		j=i-1;
		k=i;


		if(min < starting)
		{
			seek = seek + 199 - starting;
			seek = seek + 199;
			seek = seek + temp[j];
		}
		else
			seek = seek + max - starting;


		int index=0;

		for(i=k;i<total;i++)
		{
			ans[index] = temp[i];
			index++;
		}

		for(i=0;i<=j;i++)
		{
			ans[index] = temp[i];
			index++;
		}

		ans[total]=seek;

		return ans;

	}

	/********************************************************************************************
	 * This method will Calculate the access sequence for LOOK Algorithm, The return array contains
	 * access sequence , seekTime, Final head Position
	 ********************************************************************************************/

	public int[] lookcal(int[] In, int starting)
	{
		int[] temp = new int[total];
		int i,min = In[0],max=In[0];

		for(i=0;i<total;i++)
			temp[i]=In[i];

		int[] ans = new int[total+1];
		int seek = 0;

		for(i=0;i<total;i++)
		{
			if(In[i]<min)
				min = In[i];

			if(In[i]>max)
				max = In[i];
		}

		if(min < starting && starting < max)
		{
			seek = seek + max - starting;
			seek = seek + max - min;
		}
		else if( min >= starting)
			seek = seek + max - starting;

		else if( max <= starting)
			seek = seek + starting - min;

		Arrays.sort(temp);						//sort the remaining sequence

		int j,k;
		for(i=0;i<total;i++)
		{
			if(temp[i] > starting)
				break;
		}

		j=i-1;
		k=i;
		int index=0;

		for(i=k;i<total;i++)
		{
			ans[index] = temp[i];
			index++;
		}

		for(i=j;i>=0;i--)
		{
			ans[index] = temp[i];
			index++;
		}

		ans[total]=seek;

		return ans;

	}
	/********************************************************************************************
	 * This method will Calculate the access sequence for C-Look Algorithm, The return array contains
	 * access sequence , seekTime, Final head Position
	 ********************************************************************************************/
	public int[] clookcal(int[] In, int starting)
	{
		int[] temp = new int[total];
		int i,min = In[0],max=In[0];

		for(i=0;i<total;i++)
			temp[i]=In[i];

		int[] ans = new int[total+1];
		int seek = 0;

		for(i=0;i<total;i++)
		{
			if(In[i]<min)
				min = In[i];

			if(In[i]>max)
				max = In[i];
		}

		Arrays.sort(temp);			//sort the remaining sequence

		int j,k;
		for(i=0;i<total;i++)
		{
			if(temp[i] > starting)
				break;
		}

		j=i-1;
		k=i;

		if(min < starting && starting < max)
		{
			seek = seek + max - starting;
			seek = seek + max - min;
			seek = seek + temp[j] - min;
		}
		else if( min >= starting)
			seek = seek + max - starting;

		else if( max <= starting)
			seek = seek + starting - min;


		int index=0;

		for(i=k;i<total;i++)
		{
			ans[index] = temp[i];
			index++;
		}

		for(i=0;i<=j;i++)
		{
			ans[index] = temp[i];
			index++;
		}

		ans[total]=seek;

		return ans;

	}

	/********************************************************************************************
	 * This method will Calculate the access sequence for 3-Step Algorithm, The return array contains
	 * access sequence , seekTime, Final head Position
	 ********************************************************************************************/

	public int[] stepcal(int[] In, int starting) {
		int[] temp = new int[3];
		int i;
		int tstart=starting;
		
		int[] ans = new int[total+1];
		int seek = 0;
		
		int count = total;
		int flagu= 0;
		
		while(count>0)
		{
			int min = In[flagu],max=In[flagu];
			
			int tertu;
			if(count>2)
				tertu=3;
			else if(count>1)
				tertu=2;
			else
				tertu=1;

			count=count-tertu;

			for(i=flagu;i<flagu+tertu;i++)
				temp[i-flagu]=In[i];

			for(i=flagu;i<flagu+tertu;i++)
			{
				if(In[i]<min)
					min = In[i];

				if(In[i]>max)
					max = In[i];
			}

			if(min < tstart)
			{
				seek = seek + 199 - tstart;
				seek = seek + 199 - min;
			}
			else
				seek = seek + max - tstart;

			Arrays.sort(temp);


			int j,k;
			for(i=0;i<tertu;i++)
			{
				if(temp[i] > tstart)
					break;
			}

			j=i-1;
			k=i;
			int index=flagu;

			for(i=k;i<tertu;i++)
			{
				ans[index] = temp[i];
				index++;
			}

			for(i=j;i>=0;i--)
			{
				ans[index] = temp[i];
				index++;
			}

			flagu=flagu+tertu;

			tstart=ans[flagu];

		}

		ans[total]=seek;			//Seek Time is returned
		return ans;
	}

	/********************************************************************************************
	 * This method will Calculate the access sequence for Pickup Algorithm, The return array contains
	 * access sequence , seekTime, Final head Position
	 ********************************************************************************************/

	public int[] pickupcal(int[] In, int starting) {
		int[][] temp = new int[total][2];
		int i,seek=0,point=0;
		int[] ans = new int[total+1];
		
		for(i=0;i<total;i++)
		{
			temp[i][0]=In[i];
			temp[i][1]=1;
		}
		
		int count=total,index=0,tstart=starting;
		while(count>0)
		{
			for(i=0;i<total;i++)
			{
				if(temp[i][1]==1)
				{
					index=i;
					break;
				}
			}
			
			seek = seek + Math.abs(tstart-temp[index][0]);
			
			if(temp[index][0]>=tstart)
			{
				for(i=tstart;i<=temp[index][0];i++)
				{
					int j;
					for(j=0;j<total;j++)
					{
						if(i==temp[j][0] && temp[j][1]==1)
						{
							ans[point]=temp[j][0];
							point++;
							count--;
							temp[j][1]=0;
						}
					}
				}
			}
			else
			{
				for(i=tstart;i>=temp[index][0];i--)
				{
					int j;
					for(j=0;j<total;j++)
					{
						if(i==temp[j][0] && temp[j][1]==1)
						{
							ans[point]=temp[j][0];
							point++;
							count--;
							temp[j][1]=0;
						}
					}
				}
			}

		}
		
		ans[total]=seek;
		return ans;
		
	}



}


