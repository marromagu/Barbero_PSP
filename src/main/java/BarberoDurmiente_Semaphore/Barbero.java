/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BarberoDurmiente_Semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mario
 */
public class Barbero {
//Variables de Instancia:

    private Random r = new Random();//Objeto de numeros aleatorios para el tiempo de dormir los hilos
    private Semaphore semaforoSillaDeEspera;//Semaforo para los clientes que llegan y se sientan en la silla de espera
    private Semaphore semaforoSillonDeCortarElPelo;//Semaforo para los clientes que ya se sientan para que les corte el pelo
    private Semaphore semaforoCortarElPelo;//Semaforo para los clientes que estan ya sentado en el sillon y el barbero le va a cortar el pelo

    public Barbero() {
        //Constructor de Barbero
        //Se inician los semaforos
        this.semaforoSillaDeEspera = new Semaphore(5);//Son las sillas(permisos) disponibles
        this.semaforoSillonDeCortarElPelo = new Semaphore(1);//Indicara si hay un cliente en la silla del barbero
        this.semaforoCortarElPelo = new Semaphore(0);//Coordinara el cirte de pelo entre el barbero y el cliente
    }

    public void cortarPelo(int nCliente) {
        try {
            System.out.println("Un Cliente " + nCliente + "entra en la barberia.");
            semaforoSillaDeEspera.acquire();
            System.out.println("\tCliente " + nCliente + " se ha sentado en la silla de espera.");
            semaforoSillonDeCortarElPelo.acquire();
            System.out.println("El Cliente " + nCliente + " se sienta en el sillon para cortar el pelo.");
            semaforoSillaDeEspera.release();//Liberamos un espacio de la silla de espera
            semaforoCortarElPelo.acquire();//El cliente que estaba en el sillon del barbero, Tiene que espear a que el recurso de cortar pelo este disponible(exclusión mutua)
            System.out.println("El Barbero le corta el pelo al Cliente " + nCliente);
            semaforoSillonDeCortarElPelo.release();
            System.out.println("\tEl Barbero limpia el suelo...");
            System.out.println("Ha corado el pelo a " + nCortados);
        } catch (InterruptedException ex) {
            Logger.getLogger(Barbero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    int nCortados = 0;

    public void trabajar() {
        try {
            while (true) {
                //El barbero entra en un bucle infinito
                if (semaforoSillonDeCortarElPelo.availablePermits() == 0) {
                    System.out.println("AHORA CORTA EL PELO EL BARBERO");
                    Thread.sleep(1000);
                    nCortados++;
                    semaforoCortarElPelo.release();//¿?
                }else{
                    System.out.println("El barbero ve q no hay nadie en el sillon y se duerme");
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Barbero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
