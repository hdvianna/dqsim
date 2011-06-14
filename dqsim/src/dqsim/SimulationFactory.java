package dqsim;



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
      Simulation sim = new Simulation();
      sim.setArrivalRandomNumberGenerator(new UniformRandomNumberGenerator());
      sim.setDepartureRandomNumberGenerator(new UniformRandomNumberGenerator());
      return sim;
  }


}
