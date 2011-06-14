package dqsim;


import java.util.*;


/**
 * Class Server */
public class Server {


  private boolean busy;
  
  public Server () { };
  

  /**
   * @param        _busy
   */
  public void setBusy( boolean _busy )
  {
      busy = _busy;
  }


  /**
   * @return       boolean
   */
  public boolean isBusy(  )
  {
      return busy;
  }


}
