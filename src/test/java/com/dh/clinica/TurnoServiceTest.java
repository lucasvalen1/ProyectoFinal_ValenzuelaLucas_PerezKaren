package com.dh.clinica;

import com.dh.clinica.dto.request.TurnoRequestDto;
import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.entity.Paciente;
import com.dh.clinica.entity.Turno;
import com.dh.clinica.service.impl.TurnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
public class TurnoServiceTest {
    @Autowired
    TurnoService turnoService;

    Turno turno;

    TurnoRequestDto turnoRequestDto;


    @BeforeEach
    void crearTurno() {
        turno = new Turno();
        turno.setPaciente(new Paciente());
        turno.setOdontologo(new Odontologo());
        turno.setFecha(LocalDate.now());


    }
}