import java.util.LinkedList;

public class Vehiculo {
    public String marca;
    public String modelo;
    public String placa;
    public String color;
    public int puestos;
    public Usuario conductor;



    public Vehiculo(String marca, String modelo, String placa, String color, int puestos) {
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.color = color;
        this.puestos = puestos;
        this.conductor = null;
    }



}
