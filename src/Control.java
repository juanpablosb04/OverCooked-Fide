import java.util.Scanner;

public class Control {
    
    Ingrediente ingrediente0;
    Ingrediente ingrediente1;
    Ingrediente ingrediente2;
    Ingrediente ingrediente3;
    
    ListaIngrediente listaIngrediente; 
    ListaOrden listaOrden;
    
    ListaCircularIngrediente cintaTransportadora;
    
    public Control(){
        
        // CONSTRUCCION DE OBJETOS DEL JUEGO
        //img ---> ingrediente0.png
        // INSTANCIA DE INGREDIENTES
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

        // INSTANCIA DE ORDENES
        // LISTA DE INGREDIENTES DE LA ORDEN
        // HAMBURGUESA DE CARNE
        ListaIngrediente lista = new ListaIngrediente();
        lista.insertarFinal(ingrediente0);
        lista.insertarFinal(ingrediente1);
        Orden orden0 = new Orden("Hamburguesa de carne", 5, lista);
        
        // HAMBURGUESA CON QUESO
        lista = new ListaIngrediente();
        lista.insertarFinal(ingrediente0);
        lista.insertarFinal(ingrediente1);
        lista.insertarFinal(ingrediente2);
        Orden orden1 = new Orden("Hamburguesa con queso", 20, lista);
        
        // HAMBURGUESA CLASICA 
        lista = new ListaIngrediente();
        lista.insertarFinal(ingrediente0);
        lista.insertarFinal(ingrediente1);
        lista.insertarFinal(ingrediente2);
        lista.insertarFinal(ingrediente3);
        Orden orden2 = new Orden("Hamburguesa clasica", 15, lista);

        // LISTA DE ORDENES
        listaOrden = new ListaOrden();
        listaOrden.insertarFinal(orden0);
        listaOrden.insertarFinal(orden1);
        listaOrden.insertarFinal(orden2);

        cintaTransportadora = new ListaCircularIngrediente();
        
        // CARGAR DATOS ALEATORIOS EN CINTA TRANSPORTADORA
        for(int i = 0; i < 5; i++){
            cintaTransportadora.agregar(listaIngrediente.obtenerIngrediente(randomNumber(4, 0)));
        }
    }
    
    private int randomNumber(int max, int min){
        return (int) ((Math.random() * (max - min)) + min);
    }

    public char menu(){
        char opcion;
        
        System.out.println("JUGAR DE NUEVO");
        System.out.println("[1] JUGAR");
        System.out.println("[0] SALIR");
        System.out.print("OPCION: ");
        
        Scanner myObj = new Scanner(System.in);  
        String input = myObj.nextLine();  
        opcion = input.charAt(0);
        System.out.println("");

        return opcion;
    }
    
    private ListaIngrediente copiarLista(ListaIngrediente lista){
        ListaIngrediente copia = new ListaIngrediente();
        for (int i = 0; i < lista.cantidadElementos(); i++){
            copia.insertarFinal(lista.obtenerIngrediente(i));
        }
        return copia;    
    }
    
    private boolean validarOrden(ListaIngrediente ingredientesJuego, Orden ordenJuego){
        // SI LAS LISTAS NO TIENEN EL MISMO TAMAÑO LA ORDEN ESTA MAL
        // LA CANTIDAD DE INGREDIENTES NO COINCIDE 
        ListaIngrediente lista = copiarLista(ordenJuego.getLista());
        if (ingredientesJuego.cantidadElementos() != lista.cantidadElementos())
            return false;
        // CONFIRMAR QUE LOS INGREDIENTES DE LA ORDEN COINCIDEN
        int cantidad = 0;
        for (int i = 0; i < ingredientesJuego.cantidadElementos(); i++){
            if(lista.buscar(ingredientesJuego.obtenerIngrediente(i))){
                lista.eliminarElemento(ingredientesJuego.obtenerIngrediente(i));
                cantidad++;
            }
        }
        // VERIFICAR QUE LOS INGREDIENTES COINCIDEN CON LA ORDEN
        return cantidad == ordenJuego.getLista().cantidadElementos();
    }
    
