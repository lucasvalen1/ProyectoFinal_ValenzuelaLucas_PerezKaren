package com.dh.clinica.controller;


import com.dh.clinica.model.Odontologo;
import com.dh.clinica.model.Turno;
import com.dh.clinica.service.TurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarTurno(@RequestBody Turno turno){
        Turno turnoAGuardar = turnoService.guardarTurno(turno);
        if(turnoAGuardar != null){
            return ResponseEntity.ok(turnoAGuardar);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El paciente o el odontologo no fueron encontrados");
        }

    }

    @GetMapping("/buscartodos")
    public ResponseEntity<List<Turno>> buscarTodos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        Turno turnoAEncontrar = turnoService.buscarPorId(id);
        if(turnoAEncontrar != null){
            return ResponseEntity.ok(turnoAEncontrar);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El turno no fue encontrado");
        }
    }
    @PutMapping("/modificar")
    public ResponseEntity<?> modificarTurno(@RequestBody Turno turno) {
        Turno turnoEncontrado = turnoService.buscarPorId(turno.getId());
        if (turnoEncontrado != null) {
            turnoService.modificarTurno(turno);
            String jsonResponse = "{\"mensaje\": \"El turno fue modificado\"}";
            return ResponseEntity.ok(jsonResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Integer id){
        Turno turnoEncontrado = turnoService.buscarPorId(id);
        if(turnoEncontrado!= null){
            turnoService.eliminarTurno(id);
            String jsonResponse = "{\"mensaje\": \"El turno fue eliminado\"}";
            return ResponseEntity.ok(jsonResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

