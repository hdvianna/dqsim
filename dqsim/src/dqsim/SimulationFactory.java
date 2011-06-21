package dqsim;

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
      sim.setServerCost(0.0028);//10,18/3600 = 0,0028
      sim.setWaitingCost(1.95);//80/41
      sim.setArrivalRandomNumberGenerator(new UniformRandomNumberGenerator());
      sim.setDepartureRandomNumberGenerator(new UniformRandomNumberGenerator());
      sim.setName(_identification);
      return sim;
  }


}
