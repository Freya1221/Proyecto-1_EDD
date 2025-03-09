/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metrobuscaminas;

/**
 * Representa un nodo en una lista enlazada.
 * Cada nodo almacena un valor entero y una referencia al siguiente nodo en la lista.
 * Se utiliza en la estructura de datos del juego MetroBuscaminas.
 * 
 * @author Freya Blanca, Jesús Schneider
 */
public class Nodo {
    public int clave;
    public Nodo siguiente;
    
    /**
     * Constructor de la clase Nodo.
     * 
     * @param clave Valor entero que almacenará el nodo.
     * @param siguiente Referencia al siguiente nodo en la lista.
     */
    public Nodo(int clave, Nodo siguiente) {
        this.clave = clave;
        this.siguiente = siguiente;
    }

    /**
     * Getter and Setter de los atributos de Nodo
     * 
     */
    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
    
    
}
