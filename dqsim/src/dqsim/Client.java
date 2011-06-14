package dqsim;



/**
 * Class Client */
public class Client {


  private double timeOfArrival;
  private double timeOfDeparture;
  private double serviceStartTime;
  
  public Client () { };
  

  /**
   * @param        _timeOfArrival
   */
  public void setTimeOfArrival( double _timeOfArrival )
  {
      timeOfArrival = _timeOfArrival;
  }


  /**
   * @return       double
   */
  public double getTimeOfArrival()
  {
      return timeOfArrival;
  }


  /**
   * @param        _timeOfDeparture
   */
  public void setTimeOfDeparture( double _timeOfDeparture )
  {
      timeOfDeparture = _timeOfDeparture;
  }


  /**
   * @return       double
   */
  public double getTimeOfDeparture(  )
  {
      return timeOfDeparture;
  }


  /**
   * @param        _serviceStartTime
   */
  public void setServiceStartTime( double _serviceStartTime )
  {
      serviceStartTime = _serviceStartTime;
  }


  /**
   * @return       double
   */
  public double getServiceStartTime(  )
  {
      return serviceStartTime;
  }


}
