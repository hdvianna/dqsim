/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dqsim.report;

import dqsim.Client;
import dqsim.Server;
import dqsim.Simulation;
import dqsim.TimeRecord;
import dqsim.TimeRecordTracker;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public double ts() {
        double sum=0;
        for(Client client: simulation.getServerdClients()) {
            sum += client.getTimeOfDeparture() - client.getTimeOfArrival();            
        }
        return sum/simulation.getServerdClients().size();
    }

    public double tf() {
        double sum=0;
        for(Client client: simulation.getServerdClients()) {
            sum += client.getQueueEndTime() - client.getQueueStartTime();
        }
        return sum/simulation.getServerdClients().size();
    }

    public double busyAverageTime() {
        double sum=0;
        for(Server server: simulation.getServers()) {
            sum += server.getBusyTime();
        }
        return sum/simulation.getServers().size();
    }

    public double totalCost() {
        return (simulation.getWaitingCost() * ns()) +(simulation.getServerCost() * simulation.getServers().size());
    }

    public void save() {
        File file = new File("simulation_report_"+simulation.getName()+".csv");
        if (!file.exists()) {
            try {
                FileWriter fw = new FileWriter(file);
                fw.write("nf;ns;tf;ts;busy;ct" + "\r\n");
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(nf() + ";" + ";" + ns() + ";"+ tf() + ";" + ts() + ";" + busyAverageTime() + ";" + totalCost() + "\r\n");
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
