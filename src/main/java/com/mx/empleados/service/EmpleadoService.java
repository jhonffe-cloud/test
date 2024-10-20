package com.mx.empleados.service;

import com.mx.empleados.Utils.CodeGenerator;
import com.mx.empleados.model.Empleado;
import com.mx.empleados.model.Puesto;
import org.springframework.stereotype.Service;
import com.mx.empleados.repository.EmpleadoRepository;
import com.mx.empleados.repository.PuestoRepository;
import com.mx.empleados.vm.EmpleadoVM;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class EmpleadoService {
    private final EmpleadoRepository empleadoRepository;
    private final PuestoRepository puestoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository, PuestoRepository puestoRepository) {
        this.empleadoRepository = empleadoRepository;
        this.puestoRepository = puestoRepository;
    }

    /**
     * Get all employees
     * @return
     */
    public List<EmpleadoVM> getEmpleados() {
        List<Empleado> empleados = empleadoRepository.findAll();
        return empleados.stream().map(Empleado::convertToEmpleadoVM).collect(Collectors.toList());
    }

    /**
     * Eliminar empleado
     * @param codigo
     * @return
     */
    public Boolean eliminarEmpleado(String codigo) {
        Empleado empleado = empleadoRepository.findByCodigoEmpleado(codigo).orElseThrow(() -> new NoSuchElementException("empleado con Codigo " + codigo + " not found"));
        empleadoRepository.delete(empleado);
        return Boolean.TRUE;

    }

    /**
     * Actualizar empleado
     * @param empleadoVM
     * @param codigo
     * @return
     */
    public EmpleadoVM actualizarEmpleado(EmpleadoVM empleadoVM,String codigo) {

        Puesto puesto = puestoRepository.findById(empleadoVM.puestoId()).orElseThrow(() -> new NoSuchElementException("Puesto with ID " + empleadoVM.puestoId() + " not found"));

        Empleado empleado = empleadoRepository.findByCodigoEmpleado(codigo).orElseThrow(() -> new NoSuchElementException("empleado con Codigo " + empleadoVM.codigoEmpleado() + " not found"));
        empleado.setNombre(empleadoVM.nombre());
        empleado.setPuesto(puesto);
        empleado.setSalario(empleadoVM.salario());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(empleadoVM.fechaIngreso(), formatter);
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());

        empleado.setFechaContratacion(zonedDateTime);

        Empleado entity =  empleadoRepository.save(empleado);
        return entity.convertToEmpleadoVM();

    }
    /**
     * Save employee
     * @param empleadoVM
     * @return
     */
    public EmpleadoVM saveEmpleado(EmpleadoVM empleadoVM) {

        Puesto puesto = puestoRepository.findById(empleadoVM.puestoId()).orElseThrow(() -> new NoSuchElementException("Puesto with ID " + empleadoVM.puestoId() + " not found"));

        Empleado empleado = new Empleado();
        empleado.setNombre(empleadoVM.nombre());
        empleado.setCodigoEmpleado(CodeGenerator.generateCode(10));
        empleado.setPuesto(puesto);
        empleado.setSalario(empleadoVM.salario());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(empleadoVM.fechaIngreso(), formatter);
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());

        empleado.setFechaContratacion(zonedDateTime);
        Empleado entity =  empleadoRepository.save(empleado);
        return entity.convertToEmpleadoVM();
    }
}
