/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package metrobuscaminas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;

/**
 *
 * @author adcd_
 */
public class Juego extends javax.swing.JFrame {
    
    int filas;
    int columnas;
    int minas;
    JButton[][] botonesTablero;
    /**
     * Creates new form Juego
     */
    public Juego(int filas, int columnas, int minas) {
        this.filas = filas;
        this.columnas = columnas;
        this.minas = minas;
        initComponents();
        setLocationRelativeTo(null);
        cargarControles();
        
        contadorMinas.setText(String.valueOf(this.minas));
        contadorCasillas.setText(String.valueOf(this.filas*this.columnas));
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
        jPanel1.setLayout(new java.awt.GridLayout(this.filas, this.columnas, 5, 5)); // 5 píxeles de espacio horizontal y vertical
    }
   
    private void btnClick(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        String[] coordenada = btn.getName().split(",");
        int posFila = Integer.parseInt(coordenada[0]);
        int posColumna = Integer.parseInt(coordenada[1]);
        JOptionPane.showMessageDialog(rootPane, posFila + "," + posColumna);
        
        // Deshabilitar el botón después de hacer clic
        btn.setBackground(Color.LIGHT_GRAY);
        btn.setEnabled(false);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        guardarPartida = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        contadorCasillas = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        contadorMinas = new javax.swing.JLabel();

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
        jPanel2.add(guardarPartida, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 47, -1, 30));

        jLabel2.setText("Casillas faltantes:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 110, -1));

        contadorCasillas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        contadorCasillas.setText("0");
        jPanel2.add(contadorCasillas, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 315, 20, 20));

        jLabel3.setText("Cantidad de minas:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 110, -1));

        contadorMinas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        contadorMinas.setText("0");
        jPanel2.add(contadorMinas, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 20, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JLabel contadorCasillas;
    private javax.swing.JLabel contadorMinas;
    private javax.swing.JButton guardarPartida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
