/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metrobuscaminas;

/**
 * Representa una casilla dentro del juego MetroBuscaminas.
 * Cada casilla puede contener una mina, ser marcada por el usuario o ser revelada.
 * También almacena la cantidad de minas adyacentes.
 * 
 * @author Freya Blanca, Jesús Schneider
 */
public class Casilla {
    private String id;
    private int fila, columna;
    private boolean tieneMina;
    private boolean revelada; 
    private boolean marcada;
    private int minasAdyacentes;
    
    /**
     * Constructor de la clase Casilla.
     * Inicializa una casilla en la posición dada, sin mina, no revelada y sin marcar.
     * 
     * @param id Identificador único de la casilla.
     * @param fila Número de fila en la que se encuentra la casilla.
     * @param columna Número de columna en la que se encuentra la casilla.
     */
    public Casilla(String id, int fila, int columna) {
        this.id = id;
        this.fila = fila;
        this.columna = columna;
        this.tieneMina = false;
        this.revelada = false;
        this.marcada = false;
        this.minasAdyacentes = 0;
    }

    /**
     * Getter and Setter de los atributos de Casilla
     * 
     */
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

    
    /**
     * Representación en texto de la casilla según su estado:
     * - "*" si contiene una mina y ha sido revelada.
     * - Número de minas adyacentes si no es una mina y ha sido revelada.
     * - "F" si está marcada con una bandera.
     * - "?" si no ha sido revelada ni marcada.
     * 
     * @return Representación textual de la casilla.
     */
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
