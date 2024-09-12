package com.dh.clinica.repository;

import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo, Integer> {

    List<Odontologo> findByMatriculaOrNombre(String matricula, String nombre);

    List<Odontologo> findByApellidoEquals (String apellido);

}
