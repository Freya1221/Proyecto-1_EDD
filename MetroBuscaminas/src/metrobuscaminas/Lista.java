/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metrobuscaminas;

/**
 * Clase que representa una lista enlazada simple utilizada en el juego MetroBuscaminas.
 * La lista almacena enteros y se usa para gestionar adyacencias en el grafo del tablero.
 * 
 * @author Freya Blanca, Jesús Schneider
 */
public class Lista {
    private Nodo inicio;
    
    /**
     * Constructor de la lista enlazada.
     * Inicializa la lista vacía.
     */
    public Lista() {
        this.inicio = null;
    }

    public Nodo getInicio() {
        return inicio;
    }

    public void setInicio(Nodo inicio) {
        this.inicio = inicio;
    }
    
    /**
     * Inserta un nuevo nodo al inicio de la lista.
     * 
     * @param x Valor entero a insertar en la lista.
     */
    public void insertar(int x) {
        Nodo nuevo = new Nodo(x, this.getInicio());
        this.setInicio(nuevo);
    }
    
    /**
     * Elimina un nodo con un valor específico de la lista.
     * Si el nodo no existe, la lista permanece igual.
     * 
     * @param x Valor del nodo a eliminar.
     */
    public void eliminar(int x) {
        if (this.getInicio() == null) return;
        if (this.getInicio().getClave() == x) {
            this.setInicio(this.getInicio().getSiguiente());
            return;
        }
        Nodo actual = getInicio();
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getClave() == x) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                return;
            }
            actual = actual.getSiguiente();
        }
    }
    
    /**
     * Busca un valor en la lista.
     * 
     * @param x Valor a buscar.
     * @return true si el valor está en la lista, false en caso contrario.
     */
    public boolean busqueda(int x) {
        Nodo actual = this.getInicio();
        while (actual != null) {
            if (actual.getClave() == x) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }
 
    
    /**
     * Cuenta el número de elementos en la lista.
     * 
     * @return Cantidad de nodos en la lista.
     */
    public int numElementos() {
        int count = 0;
        Nodo actual = this.getInicio();
        while(actual != null) {
            count++;
            actual = actual.getSiguiente();
        }
        return count;
    }
    
    /**
     * Imprime los valores de la lista en la consola.
     */
    public void imprimir() {
        Nodo actual = this.getInicio();
        while(actual != null) {
            System.out.print(actual.getClave() + " -> ");
            actual = actual.getSiguiente();
        }
        System.out.println("null");
    }

}
