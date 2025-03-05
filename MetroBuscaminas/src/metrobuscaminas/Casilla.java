/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metrobuscaminas;

/**
 *
 * @author adcd_
 */
public class Casilla {
    private String id;
    private int fila, columna;
    private boolean tieneMina;
    private boolean revelada; 
    private boolean marcada;
    private int minasAdyacentes;
    
    
    public Casilla(String id, int fila, int columna) {
        this.id = id;
        this.fila = fila;
        this.columna = columna;
        this.tieneMina = false;
        this.revelada = false;
        this.marcada = false;
        this.minasAdyacentes = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public boolean isTieneMina() {
        return tieneMina;
    }

    public void setTieneMina(boolean tieneMina) {
        this.tieneMina = tieneMina;
    }

    public boolean isRevelada() {
        return revelada;
    }

    public void setRevelada(boolean revelada) {
        this.revelada = revelada;
    }

    public boolean isMarcada() {
        return marcada;
    }

    public void setMarcada(boolean marcada) {
        this.marcada = marcada;
    }

    public int getMinasAdyacentes() {
        return minasAdyacentes;
    }

    public void setMinasAdyacentes(int minasAdyacentes) {
        this.minasAdyacentes = minasAdyacentes;
    }

    
    
    public String toString() {
        if (revelada) {
            if (tieneMina) {
                return "*";
            } else {
                return Integer.toString(minasAdyacentes);
            }
        } else {
            if (marcada) {
                return "F";
            } else {
                return "?";
            }
        }
    }
    
}
