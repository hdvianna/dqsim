package dqsim;


import dqsim.radom.RandomNumberGenerator;
import java.util.*;


/**
 * Class Simulation */
public class Simulation {

  private RandomNumberGenerator arrivalRandomNumberGenerator;
  private RandomNumberGenerator departureRandomNumberGenerator;
  private ArrayList<Server> servers;
  private double clock;
  private double timeOfNextArrival;
  private double timeOfNextDeparture;
  private ArrayList<Client> queue;
  private double timeOfLastEvent;
  private ArrayList<Client> servedClients;
  private double endTime;
  private boolean hasEnded=false;
  
  private void createServers(int _serversNumber) {
      servers = new ArrayList<Server>();
      for(int i=0;i < _serversNumber; i++) {
          servers.add(new Server());
      }
  }

  //
  // Constructors
  //
  public Simulation (int _serversNumber, double _endTime) {
      endTime = _endTime;
      createServers(_serversNumber);
      servedClients = new ArrayList<Client>();
      queue = new ArrayList<Client>();
  };
  
  /**
   * @param        _arrivalRandomNumberGenerator
   */
  public void setArrivalRandomNumberGenerator( RandomNumberGenerator _arrivalRandomNumberGenerator )
  {
      arrivalRandomNumberGenerator = _arrivalRandomNumberGenerator;
  }


  /**
   * @param        _departureRandomNumberGenerator
   */
  public void setDepartureRandomNumberGenerator( RandomNumberGenerator _departureRandomNumberGenerator )
  {
      departureRandomNumberGenerator = _departureRandomNumberGenerator;
  }


  /**
   * @return       double
   */
  public double getClock(  )
  {
      return clock;
  }


  /**
   * @return       double
   */
  public double getTimeOfNextArrival(  )
  {
      return timeOfNextArrival;
  }


  /**
   * @return       double
   */
  public double getTimeOfNextDeparture(  )
  {
      return timeOfNextDeparture;
  }


  /**
   */
  public void nextEvent(  )
  {
  }


  public boolean hasEnded() {
      return hasEnded;
  }

}
