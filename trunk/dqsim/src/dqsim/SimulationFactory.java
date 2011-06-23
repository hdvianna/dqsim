package dqsim;

import dqsim.radom.InverseGaussianRandomNumberGenerator;
import dqsim.radom.LomaxRandomNumberGenerator;
import dqsim.radom.Parameter;
import dqsim.radom.UniformRandomNumberGenerator;



/**
 * Class SimulationFactory
 */
public class SimulationFactory {


    public SimulationFactory () { };
  

  /**
   * @return       Simulation
   */
    public static Simulation CreateSimulation(String _identification )
    {
        Simulation sim = new Simulation(10, 43200);
        
        sim.setServerCost(61.08);
        sim.setWaitingCost(0.2);

        LomaxRandomNumberGenerator arrivalRandomNumberGenerator = new LomaxRandomNumberGenerator();
        arrivalRandomNumberGenerator.addParameter("alpha", new Parameter(9.6475));
        arrivalRandomNumberGenerator.addParameter("beta", new Parameter(2951.2));
        sim.setArrivalRandomNumberGenerator(arrivalRandomNumberGenerator);

        InverseGaussianRandomNumberGenerator departureRandomNumberGenerator = new InverseGaussianRandomNumberGenerator();
        departureRandomNumberGenerator.addParameter("mu", new Parameter(263.04));
        departureRandomNumberGenerator.addParameter("lambda", new Parameter(483.99));
        sim.setDepartureRandomNumberGenerator(departureRandomNumberGenerator);

        sim.setName(_identification);
        
        return sim;
    }


}
