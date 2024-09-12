package com.dh.clinica.controller;

import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.service.impl.OdontologoService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    private OdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService) {

        this.odontologoService = odontologoService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo){

        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        Optional<Odontologo> odontologo = odontologoService.buscarPorId(id);
        if(odontologo.isPresent()){
            return ResponseEntity.ok(odontologo.get());
        } else {
            // ResponseEntity.status(HttpStatus.NOT_FOUND).body("paciente no encontrado");
            //ResponseEntity.notFound().build();
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
        }
    }

    @GetMapping("/buscartodos")
    public ResponseEntity<List<Odontologo>> buscarTodos(){
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }

    @PutMapping("/modificar")
    public ResponseEntity<?> modificarOdontologo(@RequestBody Odontologo odontologo) {
        Optional<Odontologo> odontologoEncontrado = odontologoService.buscarPorId(odontologo.getId());
        if (odontologoEncontrado.isPresent()) {
            odontologoService.modificarOdontologo(odontologo);
            String jsonResponse = "{\"mensaje\": \"El odontologo fue modificado\"}";
            return ResponseEntity.ok(jsonResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Integer id){
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok("{\"mensaje\": \"El odontologo fue eliminado\"}");

    }

    @GetMapping("/buscarpormatriculaonombre")
    public ResponseEntity<List<Odontologo>> buscarPorMatriculaONombre(@RequestParam String matricula, @RequestParam String nombre){
        return ResponseEntity.ok(odontologoService.buscarPorMatriculaONombre(matricula, nombre));
    }
    @GetMapping("/buscarporapellidoexacto")
    public ResponseEntity<List<Odontologo>> buscarPorApellidoExacto(@RequestParam String apellido){
        return ResponseEntity.ok(odontologoService.buscarPorApellidoExacto(apellido));
    }
}
