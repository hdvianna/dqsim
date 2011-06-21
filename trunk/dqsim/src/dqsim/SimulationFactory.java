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
      sim.setServerCost(61.08);//10,18 * 6 -> Custo de um servidor por dia,
                               //contabilizando salário
      sim.setWaitingCost(0.2);//Segundo a empresa simulada, o custo de
                                // espera gira em torno de R$ 83,33 h.-> R$ 22.000/264h
                                //Que é o custo de manutenção
                                // da infra-estrutura de atendimento necessária para
                                //o serviço. Como por exemplo:
                                // serviços de manutenção de infra-estrutura (TI e telefonia),
                                // custo de posição de central telefônica por cliente
                                //e licenças de software.
      sim.setArrivalRandomNumberGenerator(new UniformRandomNumberGenerator());
      sim.setDepartureRandomNumberGenerator(new UniformRandomNumberGenerator());
      sim.setName(_identification);
      return sim;
  }


}
