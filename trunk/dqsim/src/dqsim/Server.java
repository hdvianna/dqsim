package dqsim;


import java.util.*;


/**
 * Class Server */
public class Server {


    private boolean busy=false;
    private double busyTime=0;
    private Client client;

    public Server () { };

    public void serveClient(Client _client, double _clock) {        
        busy = true;
        client = _client;
        client.setServiceStartTime(_clock);
    }

    public Client endService(double _clock) {
        busyTime += (client.getServiceStartTime() - _clock);
        busy = false;
        client.setTimeOfDeparture(_clock);
        Client _ret = client;
        client = null;
        return _ret;
    }

    public Client getClient() {
        return client;
    }

    /**
    * @return       boolean
    */
    public boolean isBusy(  )
    {
        return busy;
    }

    public double getBusyTime(double _clock) {
        if (busy) {
            return busyTime + (client.getServiceStartTime() - _clock);
        } else {
            return busyTime;
        }
    }


}
