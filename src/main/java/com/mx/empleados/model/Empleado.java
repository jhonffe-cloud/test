package com.mx.empleados.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import com.mx.empleados.vm.EmpleadoVM;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


@Entity
public class Empleado {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @NotBlank
    private String nombre;
    private String codigoEmpleado;
    @ManyToOne
    private Puesto puesto;
    @Min(value = 1, message = "Salaario debe ser mayor que 0")
    @Column(nullable = false)
    private Double salario;
    private ZonedDateTime fechaContratacion;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public ZonedDateTime getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(ZonedDateTime fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public EmpleadoVM convertToEmpleadoVM() {

        return new EmpleadoVM(codigoEmpleado, nombre,null,puesto.getNombre(), fechaContratacion.format(DateTimeFormatter.ISO_ZONED_DATE_TIME), salario,null);
    }
}
