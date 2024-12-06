public class Nodo {
    public Nodo siguiente;
    public Viaje viaje;
    public Usuario usuario;

    public Nodo(Viaje viaje) {
        this.viaje = viaje;
        this.siguiente = null;
    }

    public Nodo(Usuario usuario) {
        this.usuario = usuario;
        this.siguiente = null;
    }
}
