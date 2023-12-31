
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    //Inicio Variables para Ingredientes
    private Ingrediente ingrediente0;
    private Ingrediente ingrediente1;
    private Ingrediente ingrediente2;
    private Ingrediente ingrediente3;
    ListaIngrediente listaIngrediente; 
    ListaCircularIngrediente cintaTransportadora;
    //Fin Variables para Ingredientes
    
    //Inicio variables para validacion de orden
    private int validarHCarne=0;
    private int validarHQueso=0;
    private int validarHClasica=0;
    private int usoPan=0;
    private int usoCarne=0;
    private int usoQueso=0;
    private int usoLechuga=0;
    //Inicio variables para validacion de orden

    int puntos=0;//Puntos del juego
    
    //Inicio de variables para el cronometro
    private Timer mTimer;
    private int milliseconds = 100;
    private int minutes = 5;
    private int seconds = 1;
    //Fin de variables para cronometro
    
    //Inicio de variables para generar ordenes
    ArrayList al = new ArrayList(3);
    Timer tiempoOrden1;
    Timer tiempoOrden2;
    Timer tiempoOrden3;
    //Fin de variables para generar ordenes
    
    FondoPartida fondo = new FondoPartida();//Fondo del juego
    public Juego() {
        //Inicio del sistema de ordenes
        this.setContentPane(fondo);
        initComponents();
        mTimer = new Timer(10, (ActionEvent e) -> {
        StartC();
        });
        mTimer.start();
        this.setLocationRelativeTo(null);
        Random r = new Random();
        
        String elementoAzar;
        al.add("HAMBURGUESA DE CARNE: Pan,Carne");
        al.add("HAMBURGUESA DE QUESO: Pan, Carne y Queso");
        al.add("HAMBURGUESA CLASICA: Pan, Carne, Queso y Lechuga");
        
        int a = (int) (Math.random()* 3);
        String txt = (String) al.get(a);
        ordenPrincipal1.setText(txt);
        
        imagenOrdenActiva();
        
        tiempoOrden2 = new Timer(20000, null);
        tiempoOrden2.start();
        tiempoOrden2.addActionListener(new java.awt.event.ActionListener(){
        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt){
            if(ordenPrincipal1.getText().equals("Esperando orden...")){
                generarOrdenPrincipal();
                imagenOrdenActiva();
            }else if(ordenSiguiente.getText().equals("Esperando orden...")){
                generarSiguienteOrden();
            }else if(ordenLejana.getText().equals("Esperando orden...")){
                generarOrdenLejana();
            }else{
            
            }
           }
        });
        //FIN DEL SISTEMA DE ORDENES
        
        //INICIO DEL SISTEMA DE INGREDIENTES Y CINTA
        this.ingrediente0 = new Ingrediente("ingrediente0", "Pan");
        this.ingrediente1 = new Ingrediente("ingrediente1", "Carne");
        this.ingrediente2 = new Ingrediente("ingrediente2", "Queso");
        this.ingrediente3 = new Ingrediente("ingrediente3", "Lechuga");

        // LISTA DE CUATRO INGREDIENTES 
        listaIngrediente = new ListaIngrediente();
        listaIngrediente.insertarFinal(ingrediente0);
        listaIngrediente.insertarFinal(ingrediente1);
        listaIngrediente.insertarFinal(ingrediente2);
        listaIngrediente.insertarFinal(ingrediente3);
        
        cintaTransportadora = new ListaCircularIngrediente();
        
        // CARGAR DATOS ALEATORIOS EN CINTA TRANSPORTADORA
        for(int i = 0; i < 5; i++){
            cintaTransportadora.agregar(listaIngrediente.obtenerIngrediente
        (randomNumber(4, 0)));
        }
        //Colocar los ingredientes en los botones
        colocarNombreEnBotones();
        
        
    }
    
    //Generador de ordenes
    public void generarOrdenPrincipal(){
        int a = (int) (Math.random()* 3);
        String txt3 = (String) al.get(a);
        ordenPrincipal1.setText(txt3);
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
    }//Generador de ordenes
    
    //Generador random de ingredientes
    private int randomNumber(int max, int min){
        return (int) ((Math.random() * (max - min)) + min);
    }
    
    private void colocarNombreEnBotones(){
        
        String ingredienteUno = String.valueOf
        (cintaTransportadora.obtenerIngrediente(0));
        btnUno.setText(ingredienteUno);
        
        String ingredienteDos = String.valueOf
        (cintaTransportadora.obtenerIngrediente(1));
        btnDos.setText(ingredienteDos);
        
        String ingredienteTres = String.valueOf
        (cintaTransportadora.obtenerIngrediente(2));
        btnTres.setText(ingredienteTres);
        
        String ingredienteCuatro = String.valueOf
        (cintaTransportadora.obtenerIngrediente(3));
        btnCuatro.setText(ingredienteCuatro);
        
        String ingredienteCinco = String.valueOf
        (cintaTransportadora.obtenerIngrediente(4));
        btnCinco.setText(ingredienteCinco);

    }
    //Revisa si hay 3 ingredientes en la cinta y genera 2 nuevos inmediatamente
    private void comprobarIngredienteDisponibles(){
        if(cintaTransportadora.cantidadElementos() == 3){
            cintaTransportadora.agregar(listaIngrediente.obtenerIngrediente
        (randomNumber(0, 4)));
            cintaTransportadora.agregar(listaIngrediente.obtenerIngrediente
        (randomNumber(0, 4)));
        }else{
        }
    }
    //Realiza la validacion del ingrediente seleccionado con la orden activa en ese momento
    private void validarIngredientes(Ingrediente ingrediente){
        if(ordenPrincipal1.getText().equals("HAMBURGUESA DE CARNE: Pan,Carne")){
        
            if(ingrediente.equals(ingrediente0) && usoPan==0){
                usoPan++;
                validarHCarne++;
                cintaTransportadora.eliminar(ingrediente);
            }else if(ingrediente.equals(ingrediente1) && usoCarne==0){
                usoCarne++;
                validarHCarne++;
                cintaTransportadora.eliminar(ingrediente);
            }else{
                JOptionPane.showMessageDialog
        (null,"Este ingrediente es incorrecto o ya fue incluido");
            }
            
            if(validarHCarne==2){
                JOptionPane.showMessageDialog
        (null,"Orden Completada! ");
                puntos+=5;
                lblPuntos.setText(String.valueOf(puntos));
                usoPan=0;
                usoCarne=0;
                validarHCarne=0;
                ordenPrincipal1.setText(ordenSiguiente.getText());
                ordenSiguiente.setText(ordenLejana.getText());
                ordenLejana.setText("Esperando orden...");
                imagenOrdenActiva();
            }else{    
            }
        }
        
        if(ordenPrincipal1.getText().equals
        ("HAMBURGUESA DE QUESO: Pan, Carne y Queso")){
        
            if(ingrediente.equals(ingrediente0) && usoPan==0){
                usoPan++;
                validarHQueso++;
                cintaTransportadora.eliminar(ingrediente);
            }else if(ingrediente.equals(ingrediente1) && usoCarne==0){
                usoCarne++;
                validarHQueso++;
                cintaTransportadora.eliminar(ingrediente);
            }else if(ingrediente.equals(ingrediente2) && usoQueso==0){
                usoQueso++;
                validarHQueso++;
                cintaTransportadora.eliminar(ingrediente);
            }else{
                JOptionPane.showMessageDialog
        (null,"Este ingrediente es incorrecto o ya fue incluido");
            }
            
            if(validarHQueso==3){
                JOptionPane.showMessageDialog
        (null,"Orden Completada! ");
                puntos+=10;
                lblPuntos.setText(String.valueOf(puntos));
                usoPan=0;
                usoCarne=0;
                usoQueso=0;
                validarHQueso=0;
                ordenPrincipal1.setText(ordenSiguiente.getText());
                ordenSiguiente.setText(ordenLejana.getText());
                ordenLejana.setText("Esperando orden...");
                imagenOrdenActiva();
            }else{    
            }
        }
        
        if(ordenPrincipal1.getText().equals
        ("HAMBURGUESA CLASICA: Pan, Carne, Queso y Lechuga")){
        
            if(ingrediente.equals(ingrediente0) && usoPan==0){
                usoPan++;
                validarHClasica++;
                cintaTransportadora.eliminar(ingrediente);
            }else if(ingrediente.equals(ingrediente1) && usoCarne==0){
                usoCarne++;
                validarHClasica++;
                cintaTransportadora.eliminar(ingrediente);
            }else if(ingrediente.equals(ingrediente2) && usoQueso==0){
                usoQueso++;
                validarHClasica++;
                cintaTransportadora.eliminar(ingrediente);
            }else if(ingrediente.equals(ingrediente3) && usoLechuga==0){
                usoLechuga++;
                validarHClasica++;
                cintaTransportadora.eliminar(ingrediente);
            }else{
                JOptionPane.showMessageDialog
        (null,"Este ingrediente es incorrecto o ya fue incluido");
            }
            
            if(validarHClasica==4){
                JOptionPane.showMessageDialog
        (null,"Orden Completada! ");
                puntos+=15;
                lblPuntos.setText(String.valueOf(puntos));
                usoPan=0;
                usoCarne=0;
                usoQueso=0;
                usoLechuga=0;
                validarHClasica=0;
                ordenPrincipal1.setText(ordenSiguiente.getText());
                ordenSiguiente.setText(ordenLejana.getText());
                ordenLejana.setText("Esperando orden...");
                imagenOrdenActiva();
            }else{    
            }
        }
    }

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
            nuevo.setPts(puntos);
            nuevo.setVisible(true);
            this.dispose();
            minutes = 5;
        }
         
    }
  
    private void actualizaLabel(){
    //String cronometro = + minutes + "m:" +seconds+ "s:";
    Tiempo.setText(minutes + "m:" +seconds+ "s:");
    ms.setText("."+ milliseconds);
    }
    
    private void imagenOrdenActiva(){
    
    if(ordenPrincipal1.getText().equals("HAMBURGUESA DE CARNE: Pan,Carne")){
            ImagenOrden(lblImagenOrden,"src/imagenes/HCarne.jpg");
        }else if(ordenPrincipal1.getText().equals("HAMBURGUESA DE QUESO: "
                + "Pan, Carne y Queso")){
            ImagenOrden(lblImagenOrden,"src/imagenes/HQueso.jpg");
        }else if(ordenPrincipal1.getText().equals("HAMBURGUESA CLASICA: "
                + "Pan, Carne, Queso y Lechuga")){
            ImagenOrden(lblImagenOrden,"src/imagenes/HClasica.jpg");
        }
        
    }
    
    
    private void ImagenOrden(JLabel lblImagenOrden, String root){
        
        ImageIcon image = new ImageIcon(root);
        Icon icono = new ImageIcon(image.getImage().getScaledInstance
        (lblImagenOrden.getWidth(), lblImagenOrden.getHeight(), Image.SCALE_DEFAULT));
        lblImagenOrden.setIcon(icono);
        this.repaint();
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
        BotonI2 = new javax.swing.JButton();
        jPanel1 = new FondoPartida();
        btnDos = new javax.swing.JButton();
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
        btnTres = new javax.swing.JButton();
        btnCuatro = new javax.swing.JButton();
        btnCinco = new javax.swing.JButton();
        btnUno = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        lblPuntos = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblImagenOrden = new javax.swing.JLabel();

        ordenPrincipal.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        ordenPrincipal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ordenPrincipal.setText("Esperando orden...");

        orden2.setText("Esperando orden...");

        jLabel2.setText("Orden mas Lejana");

        BotonI2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonI2ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("OverCooked Fide");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnDos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDosMouseClicked(evt);
            }
        });
        btnDos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDosActionPerformed(evt);
            }
        });
        jPanel1.add(btnDos, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 590, 110, 90));

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
        jPanel1.add(lbl3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, -1, -1));

        ordenPrincipal1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        ordenPrincipal1.setForeground(new java.awt.Color(255, 255, 255));
        ordenPrincipal1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ordenPrincipal1.setText("Esperando orden...");
        jPanel1.add(ordenPrincipal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 800, 20));

        lbl1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbl1.setForeground(new java.awt.Color(255, 255, 255));
        lbl1.setText("Siguiente Orden");
        jPanel1.add(lbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 110, 20));

        ordenSiguiente.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ordenSiguiente.setForeground(new java.awt.Color(255, 255, 255));
        ordenSiguiente.setText("Esperando orden...");
        jPanel1.add(ordenSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 610, -1));

        lbl2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbl2.setForeground(new java.awt.Color(255, 255, 255));
        lbl2.setText("Orden mas Lejana");
        jPanel1.add(lbl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        ordenLejana.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ordenLejana.setForeground(new java.awt.Color(255, 255, 255));
        ordenLejana.setText("Esperando orden...");
        jPanel1.add(ordenLejana, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 620, -1));

        Tiempo.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 24)); // NOI18N
        Tiempo.setForeground(new java.awt.Color(255, 255, 255));
        Tiempo.setText("0m: 0s");
        jPanel1.add(Tiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, -10, 100, 100));

        ms.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ms.setForeground(new java.awt.Color(255, 255, 255));
        ms.setText(".000");
        jPanel1.add(ms, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, -20, 90, 120));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Time:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 10, -1, -1));

        btnTres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTresMouseClicked(evt);
            }
        });
        btnTres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTresActionPerformed(evt);
            }
        });
        jPanel1.add(btnTres, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 590, 110, 90));

        btnCuatro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCuatroMouseClicked(evt);
            }
        });
        btnCuatro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCuatroActionPerformed(evt);
            }
        });
        jPanel1.add(btnCuatro, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 590, 110, 90));

        btnCinco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCincoMouseClicked(evt);
            }
        });
        btnCinco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCincoActionPerformed(evt);
            }
        });
        jPanel1.add(btnCinco, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 590, 110, 90));

        btnUno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUnoMouseClicked(evt);
            }
        });
        btnUno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnoActionPerformed(evt);
            }
        });
        jPanel1.add(btnUno, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 590, 110, 90));

        jButton1.setText("DERECHA");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 550, -1, -1));

        jButton2.setText("IZQUIERDA");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, -1, -1));

        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/borrar.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 590, 50, 90));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/borrar.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 590, 50, 90));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/borrar.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 590, 50, 90));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/borrar.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 590, 50, 90));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/borrar.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 590, 50, 90));

        lblPuntos.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblPuntos.setForeground(new java.awt.Color(255, 255, 255));
        lblPuntos.setText("0");
        jPanel1.add(lblPuntos, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 60, 80, 50));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Puntos:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 60, -1, 50));

        lblImagenOrden.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(lblImagenOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 150, 580, 420));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1190, 710));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDosActionPerformed

    private void BotonI2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonI2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotonI2ActionPerformed

    private void btnTresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTresActionPerformed

    private void btnCuatroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuatroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCuatroActionPerformed

    private void btnCincoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCincoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCincoActionPerformed

    private void btnUnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUnoActionPerformed

    private void btnUnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUnoMouseClicked
        // TODO add your handling code here:
        
        validarIngredientes(cintaTransportadora.obtenerIngrediente(0));
        cintaTransportadora.moverIzquierda();
        comprobarIngredienteDisponibles();
        colocarNombreEnBotones();
        
    }//GEN-LAST:event_btnUnoMouseClicked

    private void btnDosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDosMouseClicked
        // TODO add your handling code here:
        validarIngredientes(cintaTransportadora.obtenerIngrediente(1));
        cintaTransportadora.moverIzquierda();
        comprobarIngredienteDisponibles();
        colocarNombreEnBotones();
        
    }//GEN-LAST:event_btnDosMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        cintaTransportadora.moverIzquierda();
        colocarNombreEnBotones();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        cintaTransportadora.moverDerecha();
        colocarNombreEnBotones();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnTresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTresMouseClicked
        // TODO add your handling code here:
        validarIngredientes(cintaTransportadora.obtenerIngrediente(2));
        cintaTransportadora.moverIzquierda();
        comprobarIngredienteDisponibles();
        colocarNombreEnBotones();
        
    }//GEN-LAST:event_btnTresMouseClicked

    private void btnCuatroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCuatroMouseClicked
        // TODO add your handling code here:
        validarIngredientes(cintaTransportadora.obtenerIngrediente(3));
        cintaTransportadora.moverIzquierda();
        comprobarIngredienteDisponibles();
        colocarNombreEnBotones();
        
    }//GEN-LAST:event_btnCuatroMouseClicked

    private void btnCincoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCincoMouseClicked
        // TODO add your handling code here:
        validarIngredientes(cintaTransportadora.obtenerIngrediente(4));
        cintaTransportadora.moverIzquierda();
        comprobarIngredienteDisponibles();
        colocarNombreEnBotones();
        
    }//GEN-LAST:event_btnCincoMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        cintaTransportadora.eliminar(cintaTransportadora.obtenerIngrediente(0));
        cintaTransportadora.moverIzquierda();
        comprobarIngredienteDisponibles();
        colocarNombreEnBotones();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        cintaTransportadora.eliminar(cintaTransportadora.obtenerIngrediente(1));
        cintaTransportadora.moverIzquierda();
        comprobarIngredienteDisponibles();
        colocarNombreEnBotones();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        cintaTransportadora.eliminar(cintaTransportadora.obtenerIngrediente(2));
        cintaTransportadora.moverIzquierda();
        comprobarIngredienteDisponibles();
        colocarNombreEnBotones();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        cintaTransportadora.eliminar(cintaTransportadora.obtenerIngrediente(3));
        cintaTransportadora.moverIzquierda();
        comprobarIngredienteDisponibles();
        colocarNombreEnBotones();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        cintaTransportadora.eliminar(cintaTransportadora.obtenerIngrediente(4));
        cintaTransportadora.moverIzquierda();
        comprobarIngredienteDisponibles();
        colocarNombreEnBotones();
    }//GEN-LAST:event_jButton3ActionPerformed

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
    private javax.swing.JButton BotonI2;
    public javax.swing.JPanel PanelPedido;
    private javax.swing.JLabel Tiempo;
    private javax.swing.JButton btnCinco;
    private javax.swing.JButton btnCuatro;
    private javax.swing.JButton btnDos;
    private javax.swing.JButton btnTres;
    private javax.swing.JButton btnUno;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lblImagenOrden;
    private javax.swing.JLabel lblPuntos;
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
    
}
