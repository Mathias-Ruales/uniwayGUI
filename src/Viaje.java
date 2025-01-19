import java.util.ArrayList;
import java.util.List;

public class Viaje {
    int id, horaSalida, puestosDisponibles;
    String origen, destino, conductorid;
    double precio;
    Usuario conductor;
    List<Usuario> pasajeros;
    Vehiculo vehiculo;
    boolean viajeFinalizado;


    public Viaje(int id, String origen, String destino, int horaSalida, double precio, int puestosDisponibles, Usuario conductor) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.precio = precio;
        this.horaSalida = horaSalida;
        this.conductor=conductor;
        this.puestosDisponibles = puestosDisponibles;
        this.conductorid= conductor.getIdBanner();
        this.vehiculo=null;
        this.pasajeros = new ArrayList<Usuario>();
        this.viajeFinalizado = false;
    }

    public void agregarPasajero(Usuario pasajero) {
        pasajeros.add(pasajero);
    }

    public void eliminarPasagero(Usuario pasajero) {
        pasajeros.remove(pasajero);
    }

    public List<Usuario> getPasajeros() {
        return pasajeros;
    }
}
