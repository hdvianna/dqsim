/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dqsim.ui;

import dqsim.Client;
import dqsim.Server;
import dqsim.Simulation;
import dqsim.SimulationFactory;
import java.io.Console;

/**
 *
 * @author henrique
 */
public class SimConsole extends Thread {
    private Simulation sim;

    public SimConsole(Simulation _sim) {
        sim = _sim;
    }

    private void printSimData() {
        int i;
        System.out.println("Dados da Simulacao:");
        System.out.println("\tTempo: " + sim.getClock());
        System.out.println("\tProxima chegada: " + sim.getTimeOfNextArrival());
        System.out.println("\tProxima partida: " + sim.getTimeOfNextDeparture());
        System.out.println("");
        System.out.println("\tServidores ocupados: ");
        i = 0;
        for(Server server: sim.getServers()) {
            if (server.isBusy()) {
                i++;
                System.out.println("\t\t Servidor " + i + ": ");
                System.out.println("\t\t\t Tempo de chegada no sistema: " + server.getClient().getTimeOfArrival());
                System.out.println("\t\t\t Inicio de atendimento: " + server.getClient().getServiceStartTime());
                System.out.println("\t\t\t Tempo de ocupacao: " + server.getBusyTime(sim.getClock()));
            }
        }
        System.out.println("");
        System.out.println("\tFila: ");
        System.out.println("\t\t  Tamanho: " + sim.getQueue().size());
        i = 0;
        for(Client client: sim.getQueue()) {
            i++;
            System.out.println("\t\t Cliente " + i + ": ");
            System.out.println("\t\t\t Tempo de chegada no sistema: " + client.getTimeOfArrival());
        }
        System.out.println("");

    }

    public void run() {
        Console cons;        
        if ((cons = System.console()) != null ) {            
            while(!sim.hasEnded()) {
                try {
                    printSimData();
                    System.out.println("Digite fim para finalizar a simulacao, enter continua.");
                    String command;
                    command = cons.readLine();                   
                    if (command.toLowerCase().equals("fim")) {
                        break;
                    }
                    sim.nextEvent();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
            System.out.println("Simulacao finalizada.");
        }
    }
}
