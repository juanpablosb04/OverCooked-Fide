
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Juan Pablo
 */
public class Juego extends javax.swing.JFrame {
   
    private Timer mTimer;
    private int milliseconds = 100;
    private int minutes = 5;
    private int seconds = 1;
    
    ArrayList al = new ArrayList(3);
    Timer tiempoOrden1;
    Timer tiempoOrden2;
    Timer tiempoOrden3;
    private int pts = 0;
    /**
     * Creates new form Juego
     */
    FondoPartida fondo = new FondoPartida();
    public Juego() {
        this.setContentPane(fondo);
        initComponents();
        mTimer = new Timer(10, (ActionEvent e) -> {
        StartC();
        });
        mTimer.start();
        this.setLocationRelativeTo(null);
        BotonI.setOpaque(false);
        BotonI.setContentAreaFilled(false);
        BotonI.setBorderPainted(false);
        BotonD.setOpaque(false);
        BotonD.setContentAreaFilled(false);
        BotonD.setBorderPainted(false);
        Random r = new Random();
        
        String elementoAzar;
        al.add("HAMBURGUESA DE CARNE: Pan,Carne");
        al.add("HAMBURGUESA DE QUESO: Pan, Carne y Queso");
        al.add("HAMBURGUESA CLASICA: Pan, Carne, Queso y Lechuga");
        
        int a = (int) (Math.random()* 3);
        String txt = (String) al.get(a);
        ordenPrincipal1.setText(txt);
        
        tiempoOrden2 = new Timer(5000, null);
        tiempoOrden2.start();
        tiempoOrden2.addActionListener(new java.awt.event.ActionListener(){
        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt){
            if(ordenSiguiente.getText().equals("Esperando orden...")){
                generarSiguienteOrden();
            }else if(ordenLejana.getText().equals("Esperando orden...")){
                generarOrdenLejana();
            }else{
            
            }
           }
        }); 
        
    }
    
    //private void Cronometro(){
    //mTimer = new Timer(10, (ActionEvent e) -> {
    //    });
     //   StartC();
   // }
    
    private void StartC(){
    actualizaTiempo();
    actualizaLabel();
    }
    
    private void actualizaTiempo(){
      
        milliseconds--; 
        if (milliseconds == 0) {
       milliseconds = 100;
       seconds--;
       }
        if (seconds == 0) {
       seconds = 60;
       minutes--;
       }
        if (minutes == 0){
            GameOver nuevo = new GameOver();
            nuevo.setVisible(true);
            this.dispose();
           minutes = 0;
        }
         
    }
  
    private void actualizaLabel(){
    //String cronometro = + minutes + "m:" +seconds+ "s:";
    Tiempo.setText(minutes + "m:" +seconds+ "s:");
    ms.setText("."+ milliseconds);
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ordenPrincipal = new javax.swing.JLabel();
        orden2 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new FondoPartida();
        BotonI = new javax.swing.JButton();
        BotonD = new javax.swing.JButton();
        PanelPedido = new javax.swing.JPanel();
        lbl3 = new javax.swing.JLabel();
        ordenPrincipal1 = new javax.swing.JLabel();
        lbl1 = new javax.swing.JLabel();
        ordenSiguiente = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        ordenLejana = new javax.swing.JLabel();
        Tiempo = new javax.swing.JLabel();
        ms = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        ordenPrincipal.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        ordenPrincipal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ordenPrincipal.setText("Esperando orden...");

        orden2.setText("Esperando orden...");

        jLabel2.setText("Orden mas Lejana");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(BotonI, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 130, 60));

        BotonD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonDActionPerformed(evt);
            }
        });
        jPanel1.add(BotonD, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 460, 130, 60));

        PanelPedido.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout PanelPedidoLayout = new javax.swing.GroupLayout(PanelPedido);
        PanelPedido.setLayout(PanelPedidoLayout);
        PanelPedidoLayout.setHorizontalGroup(
            PanelPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PanelPedidoLayout.setVerticalGroup(
            PanelPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(PanelPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        lbl3.setFont(new java.awt.Font("Elephant", 1, 24)); // NOI18N
        lbl3.setForeground(new java.awt.Color(255, 255, 255));
        lbl3.setText("ORDEN ACTIVA");
        jPanel1.add(lbl3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, -1, -1));

        ordenPrincipal1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        ordenPrincipal1.setForeground(new java.awt.Color(255, 255, 255));
        ordenPrincipal1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ordenPrincipal1.setText("Esperando orden...");
        jPanel1.add(ordenPrincipal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, 370, 20));

        lbl1.setForeground(new java.awt.Color(255, 255, 255));
        lbl1.setText("Siguiente Orden");
        jPanel1.add(lbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 20));

        ordenSiguiente.setForeground(new java.awt.Color(255, 255, 255));
        ordenSiguiente.setText("Esperando orden...");
        jPanel1.add(ordenSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 280, -1));

        lbl2.setForeground(new java.awt.Color(255, 255, 255));
        lbl2.setText("Orden mas Lejana");
        jPanel1.add(lbl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        ordenLejana.setForeground(new java.awt.Color(255, 255, 255));
        ordenLejana.setText("Esperando orden...");
        jPanel1.add(ordenLejana, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, -1, -1));

        Tiempo.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 24)); // NOI18N
        Tiempo.setForeground(new java.awt.Color(255, 255, 255));
        Tiempo.setText("0m: 0s");
        jPanel1.add(Tiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, -20, 100, 100));

        ms.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ms.setForeground(new java.awt.Color(255, 255, 255));
        ms.setText(".000");
        jPanel1.add(ms, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, -20, 90, 120));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Time:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotonDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotonDActionPerformed

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
                new Juego().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonD;
    private javax.swing.JButton BotonI;
    public javax.swing.JPanel PanelPedido;
    private javax.swing.JLabel Tiempo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel ms;
    private javax.swing.JLabel orden2;
    private javax.swing.JLabel ordenLejana;
    private javax.swing.JLabel ordenPrincipal;
    private javax.swing.JLabel ordenPrincipal1;
    private javax.swing.JLabel ordenSiguiente;
    // End of variables declaration//GEN-END:variables
    class FondoPartida extends JPanel{
        private Image imagen;
        public void paint (Graphics g){
            imagen = new ImageIcon(getClass().getResource
        ("Imagenes/fondonuevo.jpg")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(),
                    getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }
    public void generarSiguienteOrden(){
    int a = (int) (Math.random()* 3);
    String txt1 = (String) al.get(a);
    ordenSiguiente.setText(txt1);
}
public void generarOrdenLejana(){
    int a = (int) (Math.random()* 3);
    String txt2 = (String) al.get(a);
    ordenLejana.setText(txt2);
}
}
