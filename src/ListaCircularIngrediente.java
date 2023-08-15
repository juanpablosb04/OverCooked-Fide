public class ListaCircularIngrediente {
    
    private Nodo inicio;
    private Nodo fin;
    int contador;
    
    public ListaCircularIngrediente(){
        this.inicio = null;
        this.fin = null;
        this.contador = 0;
    }

    public Nodo getInicio() {
        return inicio;
    }

    public Nodo getFin() {
        return fin;
    }

    public int getContador() {
        return contador;
    }

    public boolean listaVacia(){
        return inicio == null;
    }
    
    public boolean agregar(Ingrediente dato){
        if (contador <= 5){
            Nodo nuevoNodo = new Nodo(dato);

            if (listaVacia()){ // PRIMER ELEMENTO
                inicio = nuevoNodo;
                fin = nuevoNodo;
                fin.setSiguiente(inicio);
                contador++;
            }
            else{
                fin.setSiguiente(nuevoNodo);
                contador++;
            }

            fin = nuevoNodo;
            fin.setSiguiente(inicio);
            return true;
        }
        return false;
    }
    
    public boolean buscar(Ingrediente dato){
        Nodo actual = inicio;
        if (listaVacia())
            return false;
        do{
            if(actual.getDato() == dato){
                return true;
            }
            actual = actual.getSiguiente();
        }while(actual != inicio);
        return false;
    }
    
    public boolean eliminar(Ingrediente dato){
        if (listaVacia())
            return false;
        
        if(inicio.getDato() == dato){
            if (inicio == fin){
                inicio = null;
                fin = null;
                contador--;
            }else{
                inicio = inicio.getSiguiente();
                fin.setSiguiente(inicio);
                contador--;
            }
            return true;
        }
        
        Nodo actual = inicio;
        while(actual.getSiguiente() != inicio){
            if (actual.getSiguiente().getDato() == dato){
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                contador--;
                return true;
            }
            actual = actual.getSiguiente();
        }
        
        return false;
    }
    
    public int cantidadElementos(){
        Nodo actual = inicio;
        int cantidad = 0;
        if(!listaVacia()){
            do{
                cantidad++;
                actual = actual.getSiguiente();
            }while(actual != inicio);
        }
        return cantidad;
    }
    
    public void moverDerecha(){
        if (!listaVacia()){
            Nodo actual = inicio;
            
            while(actual.getSiguiente() != fin){
                actual = actual.getSiguiente();
            }
            
            fin.setSiguiente(inicio);
            inicio = fin;
            fin = actual;
        }
    }
    
    public void moverIzquierda(){
        if (!listaVacia()){
            inicio = inicio.getSiguiente();
            fin = fin.getSiguiente();
        }
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        if(listaVacia())
            return "NO HAY ELEMENTOS EN LA LISTA\n";

        Nodo actual = inicio;
        do{
            sb.append(actual.toString()).append("\n");
            actual = actual.getSiguiente();
        }while(actual != inicio);
        
        return sb.toString();
    }
    
    public String mostrarLista(){
        StringBuilder sb = new StringBuilder();

        if (listaVacia())
            return "NO HAY ELEMENTOS EN LA LISTA\n";
        
        Nodo actual = inicio;
        do{
            sb.append(" -> ").append(actual.getDato().getNombre());
            actual = actual.getSiguiente();
        }while(actual != inicio);
        return sb.toString();
    }
    
    private class Nodo{
        private Nodo siguiente;
        private Ingrediente dato;

        public Nodo(Ingrediente dato) {
            this.siguiente = null;
            this.dato = dato;
        }

        public Nodo getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(Nodo siguiente) {
            this.siguiente = siguiente;
        }

        public Ingrediente getDato() {
            return dato;
        }

        public void setDato(Ingrediente dato) {
            this.dato = dato;
        }

        @Override
        public String toString() {
            return "\n" + dato.toString();
        }    
    }
}