package com.dh.clinica.service;

import com.dh.clinica.entity.Paciente;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {
    Paciente guardarPaciente(Paciente paciente);
    Optional<Paciente> buscarPorId(Integer id);
    List<Paciente> buscarTodos();
    void modificarPaciente(Paciente paciente);
    void eliminarPaciente(Integer id);

    List<Paciente> buscarPorApellidoYNombre(String apellido, String nombre);

    List<Paciente> buscarLikeNombre(String nombre);
}
