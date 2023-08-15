public class ListaIngrediente {
    private Nodo inicio;
    
    public ListaIngrediente(){
        this.inicio = null;
    }

    public Nodo getInicio() {
        return inicio;
    }
    
    public boolean listaVacia(){
        return inicio == null;
    }
    
    public boolean insertarFinal(Ingrediente dato){
        Nodo nuevoNodo = new Nodo(dato);
        
        if (listaVacia()){ // Primer elemento
            inicio = nuevoNodo;
            return true;
        }
        Nodo actual = inicio;
        while(actual.getSiguiente() != null){
            actual = actual.getSiguiente();
        }
        actual.setSiguiente(nuevoNodo);
        return true;
    }
    
    public boolean insertarInicio(Ingrediente dato){
        Nodo nuevoNodo = new Nodo(dato);
        nuevoNodo.setSiguiente(inicio);
        inicio = nuevoNodo;
        return true;
    }
    
    public boolean buscar(Ingrediente dato){
        Nodo actual = inicio;
        while(actual != null){
            if (actual.getDato() == dato){
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }
    
    public boolean buscarRecursivo(Nodo actual,Ingrediente dato){
        if (actual == null)
            return false;
        
        if (actual.getDato() == dato)
            return true;
        
        return buscarRecursivo(actual.getSiguiente(), dato);
    }
    
    public int cantidadElementos(){
        Nodo actual = inicio;
        int contador = 0;
        while(actual != null){
            contador++;
            actual = actual.getSiguiente();
        }
        return contador;
    }
    
    public int cantidadElementosRecursivo(Nodo actual, int contador){
        if (actual == null)
            return contador;
        
        return cantidadElementosRecursivo(actual.getSiguiente(), contador + 1);
    }
    
    public int contadorElementos(){
        return cantidadElementosRecursivo(inicio, 0);
    }
    
    public boolean eliminarElemento(Ingrediente dato){
        if (listaVacia())
            return false;
        
        if (inicio.getDato() == dato){
            inicio = inicio.getSiguiente();
            return true;
        }
    
        Nodo actual = inicio;
        while(actual.getSiguiente() != null){
            if (actual.getSiguiente().getDato() == dato){
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }
    
    public String toString(){
        if (listaVacia()){
            return "NO HAY ELEMENTOS EN LA LISTA\n";
        }
        StringBuilder sb = new StringBuilder();
        Nodo actual = inicio;
        while(actual != null){
            sb.append(actual.toString());
            actual = actual.getSiguiente();
        }
        return sb.toString();
    }
    
    public String mostrarLista(){
        if (listaVacia()){
            return "NO HAY ELEMENTOS EN LA LISTA\n";
        }
        StringBuilder sb = new StringBuilder();
        Nodo actual = inicio;
        while(actual != null){
            sb.append(actual.getDato().getNombre()).append("\t");
            actual = actual.getSiguiente();
        }
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