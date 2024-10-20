package com.mx.empleados.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Puesto {
    @Id
    private Long id;
    private String nombre;

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
}
