/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba3_pii_ashlymartinez;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gigiw
 */

public class Principal extends JFrame {

    private GestorEmpleados gestor;

    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtSalarioBase;
    private JTextField txtBonificacion;
    private JTextField txtDescuento;
    private JTextField txtHoras;
    private JTextField txtTarifa;

    private JLabel lblBonificacion;
    private JLabel lblDescuento;
    private JLabel lblHoras;
    private JLabel lblTarifa;

    private JComboBox<String> cmbTipo;
    private JTable tablaEmpleados;
    private DefaultTableModel modeloTabla;

    private Empleado clonPendiente;

    public Principal() {
        gestor = new GestorEmpleados();
        construirInterfaz();
    }

    private void construirInterfaz() {
        setTitle("Sistema de Gestion de Empleados");
        setSize(1050, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Encabezado
        JPanel panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.setBackground(new Color(23, 43, 77));
        panelTitulo.setBorder(
                BorderFactory.createEmptyBorder(18, 20, 18, 20)
        );

        JLabel lblTitulo = new JLabel(
                "SISTEMA DE GESTION DE EMPLEADOS"
        );
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));

        panelTitulo.add(lblTitulo, BorderLayout.CENTER);
        add(panelTitulo, BorderLayout.NORTH);

        // Formulario
        JPanel panelFormulario = new JPanel(new GridLayout(0, 4, 8, 8));
        panelFormulario.setBorder(
                BorderFactory.createTitledBorder("Datos del empleado")
        );

        txtCodigo = new JTextField();
        txtNombre = new JTextField();
        txtSalarioBase = new JTextField();
        txtBonificacion = new JTextField();
        txtDescuento = new JTextField();
        txtHoras = new JTextField();
        txtTarifa = new JTextField();

        cmbTipo = new JComboBox<String>(
                new String[]{"Administrativo", "Por horas"}
        );

        panelFormulario.add(new JLabel("Codigo:"));
        panelFormulario.add(txtCodigo);
        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Tipo de empleado:"));
        panelFormulario.add(cmbTipo);
        panelFormulario.add(new JLabel("Salario base:"));
        panelFormulario.add(txtSalarioBase);

        lblBonificacion = new JLabel("Bonificacion:");
        panelFormulario.add(lblBonificacion);
        panelFormulario.add(txtBonificacion);

        lblDescuento = new JLabel("Descuento (%):");
        panelFormulario.add(lblDescuento);
        panelFormulario.add(txtDescuento);

        lblHoras = new JLabel("Horas trabajadas:");
        panelFormulario.add(lblHoras);
        panelFormulario.add(txtHoras);

        lblTarifa = new JLabel("Tarifa por hora:");
        panelFormulario.add(lblTarifa);
        panelFormulario.add(txtTarifa);

        cmbTipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCamposTipo();
            }
        });

        actualizarCamposTipo();

        // Botones
        JPanel panelBotones = new JPanel(new GridLayout(0, 4, 8, 8));
        panelBotones.setBorder(
                BorderFactory.createTitledBorder("Operaciones")
        );

        JButton btnRegistrar = new JButton("Registrar empleado");
        JButton btnCalcular = new JButton("Calcular salario");
        JButton btnBuscar = new JButton("Buscar por codigo");
        JButton btnClonar = new JButton("Clonar empleado");
        JButton btnRegistrarClon = new JButton("Registrar clon");
        JButton btnComparar = new JButton("Comparar objetos");
        JButton btnMayor = new JButton("Salario maximo");
        JButton btnPromedio = new JButton("Salario promedio");
        JButton btnLimpiar = new JButton("Limpiar formulario");

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnCalcular);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnClonar);
        panelBotones.add(btnRegistrarClon);
        panelBotones.add(btnComparar);
        panelBotones.add(btnMayor);
        panelBotones.add(btnPromedio);
        panelBotones.add(btnLimpiar);

        // Tabla
        modeloTabla = new DefaultTableModel(
                new String[]{
                    "Codigo", "Nombre", "Tipo", "Salario", "Categoria"
                }, 0
        ) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tablaEmpleados = new JTable(modeloTabla);
        tablaEmpleados.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );
        tablaEmpleados.setRowHeight(24);

        JScrollPane scrollTabla = new JScrollPane(tablaEmpleados);
        scrollTabla.setBorder(
                BorderFactory.createTitledBorder("Empleados registrados")
        );

        // Panel central
        JPanel panelCentral = new JPanel(new BorderLayout(10, 10));
        panelCentral.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );
        panelCentral.add(panelFormulario, BorderLayout.NORTH);
        panelCentral.add(scrollTabla, BorderLayout.CENTER);
        panelCentral.add(panelBotones, BorderLayout.SOUTH);

        add(panelCentral, BorderLayout.CENTER);

        // Eventos de los botones
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarEmpleado();
            }
        });

        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularSalario();
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarEmpleado();
            }
        });

        btnClonar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clonarEmpleado();
            }
        });

        btnRegistrarClon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarClon();
            }
        });

        btnComparar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                compararEmpleados();
            }
        });

        btnMayor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarMayorSalario();
            }
        });

        btnPromedio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPromedio();
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
            }
        });
    }

    private void actualizarCamposTipo() {
        boolean administrativo =
                cmbTipo.getSelectedIndex() == 0;

        lblBonificacion.setVisible(administrativo);
        txtBonificacion.setVisible(administrativo);
        lblDescuento.setVisible(administrativo);
        txtDescuento.setVisible(administrativo);

        lblHoras.setVisible(!administrativo);
        txtHoras.setVisible(!administrativo);
        lblTarifa.setVisible(!administrativo);
        txtTarifa.setVisible(!administrativo);

        revalidate();
        repaint();
    }

    private Empleado crearEmpleadoDesdeFormulario() {
        String codigo = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();

        double salarioBase =
                Double.parseDouble(txtSalarioBase.getText().trim());

        if (cmbTipo.getSelectedIndex() == 0) {
            double bonificacion =
                    Double.parseDouble(txtBonificacion.getText().trim());

            double descuento =
                    Double.parseDouble(txtDescuento.getText().trim());

            return new EmpleadoAdministrativo(
                    codigo, nombre, salarioBase,
                    bonificacion, descuento
            );

        } else {
            int horas =
                    Integer.parseInt(txtHoras.getText().trim());

            double tarifa =
                    Double.parseDouble(txtTarifa.getText().trim());

            return new EmpleadoPorHoras(
                    codigo, nombre, salarioBase, horas, tarifa
            );
        }
    }

    private void registrarEmpleado() {
        try {
            Empleado empleado = crearEmpleadoDesdeFormulario();

            gestor.registrarEmpleado(empleado);
            actualizarTabla();

            JOptionPane.showMessageDialog(
                    this, "Empleado registrado correctamente."
            );

            limpiarFormulario();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Verifica que los valores numericos sean correctos.",
                    "Datos invalidos",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(
                    this, e.getMessage(),
                    "No se pudo registrar",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void calcularSalario() {
        String codigo = pedirCodigo("Ingrese el codigo del empleado:");

        if (codigo == null) {
            return;
        }

        Empleado empleado = gestor.buscarEmpleado(codigo);

        if (empleado == null) {
            mostrarNoEncontrado();
            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "Empleado: " + empleado.getNombre()
                + "\nSalario calculado: "
                + String.format("%.2f", empleado.calcularSalario())
                + "\nCategoria: " + empleado.obtenerCategoria(),
                "Resultado del calculo",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void buscarEmpleado() {
        String codigo = pedirCodigo("Ingrese el codigo que desea buscar:");

        if (codigo == null) {
            return;
        }

        Empleado empleado = gestor.buscarEmpleado(codigo);

        if (empleado == null) {
            mostrarNoEncontrado();
            return;
        }

        cargarFormulario(empleado);

        JOptionPane.showMessageDialog(
                this,
                empleado.mostrarInformacion(),
                "Empleado encontrado",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void clonarEmpleado() {
        String codigoOriginal =
                pedirCodigo("Codigo del empleado que desea clonar:");

        if (codigoOriginal == null) {
            return;
        }

        String nuevoCodigo = JOptionPane.showInputDialog(
                this, "Ingrese el nuevo codigo para el clon:"
        );

        if (nuevoCodigo == null) {
            return;
        }

        try {
            clonPendiente = gestor.clonarEmpleado(
                    codigoOriginal, nuevoCodigo
            );

            cargarFormulario(clonPendiente);

            JOptionPane.showMessageDialog(
                    this,
                    "Clon creado correctamente."
                    + "\nTipo conservado: "
                    + clonPendiente.getClass().getSimpleName()
                    + "\nCodigo nuevo: " + clonPendiente.getCodigo()
                    + "\nPara guardarlo en la lista, pulse Registrar clon."
            );

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(
                    this, e.getMessage(),
                    "No se pudo clonar",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void registrarClon() {
        if (clonPendiente == null) {
            JOptionPane.showMessageDialog(
                    this, "Primero debe clonar un empleado."
            );
            return;
        }

        try {
            gestor.registrarEmpleado(clonPendiente);

            clonPendiente = null;
            actualizarTabla();

            JOptionPane.showMessageDialog(
                    this, "Clon registrado correctamente."
            );

            limpiarFormulario();

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(
                    this, e.getMessage(),
                    "No se pudo registrar el clon",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void compararEmpleados() {
        String codigo1 = pedirCodigo("Codigo del primer empleado:");

        if (codigo1 == null) {
            return;
        }

        String codigo2 = pedirCodigo("Codigo del segundo empleado:");

        if (codigo2 == null) {
            return;
        }

        Empleado a = gestor.buscarEmpleado(codigo1);
        Empleado b = gestor.buscarEmpleado(codigo2);

        if (a == null || b == null) {
            mostrarNoEncontrado();
            return;
        }

        boolean mismaReferencia =
                gestor.compararReferencia(codigo1, codigo2);

        boolean mismosAtributos =
                gestor.compararAtributos(codigo1, codigo2);

        JOptionPane.showMessageDialog(
                this,
                "Misma referencia de memoria: "
                + (mismaReferencia ? "Si" : "No")
                + "\nMismos atributos relevantes: "
                + (mismosAtributos ? "Si" : "No"),
                "Comparacion de empleados",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void mostrarMayorSalario() {
        Empleado mayor = gestor.obtenerEmpleadoMayorSalario();

        if (mayor == null) {
            JOptionPane.showMessageDialog(
                    this, "No hay empleados registrados."
            );
            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "Empleado: " + mayor.getNombre()
                + "\nCodigo: " + mayor.getCodigo()
                + "\nSalario: "
                + String.format("%.2f", mayor.calcularSalario()),
                "Salario maximo",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void mostrarPromedio() {
        ArrayList<Empleado> lista = gestor.obtenerEmpleados();

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this, "No hay empleados registrados."
            );
            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "Salario promedio: "
                + String.format(
                        "%.2f", gestor.calcularSalarioPromedio()
                ),
                "Estadistica de salarios",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private String pedirCodigo(String mensaje) {
        String codigo = JOptionPane.showInputDialog(this, mensaje);

        if (codigo == null) {
            return null;
        }

        codigo = codigo.trim();

        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this, "El codigo no puede estar vacio."
            );
            return null;
        }

        return codigo;
    }

    private void mostrarNoEncontrado() {
        JOptionPane.showMessageDialog(
                this,
                "No se encontro un empleado con ese codigo.",
                "Busqueda",
                JOptionPane.WARNING_MESSAGE
        );
    }

    private void cargarFormulario(Empleado empleado) {
        txtCodigo.setText(empleado.getCodigo());
        txtNombre.setText(empleado.getNombre());
        txtSalarioBase.setText(
                String.valueOf(empleado.getSalarioBase())
        );

        if (empleado instanceof EmpleadoAdministrativo) {
            EmpleadoAdministrativo administrativo =
                    (EmpleadoAdministrativo) empleado;

            cmbTipo.setSelectedIndex(0);

            txtBonificacion.setText(
                    String.valueOf(administrativo.getBonificacion())
            );
            txtDescuento.setText(
                    String.valueOf(administrativo.getDescuento())
            );

        } else if (empleado instanceof EmpleadoPorHoras) {
            EmpleadoPorHoras porHoras =
                    (EmpleadoPorHoras) empleado;

            cmbTipo.setSelectedIndex(1);

            txtHoras.setText(
                    String.valueOf(porHoras.getHorasTrabajadas())
            );
            txtTarifa.setText(
                    String.valueOf(porHoras.getTarifaPorHora())
            );
        }

        actualizarCamposTipo();
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);

        ArrayList<Empleado> lista = gestor.obtenerEmpleados();

        for (int i = 0; i < lista.size(); i++) {
            Empleado empleado = lista.get(i);

            String tipo;

            if (empleado instanceof EmpleadoAdministrativo) {
                tipo = "Administrativo";
            } else {
                tipo = "Por horas";
            }

            modeloTabla.addRow(new Object[]{
                empleado.getCodigo(),
                empleado.getNombre(),
                tipo,
                String.format("%.2f", empleado.calcularSalario()),
                empleado.obtenerCategoria()
            });
        }
    }

    private void limpiarFormulario() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtSalarioBase.setText("");
        txtBonificacion.setText("");
        txtDescuento.setText("");
        txtHoras.setText("");
        txtTarifa.setText("");

        cmbTipo.setSelectedIndex(0);
        actualizarCamposTipo();
        clonPendiente = null;
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }
}
