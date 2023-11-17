/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package BarberoDurmiente_Semaphore;


/**
 *
 * @author mario
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        
        System.out.println("Instanciamos la clase Barbero.");
        Barbero miBarbero = new Barbero();
        System.out.println("Creamos un hilo del metodo \"trabajar\" de Barbero.");
        Thread hiloBarbero = new Thread(()-> miBarbero.trabajar());//El barbero tiene su propio hilo para simular que trabaja en paralelo con los clientes
        System.out.println("Iniciamos el hilo...");
        hiloBarbero.start();
        System.out.println("Ahora dentro de un bucle for creamos hilos de la clase cliente.");
        for (int i = 0; i < 10; i++) {
            Thread hiloCliente = new Thread(new Cliente(i,miBarbero));
            System.out.println("Hilo de cliente creado [ "+i+" ]");
            hiloCliente.start();
        }
        
        
    }
}
