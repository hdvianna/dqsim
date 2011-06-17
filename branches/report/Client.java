package dqsim;



/**
 * Class Client */
public class Client {


  private double timeOfArrival=-1;
  private double timeOfDeparture=-1;
  private double serviceStartTime=-1;
  private double queueStartTime=-1;
  private double queueEndTime=-1;
  
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

    /**
   * @param        _serviceStartTime
   */
  public void setQueueStartTime( double _queueStartTime )
  {
      queueStartTime = _queueStartTime;
  }


  /**
   * @return       double
   */
  public double getQueueStartTime(  )
  {
      return queueStartTime;
  }

    public void setQueueEndTime( double _queueEndTime )
  {
      queueEndTime = _queueEndTime;
  }


  /**
   * @return       double
   */
  public double getQueueEndTime(  )
  {
      return queueEndTime;
  }


}
