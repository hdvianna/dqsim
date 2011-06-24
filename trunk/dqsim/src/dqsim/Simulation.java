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
    private double serverCost;
    private double waitingCost;
    private String name;

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

    private void departure() {
        for(Server server : servers) {
            if (server.isBusy() && server.getClient().getTimeOfDeparture() == clock) {
                servedClients.add(server.endService(clock));
                if (queue.size() > 0) {
                    Server freeServer =  getFreeServer();
                    Client client = queue.remove(0);
                    client.setQueueEndTime(clock);
                    client.setTimeOfDeparture(clock + departureRandomNumberGenerator.generate());
                    freeServer.serveClient(client, clock);
                    queueTimeTracker.updateTimeRecordTracker(queue.size()+1, queue.size(), clock);
                } else {
                    busyServers--;
                }
                systemTimeTracker.updateTimeRecordTracker((busyServers+queue.size())+1, busyServers+queue.size(), clock);
            }
        }
    }

    private void arrival() {
        Server server = getFreeServer();
        Client client = new Client();
        client.setTimeOfArrival(clock);        
        if (server != null) {
            client.setTimeOfDeparture(clock + departureRandomNumberGenerator.generate());
            server.serveClient(client, clock);            
            busyServers++;
        } else {
            client.setQueueStartTime(clock);
            queue.add(client);
            queueTimeTracker.updateTimeRecordTracker(queue.size()-1, queue.size(), clock);
        }
        systemTimeTracker.updateTimeRecordTracker((busyServers+queue.size())-1, busyServers+queue.size(), clock);
    }

    private Client getNextDepartureClient(  )
    {
        double nextDeparture = -1;
        Client nextDepartureClient = null;
        for(Server server : servers) {
            if (server.isBusy()) {
                if (server.getClient().getTimeOfDeparture() < nextDeparture || nextDeparture == -1) {
                    nextDeparture = server.getClient().getTimeOfDeparture();
                    nextDepartureClient = server.getClient();
                }
            }
        }
        return nextDepartureClient;
    }

    public void nextEvent(  )
    {
        if (timeOfNextArrival == -1) {
            timeOfNextArrival = arrivalRandomNumberGenerator.generate();
        } else {
            if (timeOfNextArrival < timeOfNextDeparture ||timeOfNextDeparture==-1) {
                clock = timeOfNextArrival;
                arrival();                
                timeOfNextDeparture =  getNextDepartureClient().getTimeOfDeparture();
                timeOfNextArrival = clock + arrivalRandomNumberGenerator.generate(); 
            } else {
                if (busyServers==0) {
                    timeOfNextDeparture = -1;
                } else {
                    clock = timeOfNextDeparture;                    
                    departure();
                    if (busyServers > 0) {
                        timeOfNextDeparture =  getNextDepartureClient().getTimeOfDeparture();
                    }
                }
            }
        }
        hasEnded = timeOfNextArrival >= endTime;
    }

    public void finishSimulation() {
        while(busyServers > 0) {
            clock = timeOfNextDeparture = getNextDepartureClient().getTimeOfDeparture();
            departure();            
        }
        if (queueTimeTracker.getTimeTracker().size() > 0 && queueTimeTracker.getTimeTracker().get(0).get(queueTimeTracker.getTimeTracker().get(0).size()-1).getEndTime()==-1) {
            queueTimeTracker.getTimeTracker().get(0).get(queueTimeTracker.getTimeTracker().get(0).size()-1).setEndTime(clock);
        }
        if (systemTimeTracker.getTimeTracker().size() > 0 && systemTimeTracker.getTimeTracker().get(0).get(systemTimeTracker.getTimeTracker().get(0).size()-1).getEndTime()==-1) {
            systemTimeTracker.getTimeTracker().get(0).get(systemTimeTracker.getTimeTracker().get(0).size()-1).setEndTime(clock);
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

    public TimeRecordTracker getQueueRecordTimeTracker() {
            return queueTimeTracker;
    }

     public TimeRecordTracker getSystemRecordTimeTracker() {
            return systemTimeTracker;
    }

    public double getEndTime() {
        return endTime;
    }

    public void setServerCost(double _serverCost) {
        serverCost = _serverCost;
    }

    public double getServerCost() {
        return serverCost;
    }

    public void setWaitingCost(double _waitingCost) {
        waitingCost = _waitingCost;
    }

    public double getWaitingCost() {
        return waitingCost;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }
}
