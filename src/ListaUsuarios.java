import javax.swing.*;
import java.util.LinkedList;

public class ListaUsuarios {
    public LinkedList<Usuario> listaUsuarios;
    public ListaUsuarios() {
        listaUsuarios = new LinkedList<>();
    }



    public void agragarUsuario(Usuario usuario) {
        if (!listaUsuarios.contains(usuario)) {
            listaUsuarios.add(usuario);
        } else {
            JOptionPane.showMessageDialog(null, "Usuario ya existe");
        }
    }
    public void eliminarUsuario(Usuario usuario) {
        listaUsuarios.remove(usuario);
    }
    public Usuario buscaUsuario(String idBanner) {
        if (listaUsuarios.isEmpty()) {
            return null;
        }
        for (Usuario usuario : listaUsuarios) {
            if (usuario.idBanner.equals(idBanner)) {
                return usuario;
            }
        }
        JOptionPane.showMessageDialog(null, "Usuario no encontrado");
        return null;
    }
}
