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
        clock = 0;
        timeOfLastEvent = 0;
        timeOfNextArrival = -1;
        timeOfNextDeparture = -1;
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
               freeServer.serveClient(queue.remove(0), clock);
            }
        }
    }

    private void arrival() {
        Server server = getFreeServer();
        Client client = new Client();
        client.setTimeOfArrival(clock);
        if (server != null) {
            server.serveClient(client, clock);
        } else {
            queue.add(client);
        }
    }

    public void nextEvent(  )
    {
        if (timeOfNextArrival <= clock) {
            timeOfNextArrival = clock + arrivalRandomNumberGenerator.generate();
        }

        if (timeOfNextDeparture <= clock ) {
            timeOfNextDeparture = timeOfNextArrival + departureRandomNumberGenerator.generate();
        }

        if (timeOfNextArrival < timeOfNextDeparture) {
            clock = timeOfNextArrival;
            arrival();
        } else {
            clock = timeOfNextDeparture;
            departure();
        }
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
}
