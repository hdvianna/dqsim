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
    private boolean gotoEnd = false;

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
        System.out.println("\tServidores: ");
        i = 0;
        for(Server server: sim.getServers()) {
            i++;
            if (server.isBusy()) {                
                System.out.println("\t\t Servidor " + i + ": ");
                System.out.println("\t\t\t Tempo de chegada no sistema: " + server.getClient().getTimeOfArrival());
                System.out.println("\t\t\t Inicio de atendimento: " + server.getClient().getServiceStartTime());
                System.out.println("\t\t\t Tempo de ocupacao: " + server.getBusyTime(sim.getClock()));
            } else {
                System.out.println("\t\t Servidor " + i + " desocupado.");
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
                    sim.nextEvent();
                    if (!gotoEnd) {
                        printSimData();
                        System.out.println("COMANDOS:");
                        System.out.println("fim - vai para o fim a simulacao.");
                        System.out.println("sair - abandona a simulacao.");
                        System.out.println("<enter> - segue para o proximo passo.");
                        String command;
                        command = cons.readLine();
                        if (command.toLowerCase().equals("sair")) {
                            break;
                        } else if (command.toLowerCase().equals("fim")) {
                            gotoEnd = true;
                        }
                    } else {                        
                        sleep(1);
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
            System.out.println("Simulacao finalizada.");
        }
    }
}
