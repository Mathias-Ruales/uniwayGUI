import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ListaViajes {
    public Nodo inicio;
    public int tamano;

    public ListaViajes() {
        inicio = null;
        tamano = 0;
    }
    boolean estavacia(){
        return inicio == null;
    }
    public boolean existeViaje(int id) {
        Nodo actual = inicio;
        while (actual != null) {
            if (actual.viaje.id == id) {
                return true;
            }
        actual=actual.siguiente;
        }
        return false;
    }

    public void agregarViaje(Viaje v, JTable jTable){
        if(estavacia()){
            inicio = new Nodo(v);
        } else {
            if(!existeViaje(v.id)){
                Nodo nuevo = new Nodo(v);
                nuevo.siguiente = inicio;
                inicio = nuevo;
            } else {
                JOptionPane.showMessageDialog(null, "Viaje ya existe");
                return;
            }
        }
        tamano++;
        actualizarTabla(jTable);
    }

    public void actualizarTabla(JTable jTable){
        DefaultTableModel modelo = (DefaultTableModel) jTable.getModel();
        modelo.setNumRows(0);
        Nodo actual = inicio;
        while (actual != null) {
            modelo.addRow(new Object[]{
                    actual.viaje.id,
                    actual.viaje.conductor.nombre,
                    actual.viaje.origen,
                    actual.viaje.destino,
                    actual.viaje.horaSalida,
                    actual.viaje.precio,
                    actual.viaje.puestosDisponibles
            });
            actual=actual.siguiente;
        }
    }

    public Viaje escogerViaje(int id) {
        Nodo actual = inicio;
        while (actual != null) {
            if (actual.viaje.id == id) {
                return actual.viaje;
            }
            actual=actual.siguiente;
        }
        return null;
    }

    public void ordenarPorHora(){
        if(inicio==null || inicio.siguiente==null){
            return;
        }

        boolean ordenados;
        do {
            ordenados = true;
            Nodo actual = inicio;
            while (actual.siguiente != null) {
                if(actual.viaje.horaSalida>actual.siguiente.viaje.horaSalida){
                    Viaje temp = actual.viaje;
                    actual.viaje=actual.siguiente.viaje;
                    actual.siguiente.viaje=temp;
                    ordenados = false;
                }
                actual=actual.siguiente;
            }
        } while (!ordenados);
    }

    public ListaViajes filtrarViajes(String origen, String destino, JTable jTable){
        ListaViajes listafiltrada = new ListaViajes();
        Nodo actual = inicio;

        while (actual != null) {
            if(origen.equals(actual.viaje.origen) && destino.equals(actual.viaje.destino)){
               listafiltrada.agregarViaje(actual.viaje, jTable);
            }
            actual=actual.siguiente;
        }
        return listafiltrada;
    }
    public ListaViajes filtrarViajesConductor(String idBanner, JTable jTable){
        ListaViajes listafiltrada = new ListaViajes();
        Nodo actual = inicio;

        while (actual != null) {
            if(idBanner.equals(actual.viaje.conductor.idBanner)){
                listafiltrada.agregarViaje(actual.viaje, jTable);
            }
            actual=actual.siguiente;
        }
        return listafiltrada;
    }

    public void eliminarViaje(Viaje viaje, JTable jTable) {
        if(estavacia()){
            return;
        }
        Nodo actual = inicio;
        Nodo anterior = null;
        while (actual != null) {
            if (actual.viaje == viaje) {
                if(anterior==null){
                    inicio = actual.siguiente;
                } else {
                    anterior.siguiente = actual.siguiente;
                }
                tamano--;
                break;
            } else {
                anterior = actual;
            }
            actual=actual.siguiente;
        }
        actualizarTabla(jTable);
    }

    public void eliminarViajes(String idConductor, JTable jTable) {
        if (estavacia()) {
            return;
        }
        Nodo actual = inicio;
        Nodo anterior = null;
        while (actual != null) {
            if (actual.viaje.conductor.idBanner.equals(idConductor)) {
                if (anterior == null) {
                    inicio = actual.siguiente;
                } else {
                    anterior.siguiente = actual.siguiente;
                }
                tamano--;
            } else {
                anterior = actual;
            }
            actual = actual.siguiente;
        }
        actualizarTabla(jTable);
    }


}
