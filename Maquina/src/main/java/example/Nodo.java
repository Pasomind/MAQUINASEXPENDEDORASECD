package example;

public class Nodo {
    private String nombre;
    private Nodo siguiente;
    private Nodo anterior;

    public Nodo(String nombre, Nodo siguiente, Nodo anterior) {
        this.nombre = nombre;
        this.siguiente = siguiente;
        this.anterior = anterior;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public Nodo getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo anterior) {
        this.anterior = anterior;
    }
}
