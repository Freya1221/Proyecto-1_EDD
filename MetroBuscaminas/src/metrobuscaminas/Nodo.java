/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metrobuscaminas;

/**
 *
 * @author adcd_
 */
public class Nodo {
    public int clave;
    public Nodo siguiente;
    
    public Nodo(int clave, Nodo siguiente) {
        this.clave = clave;
        this.siguiente = siguiente;
    }
}
