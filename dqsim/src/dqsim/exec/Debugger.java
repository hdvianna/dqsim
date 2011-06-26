/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dqsim.exec;

import dqsim.Simulation;
import dqsim.SimulationFactory;
import dqsim.report.Report;

//import dqsim.radom.*;

/*import org.cloudbus.cloudsim.distributions.LomaxDistribution;

import org.cloudbus.cloudsim.distributions.ParetoDistr;
*/

//import umontreal.iro.lecuyer.randvar.InverseGaussianGen;

/**
 *
 * @author henrique
 */
public class Debugger {
    public static void main(String[] args) {
        //MTRandom mt = new MTRandom();

        /*
        //Alpha = Shape = 9.6475
        //Beta = Scale = 2951.2
        

        LomaxDistribution lomax = new LomaxDistribution(mt, 9.6475, 2951.2, 2951.2);
        ParetoDistr pareto = new ParetoDistr(mt, 9.6475, 2951.2);
        for (int i=0; i < 860; i++) {
            System.out.println((int) lomax.sample());
        }
         * */

        /*InverseGaussianGen inverseGaussian = new InverseGaussianGen(mt, 263.04, 483.99);
        for (int i=0; i < 860; i++) {
            System.out.println(inverseGaussian.nextDouble());
        }*/
        for (int i=0; i < 25; i++) {
        // TODO code application logic here
            Simulation sim = SimulationFactory.CreateSimulation("TRABALHO_MODELAGEM_3-SERVIDORES", 2);
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
