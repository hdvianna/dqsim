package dqsim;


import java.util.*;


/**
 * Class Simulation */
public class Simulation {

  private RandomNumberGenerator arrivalRandomNumberGenerator;
  private RandomNumberGenerator departureRandomNumberGenerator;
  private ArrayList<Server> _servers;
  private double clock;
  private double timeOfNextArrival;
  private double timeOfNextDeparture;
  private ArrayList<Client> queue;
  private double timeOfLastEvent;
  private ArrayList<Client> servedClients;
  
  //
  // Constructors
  //
  public Simulation () { };
  
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


}
