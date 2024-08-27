package com.dh.clinica.controller;

import com.dh.clinica.model.Odontologo;
import com.dh.clinica.service.OdontologoService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    private OdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService) {

        this.odontologoService = odontologoService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardar(odontologo));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        Odontologo odontologo = odontologoService.buscarPorId(id);
        if(odontologo != null){
            return ResponseEntity.ok(odontologo);
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
        Odontologo odontologoEncontrado = odontologoService.buscarPorId(odontologo.getId());
        if (odontologoEncontrado != null) {
            odontologoService.modificarOdontologo(odontologo);
            String jsonResponse = "{\"mensaje\": \"El odontologo fue modificado\"}";
            return ResponseEntity.ok(jsonResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Integer id){
        Odontologo odontologoEncontrado = odontologoService.buscarPorId(id);
        if(odontologoEncontrado!= null){
            odontologoService.eliminarOdontologo(id);
            String jsonResponse = "{\"mensaje\": \"El odontologo fue eliminado\"}";
            return ResponseEntity.ok(jsonResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
