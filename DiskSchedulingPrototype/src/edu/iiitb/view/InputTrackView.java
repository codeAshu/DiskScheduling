package edu.iiitb.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import edu.iiitb.controller.LegacyController;
import edu.iiitb.model.ScheduleModel;
import  static edu.iiitb.view.SelectAlgoView.choice;

@SuppressWarnings("serial")
public class InputTrackView extends JFrame implements ActionListener {

	public	JLabel jl1,jl2,jl3;
	public JTextField jt1,jt2;
	public JButton jb1,jb2;
	Color c = new Color(102,178,255);
	public InputTrackView()
	{

		setLocation(400,100);
		setSize(600,400);
		setTitle("Input Tracks");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		this.getContentPane().setBackground(Color.orange);

		//Add fields and set bounds

		jl1 = new JLabel("Provide Track Numbers and Starting Header Position"); 
		jl1.setFont(new Font("Times New Roman", Font.BOLD,15));
		jl1.setBounds(30,10, 420, 20);	
		add(jl1);

		jl2 = new JLabel("Please input the track numbers seperated by Comma  ( , )");
		jl2.setBounds(30,50, 400, 20);
		add(jl2);

		jt1 = new JTextField();
		jt1.setBounds(30,70,500,20);
		jt1.setBackground(c);
		add(jt1);

		jl3 = new JLabel("Please input the header position");
		jl3.setBounds(30,130, 400, 20);
		add(jl3);

		jt2 = new JTextField();
		jt2.setBounds(30,150,40,20);
		jt2.setBackground(c);
		
		add(jt2);

		jb1 = new JButton("Back");
		jb2 = new JButton("View Performance");

		jb1.setBounds(50,250, 100, 30);
		jb2.setBounds(200,250, 150, 30);
		
		jb1.setBackground(c);
		jb2.setBackground(c);
		

		add(jb1);
		add(jb2);

		jb1.addActionListener(this);
		jb2.addActionListener(this);

		setVisible(true);

	}




	@Override
	public void actionPerformed(ActionEvent arg0) {

		Object o = arg0.getSource();

		/*events for Back Button*/
		if(o.equals(jb1))				 				
		{
			choice = 0;									//reset the choice
			new SelectAlgoView();   					// go back to select Algo Screen
			dispose();
		}
		if(o.equals(jb2))				 				
		{

			String trackInput = " ";
			int startPoint = 0;
			try {

				trackInput = jt1.getText();
				startPoint = Integer.parseInt(jt2.getText());

			} catch (NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Please Enter only Numbers");
				new InputTrackView();
				dispose();
			
			}
			

				//all validations for the text field trackInput

				/*





				 */


				//create schedule model and pass to ScheduleGraphController
				ScheduleModel sm = new ScheduleModel(choice, trackInput, startPoint);
				LegacyController lg = new LegacyController();
				lg.process(trackInput);
				new ScheduleGraphView(sm);

						
				dispose();
			
		}
	}
}

