/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba3_pii_ashlymartinez;
import java.util.ArrayList;

/**
 *
 * @author gigiw
 */
public class GestorEmpleados {

    private ArrayList<Empleado> empleados;

    public GestorEmpleados() {
        empleados = new ArrayList<Empleado>();
    }

    public void registrarEmpleado(Empleado empleado) {
        if (empleado == null) {
            throw new IllegalArgumentException(
                "El empleado no puede ser nulo."
            );
        }

        if (buscarEmpleado(empleado.getCodigo()) != null) {
            throw new IllegalArgumentException(
                "Ya existe un empleado con ese código."
            );
        }

        empleados.add(empleado);
    }

    public Empleado buscarEmpleado(String codigo) {
        if (codigo == null) {
            return null;
        }

        for (int i = 0; i < empleados.size(); i++) {
            Empleado empleado = empleados.get(i);

            if (empleado.getCodigo().equalsIgnoreCase(codigo.trim())) {
                return empleado;
            }
        }
        return null;
    }

    public boolean existeCodigo(String codigo) {
        return buscarEmpleado(codigo) != null;
    }

    public ArrayList<Empleado> obtenerEmpleados() {
        return new ArrayList<Empleado>(empleados);
    }

    public Empleado clonarEmpleado(String codigoOriginal, String nuevoCodigo) {
        Empleado original = buscarEmpleado(codigoOriginal);

        if (original == null) {
            throw new IllegalArgumentException(
                "No existe el empleado que deseas clonar."
            );
        }

        if (nuevoCodigo == null || nuevoCodigo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El nuevo código no puede estar vacío."
            );
        }

        if (existeCodigo(nuevoCodigo)) {
            throw new IllegalArgumentException(
                "El nuevo código ya está registrado."
            );
        }

        Empleado clon = original.clone();
        clon.setCodigo(nuevoCodigo.trim());

        return clon;
    }

    public Empleado obtenerEmpleadoMayorSalario() {
        if (empleados.isEmpty()) {
            return null;
        }

        Empleado mayor = empleados.get(0);

        for (int i = 1; i < empleados.size(); i++) {
            Empleado actual = empleados.get(i);

            if (actual.calcularSalario() > mayor.calcularSalario()) {
                mayor = actual;
            }
        }

        return mayor;
    }

    public double calcularSalarioPromedio() {
        if (empleados.isEmpty()) {
            return 0;
        }

        double suma = 0;

        for (int i = 0; i < empleados.size(); i++) {
            suma += empleados.get(i).calcularSalario();
        }

        return suma / empleados.size();
    }

    public boolean compararReferencia(String codigo1, String codigo2) {
        Empleado empleado1 = buscarEmpleado(codigo1);
        Empleado empleado2 = buscarEmpleado(codigo2);

        return empleado1 != null && empleado1 == empleado2;
    }

    public boolean compararAtributos(String codigo1, String codigo2) {
        Empleado empleado1 = buscarEmpleado(codigo1);
        Empleado empleado2 = buscarEmpleado(codigo2);

        if (empleado1 == null || empleado2 == null) {
            return false;
        }

        if (!empleado1.getClass().equals(empleado2.getClass())) {
            return false;
        }

        if (!empleado1.getNombre().equals(empleado2.getNombre())
                || Double.compare(
                    empleado1.getSalarioBase(),
                    empleado2.getSalarioBase()
                ) != 0) {
            return false;
        }

        if (empleado1 instanceof EmpleadoAdministrativo) {
            EmpleadoAdministrativo a =
                    (EmpleadoAdministrativo) empleado1;
            EmpleadoAdministrativo b =
                    (EmpleadoAdministrativo) empleado2;

            return Double.compare(a.getBonificacion(), b.getBonificacion()) == 0
                    && Double.compare(a.getDescuento(), b.getDescuento()) == 0;
        }

        if (empleado1 instanceof EmpleadoPorHoras) {
            EmpleadoPorHoras a = (EmpleadoPorHoras) empleado1;
            EmpleadoPorHoras b = (EmpleadoPorHoras) empleado2;

            return a.getHorasTrabajadas() == b.getHorasTrabajadas()
                    && Double.compare(
                        a.getTarifaPorHora(),
                        b.getTarifaPorHora()
                    ) == 0;
        }

        return false;
    }
}

