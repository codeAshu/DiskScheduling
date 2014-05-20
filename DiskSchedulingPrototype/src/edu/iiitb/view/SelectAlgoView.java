package edu.iiitb.view;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import edu.iiitb.controller.DbController;



/*This class will have all the fields and event driven actions for 
 * selecting the algorithm
 */
public class SelectAlgoView extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	public	JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9,jl10;
	public	JRadioButton jrd1,jrd2,jrd3,jrd4,jrd5,jrd6,jrd7,jrd8;
	public	JButton jb1,jb2;
	public static int choice=-1;
	public static int i=0;
	Color c = new Color(102,178,255);

	public static void main(String[] args) {

		new SelectAlgoView();

	}

	//Parameterised constructor for the first view
	public  SelectAlgoView()
	{
		setLocation(400,100);
		setSize(700,500);
		setTitle("Select Algorithm");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		this.getContentPane().setBackground(Color.orange);

		//Add fields and set bounds

		jl1 = new JLabel("Select Any One of the Algorithm Below"); 
		jl1.setFont(new Font("Times New Roman", Font.BOLD,18));
		jl1.setBounds(200,20, 400, 20);


		jl2 = new JLabel("click  \"Next\"   to input the sequence or click   \"Average Comparison\"   to see comparison on already existing data.");
		jl2.setBounds(10,40, 650, 20);


		jl3= new JLabel("First Come -First Serve (FCFS)");
		jl3.setBounds(100,90, 250, 20);


		jl4= new JLabel("Shortest Seek Time First (SSTF)");
		jl4.setBounds(100,140, 250, 30);

		jl5= new JLabel("SCAN");
		jl5.setBounds(100,190, 150, 30);

		jl6= new JLabel("Circular Scan (C-SCAN)");
		jl6.setBounds(100,240,150, 30);

		jl7= new JLabel("C-LOOK");
		jl7.setBounds(100,290,150, 30);

		jl8= new JLabel("Look");
		jl8.setBounds(100,340,150, 30);
		
		jl9= new JLabel("3-Step Scan");
		jl9.setBounds(450,90, 250, 20);

		jl10= new JLabel("Pickup");
		jl10.setBounds(450,140, 250, 30);



		ButtonGroup group = new ButtonGroup();


		jrd1=new JRadioButton();
		jrd1.setBounds(50,90, 20, 20);
		jrd1.setBackground(c);

		jrd2= new JRadioButton();
		jrd2.setBounds(50,140, 20, 30);
		jrd2.setBackground(c);

		jrd3= new JRadioButton();
		jrd3.setBounds(50,190, 20, 30);
		jrd3.setBackground(c);

		jrd4= new JRadioButton();
		jrd4.setBounds(50,240, 20, 30);
		jrd4.setBackground(c);

		jrd5= new JRadioButton();
		jrd5.setBounds(50,290, 20, 30);
		jrd5.setBackground(c);

		jrd6= new JRadioButton();
		jrd6.setBounds(50,340, 20, 30);
		jrd6.setBackground(c);
		
		jrd7=new JRadioButton();
		jrd7.setBounds(400,90, 20, 20);
		jrd7.setBackground(c);

		jrd8= new JRadioButton();
		jrd8.setBounds(400,140, 20, 30);
		jrd8.setBackground(c);


		jb1 = new JButton("Next");
		jb2 = new JButton("Average Comparison");
		
		
		jb1.setBackground(c);
		jb2.setBackground(c);
		
		jb1.setBounds(150,400, 100, 30);
		jb2.setBounds(350,400, 200, 30);

		add(jb1);
		add(jb2);

		add(jl1);
		add(jl2);
		add(jl3);
		add(jl4);
		add(jl5);
		add(jl6);
		add(jl7);
		add(jl8);
		add(jl9);
		add(jl10);

		group.add(jrd1);
		group.add(jrd2);
		group.add(jrd3);
		group.add(jrd4);
		group.add(jrd5);
		group.add(jrd6);
		group.add(jrd7);
		group.add(jrd8);

		add(jrd1);
		add(jrd2);
		add(jrd3);
		add(jrd4);
		add(jrd5);
		add(jrd6);
		add(jrd7);
		add(jrd8);

		jrd1.addActionListener(this);
		jrd2.addActionListener(this);
		jrd3.addActionListener(this);
		jrd4.addActionListener(this);
		jrd5.addActionListener(this);
		jrd6.addActionListener(this);
		jrd7.addActionListener(this);
		jrd8.addActionListener(this);

		jb1.addActionListener(this);
		jb2.addActionListener(this);
		setVisible(true);

	}
	@Override
	public void actionPerformed(ActionEvent arg0) {


		Object o = arg0.getSource();


		//Save the choices
		if(o.equals(jrd1))
			choice = 0;

		else if(o.equals(jrd2))
			choice = 1;

		else if(o.equals(jrd3))
			choice = 2;

		else if(o.equals(jrd4))
			choice = 3;

		else if(o.equals(jrd5))
			choice = 4;

		else if(o.equals(jrd6))
			choice = 5;
		
		else if(o.equals(jrd7))
			choice = 6;
		
		else if(o.equals(jrd8))
			choice = 7;


		if(o.equals(jb1))				 				//events for Save Button
		{	
			if(choice == -1 )							//if no choice is given

			{
				JOptionPane.showMessageDialog(null, "Please select one option");
			}

			//pressing Next after selecting one Algorithm 

			else
			{
				new	InputTrackView();						//go to Next screen
				dispose();
			}
		}
		if(o.equals(jb2))								
		{

			DbController db = new DbController();
			db.disp_data();



		}

	}
}

