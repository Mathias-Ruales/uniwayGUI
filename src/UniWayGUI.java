import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UniWayGUI {
    private JPanel pGeneral;
    private JTabbedPane tabs;
    private JTextField IngresoBanner;
    private JButton ingresarButton;
    private JButton registrarseButton;
    private JPanel tpIngreso;
    private JPanel tpRegistro;
    private JPanel pRegistro;
    private JPanel tpAnadirViaje;
    private JPanel pAnadirViaje;
    private JPanel tpEscogerViaje;
    private JPanel pEscogerViaje;
    private JPanel tpPerfil;
    private JPanel pPerfil;
    private JTextField RegistroBanner;
    private JTextField RegistroNombre;
    private JTextField RegistroTelefono;
    private JCheckBox quieroSerConductorCheckBox;
    private JComboBox cbOrigenAV;
    private JComboBox cbDestinoAV;
    private JTextField horaSalidaAV;
    private JTextField precioAV;
    private JButton añadirViajeButton;
    private JComboBox cbOrigenEV;
    private JComboBox cbDestinoEV;
    private JButton buscarViajeButton;
    private JTable viajesTable;
    private JButton crearCuentaButton;
    private JButton salirButton;
    private JTextField perfilNombre;
    private JTextField perfilTelf;
    private JButton editarPerfilButton;
    private JTextField perfilBanner;
    private JPanel pIngreso;
    private JButton guardarCambiosButton;
    private JCheckBox quieresSerConductorCheckBox;
    ListaUsuarios listaUsuarios=new ListaUsuarios();
    ListaViajes listaViajes=new ListaViajes();
    Usuario usuarioActual = null;
    public UniWayGUI() {
        quieresSerConductorCheckBox.setVisible(false);
        guardarCambiosButton.setVisible(false);
        tabs.remove(tpRegistro);
        tabs.remove(tpAnadirViaje);
        tabs.remove(tpEscogerViaje);
        tabs.remove(tpPerfil);

        JCheckBox tempCB =  new JCheckBox();
        tempCB.setSelected(true);
        Usuario admin = new Usuario("admin", "123", "0000", tempCB);
        listaUsuarios.agragarUsuario(admin);

        Viaje viajeDef = new Viaje(0, "Norte", "Sur", 11, 1.5, admin);
        listaViajes.agregarViaje(viajeDef, viajesTable);

        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String banner = IngresoBanner.getText();
                    if (listaUsuarios.buscaUsuario(banner) != null){
                        JOptionPane.showMessageDialog(null, "Ingreso exitoso");
                        usuarioActual=listaUsuarios.buscaUsuario(banner);


                        tabs.addTab("Perfil", tpPerfil);
                        if (listaUsuarios.buscaUsuario(banner).esconductor){
                            tabs.addTab("Añadir Viaje", tpAnadirViaje);
                        }
                        tabs.addTab("EscogerViaje", tpEscogerViaje);
                        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Id", "Conductor", "Origen", "Destino", "Hora de salida", "Precio"}, 0);
                        viajesTable.setModel(modelo);

                        tabs.remove(tpIngreso);
                        perfilBanner.setText(listaUsuarios.buscaUsuario(banner).idBanner);
                        perfilBanner.setEditable(false);
                        perfilNombre.setText(listaUsuarios.buscaUsuario(banner).nombre);
                        perfilNombre.setEditable(false);
                        perfilTelf.setText(listaUsuarios.buscaUsuario(banner).telefono);
                        perfilTelf.setEditable(false);
                        IngresoBanner.setText("");
                    }

                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Ingrese un ID valido");
                }


            }
        });
        buscarViajeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                    String origen = cbOrigenEV.getSelectedItem().toString();
                    String destino = cbDestinoEV.getSelectedItem().toString();
                    if(origen==destino){
                        JOptionPane.showMessageDialog(null, "El origen y el destino no pueden ser iguales.");
                    } else {
                        ListaViajes listaviajes = listaViajes.filtrarViajes(origen,destino, viajesTable);
                        listaviajes.ordenarPorHora();
                        listaviajes.actualizarTabla(viajesTable);
                    }

                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Invalido");
                }
            }
        });
        añadirViajeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String origen = cbOrigenAV.getSelectedItem().toString();
                    String destino = cbDestinoAV.getSelectedItem().toString();
                    if (origen != destino){
                        int horasalida = Integer.parseInt(horaSalidaAV.getText());
                        double precio = Double.parseDouble(precioAV.getText());
                        int id = listaViajes.tamano;
                        Usuario conductor = usuarioActual;
                        listaViajes.agregarViaje(new Viaje(id, origen, destino, horasalida, precio, conductor), viajesTable);

                        cbOrigenEV.setSelectedIndex(0);
                        cbDestinoEV.setSelectedIndex(0);
                        horaSalidaAV.setText("");
                        precioAV.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "El origen y el destino no pueden ser iguales");
                    }



                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "No se pudo ingresar el viaje");
                }
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabs.remove(tpPerfil);
                tabs.remove(tpAnadirViaje);
                tabs.remove(tpEscogerViaje);

                tabs.addTab("Ingreso", tpIngreso);


            }
        });
        registrarseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabs.remove(tpIngreso);
                tabs.add("Registro", tpRegistro);
            }
        });
        crearCuentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String idBanner = RegistroBanner.getText();
                    String nombre = RegistroNombre.getText();
                    String telefono = RegistroTelefono.getText();
                    Usuario usuario = new Usuario(nombre, idBanner, telefono, quieroSerConductorCheckBox);

                    listaUsuarios.agragarUsuario(usuario);
                    JOptionPane.showMessageDialog(null, "Usuario agregado exitosamente");

                    RegistroBanner.setText("");
                    RegistroNombre.setText("");
                    RegistroTelefono.setText("");
                    quieroSerConductorCheckBox.setSelected(false);

                    tabs.remove(tpRegistro);
                    tabs.addTab("Ingreso",tpIngreso);
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "No se pudo ingresar el Usuario");
                }
            }
        });
        guardarCambiosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCambiosButton.setVisible(false);
                String idbanner = usuarioActual.getIdBanner();


                try {
                    String nombre = perfilNombre.getText();
                    String telefono = perfilTelf.getText();
                    listaUsuarios.buscaUsuario(idbanner).setNombre(nombre);
                    listaUsuarios.buscaUsuario(idbanner).setTelefono(telefono);
                    listaUsuarios.buscaUsuario(idbanner).setEsconductor(quieresSerConductorCheckBox.isSelected());
                    JOptionPane.showMessageDialog(null, "Cambios guardados exitosamente");
                    if (!usuarioActual.esconductor){
                        listaViajes.eliminarViaje(usuarioActual.idBanner, viajesTable);
                        tabs.remove(tpAnadirViaje);
                    } else {
                        tabs.addTab("Anadir Viaje",tpAnadirViaje);
                    }
                    editarPerfilButton.setVisible(false);
                    perfilNombre.setEditable(false);
                    perfilTelf.setEditable(false);
                    quieroSerConductorCheckBox.setSelected(false);
                    quieresSerConductorCheckBox.setVisible(false);
                    editarPerfilButton.setVisible(true);
                    listaViajes.actualizarTabla(viajesTable);
                } catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Error al editar.");
                }

            }
        });
        editarPerfilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarPerfilButton.setVisible(false);
                quieresSerConductorCheckBox.setVisible(true);
                usuarioActual.getEsconductor(usuarioActual.esconductor, quieresSerConductorCheckBox);
                guardarCambiosButton.setVisible(true);
                perfilNombre.setEditable(true);
                perfilTelf.setEditable(true);
            }
        });
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("UniWayGUI");
        frame.setContentPane(new UniWayGUI().pGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
