/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba3_pii_ashlymartinez;

/**
 *
 * @author gigiw
 */
public abstract class Empleado implements Cloneable {

    private String codigo;
    private String nombre;
    private double salarioBase;

    public Empleado(String codigo, String nombre, double salarioBase) {
        setCodigo(codigo);
        setNombre(nombre);
        setSalarioBase(salarioBase);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El código no puede estar vacío."
            );
        }

        this.codigo = codigo.trim();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El nombre no puede estar vacío."
            );
        }

        this.nombre = nombre.trim();
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        if (!Double.isFinite(salarioBase) || salarioBase < 0) {
            throw new IllegalArgumentException(
                "El salario base debe ser un número válido y no negativo."
            );
        }

        this.salarioBase = salarioBase;
    }

    public abstract double calcularSalario();

    public String mostrarInformacion() {
        return "Código: " + codigo + "\nNombre: " + nombre+ "\nSalario base: " + salarioBase+ "\nSalario calculado: " + calcularSalario()+ "\nCategoría: " + obtenerCategoria();
    }

    public String obtenerCategoria() {
        double salario = calcularSalario();

        if (salario < 10000) {
            return "Básica";
        } else if (salario < 20000) {
            return "Intermedia";
        } else {
            return "Alta";
        }
    }

    @Override
    public Empleado clone() {
        try {
            return (Empleado) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(
                "No se pudo clonar el empleado.", e
            );
        }
    }
}
