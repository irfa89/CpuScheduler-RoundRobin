
package com.scheduler.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;

public class SchedulerClock {
	
	   private int time;
	
    public SchedulerClock()
    {
        time = 0;
    }
    public void updateTime(int runTime)
    {
        time = time + runTime;
    }
    public int getTime()
    {
        return time;
    }
    public void updateArrivalTime(ArrayList<Process> processList, int runTime)
    {
        for(int i=0;i<processList.size();i++)
        {
            Process process =processList.get(i);
            process.updateArrivalTime(runTime);
        }
    }
    public void updateWaitTime(Queue<Process> queue, int runTime, Process currentProcess)
    {
        Iterator<Process> itr = queue.iterator();
        while(itr.hasNext())
        {
            Process process = itr.next();
            if(process != currentProcess)
            {
            process.setCurrentWaitTime(runTime);
            }
     }
    }
}