    public void jugar(){

        // TO DO...
        // 1. Array de 4 ingredientes [OK]
        // 2. Obtener copias random de ingredientes en el array [OK]
        // 3. Cargar ingredientes random en la lista circular (cinta transportadora) NO > 5 [OK]
        // 4. Obtener y eliminar ingrediente de la lista (por posicion(?) [OK]
        // 4.1 Desplazar elementos a la IZQUIERDA! [OK]

        // 5. Si hay 3 elementos en lista volver al paso 2. [OK]
        // 6. Construir orden [0]
        // 7. Validar orden (Pedido completo) [OK]
        // 7.1 Cargar nueva orden [OK]
        // 8 Sumar puntos [OK]
        // 9 Detener el cronometro [OK] 

        char opcion = 'a';

        Orden ordenJuego;
        ListaOrden listaOrdenJuego = new ListaOrden();
        ListaOrden listaOrdenCompletada = new ListaOrden();
        
        ListaIngrediente listaJuego = new ListaIngrediente();
        Ingrediente ingredienteJuego;
        
        int puntosJuego = 0;
        int tiempo = 20;
        
        do{ // ESTE CICLO SIMULA EL JUEGO
            
            // OBTENER ORDEN ALEATORIA 
            // DEBEN SER 3 ORDENES ALEATORIAS
            // CARGADAS EN UNA LISTA DE ORDENES! 
            
            for (int i = 0; i < 3; i++){
                listaOrdenJuego.insertarFinal(listaOrden.obtenerOrden(randomNumber(3, 0)));
            }
            
            ordenJuego = listaOrdenJuego.obtenerOrden(0);
            
            do{ // ESTE CICLO SIMULA EL CRONOMETRO!  
                
                System.out.println("ORDENES EN JUEGO: ");
                System.out.println(listaOrdenJuego.toString());
                
                // PUNTAJE
                System.out.println("PUNTOS " + puntosJuego);
                
                // ORDEN EN JUEGO
                System.out.println("ORDEN EN JUEGO " + ordenJuego);

                // MOSTRAR ORDEN EN CONSTRUCCION
                System.out.println(listaJuego.mostrarLista());
                
                // INGREDIENTES EN CINTA TRANSPORTADORA
                System.out.println(cintaTransportadora.mostrarLista());

                // CONSTRUIR ORDEN
                // SELECCIONAR INGREDIENTE
                int posicion = 0;
                String eliminar = "";
                System.out.println("ELIMINAR INGREDIENTE ?\n[1] SI [0] NO");
                System.out.print("Opcion: ");
                Scanner myObj = new Scanner(System.in);  
                eliminar = myObj.nextLine();  
                
                System.out.println("SELECCIONAR INGREDIENTE");
                System.out.println("POSICION DEL INGREDIENTE: ");
                myObj = new Scanner(System.in);  
                String input = myObj.nextLine();  
                posicion = Integer.parseInt(input);
                System.out.println("");
                // OBTENER INGREDIENTE DE LA LISTA
                ingredienteJuego = cintaTransportadora.obtenerIngrediente(posicion);
                
                // BASURERO PARA ELIMINAR INGREDIENTE
                if(eliminar.equals("1")){
                    // ELIMINAR EL INGREDIENTE DE LA CINTA TRANSPORTADORA
                    cintaTransportadora.eliminar(ingredienteJuego);

                    // MOVER LA CINTA TRANSPORTADORA A LA IZQUIERDA
                    cintaTransportadora.moverIzquierda();
                }else{
                     
                    // AGREGAR INGREDIENTE A LA LISTA DE LA ORDEN
                    listaJuego.insertarFinal(ingredienteJuego);

                    // ELIMINAR EL INGREDIENTE DE LA CINTA TRANSPORTADORA
                    cintaTransportadora.eliminar(ingredienteJuego);

                    // MOVER LA CINTA TRANSPORTADORA A LA IZQUIERDA
                    cintaTransportadora.moverIzquierda();
                }

               
                
                // SI EN LA LISTA HAY 3 INGREDIENTES
                // CARGAR NUEVOS INGREDIENTES
                if(cintaTransportadora.cantidadElementos() <= 3){
                    cintaTransportadora.agregar(listaIngrediente.obtenerIngrediente(randomNumber(0, 4)));
                    cintaTransportadora.agregar(listaIngrediente.obtenerIngrediente(randomNumber(0, 4)));
                }
                
                if(listaJuego.cantidadElementos() >= ordenJuego.getLista().cantidadElementos()){
                    if (validarOrden(listaJuego, ordenJuego)){
                        System.out.println("ORDEN COMPLETADA!");
                        puntosJuego += ordenJuego.getPuntaje();
                        // ORDEN COMPLETADA, SE PASA A LA SIGUIENTE
                        listaOrdenCompletada.insertarFinal(ordenJuego);
                        // ELIMINAR ORDEN DE LAS ORDENES EN JUEGO
                        listaOrdenJuego.eliminarElemento(ordenJuego); 
                        // ACTUALIZAR ORDEN EN JUEGO
                        ordenJuego = listaOrdenJuego.obtenerOrden(0);
                        // LIMPIAR LISTA DE INGREDIENTES DE ORDEN EN JUEGO
                        listaJuego = new ListaIngrediente();

                    }else{
                        // LIMPIAR LISTA DE INGREDIENTES DE ORDEN EN JUEGO
                        System.out.println("ERROR EN LA ORDEN\n INTENTA DE NUEVO");
                        listaJuego = new ListaIngrediente();
                    }
                }
                // AGREGAR ORDEN NUEVA
                if (listaOrdenJuego.cantidadElementos() < 3){
                    listaOrdenJuego.insertarFinal(listaOrden.obtenerOrden(randomNumber(3, 0)));
                }
                
                tiempo--;
            }while(tiempo > 0);
            
            System.out.println("FIN DEL TIEMPO");
            System.out.println("PUNTAJE: " + puntosJuego);
            System.out.println("ORDENES COMPLETADAS: ");
            System.out.println(listaOrdenCompletada.toString());
            
           opcion = menu();
        }while(opcion != '0');
    }   
}