/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BarberoDurmiente_Synchronized;

import java.time.Duration;
import java.util.Random;

/**
 *
 * @author mario
 */
public class Barbero {

    private int sillasDeEspera = 5;
    private int sillasDeEsperaOcupadas;
    private int sillaDeCortarElPelo;

    public void sillaDeEspera(int id) throws InterruptedException {
        synchronized (this) {
            while (sillasDeEspera == sillasDeEsperaOcupadas) {
                System.out.println("El cliente espera a sentarse " + id);
                this.wait();//Mientras las sillas esten ocupadas los clietnes esperan
            }
            //Si las sillas no estan ocupadas los clientes se sientan
            sillasDeEsperaOcupadas++;
            System.out.println("El Cliente se sienta en la silla " + id);
        }
    }

    public void sillaDeCortarPelo(int i) throws InterruptedException {
        synchronized (this) {
            while (sillaDeCortarElPelo > 0) {
                this.wait();
            }
            sillaDeCortarElPelo++;
            System.out.println("El Cliente se sienta en la silla para cortar el pelo " + i);
            sillasDeEsperaOcupadas--;
            cortarPelo(i);
        }
    }

    public void cortarPelo(int id) throws InterruptedException {
        synchronized (this) {
            int t = 0;
            if (sillaDeCortarElPelo != 0) {
                int r = new Random().nextInt(2) + 1;
                switch (r) {
                    case 1:
                        System.out.println("El barbero se duerme...");
                        t = (new Random().nextInt(2) + 3);
                        Thread.sleep(t * 1000);
                    case 2:
                        System.out.println("El Barbero le corta el pelo al cliente " + id);
                        t = (new Random().nextInt(4) + 5);
                        Thread.sleep(t * 1000);
                        this.notify();
                        sillaDeCortarElPelo--;
                        System.out.println("Limpia el suelo...");
                        Thread.sleep(5000);
                        break;
                    default:
                        throw new AssertionError();
                }
            } else {
                System.out.println("Se duerme...");
                t = (new Random().nextInt(10) + 10);
                Thread.sleep(t * 1000);
            }

        }

    }

}
