/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dqsim.exec;

import dqsim.SimulationFactory;
import dqsim.ui.SimConsole;

/**
 *
 * @author henrique
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SimConsole console = new SimConsole(SimulationFactory.CreateSimulation("TRABALHO_MODELAGEM"));
        console.start();
    }

}
