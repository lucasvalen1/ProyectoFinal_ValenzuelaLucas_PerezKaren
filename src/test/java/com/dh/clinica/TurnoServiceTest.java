package com.dh.clinica;

import com.dh.clinica.dto.request.TurnoRequestDto;
import com.dh.clinica.dto.response.TurnoResponseDto;
import com.dh.clinica.entity.Domicilio;
import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.entity.Paciente;
import com.dh.clinica.entity.Turno;
import com.dh.clinica.service.impl.OdontologoService;
import com.dh.clinica.service.impl.PacienteService;
import com.dh.clinica.service.impl.TurnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
public class TurnoServiceTest {
    @Autowired
    TurnoService turnoService;

    @Autowired
    PacienteService pacienteService;

    @Autowired
    OdontologoService odontologoService;

    Turno turno;

    TurnoRequestDto turnoRequestDto;

    TurnoResponseDto turnoResponseDto;


    @BeforeEach
    void crearTurno() {

        Domicilio domicilio = new Domicilio(null,"Falsa", 456, "Cipolleti", "Rio Negro");
        Paciente paciente = new Paciente();
        paciente.setApellido("Romero");
        paciente.setNombre("Luciana");
        paciente.setDni("56655");
        paciente.setFechaIngreso(LocalDate.of(2024, 7, 16));
        paciente.setDomicilio(domicilio);
        Paciente pacienteDesdeDb = pacienteService.guardarPaciente(paciente);

        Odontologo odontologo= new Odontologo();
        odontologo.setApellido("Martinez");
        odontologo.setNombre("Jose");
        odontologo.setMatricula("A632");
        Odontologo odontologoDesdeDb = odontologoService.guardarOdontologo(odontologo);

        turnoRequestDto = new TurnoRequestDto(pacienteDesdeDb.getId(), odontologoDesdeDb.getId(), LocalDate.now().toString());

        turnoResponseDto = turnoService.guardarTurno(turnoRequestDto);


    }

    @Test
    @DisplayName("Testear buscar por id")
    void buscarPorId (){

        TurnoResponseDto turnoEncontrado = turnoService.buscarPorId(turnoResponseDto.getId()).get();
        assertEquals( turnoResponseDto.getId(), turnoEncontrado.getId());


    }

}