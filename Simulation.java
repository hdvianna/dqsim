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
    private double timeOfLastEvent;
    private double timeOfNextArrival;
    private double timeOfNextDeparture;
    private ArrayList<Client> queue;    
    private ArrayList<Client> servedClients;
    private int busyServers=0;
    private double endTime;
    private boolean hasEnded=false;
    private TimeRecordTracker queueTimeTracker;
    private TimeRecordTracker systemTimeTracker;

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
        clock = 0;
        timeOfLastEvent = 0;
        timeOfNextArrival = -1;
        timeOfNextDeparture = -1;
        endTime = _endTime;
        createServers(_serversNumber);
        servedClients = new ArrayList<Client>();
        queue = new ArrayList<Client>();
        queueTimeTracker = new TimeRecordTracker(clock);
        systemTimeTracker = new TimeRecordTracker(clock);
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

    private Server getFreeServer () {
        for(Server server : servers) {
            if (!server.isBusy()) {
                return server;
            }
        }
        return null;
    }

    private Server getServerWithOldestClient() {
        Server retServer = null;
        for(Server server : servers) {
            if (server.isBusy() && 
                (retServer == null ||
                server.getClient().getServiceStartTime() < retServer.getClient().getServiceStartTime()))
            {
                retServer = server;
                
            }
        }
        return retServer;
    }

    private void departure() {
        Server server = getServerWithOldestClient();
        if (server != null) {
            servedClients.add(server.endService(clock));
            if (queue.size() > 0) {
                Server freeServer =  getFreeServer();
                Client client = queue.remove(0);
                client.setQueueEndTime(clock);
                freeServer.serveClient(client, clock);
                queueTimeTracker.updateTimeRecordTracker(queue.size()+1, queue.size(), clock);
            } else {
                busyServers--;
            }
            systemTimeTracker.updateTimeRecordTracker((busyServers+queue.size())+1, busyServers+queue.size(), clock);
        } 
    }

    private void arrival() {
        Server server = getFreeServer();
        Client client = new Client();
        client.setTimeOfArrival(clock);
        if (server != null) {
            server.serveClient(client, clock);
            busyServers++;
        } else {
            client.setQueueStartTime(clock);
            queue.add(client);
            queueTimeTracker.updateTimeRecordTracker(queue.size()-1, queue.size(), clock);
        }
        systemTimeTracker.updateTimeRecordTracker((busyServers+queue.size())-1, busyServers+queue.size(), clock);
    }

    public void nextEvent(  )
    {
        if (timeOfNextArrival == -1) {
            timeOfNextArrival = arrivalRandomNumberGenerator.generate();
        } else {            
            if (timeOfNextArrival <= timeOfNextDeparture ||timeOfNextDeparture==-1) {
                clock = timeOfNextArrival;
                timeOfNextArrival = clock + arrivalRandomNumberGenerator.generate();
                if (timeOfNextDeparture==-1) {
                    timeOfNextDeparture = timeOfNextArrival + departureRandomNumberGenerator.generate();
                }
                arrival();                
            } else {
                clock = timeOfNextDeparture;
                timeOfNextDeparture = clock + departureRandomNumberGenerator.generate();
                departure();
                if (busyServers==0) {
                    timeOfNextDeparture = -1;
                }
            }            
        }
        hasEnded = clock >= endTime;

    }

    public boolean hasEnded() {
        return hasEnded;
    }

    public ArrayList<Client> getQueue() {
        return queue;
    }

    public ArrayList<Client> getServerdClients() {
        return servedClients;
    }

    public ArrayList<Server> getServers() {
        return servers;
    }

    public TimeRecordTracker getQueueRecordTimeTracker() {
            return queueTimeTracker;
    }

     public TimeRecordTracker getSystemRecordTimeTracker() {
            return systemTimeTracker;
    }

    public double getEndTime() {
        return endTime;
    }
}
