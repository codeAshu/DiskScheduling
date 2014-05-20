package edu.iiitb.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


/*
 * This view will show the scheduling graph of algorithm
 * one compare All button , one Back button , one drop down to select another algorithm
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Scanner;

import javax.swing.JComboBox;

import edu.iiitb.controller.AvgCompController;
import edu.iiitb.controller.ScheduleGraphController;
import edu.iiitb.model.ScheduleModel;

public class ScheduleGraphView extends JFrame implements ActionListener 
{
	private static final long serialVersionUID = 1L;
	public	JLabel input,inputv,stpoint,stpointv,algo,seekt,seektv,seektr;
	public	JButton jb1,jb2;
	public String[] alist = { "FCFS","SSTF","SCAN","C-SCAN","LOOK","C-Look","3-Step Scan","Pickup" };
	@SuppressWarnings("rawtypes")
	public JComboBox algolist;

	public static int[] Input = new int[201];
	public static int total = 0;
	public static int[] Drawput = new int[201];
	public static String data;
	public static int starting;
	public static int select;

	final static float dash1[] = {10.0f};
	final static BasicStroke dashed =
			new BasicStroke(0.5f,
					BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_MITER,
					10.0f, dash1, 0.0f);

	final static BasicStroke normal = new BasicStroke(2.0f);

	final static BasicStroke bold = new BasicStroke(3.5f);

	Color c = new Color(102,178,255);
	/********************************************************************************************
	 * This method will assign a known high value to the node which are not directly connected
	 ********************************************************************************************/
	public static int count(String data)
	{
		//Use Of ExtractRequest Function
		int ans=0;
		Scanner read;
		read= new Scanner(data);
		read.useDelimiter(",");

		while(read.hasNext())
		{
			Input[ans] = (read.nextInt());
			ans++;
		}

		read.close();
		return ans;
	}



	/********************************************************************************************
	 * This method will assign a known high value to the node which are not directly connected
	 ********************************************************************************************/
	public ScheduleGraphView(ScheduleModel sm)
	{

		ScheduleGraphController sg = new ScheduleGraphController();


		data = sm.getTrackInput();
		int inalgo= sm.getAlgoType();
		starting=sm.getStartPoint();

		total= count(data);

		//average graph controller

		select=inalgo;
		

		switch(inalgo)
		{
		case 0:Drawput = sg.fcfscal(Input,starting);
		break;
		case 1:Drawput = sg.sstfcal(Input,starting);
		break;
		case 2:Drawput = sg.scancal(Input,starting);
		break;
		case 3:Drawput = sg.cscancal(Input,starting);
		break;
		case 4:Drawput = sg.lookcal(Input,starting);
		break;
		case 5:Drawput = sg.clookcal(Input,starting);
		break;
		case 6:Drawput = sg.stepcal(Input,starting);
		break;
		case 7:Drawput = sg.pickupcal(Input,starting);
		break;
		}

		setLocation(410,20);
		setSize(700,700);
		setTitle("Schedule Graph");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		this.getContentPane().setBackground(Color.orange);

		input = new JLabel("Given Input:"); 
		input.setFont(new Font("Arial", Font.BOLD,18));
		input.setBounds(50,50, 400, 20);

		stpoint = new JLabel("Starting Point:"); 
		stpoint.setFont(new Font("Arial", Font.BOLD,18));
		stpoint.setBounds(50,100, 400, 20);

		stpointv = new JLabel(Integer.toString(starting)); 
		stpointv.setFont(new Font("Arial", Font.ITALIC,14));
		stpointv.setBounds(180,100, 400, 20);

		seekt = new JLabel("Movement: "); 
		seekt.setFont(new Font("Arial", Font.BOLD,18));
		seekt.setBounds(450,100, 400, 20);

		seektv = new JLabel(Integer.toString(Drawput[total])); 
		seektv.setFont(new Font("Arial", Font.ITALIC,14));
		seektv.setBounds(550,100, 400, 20);
		
		seektr = new JLabel("Tracks"); 
		seektr.setFont(new Font("Arial", Font.BOLD,18));
		seektr.setBounds(580,100, 400, 20);


		algo = new JLabel("Algo:"); 
		algo.setFont(new Font("Arial", Font.BOLD,18));
		algo.setBounds(250,100, 400, 20);

		inputv = new JLabel(data); 
		inputv.setFont(new Font("Arial", Font.ITALIC,14));
		inputv.setBounds(175,50, 400, 20);

		algolist = new JComboBox(alist);
		algolist.setFont(new Font("Arial", Font.ITALIC,14));
		algolist.setBounds(305,100, 100, 20);
		algolist.setSelectedIndex(inalgo);

		jb1 = new JButton("Back");
		jb2 = new JButton("Algo Comparison");

		jb1.setBounds(100,600, 100, 30);
		jb2.setBounds(400,600, 200, 30);
		
		jb1.setBackground(c);
		jb2.setBackground(c);
		
		

		add(jb1);
		add(jb2);

		add(input);
		add(inputv);
		add(stpoint);
		add(stpointv);
		add(algo);
		add(algolist);
		add(seekt);
		add(seektv);
		add(seektr);



		algolist.addActionListener(this);
		jb1.addActionListener(this);
		jb2.addActionListener(this);

		setVisible(true);

	}
	public void setlabel(Graphics g,int val,int x,int y,int mode)
	{
		if(mode==1)
			g.setFont(new Font("Arial", Font.BOLD,18));
		else
		{
			g.setFont(new Font("Arial", Font.ITALIC,14));
			g.setColor(Color.blue);
		}

		g.drawString(Integer.toString(val), x, y);

	}

	/********************************************************************************************
	 * This method will assign a known high value to the node which are not directly connected
	 ********************************************************************************************/
	public void drawl(Graphics g)
	{
		setlabel(g,0,40,190,1);
		setlabel(g,199,640,190,1);
		setlabel(g,starting,starting*3+40,190,1);

		linenormal(g,50, 200, 647, 200,0);
		linenormal(g,50,198,50,202,0);
		linenormal(g,647,198,647,202,0);

		int i,x;
		for(i=0;i<total;i++)
		{
			x=Input[i];
			x=x*3 + 50;
			linenormal(g,x,198,x,202,0);
		}

		int num=1;
		int gap=350/total;
		int pre=starting*3 + 50;

		if(select==2 || select == 3 || select==6)
		{
			int preval=starting;
			int cflag=0;


			for(i=0;i<total;i++)
			{
				x = Drawput[i];
				if(x<preval && cflag==0)
				{
					linenormal(g,preval*3+50, (num-1)*gap+200, 647, num*gap+200,1);
					num++;
					pre=647;
					cflag=1;

				}

				preval=x;

				x=x*3+50;

				linenormal(g,pre,(num-1)*gap + 200,x,num*gap + 200,1);

				if(x<pre)
					setlabel(g,Drawput[i],x-20,num*gap+200,0);
				else
					setlabel(g,Drawput[i],x+15,num*gap+200,0);

				linedash(g,x,200,x,num*gap+200);

				g.setColor(Color.BLUE);
				g.fillOval(x-5,num*gap + 195,10,10);
				num++;
				pre=x;
			}

		}
		else
		{
			for(i=0;i<total;i++)
			{
				x = Drawput[i];
				x=x*3+50;
				linenormal(g,pre,(num-1)*gap + 200,x,num*gap + 200,1);

				if(x<pre)
					setlabel(g,Drawput[i],x-20,num*gap+200,0);
				else
					setlabel(g,Drawput[i],x+15,num*gap+200,0);

				linedash(g,x,200,x,num*gap+200);
				g.setColor(Color.BLUE);
				g.fillOval(x-5,num*gap + 195,10,10);
				num++;
				pre=x;
			}
		}
	}

	public void linenormal(Graphics g,int x1,int y1,int x2,int y2,int mode)
	{
		if(mode==0)
		{
			((Graphics2D) g).setStroke(bold);
			g.setColor(Color.BLUE);
		}
		else
		{
			((Graphics2D) g).setStroke(normal);
			g.setColor(Color.RED);
		}

		g.drawLine(x1,y1,x2,y2);
	}

	public void linedash(Graphics g,int x1,int y1,int x2,int y2)
	{
		((Graphics2D) g).setStroke(dashed);
		g.setColor(Color.BLUE);
		g.drawLine(x1,y1,x2,y2);
	}

	/********************************************************************************************
	 * This method will assign a known high value to the node which are not directly connected
	 ********************************************************************************************/
	public void paint(Graphics g) {
		super.paint(g);
		drawl(g);
	}
	int nval=0;
	@Override
	public void actionPerformed(ActionEvent arg0) {

		Object o = arg0.getSource();
		
		if(o.equals(jb1))				 					//events for Back Button
		{	
			new	InputTrackView();						//go to Next screen
			dispose();

		}
		if(o.equals(jb2))								
		{
			AvgCompController av = new AvgCompController();
			av.showComp(data,starting);
			//dispose();


		}
		if(o.equals(algolist))
		{
			nval=algolist.getSelectedIndex();
			select=nval;
			ScheduleModel sm = new ScheduleModel(nval,data,starting);
			new ScheduleGraphView(sm);
			dispose();

		}	
		
	}
}
