/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BarberoDurmiente;

import java.util.Random;

/**
 *
 * @author mario
 */
public class Barbero {

    private int sillas = 5;
    private int sillasOcupadas;

    public synchronized void sillaDeEspera(int id) throws InterruptedException {
        while (sillas == sillasOcupadas) {
            System.out.println("El cliente espera a sentarse " + id);
            this.wait();//Mientras las sillas esten ocupadas los clietnes esperan
        }
        //Si las sillas no estan ocupadas los clientes se sientan
        sillasOcupadas++;
        System.out.println("El Cliente se sienta en la silla " + id);
    }

    public synchronized int cortarPelo(int id) {
        while (true) {
            if (sillasOcupadas > 0) {
                int r = new Random().nextInt(2) + 1;
                switch (r) {
                    case 1:
                        System.out.println("El barbero se duerme...");
                        return 1;
                    case 2:
                        System.out.println("El Barbero le corta el pelo al cliente " + id);
                        this.notify();
                        sillasOcupadas--;
                        System.out.println("Limpia el suelo...");
                        return 2;
                    default:
                        throw new AssertionError();
                }
            } else {
                System.out.println("Se duerme...");
                return 0;
            }
        }

    }

}
