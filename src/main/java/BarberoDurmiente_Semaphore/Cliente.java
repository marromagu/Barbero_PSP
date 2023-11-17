/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BarberoDurmiente_Semaphore;


/**
 *
 * @author mario
 */
public class Cliente implements Runnable {
    private int nCliente;
    private Barbero miBarbero;

    public Cliente(int nCliente, Barbero miBarbero) {
        this.nCliente = nCliente;
        this.miBarbero = miBarbero;
    }

    @Override
    public void run() {
        miBarbero.cortarPelo(nCliente);
    }
    
    

}
