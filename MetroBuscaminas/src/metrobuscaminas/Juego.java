/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package metrobuscaminas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author adcd_
 */
public class Juego extends javax.swing.JFrame {
    
    int filas;
    int columnas;
    int minas;
    JButton[][] botonesTablero;
    Grafo grafo;
    boolean marcar_bool;
    
    int casillasRestantes;
    int flaggedCount = 0;

    
    /**
     * Creates new form Juego
     */
    public Juego(int filas, int columnas, int minas) {
        this.filas = filas;
        this.columnas = columnas;
        this.minas = minas;
        this.marcar_bool = false;
        initComponents();
        setLocationRelativeTo(null);
        cargarControles();

        contadorMinas.setText(String.valueOf(this.minas));
        casillasRestantes = (this.filas * this.columnas)-this.minas;
        contadorCasillas.setText(String.valueOf(casillasRestantes));

        grafo = new Grafo(filas, columnas); // Inicializar Grafo
        grafo.asignarMinas(minas);
        grafo.calcularMinasAdyacentes();
        DFS.setSelected(true);
    }
    
   private void cargarControles() {
        botonesTablero = new JButton[this.filas][this.columnas];
        jPanel1.setLayout(new java.awt.GridLayout(this.filas, this.columnas));

        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.columnas; j++) {
                botonesTablero[i][j] = new JButton();
                botonesTablero[i][j].setName(i + "," + j);
                botonesTablero[i][j].setBorder(null);
                botonesTablero[i][j].setBackground(Color.GRAY);
                botonesTablero[i][j].setBorder(new BevelBorder(BevelBorder.RAISED));

                botonesTablero[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        btnClick(e);
                    }
                });
                jPanel1.add(botonesTablero[i][j]);
            }
        }
        jPanel1.setLayout(new java.awt.GridLayout(this.filas, this.columnas, 5, 5));
    }
   

    
    private void btnClick(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        String[] coordenada = btn.getName().split(",");
        int posFila = Integer.parseInt(coordenada[0]);
        int posColumna = Integer.parseInt(coordenada[1]);
        int index = posFila * columnas + posColumna; // Convertir coordenadas a índice lineal
        Casilla casilla = grafo.getCasillas()[index];

        // Si el modo marcar está activado:
        if (marcar_bool) {
            // Solo se permite marcar si la casilla no fue revelada aún
            if (!casilla.isRevelada()) {
                if (!casilla.isMarcada()) {
                    if (flaggedCount < minas) {
                        grafo.marcarCasilla(index);
                        flaggedCount++;
                        btn.setBackground(Color.YELLOW);
                        btn.setText("🚩");
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Solo se permiten " + minas + " banderas.");
                    }
                } else { // Si ya estaba marcada, la desmarca
                    grafo.desmarcarCasilla(index);
                    flaggedCount--;
                    btn.setBackground(Color.GRAY);
                    btn.setText("");
                }
            }
            verificarVictoria(); // Comprobar si el usuario ganó tras marcar/desmarcar
            return;
        }

        // Si la casilla ya fue revelada o está marcada, no hacer nada
        if (casilla.isRevelada() || casilla.isMarcada()) {
            return;
        }

        // Si el usuario hace clic en una mina, pierde el juego
        if (casilla.isTieneMina()) {
            btn.setBackground(Color.RED);
            btn.setText("💣");
            JOptionPane.showMessageDialog(rootPane, "¡BOOM! Pisaste una mina.");
            this.setVisible(false);
            Home home = new Home();
            home.setVisible(true);
        } else {
            if (DFS.isSelected()) {
                grafo.revelarCasilla(index);
            } else if (BFS.isSelected()) {
                grafo.revelarCasillaBFS(index);
            }
            actualizarTablero();
        }
    }
    
    private void verificarVictoria() {
        int minasMarcadasCorrectamente = 0;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                int index = i * columnas + j;
                Casilla casilla = grafo.getCasillas()[index];

                // Si la casilla es una mina y está marcada, sumamos al contador
                if (casilla.isTieneMina() && casilla.isMarcada()) {
                    minasMarcadasCorrectamente++;
                }
            }
        }

        // Si todas las minas están marcadas correctamente, el jugador gana
        if (minasMarcadasCorrectamente == minas) {
            JOptionPane.showMessageDialog(rootPane, "¡Felicidades! Has marcado todas las minas y GANASTE.");
            this.setVisible(false);
            Home home = new Home();
            home.setVisible(true);
        }
    }
    
    public Grafo getGrafo() {
        return this.grafo;
    }
    public void setFlaggedCount(int flaggedCount) {
        this.flaggedCount = flaggedCount;
    }

    public void actualizarTablero() {
        casillasRestantes = 0;
        boolean juegoPerdido = false;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                int index = i * columnas + j;
                Casilla casilla = grafo.getCasillas()[index];
                JButton btn = botonesTablero[i][j];
                if (casilla.isRevelada()) {
                    btn.setEnabled(false);
                    if (casilla.isTieneMina()) {
                        btn.setBackground(Color.RED);
                        btn.setText("💣");
                        JOptionPane.showMessageDialog(rootPane, "¡Perdiste!");
                        juegoPerdido = true;
                        this.setVisible(false);
                        Home home = new Home();
                        home.setVisible(true);
                    } else {
                        btn.setBackground(Color.LIGHT_GRAY);
                        if (casilla.getMinasAdyacentes() > 0) {
                            btn.setText(String.valueOf(casilla.getMinasAdyacentes())); // Mostrar número de minas adyacentes
                        }else {
                            btn.setText("");
                        }
                    }
                }else {
                    if (casilla.isMarcada()) {
                        btn.setBackground(Color.YELLOW);
                        btn.setText("🚩");
                    } else {
                        btn.setBackground(Color.GRAY);
                        btn.setText("");
                    }
                    casillasRestantes++; // Incrementar si la casilla no está revelada
                }

            }
        }
        contadorCasillas.setText(String.valueOf(casillasRestantes-this.minas));
        if (casillasRestantes-this.minas == 0 && juegoPerdido == false){
            JOptionPane.showMessageDialog(rootPane, "¡TERMINO EL JUEGO, GANASTE!");
            this.setVisible(false);
            Home home = new Home();
            home.setVisible(true);
        }
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        guardarPartida = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        contadorCasillas = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        contadorMinas = new javax.swing.JLabel();
        marcar = new javax.swing.JButton();
        ver_opcion_marcar = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        BFS = new javax.swing.JRadioButton();
        DFS = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 470, 420));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setText("MetroBuscaminas");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, -1, 40));

        guardarPartida.setText("Guardar Partida");
        guardarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarPartidaActionPerformed(evt);
            }
        });
        jPanel2.add(guardarPartida, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 47, -1, 30));

        jLabel2.setText("Casillas faltantes:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 110, -1));

        contadorCasillas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        contadorCasillas.setText("0");
        jPanel2.add(contadorCasillas, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 315, 20, 20));

        jLabel3.setText("Metodo de busqueda:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 200, 130, -1));

        contadorMinas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        contadorMinas.setText("0");
        jPanel2.add(contadorMinas, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 20, -1));

        marcar.setText("Marcar");
        marcar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marcarActionPerformed(evt);
            }
        });
        jPanel2.add(marcar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 110, 120, -1));

        ver_opcion_marcar.setText("Desactivado");
        jPanel2.add(ver_opcion_marcar, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 150, -1, -1));

        jLabel4.setText("Cantidad de minas:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 110, -1));

        buttonGroup2.add(BFS);
        BFS.setText("BFS");
        jPanel2.add(BFS, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 260, -1, -1));

        buttonGroup2.add(DFS);
        DFS.setText("DFS");
        DFS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DFSActionPerformed(evt);
            }
        });
        jPanel2.add(DFS, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 230, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void marcarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marcarActionPerformed
        if (marcar_bool == false){
            marcar_bool = true;
            this.ver_opcion_marcar.setText("Activado");
        }else{
            marcar_bool = false;
            this.ver_opcion_marcar.setText("Desactivado");
        }
    }//GEN-LAST:event_marcarActionPerformed

    private void guardarPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarPartidaActionPerformed
        // Crear el JFileChooser
        JFileChooser fc = new JFileChooser();

        // Crear y asignar el filtro para archivos CSV
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos CSV", "csv");
        fc.setFileFilter(filtro);
        fc.setAcceptAllFileFilterUsed(false);

        // Mostrar el diálogo de guardado
        int returnVal = fc.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String path = file.getAbsolutePath();

            // Forzar que el archivo tenga la extensión .csv
            if (!path.toLowerCase().endsWith(".csv")) {
                file = new File(path + ".csv");
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                //Escribir la línea de cabecera para las dimensiones del tablero
                bw.write("filas,columnas,minas");
                bw.newLine();
                //Escribir los valores de filas, columnas y minas
                bw.write(filas + "," + columnas + "," + minas);
                bw.newLine();

                //Recorrer cada casilla y escribir su estado en formato CSV
                Casilla[] casillas = grafo.getCasillas();
                for (Casilla c : casillas) {
                    bw.write(
                        c.getId() + "," + 
                        c.getFila() + "," + 
                        c.getColumna() + "," +
                        c.isRevelada() + "," +
                        c.isMarcada() + "," +
                        c.isTieneMina() + "," +
                        c.getMinasAdyacentes()
                    );
                    bw.newLine();
                }

                bw.flush();
                JOptionPane.showMessageDialog(this, "Partida guardada exitosamente en:\n" + file.getAbsolutePath());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar la partida: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_guardarPartidaActionPerformed

    private void DFSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DFSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DFSActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton BFS;
    private javax.swing.JRadioButton DFS;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel contadorCasillas;
    private javax.swing.JLabel contadorMinas;
    private javax.swing.JButton guardarPartida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton marcar;
    private javax.swing.JLabel ver_opcion_marcar;
    // End of variables declaration//GEN-END:variables
}
