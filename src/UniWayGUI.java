import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

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
    private JPanel tpBuscarViaje;
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
    private JButton volverButton;
    private JButton escogerViajeButton1;
    private JComboBox cbEscogerViaje;
    private JButton atrasButton;
    private JTable tusViajesTable;
    private JButton añadirViajeButton1;
    private JButton eliminarViajeButton;
    private JPanel tpViajesConductor;
    private JButton eliminarButton;
    private JComboBox cbEliminarViaje;
    private JButton volverButton1;
    private JPanel tpViaje;
    private JPanel tpEliminarViaje;
    private JButton volverButton2;
    private JTable eliminarViajeTable;
    private JButton registrarVehiculoButton;
    private JPanel tpRegistrarVehiculo;
    private JTextField txtPlaca;
    private JTextField txtPuestos;
    private JButton registrarButton;
    private JButton volverButton3;
    private JComboBox cbMarca;
    private JComboBox cbModelo;
    private JComboBox cbColor;
    private JTextField txtOrigenViaje;
    private JTextField txtDestinoViaje;
    private JTextField txtHoraSalidaViaje;
    private JTextField txtCarroViaje;
    private JButton cancelarViajeButton;
    private JButton registrarVehiculoButton1;
    private JTextField txtPrecioViaje;
    private JButton comenzarViajeButton;
    private JButton empezarViajeButton;
    private JPanel tpViajeEnCurso;
    private JPanel tpCalificar;
    private JTextField txtOrigenVEC;
    private JTextField txtDestinoVEC;
    private JTextField txtPasajerosVEC;
    private JButton finalizarViajeButton;
    private JComboBox comboBox1;
    private JButton calificarButton;
    private JScrollPane eliminarTable;
    private JPanel tpElminiarViaje;
    ArrayList<String> universidades = new ArrayList<>(Arrays.asList("UDLA Park", "UDLA Granados", "UDLA Colon"));
    ArrayList<String> sectores = new ArrayList<>(Arrays.asList("Norte", "Sur", "Valles"));
    ArrayList<String> marcas = new ArrayList<>(Arrays.asList("Kia", "Chevrolet"));
    ArrayList<String> modelosKia = new ArrayList<>(Arrays.asList("Picanto", "Soul", "Sportage"));
    ArrayList<String> modelosCh = new ArrayList<>(Arrays.asList("Spark", "Aveo", "Sail"));
    ArrayList<String> colores = new ArrayList<>(Arrays.asList("Blanco", "Negro", "Gris"));






    public LinkedList<String> modelos = new LinkedList<>();
    ListaUsuarios listaUsuarios=new ListaUsuarios();
    ListaViajes listaViajes=new ListaViajes();
    Usuario usuarioActual = null;
    Vehiculo vehiculoActual = null;
    public UniWayGUI() {
        quieresSerConductorCheckBox.setVisible(false);
        guardarCambiosButton.setVisible(false);
        registrarVehiculoButton1.setVisible(false);


        tabs.remove(tpRegistro);
        tabs.remove(tpAnadirViaje);
        tabs.remove(tpBuscarViaje);
        tabs.remove(tpEscogerViaje);
        tabs.remove(tpPerfil);
        tabs.remove(tpEliminarViaje);
        tabs.remove(tpViajesConductor);
        tabs.remove(tpViaje);
        tabs.remove(tpRegistrarVehiculo);
        tabs.remove(tpViajeEnCurso);
        tabs.remove(tpCalificar);

        JCheckBox tempCB =  new JCheckBox();
        tempCB.setSelected(true);
        Usuario admin = new Usuario("admin", "123", "0000", tempCB);
        Vehiculo carroadmin = new Vehiculo("Kia", "Picanto", "PBL-4013", "Negro", 4);
        admin.vehiculo = carroadmin;
        listaUsuarios.agragarUsuario(admin);

        Viaje viajeDef = new Viaje(0, "Norte", "UDLA Park", 11, 1.5, 4, admin);
        viajeDef.vehiculo = carroadmin;
        listaViajes.agregarViaje(viajeDef, viajesTable);
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Id", "Conductor", "Origen", "Destino", "Hora de salida", "Precio", "Puestos"}, 0);


        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String banner = IngresoBanner.getText();
                    if (listaUsuarios.buscaUsuario(banner) != null){
                        JOptionPane.showMessageDialog(null, "Ingreso exitoso");
                        tabs.remove(tpIngreso);
                        usuarioActual=listaUsuarios.buscaUsuario(banner);

                        if (usuarioActual.viajeEnCurso != null){
                            if (!usuarioActual.viajeEnCurso.viajeFinalizado){
                                tabs.addTab("Viaje", tpViaje);
                            } else {
                                tabs.addTab("Calificar", tpCalificar);
                                usuarioActual.viajeEnCurso = null;
                            }
                            tabs.addTab("Perfil", tpPerfil);
                        } else {

                            viajesTable.setModel(modelo);

                            if (listaUsuarios.buscaUsuario(banner).esconductor && usuarioActual.manejando != null) {
                                tabs.addTab("Viaje en Curso", tpViajeEnCurso);
                                tabs.addTab("Perfil", tpPerfil);
                            } else {
                                if (listaUsuarios.buscaUsuario(banner).esconductor) {
                                    tabs.addTab("Viajes", tpViajesConductor);
                                    tusViajesTable.setModel(modelo);
                                    eliminarViajeTable.setModel(modelo);
                                    ListaViajes viajesconductor = listaViajes.filtrarViajesConductor(usuarioActual.idBanner, tusViajesTable);
                                    viajesconductor.actualizarTabla(eliminarViajeTable);
                                    viajesconductor.actualizarTabla(tusViajesTable);
                                }
                                tabs.addTab("Buscar Viaje", tpBuscarViaje);
                                cbOrigenEV.removeAllItems();
                                for (String universidad : universidades){
                                    cbOrigenEV.addItem(universidad);
                                }
                                for (String sector : sectores){
                                    cbOrigenEV.addItem(sector);
                                }

                                tabs.addTab("Perfil", tpPerfil);

                            }

                        }
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
                    if(origen.equals(destino)){
                        JOptionPane.showMessageDialog(null, "El origen y el destino no pueden ser iguales.");
                    } else {

                        ListaViajes viajesdisponibles = listaViajes.filtrarViajes(origen,destino, viajesTable);
                        if (viajesdisponibles != null && !viajesdisponibles.estavacia()){
                            tabs.remove(tpBuscarViaje);
                            tabs.remove(tpAnadirViaje);
                            tabs.remove(tpPerfil);
                            if (usuarioActual.esconductor){
                                tabs.remove(tpViajesConductor);
                            }
                            tabs.addTab("Escoger Viaje", tpEscogerViaje);
                            if (usuarioActual.esconductor){
                                tabs.addTab("Viajes", tpViajesConductor);


                            }
                            tabs.addTab("Perfil", tpPerfil);
                            viajesdisponibles.ordenarPorHora();
                            Nodo actual = viajesdisponibles.inicio;
                            cbEscogerViaje.removeAllItems();
                            while (actual != null){
                                cbEscogerViaje.addItem(actual.viaje.id);
                                actual=actual.siguiente;
                            }
                            cbEscogerViaje.setEnabled(true);
                            viajesdisponibles.actualizarTabla(viajesTable);
                        } else {
                            cbEscogerViaje.setEnabled(false);
                            JOptionPane.showMessageDialog(null, "No hay viajes disponibles");
                        }
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
                    int puestos = vehiculoActual.puestos;
                    if (origen != destino){
                        int horasalida = Integer.parseInt(horaSalidaAV.getText());
                        double precio = Double.parseDouble(precioAV.getText());
                        int id = listaViajes.tamano;
                        Usuario conductor = usuarioActual;
                        listaViajes.agregarViaje(new Viaje(id, origen, destino, horasalida, precio, puestos, conductor), viajesTable);

                        JOptionPane.showMessageDialog(null, "Viajes agregada con exito");



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
                if(usuarioActual.esconductor){
                    tabs.remove(tpViajesConductor);
                }

                atrasButton.setEnabled(true);

                tabs.remove(tpPerfil);
                tabs.remove(tpAnadirViaje);
                tabs.remove(tpEscogerViaje);
                tabs.remove(tpBuscarViaje);
                tabs.remove(tpViaje);
                tabs.remove(tpViajeEnCurso);
                tabs.remove(tpCalificar);
                tabs.addTab("Ingreso", tpIngreso);




            }
        });
        registrarseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabs.remove(tpIngreso);
                tabs.add("Registro", tpRegistro);
                registrarVehiculoButton.setVisible(false);
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
                    if (quieresSerConductorCheckBox.isSelected()){
                        usuario.vehiculo=vehiculoActual;
                    }
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
                        listaViajes.eliminarViajes(usuarioActual.idBanner, viajesTable);
                        tabs.remove(tpViajesConductor);
                    } else {
                        tabs.remove(tpPerfil);
                        tabs.remove(tpBuscarViaje);
                        tabs.addTab("Viajes",tpViajesConductor);
                        tabs.addTab("Buscar Viaje",tpBuscarViaje);
                        tabs.addTab("Perfil",tpPerfil);
                        tabs.setSelectedComponent(tpPerfil);
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
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabs.remove(tpRegistro);
                tabs.add("Ingreso", tpIngreso);
            }
        });
        escogerViajeButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = (Integer)cbEscogerViaje.getSelectedItem();
                Viaje viajeEscogido = listaViajes.escogerViaje(id);
                if(viajeEscogido.puestosDisponibles > 0){
                    viajeEscogido.agregarPasajero(usuarioActual);
                    viajeEscogido.puestosDisponibles--;

                    usuarioActual.viajeEnCurso = viajeEscogido;

                    listaViajes.actualizarTabla(viajesTable);
                }
                atrasButton.setEnabled(false);
                tabs.remove(tpEscogerViaje);
                tabs.remove(tpPerfil);

                tabs.addTab("Viaje", tpViaje);
                tabs.addTab("Perfil", tpPerfil);

                tabs.setSelectedComponent(tpViaje);

                txtOrigenViaje.setText(viajeEscogido.origen);
                txtOrigenViaje.setEditable(false);
                txtDestinoViaje.setText(viajeEscogido.destino);
                txtDestinoViaje.setEditable(false);
                txtCarroViaje.setText(viajeEscogido.conductor.vehiculo.marca + " " + viajeEscogido.conductor.vehiculo.modelo + " " + viajeEscogido.conductor.vehiculo.color);
                txtCarroViaje.setEditable(false);
                txtPrecioViaje.setText(String.valueOf(viajeEscogido.precio));
                txtPrecioViaje.setEditable(false);
                txtHoraSalidaViaje.setText(String.valueOf(viajeEscogido.horaSalida));
                txtHoraSalidaViaje.setEditable(false);

                JOptionPane.showMessageDialog(null,"Se ha escogido el viaje con exito");
            }
        });


        cbOrigenEV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String origen = String.valueOf(cbOrigenEV.getSelectedItem());
                if (!universidades.contains(origen)) {
                    cbDestinoEV.removeAllItems();
                    for (String universidades : universidades) {
                        cbDestinoEV.addItem(universidades);
                    }
                }else{
                    cbDestinoEV.removeAllItems();
                    for (String universidades : universidades) {
                        cbDestinoEV.addItem(universidades);
                    }
                    for (String sector : sectores) {
                        cbDestinoEV.addItem(sector);
                    }
                }

            }
        });
        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(usuarioActual.esconductor){
                    tabs.remove(tpViajesConductor);
                }
                tabs.remove(tpEscogerViaje);
                tabs.remove(tpPerfil);
                tabs.addTab("Buscar Viaje",tpBuscarViaje);
                tabs.addTab("Perfil", tpPerfil);
            }
        });
        añadirViajeButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabs.remove(tpViajesConductor);
                tabs.remove(tpBuscarViaje);
                tabs.remove(tpPerfil);
                tabs.addTab("Añadir Viaje",tpAnadirViaje);
                tabs.addTab("Buscar Viaje",tpBuscarViaje);
                tabs.addTab("Perfil", tpPerfil);
            }
        });
        volverButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabs.remove(tpAnadirViaje);
                tabs.remove(tpBuscarViaje);
                tabs.remove(tpPerfil);
                tabs.addTab("Viajes", tpViajesConductor);
                tabs.addTab("Buscar Viaje",tpBuscarViaje);
                tabs.addTab("Perfil", tpPerfil);
            }
        });
        eliminarViajeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comenzarViajeButton.setVisible(false);
                eliminarButton.setVisible(true);
                ListaViajes viajesconductor = listaViajes.filtrarViajesConductor(usuarioActual.idBanner, eliminarViajeTable);
                tabs.remove(tpViajesConductor);
                tabs.remove(tpBuscarViaje);
                tabs.remove(tpPerfil);
                tabs.addTab("Eliminar Viaje", tpEliminarViaje);
                tabs.addTab("Buscar Viaje",tpBuscarViaje);
                tabs.addTab("Perfil", tpPerfil);
                Nodo actual = viajesconductor.inicio;
                cbEliminarViaje.removeAllItems();
                while (actual != null){
                    cbEliminarViaje.addItem(actual.viaje.id);
                    actual=actual.siguiente;
                }

            }
        });
        volverButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarButton.setVisible(true);
                comenzarViajeButton.setVisible(true);
                tabs.remove(tpEliminarViaje);
                tabs.remove(tpBuscarViaje);
                tabs.remove(tpPerfil);
                tabs.addTab("Viajes", tpViajesConductor);
                tabs.addTab("Buscar Viaje",tpBuscarViaje);
                tabs.addTab("Perfil", tpPerfil);
            }
        });


        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    ListaViajes viajesconductor = listaViajes.filtrarViajesConductor(usuarioActual.idBanner, eliminarViajeTable);

                    int id = (Integer)cbEliminarViaje.getSelectedItem();
                    Viaje viajeeliminar = listaViajes.escogerViaje(id);

                    viajesconductor.eliminarViaje(viajeeliminar, eliminarViajeTable);
                    listaViajes.eliminarViaje(viajeeliminar, eliminarViajeTable);

                    viajesconductor.actualizarTabla(eliminarViajeTable);
                    viajesconductor.actualizarTabla(tusViajesTable);
                    cbEliminarViaje.removeItem(cbEliminarViaje.getSelectedItem());
                    JOptionPane.showMessageDialog(null,"Se ha eliminado el viaje con exito.");


            }
        });
        quieroSerConductorCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (quieroSerConductorCheckBox.isSelected()){
                    registrarVehiculoButton.setVisible(true);
                    registrarVehiculoButton.setEnabled(true);
                    crearCuentaButton.setEnabled(false);
                } else {
                    registrarVehiculoButton.setVisible(false);
                    crearCuentaButton.setEnabled(true);
                }
            }
        });
        quieresSerConductorCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        registrarVehiculoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabs.remove(tpRegistro);
                tabs.addTab("Registrar Vehiculo",tpRegistrarVehiculo);
                cbMarca.removeAllItems();
                for (String marcas: marcas){
                    cbMarca.addItem(marcas);
                }

            }
        });

        cbMarca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cbModelo.removeAllItems();
                String marca = cbMarca.getSelectedItem().toString();
                if (marca == "Kia"){
                    for (String modelo: modelosKia){
                        cbModelo.addItem(modelo);
                    }
                } else {
                    for (String modelo: modelosCh){
                        cbModelo.addItem(modelo);
                    }
                }

            }
        });
        cbModelo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cbColor.removeAllItems();
                for (String colores: colores){
                    cbColor.addItem(colores);
                }
            }
        });
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String marca = cbMarca.getSelectedItem().toString();
                    String modelo = cbModelo.getSelectedItem().toString();
                    String placa = txtPlaca.getText();
                    String color = cbColor.getSelectedItem().toString();
                    int puestos = Integer.parseInt(txtPuestos.getText());

                    vehiculoActual = new Vehiculo(marca, modelo, placa, color, puestos);

                    tabs.remove(tpRegistrarVehiculo);

                    if (usuarioActual != null){
                        usuarioActual.vehiculo = vehiculoActual;
                        tabs.addTab("Viajes", tpViajesConductor);
                        tabs.addTab("Buscar Viaje",tpBuscarViaje);
                        tabs.addTab("Perfil", tpPerfil);
                    } else {
                        tabs.addTab("Registro",tpRegistro);
                        registrarVehiculoButton.setEnabled(false);
                        crearCuentaButton.setEnabled(true);
                    }
                    JOptionPane.showMessageDialog(null, "Registro exitoso");



                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null,"No se puede registrar vehiculo");
                }
            }
        });
        volverButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabs.remove(tpRegistrarVehiculo);
                if (usuarioActual == null) {
                    tabs.addTab("Registro", tpRegistro);
                } else {
                    if (usuarioActual.esconductor){
                        tabs.addTab("Tus Viajes", tpViajesConductor);
                    }
                    tabs.addTab("Escoger Viaje", tpEscogerViaje);
                    tabs.addTab("Perfil", tpPerfil);
                    tabs.setSelectedComponent(tpPerfil);
                }
            }
        });

        quieresSerConductorCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quieresSerConductorCheckBox.isSelected() && usuarioActual.vehiculo == null){
                    registrarVehiculoButton1.setVisible(true);
                    registrarVehiculoButton1.setEnabled(true);
                    guardarCambiosButton.setEnabled(false);
                } else {
                    registrarVehiculoButton1.setVisible(false);
                    registrarVehiculoButton1.setEnabled(false);
                    guardarCambiosButton.setEnabled(true);
                }
            }
        });
        registrarVehiculoButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabs.remove(tpPerfil);
                tabs.remove(tpBuscarViaje);
                if (usuarioActual.esconductor){
                    tabs.remove(tpViajesConductor);
                }
                tabs.addTab("Registrar Vehiculo",tpRegistrarVehiculo);
                cbMarca.removeAllItems();
                for (String marcas: marcas){
                    cbMarca.addItem(marcas);
                }
            }
        });
        empezarViajeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarButton.setVisible(false);
                comenzarViajeButton.setVisible(true);
                ListaViajes viajesconductor = listaViajes.filtrarViajesConductor(usuarioActual.idBanner, eliminarViajeTable);
                tabs.remove(tpViajesConductor);
                tabs.remove(tpBuscarViaje);
                tabs.remove(tpPerfil);
                tabs.addTab("Comenzar Viaje", tpEliminarViaje);
                tabs.addTab("Buscar Viaje",tpBuscarViaje);
                tabs.addTab("Perfil", tpPerfil);
                Nodo actual = viajesconductor.inicio;
                cbEliminarViaje.removeAllItems();
                while (actual != null){
                    cbEliminarViaje.addItem(actual.viaje.id);
                    actual=actual.siguiente;
                }
            }
        });
        comenzarViajeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabs.remove(tpEliminarViaje);
                tabs.remove(tpBuscarViaje);
                tabs.remove(tpPerfil);

                int id = (Integer)cbEliminarViaje.getSelectedItem();
                Viaje viajeEscogido = listaViajes.escogerViaje(id);
                usuarioActual.manejando = viajeEscogido;

                int puestos = viajeEscogido.conductor.vehiculo.puestos - viajeEscogido.puestosDisponibles;

                txtOrigenVEC.setText(viajeEscogido.origen);
                txtOrigenVEC.setEditable(false);
                txtDestinoVEC.setText(viajeEscogido.destino);
                txtDestinoVEC.setEditable(false);
                txtPasajerosVEC.setText(String.valueOf(puestos));
                txtPasajerosVEC.setEditable(false);

                tabs.addTab("Viaje en Curso", tpViajeEnCurso);
                tabs.addTab("Perfil", tpPerfil);

            }
        });
        finalizarViajeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Viaje viajeEscogido = usuarioActual.manejando;
                for (Usuario pasajeros: viajeEscogido.pasajeros){
                    pasajeros.viajeEnCurso.viajeFinalizado = true;
                }
                usuarioActual.manejando.viajeFinalizado = true;
                usuarioActual.manejando = null;
                listaViajes.eliminarViaje(viajeEscogido, tusViajesTable);

                tabs.remove(tpViajeEnCurso);
                tabs.remove(tpPerfil);
                tabs.add("Viajes", tpViajesConductor);
                tabs.add("Perfil", tpPerfil);
            }
        });

        calificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Gracias por tu respuesta");
                tabs.remove(tpCalificar);
                tabs.remove(tpPerfil);
                tabs.addTab("Buscar Viaje", tpBuscarViaje);
                tabs.addTab("Perfil", tpPerfil);
            }
        });
        cancelarViajeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
