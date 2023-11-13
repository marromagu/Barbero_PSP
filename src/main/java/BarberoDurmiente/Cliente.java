/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BarberoDurmiente;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mario
 */
public class Cliente extends Thread {

    Barbero miBarbero;
    int id;

    public Cliente(Barbero miBarbero, int id) {
        this.miBarbero = miBarbero;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            miBarbero.sillaDeEspera(id);
            Thread.sleep(1000);
            switch (miBarbero.cortarPelo(id)) {
                case 0:
                    Thread.sleep(new Random().nextInt(1000) + 1000);
                   
                    break;
                case 1:
                    Thread.sleep(new Random().nextInt(3000) + 3000);
                   
                    break;
                case 2:
                    Thread.sleep(new Random().nextInt(6000) + 5000);
                    break;
                default:
                    throw new AssertionError();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
