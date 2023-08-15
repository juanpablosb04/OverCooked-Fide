public class Orden {
    String nombre;
    int puntaje;
    ListaIngrediente lista;
    
    public Orden(String nombre, int puntaje) {
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.lista = new ListaIngrediente();
    }
    
    public Orden(String nombre, int puntaje, ListaIngrediente lista) {
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.lista = lista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public ListaIngrediente getLista() {
        return lista;
    }

    public void setLista(ListaIngrediente lista) {
        this.lista = lista;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Orden:\t").append(nombre).append("\tPuntaje: ").append(puntaje).append("\n");
        sb.append("Ingredientes:\t");
        sb.append(lista.listaVacia() ? "Orden sin ingredientes" : lista.mostrarLista()).append("\n");
        return sb.toString();
    } 
}