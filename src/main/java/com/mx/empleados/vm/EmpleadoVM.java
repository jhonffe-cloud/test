package com.mx.empleados.vm;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EmpleadoVM(String codigoEmpleado,
                         @NotBlank(message = "El nombre no puede estar vacío")
                         String nombre, Long puestoId,String puesto,
                         @NotBlank(message = "La fechaIngreso no puede estar vacío")
                         String fechaIngreso,
                         @NotNull(message = "El Salario no puede ser nulo")
                         @Min(value = 1, message = "El Salario debe ser mayor que 0")
                         Double salario,
                         ErrorVM error
                         ) {
}
