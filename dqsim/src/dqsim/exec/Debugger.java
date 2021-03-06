/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dqsim.exec;

import dqsim.Simulation;
import dqsim.SimulationFactory;
import dqsim.report.Report;

/**
 *
 * @author henrique
 */
public class Debugger {
    public static void main(String[] args) {
        for (int i=0; i < 25; i++) {        
            Simulation sim = SimulationFactory.CreateSimulation("TRABALHO_MODELAGEM_3-SERVIDORES", 1);
            while(!sim.hasEnded()) {
                sim.nextEvent();
            }
            sim.finishSimulation();
            System.out.println("Simulacao finalizada.");
            Report report = new Report(sim);
            System.out.println("NS: " + report.ns());
            System.out.println("TS: " + report.ts());
            System.out.println("NF: " + report.nf());
            System.out.println("TF: " + report.tf());
            System.out.println("Ocupacao media: " + report.busyAverageTime());
            System.out.println("Custo total: " + report.totalCost());
            report.save();
        }
    }
}
