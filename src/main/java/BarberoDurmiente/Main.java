/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package BarberoDurmiente;

/**
 *
 * @author mario
 */
public class Main {

    public static void main(String[] args) {
        Barbero miBarbero = new Barbero();
        for (int i = 0; i < 10; i++) {
            Cliente miCliente = new Cliente(miBarbero, i);
            miCliente.start();
        }
    }
}
