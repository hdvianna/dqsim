package dqsim;

import dqsim.radom.InverseGaussianRandomNumberGenerator;
import dqsim.radom.LomaxRandomNumberGenerator;
import dqsim.radom.Parameter;



/**
 * Class SimulationFactory
 */
public class SimulationFactory {


    public SimulationFactory () { };
  

  /**
   * @return       Simulation
   */
    public static Simulation CreateSimulation(String _identification, int _serversNumber )
    {
        Simulation sim = new Simulation(_serversNumber, 43200);
        
        sim.setServerCost(0.001413889);
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

    public static Simulation CreateSimulation(String _identification) {
        return SimulationFactory.CreateSimulation(_identification, 10);
    }


}
