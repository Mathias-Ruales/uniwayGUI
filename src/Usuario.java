import javax.swing.*;
import java.util.LinkedList;

public class Usuario {
    String nombre, idBanner, telefono;
    boolean esconductor;
    Vehiculo vehiculo;
    Viaje viajeEnCurso;
    Viaje manejando;

    public Usuario(String nombre, String idBanner, String telefono, JCheckBox jCheckBox){
        this.nombre = nombre;
        this.idBanner = idBanner;
        this.telefono = telefono;
        this.esconductor = jCheckBox.isSelected();
        this.vehiculo = null;
        this.viajeEnCurso = null;
        this.manejando = null;

    }

    public String getIdBanner() {
        return idBanner;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdBanner(String idBanner) {
        this.idBanner = idBanner;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public void setEsconductor(boolean esconductor) {
        this.esconductor = esconductor;
    }
    public void getEsconductor(boolean esconductor, JCheckBox checkBox) {
        checkBox.setSelected(esconductor);
        return;
    }
}
