/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metrobuscaminas;

import java.util.Random;

/**
 *
 * @author adcd_
 */
public class Grafo {
    private int filas, columnas;
    private int maxNodos;
    private Lista[] listaAdy;
    private Casilla[] casillas;
    
    public Grafo(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.maxNodos = filas * columnas;
        listaAdy = new Lista[maxNodos];
        casillas = new Casilla[maxNodos];
       
        // Inicializar cada lista y crear las casillas
        for (int i = 0; i < maxNodos; i++) {
            listaAdy[i] = new Lista();
            int fila = i / columnas;
            int col = i % columnas;
            String id = this.obtenerLetra(col) + Integer.toString(fila + 1);
            casillas[i] = new Casilla(id, fila, col);
        }
        // Conectar cada casilla con sus casillas adyacentes (incluyendo diagonales)
        construirGrafo();
        
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public int getMaxNodos() {
        return maxNodos;
    }

    public void setMaxNodos(int maxNodos) {
        this.maxNodos = maxNodos;
    }

    public Lista[] getListaAdy() {
        return listaAdy;
    }

    public void setListaAdy(Lista[] listaAdy) {
        this.listaAdy = listaAdy;
    }

    public Casilla[] getCasillas() {
        return casillas;
    }

    public void setCasillas(Casilla[] casillas) {
        this.casillas = casillas;
    }
    
    private String obtenerLetra(int col) {
        return Character.toString((char)('A' + col));
    }
    
    private void construirGrafo() {
        for (int i = 0; i < this.getMaxNodos(); i++) {
            int fila = this.getCasillas()[i].getFila();
            int col = this.getCasillas()[i].getColumna();
            for (int df = -1; df <= 1; df++) {
                for (int dc = -1; dc <= 1; dc++) {
                    if (df == 0 && dc == 0) continue;
                    int nf = fila + df;
                    int nc = col + dc;
                    if (nf >= 0 && nf < this.getFilas() && nc >= 0 && nc < this.getColumnas()) {
                        int vecinoIndex = nf * this.getColumnas() + nc;
                        
                        if (!this.getListaAdy()[i].busqueda(vecinoIndex)) {
                            this.getListaAdy()[i].insertar(vecinoIndex);
                        }
                        if (!this.getListaAdy()[vecinoIndex].busqueda(i)) {
                            this.getListaAdy()[vecinoIndex].insertar(i);
                        }
                    }
                }
            }
        }
    }
    
    public void asignarMinas(int numMinas) {
        if (numMinas > this.getMaxNodos()) {
            throw new IllegalArgumentException("El número de minas no puede exceder el número de casillas.");
        }
        Random rand = new Random();
        int minasColocadas = 0;
        while (minasColocadas < numMinas) {
            int indice = rand.nextInt(this.getMaxNodos());
            if (!this.getCasillas()[indice].isTieneMina()) {
                this.getCasillas()[indice].setTieneMina(true);
                minasColocadas++;
            }
        }
    }
    
    public void calcularMinasAdyacentes() {
        for (int i = 0; i < this.getMaxNodos(); i++) {
            int count = 0;
            Nodo nodo = this.getListaAdy()[i].getInicio();
            while (nodo != null) {
                if (this.getCasillas()[nodo.getClave()].isTieneMina()) {
                    count++;
                }
                nodo = nodo.getSiguiente();
            }
            this.getCasillas()[i].setMinasAdyacentes(count);
        }
    }
    
    public void revelarCasilla(int index) {
        if (index < 0 || index >= this.getMaxNodos()) return;
        Casilla casilla = this.getCasillas()[index];
        if (casilla.isRevelada() || casilla.isMarcada()) return; // Si ya fue revelada o está marcada, no se hace nada

        casilla.setRevelada(true);
        // Si la casilla es segura (sin mina) y no tiene minas adyacentes, se revela el área vecina
        if (!casilla.isTieneMina() && casilla.getMinasAdyacentes() == 0) {
            Nodo nodo = this.getListaAdy()[index].getInicio();
            while (nodo != null) {
                revelarCasilla(nodo.getClave());
                nodo = nodo.getSiguiente();
            }
        }
    }
    
    public void marcarCasilla(int index) {
        if (index < 0 || index >= this.getMaxNodos()) return;
        Casilla casilla = this.getCasillas()[index];
        if (!casilla.isRevelada()) {
            casilla.setMarcada(true);
        }
    }
    
    public void desmarcarCasilla(int index) {
        if (index < 0 || index >= this.getMaxNodos()) return;
        Casilla casilla = this.getCasillas()[index];
        casilla.setMarcada(false);
    }
    
    
    public void imprimirTablero() {
        for (int f = 0; f < this.getFilas(); f++) {
            for (int c = 0; c < this.getColumnas(); c++) {
                int index = f * this.getColumnas() + c;
                System.out.print(this.getCasillas()[index].toString() + " ");
            }
            System.out.println();
        }
    }
    
    
    
}
