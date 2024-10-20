package com.mx.empleados.web;

import com.mx.empleados.vm.ErrorVM;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.mx.empleados.service.EmpleadoService;
import org.springframework.http.ResponseEntity;
import com.mx.empleados.vm.EmpleadoVM;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/empleados")
@CrossOrigin(origins = "*")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping()
    public ResponseEntity<List<EmpleadoVM>> getEmpleados() {

        return ResponseEntity.ok(empleadoService.getEmpleados());
    }

    @PostMapping
    public ResponseEntity<EmpleadoVM> saveEmpleado(@Valid @RequestBody EmpleadoVM empleadoVM) {

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(empleadoService.saveEmpleado(empleadoVM));
        } catch (NoSuchElementException e) {

            ErrorVM error = new ErrorVM(e.getMessage());
            EmpleadoVM empleadoError = new EmpleadoVM(null, null, null, null, null, null, error);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(empleadoError);
        }

    }

    @PutMapping("/{codigo}")
    public ResponseEntity<EmpleadoVM> actualizarEmpleado(@Valid @RequestBody EmpleadoVM empleadoVM,@PathVariable String codigo) {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(empleadoService.actualizarEmpleado(empleadoVM,codigo));
        } catch (NoSuchElementException e) {

            ErrorVM error = new ErrorVM(e.getMessage());
            EmpleadoVM empleadoError = new EmpleadoVM(null, null, null, null, null, null, error);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(empleadoError);
        }

    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<EmpleadoVM> eliminarEmpleado(@PathVariable String codigo) {

        try {
            empleadoService.eliminarEmpleado(codigo);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (NoSuchElementException e) {

            ErrorVM error = new ErrorVM(e.getMessage());
            EmpleadoVM empleadoError = new EmpleadoVM(null, null, null, null, null, null, error);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(empleadoError);
        }

    }


}
