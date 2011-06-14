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
        busyTime += (_clock-client.getServiceStartTime());
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
            System.out.println("getBusyTime:_clock" + _clock);
            System.out.println("getBusyTime:client.getServiceStartTime" + client.getServiceStartTime());
            return busyTime + (_clock-client.getServiceStartTime());
        } else {
            return busyTime;
        }
    }


}
