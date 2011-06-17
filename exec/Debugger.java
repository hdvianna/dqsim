/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dqsim.exec;

import dqsim.Simulation;
import dqsim.SimulationFactory;

/**
 *
 * @author henrique
 */
public class Debugger {
    public static void main(String[] args) {
        // TODO code application logic here
        Simulation sim = SimulationFactory.CreateSimulation("TRABALHO_MODELAGEM");
        while(!sim.hasEnded()) {
            sim.nextEvent();
        }
    }
}
