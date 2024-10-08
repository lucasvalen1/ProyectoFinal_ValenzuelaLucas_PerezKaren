package com.dh.clinica.service.impl;


import com.dh.clinica.dto.request.TurnoModificarDto;
import com.dh.clinica.dto.request.TurnoRequestDto;
import com.dh.clinica.dto.response.OdontologoResponseDto;
import com.dh.clinica.dto.response.PacienteResponseDto;
import com.dh.clinica.dto.response.TurnoResponseDto;
import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.entity.Paciente;
import com.dh.clinica.entity.Turno;
import com.dh.clinica.exception.BadRequestException;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.repository.ITurnoRepository;
import com.dh.clinica.service.ITurnoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {

    private final Logger logger = LoggerFactory.getLogger(TurnoService.class);
    private ITurnoRepository turnoRepository;
    private PacienteService pacienteService;
    private OdontologoService odontologService;
    @Autowired
    private ModelMapper modelMapper;

    public TurnoService(ITurnoRepository turnoRepository, PacienteService pacienteService, OdontologoService odontologService) {
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
        this.odontologService = odontologService;
    }

    @Override
    public TurnoResponseDto guardarTurno(TurnoRequestDto turnoRequestDto){
        Optional<Paciente> paciente = pacienteService.buscarPorId(turnoRequestDto.getPaciente_id());
        Optional<Odontologo>  odontologo = odontologService.buscarPorId(turnoRequestDto.getOdontologo_id());
        Turno turno = new Turno ();
        Turno turnoDesdeDb = null;
        TurnoResponseDto turnoARetornar= null;
        if (paciente.isPresent() && odontologo.isPresent()) {
            turno.setPaciente(paciente.get());
            turno.setOdontologo(odontologo.get());
            turno.setFecha(LocalDate.parse(turnoRequestDto.getFecha()));
            // voy a persistir el turno
            turnoDesdeDb = turnoRepository.save(turno);
            logger.info("turnos guardado "+turnoDesdeDb);

            //turnoARetornar = convertirTurnoAResponse(turnoDesdeDb);
            turnoARetornar = mappearATurnoResponse(turnoDesdeDb);
        } else{
            throw new BadRequestException("El turno no fue agregado porque no se encontro el paciente u odontologo");
        }

        return turnoARetornar;
    }

    @Override
    public Optional<TurnoResponseDto> buscarPorId(Integer id) {
        Optional<Turno> turnoDesdeDb = turnoRepository.findById(id);
        TurnoResponseDto turnoResponseDto = null;
        if(turnoDesdeDb.isPresent()){
            turnoResponseDto= mappearATurnoResponse(turnoDesdeDb.get());
            logger.info("turnos encontrado  "+turnoDesdeDb);
        }
        return Optional.ofNullable(turnoResponseDto);

    }

    @Override
    public List<TurnoResponseDto> buscarTodos() {
        List<Turno> turnos = turnoRepository.findAll();
        List<TurnoResponseDto> turnoRespuesta = new ArrayList<>();
        for(Turno t: turnos){
            TurnoResponseDto turnoAxuliar = mappearATurnoResponse(t);
            logger.info("turnos "+t);
            turnoRespuesta.add(turnoAxuliar);
        }
        return turnoRespuesta;
    }

    @Override
    public void modificarTurno(TurnoModificarDto turnoModificarDto) {
        Optional<Paciente> paciente = pacienteService.buscarPorId(turnoModificarDto.getPaciente_id());
        Optional<Odontologo> odontologo = odontologService.buscarPorId(turnoModificarDto.getOdontologo_id());
        Turno turno = null;
        if (paciente.isPresent() && odontologo.isPresent()) {
            turno = new Turno(turnoModificarDto.getId(), paciente.get(), odontologo.get(),
                    LocalDate.parse(turnoModificarDto.getFecha()));
            // voy a persistir el turno
            turnoRepository.save(turno);
            logger.info("turnos modificado  "+turno);
        }
    }

    @Override
    public void eliminarTurno(Integer id) {
        Optional<Turno> turno = turnoRepository.findById(id);
        if (turno.isPresent()){
            turnoRepository.deleteById(id);
            logger.info("turnos eliminado "+turno);
        } else {
            throw new ResourceNotFoundException("El turno " +id+" no fue encontrado");
        }
    }

    @Override
    public List<Turno> buscarTurnoPorApellido(String apellidoPaciente) {
        logger.info("turnos encontrado"+turnoRepository.buscarTurnoPorApellido(apellidoPaciente));
        return turnoRepository.buscarTurnoPorApellido(apellidoPaciente);
    }

    private TurnoResponseDto convertirTurnoAResponse (Turno turnoDesdeDb){
        OdontologoResponseDto odontologoResponseDto = new OdontologoResponseDto(
                turnoDesdeDb.getOdontologo().getId(), turnoDesdeDb.getOdontologo().getMatricula(),
                turnoDesdeDb.getOdontologo().getNombre(), turnoDesdeDb.getOdontologo().getApellido()
        );

        PacienteResponseDto pacienteResponseDto= new PacienteResponseDto(
                turnoDesdeDb.getPaciente().getId(), turnoDesdeDb.getPaciente().getNombre(),
                turnoDesdeDb.getPaciente().getApellido(), turnoDesdeDb.getPaciente().getDni()
        );

        TurnoResponseDto turnoARetornar = new TurnoResponseDto(
                turnoDesdeDb.getId(), pacienteResponseDto, odontologoResponseDto,
                turnoDesdeDb.getFecha().toString()
        );
        return turnoARetornar;
    }

    private TurnoResponseDto mappearATurnoResponse(Turno turno){
        TurnoResponseDto turnoResponseDto = modelMapper.map(turno, TurnoResponseDto.class);
        turnoResponseDto.setOdontologoResponseDto(modelMapper.map(turno.getOdontologo(), OdontologoResponseDto.class));
        turnoResponseDto.setPacienteResponseDto(modelMapper.map(turno.getPaciente(), PacienteResponseDto.class));
        return turnoResponseDto;
    }
}
