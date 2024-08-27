package com.dh.clinica.controller;

import com.dh.clinica.model.Odontologo;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.service.OdontologoService;
import com.dh.clinica.service.PacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VistaController {
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    public VistaController(PacienteService pacienteService, OdontologoService odontologoService) {
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    // localhost:8080/20  -> @PathVariable
    // localhost:8080?id=1  -> @RequestParams
    @GetMapping("/index")
    public String mostrarPacientePorId(Model model, @RequestParam Integer id){
        Paciente paciente = pacienteService.buscarPorId(id);
        model.addAttribute("nombrePaciente", paciente.getNombre());
        model.addAttribute("apellidoPaciente", paciente.getApellido());
        return "paciente";
    }

    @GetMapping("/index2/{id}")
    public String mostrarPacientePorId2(Model model, @PathVariable Integer id){
        Paciente paciente = pacienteService.buscarPorId(id);
        model.addAttribute("nombrePaciente", paciente.getNombre());
        model.addAttribute("apellidoPaciente", paciente.getApellido());
        return "paciente";
    }

    @GetMapping("/index3")
    public String mostrarOdontologoPorId(Model model, @RequestParam Integer id){
        Odontologo odontologo = odontologoService.buscarPorId(id);
        model.addAttribute("nombreOdontologo", odontologo.getNombre());
        model.addAttribute("apellidoOdontologo", odontologo.getApellido());
        model.addAttribute("matriculaOdontologo", odontologo.getMatricula());
        return "odontologo";
    }
}
