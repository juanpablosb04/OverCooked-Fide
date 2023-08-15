public class Ingrediente {
    String id;
    String nombre;

    public Ingrediente(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Ingrediente: " + id + "\t" + nombre;
    }
}