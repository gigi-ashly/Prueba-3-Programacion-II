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
public class EmpleadoPorHoras extends Empleado {

    private int horasTrabajadas;
    private double tarifaPorHora;

    public EmpleadoPorHoras(
            String codigo,
            String nombre,
            double salarioBase,
            int horasTrabajadas,
            double tarifaPorHora) {

        super(codigo, nombre, salarioBase);

        setHorasTrabajadas(horasTrabajadas);
        setTarifaPorHora(tarifaPorHora);
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(int horasTrabajadas) {
        if (horasTrabajadas < 0) {
            throw new IllegalArgumentException(
                "Las horas trabajadas no pueden ser negativas."
            );
        }

        this.horasTrabajadas = horasTrabajadas;
    }

    public double getTarifaPorHora() {
        return tarifaPorHora;
    }

    public void setTarifaPorHora(double tarifaPorHora) {
        if (!Double.isFinite(tarifaPorHora)
                || tarifaPorHora < 0) {
            throw new IllegalArgumentException(
                "La tarifa por hora no puede ser negativa."
            );
        }

        this.tarifaPorHora = tarifaPorHora;
    }

    @Override
    public double calcularSalario() {
        return getSalarioBase()
                + (horasTrabajadas * tarifaPorHora);
    }
}

