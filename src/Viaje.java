public class Viaje {
    int id, horaSalida, puestosDisponibles;
    String origen, destino, conductorid;
    double precio;
    Usuario conductor;

    public Viaje(int id, String origen, String destino, int horaSalida, double precio, Usuario conductor) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.precio = precio;
        this.horaSalida = horaSalida;
        this.conductor=conductor;
        this.conductorid= conductor.getIdBanner();
    }

}
