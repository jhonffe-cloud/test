package com.mx.empleados.repository;

import com.mx.empleados.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {


    Optional<Empleado> findByCodigoEmpleado(String codigoEmpleado);
}
