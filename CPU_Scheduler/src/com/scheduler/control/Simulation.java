
package com.scheduler.control;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.scheduler.model.CPU;
import com.scheduler.model.Process;
import com.scheduler.model.SchedulerClock;

public class Simulation {
	
	 	private ArrayList<Process> processArrayList;
	    private int timeQuantum;
	    private CPU cpu;
	    private Queue<Process> readyQueue;
	    private SchedulerClock clock;
	    private ArrayList<Process> finishedProcesses;
	    private static int totalNumberProcess;
	            
        private double averageWaitTime;
        private double averageTurnAroundTime;
        private double throughput;
        private double runningTimeMinusContextSwitch;
        private double utilization;
	
	    public static String jAverageWaitTime, jAverageTurnaroundTime,jCpuUtization,jThroughput ;

	
	public Simulation() {}
	
    public Simulation(ArrayList<Process> process, int quantum)
    {
        processArrayList = process;
        timeQuantum = quantum;
        cpu=new CPU(timeQuantum);
        readyQueue = new LinkedList<Process>();
        clock = new SchedulerClock();
        finishedProcesses = new ArrayList<Process>();
        totalNumberProcess = 5;
        for(int i =0;i<process.size();i++)
        {
            process.get(i).setInitialArrivalTime();
            process.get(i).setInitialBurstTime();
        }
    }
    public void runRoundRobinSimulation()
    {
        ProcessCreator(null);
        RoundRobin();
        
    }
    public void ProcessCreator(Process currentProcess)
    {
        for(int i=0;i<processArrayList.size();i++)
        {
            Process process =processArrayList.get(i);
            if(process.readyToExecute()==true && process.processFinished()==0 && (process != currentProcess) && !readyQueue.contains(process))
            {
                readyQueue.add(process);
                System.out.println("NEW Process " + process.getProcessName() + " added to ready queue");
            }
        }
    }

    public void RoundRobin()
    {
        boolean done = false;

        while(!done)
        {
        Process currentProcess = readyQueue.peek(); 
        if(currentProcess != null){
        int runTimeOfPrcoessOnCPU=cpu.run(currentProcess);
        clock.updateTime(runTimeOfPrcoessOnCPU);
        clock.updateArrivalTime(processArrayList,runTimeOfPrcoessOnCPU);
        clock.updateWaitTime(readyQueue,runTimeOfPrcoessOnCPU, currentProcess);
        ProcessCreator(currentProcess);
        if(currentProcess.processFinished()==1)
        {
            System.out.println("Process " + currentProcess.getProcessName() + " completed execution");

              processArrayList.remove(currentProcess);
              System.out.println("Process " + currentProcess.getProcessName() + " removed from ready queue");
            finishedProcesses.add(readyQueue.remove());
            
            if(finishedProcesses.size()==totalNumberProcess)
        {
            this.EndingSimulation();
            done=true;
        }
        }
        else
        {
            currentProcess.updateContextSwitch();
            currentProcess.getCurrentProcessInfo();
            readyQueue.remove();
            readyQueue.add(currentProcess);
            ProcessCreator(currentProcess);
        }
        }
        else
        {
            
            clock.updateTime(timeQuantum);
            clock.updateArrivalTime(processArrayList,timeQuantum);
            ProcessCreator(null);
        }
        }
    }
    public void EndingSimulation()
    {
    	int totalWaitTime=0;
        int totalTurnAroundTime = 0;
        int totalContextSwitches = 0;
        int wait = 0;
        
        for(int i =0;i<finishedProcesses.size();i++){
        finishedProcesses.get(i).setTurnAroundTime();}
        
        int p1TurnAround= finishedProcesses.get(0).getTurnAroundTime();
        int p5TurnAround= finishedProcesses.get(1).getTurnAroundTime();
        int p3TurnAround= finishedProcesses.get(2).getTurnAroundTime();
        int p4TurnAround= finishedProcesses.get(3).getTurnAroundTime();
        int p2TurnAround= finishedProcesses.get(4).getTurnAroundTime();
        int maxTurnAround = Math.max( p1TurnAround, Math.max( p2TurnAround, Math.max( p3TurnAround, Math.max(p4TurnAround, p5TurnAround))));
        totalTurnAroundTime = p1TurnAround+p2TurnAround+p3TurnAround+p4TurnAround+p5TurnAround;
            
        for(int i =0;i<finishedProcesses.size();i++){
        wait = wait + finishedProcesses.get(i).getTotalWaitTime();}
        for(int i =0;i<finishedProcesses.size();i++)
        {
            totalWaitTime = wait;
            
            totalContextSwitches = totalContextSwitches + finishedProcesses.get(i).getContextSwitch();
        }
        
        averageWaitTime = totalWaitTime/totalNumberProcess;
        averageTurnAroundTime = totalTurnAroundTime/totalNumberProcess;
        throughput= ((double)cpu.getThroughput()/clock.getTime()) *100.0;
        runningTimeMinusContextSwitch=clock.getTime()-totalContextSwitches;
        utilization= (runningTimeMinusContextSwitch/clock.getTime())*100.0;
        
        jAverageWaitTime = String.valueOf(averageWaitTime).format("%.2f", averageWaitTime);
        jAverageTurnaroundTime = String.valueOf(averageTurnAroundTime).format("%.2f", averageTurnAroundTime);
        jCpuUtization= String.valueOf(utilization).format("%.2f", utilization);
        jThroughput= String.valueOf(throughput).format("%.2f",throughput);
     
               
    }
    
    public void summaryDisplay() {
    	
    System.out.println();
    System.out.println();
    System.out.println("---RESULTS---");
    System.out.println("Average wait time: " + averageWaitTime);
    System.out.println("Average turnaround time: " + averageTurnAroundTime);
    System.out.println("Throughput: " + throughput + "%");
    System.out.println("CPU utilization: " + utilization + "%");
    	
    }
    
}
