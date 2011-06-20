/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dqsim.report;

import dqsim.Simulation;
import dqsim.TimeRecord;
import dqsim.TimeRecordTracker;
import java.util.ArrayList;

/**
 *
 * @author henrique
 */
public class Report {
    private Simulation simulation;

    public Report(Simulation _simulation){
        simulation = _simulation;
    }

    private double weightedAverage(ArrayList<ArrayList<TimeRecord>> timeTracker) {
        double sum=0;
        for(int i=0; i<timeTracker.size();i++) {
            double partialSum = 0;
            for(TimeRecord timeRecord : timeTracker.get(i)) {
                if(timeRecord.getEndTime() == -1) {
                    timeRecord.setEndTime(simulation.getClock());
                }
                partialSum += timeRecord.getEndTime() - timeRecord.getStartTime();
            }
            sum += (partialSum * i);
        }
        return sum/simulation.getClock();
    }

    public double nf() {
       return weightedAverage(simulation.getQueueRecordTimeTracker().getTimeTracker());
    }

    public double ns() {
       return weightedAverage(simulation.getSystemRecordTimeTracker().getTimeTracker());
    }
}
