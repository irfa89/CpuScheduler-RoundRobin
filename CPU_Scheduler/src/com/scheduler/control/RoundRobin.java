
package com.scheduler.control;
import java.util.Scanner;

import com.scheduler.model.Process;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
public class RoundRobin {
	
    public void scanFile(String FileName , String TimeQuantum) {
        
        //Scanner in = new Scanner(System.in);
        //System.out.println("Time Quantum : ");
        int timeQuantum = Integer.parseInt(TimeQuantum);
        //System.out.println("Input File Name : ");
        ArrayList<Process> process = new ArrayList<Process>();
        try{
        String filename = FileName;
        File inputFile = new File(filename);
        Scanner inFile = new Scanner(inputFile);
        while(inFile.hasNextLine())
        {
            String processLine = inFile.nextLine();
            int i = 0;
            while (!Character.isDigit(processLine.charAt(i))) 
            { 
            	i++; 
            }
            String processName = processLine.substring(0,i);
            String processArrival = processLine.substring(i,i+2).trim();
            String processBurst = processLine.substring(i+2).trim();
            int processArrivalTime = Integer.parseInt(processArrival);
            int processBurstTime = Integer.parseInt(processBurst);   
            process.add(new Process(processName,processArrivalTime,processBurstTime,timeQuantum));
        }
              
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        Simulation simulation = new Simulation(process,timeQuantum);
        simulation.runRoundRobinSimulation();

        System.out.println("---END---");
        }
    }
