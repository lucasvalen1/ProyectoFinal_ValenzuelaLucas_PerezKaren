package com.dh.clinica.controller;


import com.dh.clinica.dto.request.TurnoModificarDto;
import com.dh.clinica.dto.request.TurnoRequestDto;
import com.dh.clinica.dto.response.TurnoResponseDto;
import com.dh.clinica.entity.Turno;
import com.dh.clinica.service.impl.TurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarTurno(@RequestBody TurnoRequestDto turnoRequestDto){
            TurnoResponseDto turnoAGuardar=  turnoService.guardarTurno(turnoRequestDto);
            return ResponseEntity.ok(turnoAGuardar);
    }

    @GetMapping("/buscartodos")
    public ResponseEntity<List<TurnoResponseDto>> buscarTodos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        Optional<TurnoResponseDto> turnoAEncontrar = turnoService.buscarPorId(id);
        if(turnoAEncontrar.isPresent()){
            return ResponseEntity.ok(turnoAEncontrar);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El turno no fue encontrado");
        }
    }
    @PutMapping("/modificar")
    public ResponseEntity<?> modificarTurno(@RequestBody TurnoModificarDto turnoModificarDto) {
        Optional<TurnoResponseDto> turnoEncontrado = turnoService.buscarPorId(turnoModificarDto.getId());
        if (turnoEncontrado.isPresent()) {
            turnoService.modificarTurno(turnoModificarDto);
            String jsonResponse = "{\"mensaje\": \"El turno fue modificado\"}";
            return ResponseEntity.ok(jsonResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Integer id){
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("{\"mensaje\": \"El turno fue eliminado\"}");

    }

    @GetMapping("/buscarporapellido/{apellidoPaciente}")
    public ResponseEntity<List<Turno>> buscarTurnoApellido(@PathVariable String apellidoPaciente){
        return ResponseEntity.ok(turnoService.buscarTurnoPorApellido(apellidoPaciente));
    }
}

