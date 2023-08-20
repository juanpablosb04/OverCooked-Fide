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
    
    
    
    public Ingrediente obtenerIngrediente(int posicion){
        Ingrediente dato = new Ingrediente("", "");
        Nodo actual = inicio;
        int i = 0;
        if (listaVacia())
            return dato;
        if (posicion > 4 || posicion < 0)
            return dato;
        while(actual != null){
            if(i == posicion){
                return actual.getDato();
            }
            i++;
            actual = actual.getSiguiente();
        }
        return dato;
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