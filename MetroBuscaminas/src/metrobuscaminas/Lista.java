/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metrobuscaminas;

/**
 *
 * @author adcd_
 */
public class Lista {
    private Nodo inicio;
    
    public Lista() {
        this.inicio = null;
    }

    public Nodo getInicio() {
        return inicio;
    }

    public void setInicio(Nodo inicio) {
        this.inicio = inicio;
    }
    
    public void insertar(int x) {
        Nodo nuevo = new Nodo(x, this.getInicio());
        this.setInicio(nuevo);
    }
    
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
 
    
    public int numElementos() {
        int count = 0;
        Nodo actual = this.getInicio();
        while(actual != null) {
            count++;
            actual = actual.getSiguiente();
        }
        return count;
    }
    
    public void imprimir() {
        Nodo actual = this.getInicio();
        while(actual != null) {
            System.out.print(actual.getClave() + " -> ");
            actual = actual.getSiguiente();
        }
        System.out.println("null");
    }

}
