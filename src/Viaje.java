import java.util.ArrayList;
import java.util.List;

public class Viaje {
    int id, horaSalida, puestosDisponibles;
    String origen, destino, conductorid;
    double precio;
    Usuario conductor;
    List<Usuario> pasageros;


    public Viaje(int id, String origen, String destino, int horaSalida, double precio, Usuario conductor) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.precio = precio;
        this.horaSalida = horaSalida;
        this.conductor=conductor;
        this.conductorid= conductor.getIdBanner();
        this.pasageros = new ArrayList<Usuario>();
    }

    public void agregarPasagero(Usuario pasajero) {
        pasageros.add(pasajero);
    }

    public void eliminarPasagero(Usuario pasajero) {
        pasageros.remove(pasajero);
    }

    public List<Usuario> getPasageros() {
        return pasageros;
    }
}
