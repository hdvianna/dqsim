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
      sim.setArrivalRandomNumberGenerator(new UniformRandomNumberGenerator());
      sim.setDepartureRandomNumberGenerator(new UniformRandomNumberGenerator());
      return sim;
  }


}
