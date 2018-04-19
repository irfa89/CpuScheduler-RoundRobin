package com.scheduler.view;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import com.scheduler.control.RoundRobin;
import com.scheduler.control.Simulation;

import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class ApplicationGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel lblTimeQuantum,lblInputFile,lblWatchDog,lblCpuUtilization,lblThroughput,lblAvgTurnaroundTime,lblAvgWaitTime;
	private JTextField tInputFile,tAvgWaitTime,tTimeQuantum,tAvgTurnaroundTime,tCpuUtilization,tThroughput;
	private JButton bExecute,bClear;
	private JTextArea taWatchDog;
	private JScrollPane jsScroll;
	
	RoundRobin roundRobin = new RoundRobin();
	MessageBox Alert = new MessageBox();
	
	Console console = System.console();
	
	public ApplicationGUI() {
		
		setTitle("CPU Scheduler-Simulator");
		getContentPane().setBackground(SystemColor.activeCaption);
		getContentPane().setLayout(null);
		setVisible(true);
		setResizable(true);
		//setSize(700,900);
	
		IOStream();
	}
	
	public void GUI() {
		
		lblTimeQuantum = new JLabel("Time Quantum");
		lblTimeQuantum.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTimeQuantum.setBounds(12, 26, 148, 27);
		getContentPane().add(lblTimeQuantum);
		
		tTimeQuantum = new JTextField();
		tTimeQuantum.setFont(new Font("Times New Roman", Font.BOLD, 18));
		tTimeQuantum.setColumns(10);
		tTimeQuantum.setBounds(252, 28, 156, 22);
		tTimeQuantum.setForeground(Color.BLACK);
		tTimeQuantum.setBackground(Color.WHITE);
		getContentPane().add(tTimeQuantum);
		
		lblInputFile = new JLabel("Input File");
		lblInputFile.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblInputFile.setBounds(12, 66, 148, 27);
		getContentPane().add(lblInputFile);
		
		tInputFile = new JTextField();
		tInputFile.setFont(new Font("Times New Roman", Font.BOLD, 18));
		tInputFile.setBounds(252, 68, 156, 22);
		tInputFile.setForeground(Color.BLACK);
		tInputFile.setBackground(Color.WHITE);
		getContentPane().add(tInputFile);
		tInputFile.setColumns(10);
		
		lblWatchDog = new JLabel("Watchdog");
		lblWatchDog.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblWatchDog.setBounds(24, 370, 187, 27);
		getContentPane().add(lblWatchDog);
		
		tAvgTurnaroundTime = new JTextField();
		tAvgTurnaroundTime.setEditable(false);
		tAvgTurnaroundTime.setFont(new Font("Times New Roman", Font.BOLD, 18));
		tAvgTurnaroundTime.setColumns(10);
		tAvgTurnaroundTime.setBounds(252, 308, 156, 22);
		tAvgTurnaroundTime.setForeground(Color.BLACK);
		tAvgTurnaroundTime.setBackground(Color.WHITE);
		getContentPane().add(tAvgTurnaroundTime);
		
		lblCpuUtilization = new JLabel("CPU Utilization");
		lblCpuUtilization.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblCpuUtilization.setBounds(12, 174, 148, 27);
		getContentPane().add(lblCpuUtilization);
		
		tCpuUtilization = new JTextField();
		tCpuUtilization.setEditable(false);
		tCpuUtilization.setFont(new Font("Times New Roman", Font.BOLD, 18));
		tCpuUtilization.setColumns(10);
		tCpuUtilization.setBounds(252, 176, 156, 22);
		tCpuUtilization.setForeground(Color.BLACK);
		tCpuUtilization.setBackground(Color.WHITE);
		getContentPane().add(tCpuUtilization);
		
		lblThroughput = new JLabel("Throughput");
		lblThroughput.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblThroughput.setBounds(12, 220, 148, 27);
		getContentPane().add(lblThroughput);
		
		tThroughput = new JTextField();
		tThroughput.setEditable(false);
		tThroughput.setFont(new Font("Times New Roman", Font.BOLD, 18));
		tThroughput.setColumns(10);
		tThroughput.setBounds(252, 222, 156, 22);
		tThroughput.setForeground(Color.BLACK);
		tThroughput.setBackground(Color.WHITE);
		getContentPane().add(tThroughput);
		
		lblAvgTurnaroundTime = new JLabel("Avg. Turnaround Time");
		lblAvgTurnaroundTime.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblAvgTurnaroundTime.setBounds(12, 306, 179, 27);
		getContentPane().add(lblAvgTurnaroundTime);
		
		tAvgWaitTime = new JTextField();
		tAvgWaitTime.setEditable(false);
		tAvgWaitTime.setFont(new Font("Times New Roman", Font.BOLD, 18));
		tAvgWaitTime.setColumns(10);
		tAvgWaitTime.setBounds(252, 262, 156, 22);
		tAvgWaitTime.setForeground(Color.BLACK);
		tAvgWaitTime.setBackground(Color.WHITE);
		getContentPane().add(tAvgWaitTime);
		
		taWatchDog = new JTextArea();
		taWatchDog.setEditable(false);
		taWatchDog.setBounds(22, 410, 365, 400);
		taWatchDog.setForeground(Color.BLACK);
		taWatchDog.setBackground(Color.WHITE);
		taWatchDog.setLineWrap(true);
		taWatchDog.setWrapStyleWord(true);
		getContentPane().add(taWatchDog);
		
			
		jsScroll = new JScrollPane(taWatchDog);
		jsScroll.setBounds(22, 410, 365, 400);
		jsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(jsScroll);
		
		bExecute = new JButton("Execute");
		bExecute.setFont(new Font("Times New Roman", Font.BOLD, 18));
		bExecute.setBounds(306, 115, 97, 27);
		getContentPane().add(bExecute);
		
		bClear = new JButton("Reset");
		bClear.setFont(new Font("Times New Roman", Font.BOLD, 18));
		bClear.setBounds(197, 115, 97, 27);
		getContentPane().add(bClear);
		
		lblAvgWaitTime = new JLabel("Avg. Waiting Time");
		lblAvgWaitTime.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblAvgWaitTime.setBounds(12, 260, 148, 27);
		getContentPane().add(lblAvgWaitTime);
		
		bActionEvents();

	}
	
	public void bActionEvents() {
		bExecute.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String  TimeQuantum  = tTimeQuantum.getText();
				String FileName = tInputFile.getText();				
				roundRobin.scanFile(FileName,TimeQuantum);
				
				tAvgWaitTime.setText(com.scheduler.control.Simulation.jAverageWaitTime);
				tAvgTurnaroundTime.setText(com.scheduler.control.Simulation.jAverageTurnaroundTime);
				tCpuUtilization.setText(com.scheduler.control.Simulation.jCpuUtization);
				tThroughput.setText(com.scheduler.control.Simulation.jThroughput);
				
				Alert.updateMsg("Execution Complete...!", "Information Box");
	
			}	
		});
			
		bClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				tTimeQuantum.setText("");
				tInputFile.setText("");
				tAvgWaitTime.setText("");
				tAvgTurnaroundTime.setText("");
				tCpuUtilization.setText("");
				tThroughput.setText("");
				taWatchDog.setText("");
			}
			
		});
	}
	
	public void IOStream() {
		
	final PrintStream sysOut = System.out;

	System.setOut(new PrintStream(new OutputStream() {
	public void write(int b) throws IOException {
		taWatchDog.append(String.valueOf((char)b));
	sysOut.write(b);
	}
	
	}));
	
	}

}

