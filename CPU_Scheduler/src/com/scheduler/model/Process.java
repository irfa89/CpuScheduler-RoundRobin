
package com.scheduler.model;

public class Process {
	
	private String processName;
    private int arrivalTime;
    private int burstTime;
    private int timeQuantum;
    private int contextSwitch;
    private int waitTime;
    private int turnAroundTime;
    private int initialArrivalTime;
    private int initialburstTime;
	
    public Process(String name, int at, int bt, int quantum)
    {
        processName = name;
        arrivalTime = at;
        burstTime = bt; 
        timeQuantum = quantum;
        contextSwitch=0;
        waitTime = 0;
        turnAroundTime=0;
        initialArrivalTime =0;
        initialburstTime=0;
    }
    public String getProcessName()
    {
        return processName;
    }
    public void setInitialArrivalTime()
    {
         initialArrivalTime = arrivalTime;
    }
    public int getInitialArrivalTime()
    {
        return initialArrivalTime;
    }
    public int getProcessArrivalTime()
    {
        return arrivalTime;
    }
    public void updateArrivalTime(int runTime)
    {
        arrivalTime = arrivalTime - runTime;
        if(arrivalTime<0)
        {
            arrivalTime = 0;
        }
    }
    public void setInitialBurstTime()
    {
        initialburstTime=burstTime;
    }
    public int getinitialBurstTime()
    {
        return initialburstTime;
    }
    public void setCurrentWaitTime(int runTime)
    {
            waitTime = waitTime + runTime;  
    }
    public int getTotalWaitTime()
    {
        return waitTime;
    }
    public void updateRemainingBurstTime()
    {
        burstTime=burstTime - timeQuantum;
        if(burstTime<0)
        {
            burstTime=0;
        }
    }
    public int getRemainingBurstTime()
    {
        return burstTime;
    }
    public void updateContextSwitch()
    {
        contextSwitch++;
    }
    public int getContextSwitch()
    {
        return contextSwitch;
    }
    public void setTurnAroundTime()
    {
        turnAroundTime = this.getTotalWaitTime() + this.getinitialBurstTime();
    }
    public int getTurnAroundTime()
    {
        return turnAroundTime;
    }
    public void getCurrentProcessInfo()
    {
        System.out.println("---------------------------------------------");
        System.out.println("Process Name:" + processName);
        System.out.println("Process remaining burst time:" + getRemainingBurstTime());
        System.out.println("Time until ready to enter ready queue:" + getProcessArrivalTime());
        System.out.println("Process ready to enter ready queue:" + readyToExecute());
        System.out.println("Process Context switch:" + contextSwitch);
        System.out.println("Process total wait time:" + getTotalWaitTime());
        System.out.println("Process total turnaound time:" + getTurnAroundTime());
        System.out.println("---------------------------------------------");
        System.out.println();
    }
    public int processFinished(){
        if(getRemainingBurstTime()==0){
            return 1;
        }
        else return 0;
    }
    public boolean readyToExecute()
    {
        if (getProcessArrivalTime() == 0) {return true;}
        else return false;
        
    }
}
