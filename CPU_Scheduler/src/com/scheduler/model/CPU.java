
package com.scheduler.model;

public class CPU {
	
	private int timeQuantum;
    private int throughput;
	
    public CPU(int quantum)
    {
       timeQuantum = quantum; 
       throughput = 0; 
    }
    public int run(Process process)
    {
        if(process.getRemainingBurstTime()>timeQuantum)
        {
            process.updateRemainingBurstTime();
            return timeQuantum;
            
        }
        else if(process.getRemainingBurstTime()<timeQuantum)
        {
            int run = process.getRemainingBurstTime();
            process.updateRemainingBurstTime();
            throughput++;
            return run;     }
        else
        {
            process.updateRemainingBurstTime();
            throughput++;
            return timeQuantum;
        }
    }
    public int getThroughput()
    {
        return throughput;
    }
    
}
