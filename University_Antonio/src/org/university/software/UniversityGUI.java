package org.university.software;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class UniversityGUI extends JFrame{
	
	private JMenuBar menuBar;
	private JMenu fileMenu, stdMenu,admMenu;
	private JMenuItem save,load,exit,addCourse,dropCourse,printsch,printAll;
	private JTextArea infoWindow;
	private University univer;
	
	private JLabel lb1 = new JLabel("Student Name",JLabel.LEFT);
	private JTextField tx1= new JTextField();
	private JLabel lb2 = new JLabel("Department",JLabel.LEFT);
	private JTextField tx2= new JTextField();
	private JLabel lb3 = new JLabel("Course #",JLabel.LEFT);
	private JTextField tx3= new JTextField();
	
	private JPanel p = new JPanel(new CardLayout());
	private  JScrollPane sp;
	private GridLayout gl= new GridLayout(4,2);
	
	UniversityGUI(String guiTitle, University univer){
		
		super(guiTitle);
		
		this.univer=new University(univer);
		
		setSize(400, 200);
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(new JLabel("<HTML><center>Welcome to University of Arizona" +
				"<BR>Choose an action from the above menus.</center></HTML>"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
		
		buildGui();
		
	}

	public void buildGui(){
		
		
		menuBar= new JMenuBar();
		setJMenuBar(menuBar);
		
		
		p.setLayout(gl);
		p.add(lb1);
		p.add(tx1);
		p.add(lb2);
		p.add(tx2);
		p.add(lb3);
		p.add(tx3);
		
		infoWindow=new JTextArea(40,40);
		infoWindow.setText(univer.getAllInfo());
		infoWindow.setEditable(false);
		
		sp =new JScrollPane(infoWindow);
	
		fileMenu = new JMenu("File");
		save= new JMenuItem("Save");
		load=new JMenuItem("Load");
		exit=new JMenuItem("Exit");
		
		save.addActionListener(new MenuListener());
		load.addActionListener(new MenuListener());
		exit.addActionListener(new MenuListener());
		
		fileMenu.add(save);
		fileMenu.add(load);
		fileMenu.add(exit);
		
		stdMenu= new JMenu("Students");
		addCourse= new JMenuItem("Add Course");
		dropCourse= new JMenuItem("Drop Course");
		printsch= new JMenuItem("Print Schedule");
		
		addCourse.addActionListener(new MenuListener());
		dropCourse.addActionListener(new MenuListener());
		printsch.addActionListener(new MenuListener());
		
		stdMenu.add(addCourse);
		stdMenu.add(dropCourse);
		stdMenu.add(printsch);
		
		admMenu=new JMenu("Administrators");
		printAll=new JMenuItem("Print All Info");
		printAll.addActionListener(new MenuListener());
		admMenu.add(printAll);
			
		menuBar.add(fileMenu);
		menuBar.add(stdMenu);
		menuBar.add(admMenu);		
		
	}
	
	private class MenuListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 	{
			JMenuItem source = (JMenuItem)(e.getSource());
			
			
			 if(source.equals(save)){
				univer.saveData(univer);
				//System.out.println("Before Serializing ... ");
				//u1.printAll();;
						
			}
			else if(source.equals(load)){
				univer= univer.loadData();
				//System.out.println("\n\nAfter Serializing .....");
				//u2.printAll();			
				
			}
			else if(source.equals(printAll)){
				
				JOptionPane.showMessageDialog(new Frame(), sp,"University Info", JOptionPane.DEFAULT_OPTION);
				
			}
			 
			else if(source.equals(exit)){
				
				System.exit(0);
			} 
			 
			else if(source.equals(addCourse)){
				
				
				JOptionPane.showConfirmDialog(new Frame(),p, "Add Course", JOptionPane.OK_CANCEL_OPTION);
			} 
		}
	}
}
