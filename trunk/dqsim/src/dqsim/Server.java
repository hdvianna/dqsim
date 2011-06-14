package dqsim;


import java.util.*;


/**
 * Class Server */
public class Server {


    private boolean busy=false;
    private double busyTime=0;
    private Client client;
    private double serviceStartTime;

    public Server () { };

    public void serveClient(Client _client, double _clock) {
        serviceStartTime = _clock;
        busy = true;
        client = _client;
        client.setServiceStartTime(_clock);
    }

    public Client endService(double _clock) {
        busyTime += serviceStartTime - _clock;
        busy = false;
        client.setTimeOfDeparture(_clock);
        return client;
    }

    /**
    * @return       boolean
    */
    public boolean isBusy(  )
    {
        return busy;
    }


}
