package com.dh.clinica.service.impl;



import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.entity.Turno;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.repository.IOdontologoRepository;
import com.dh.clinica.service.IOdontologoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OdontologoService implements IOdontologoService {
    private final Logger logger = LoggerFactory.getLogger(OdontologoService.class);

    private IOdontologoRepository iOdontologoRepository;

    public OdontologoService(IOdontologoRepository iOdontologoRepository) {
        this.iOdontologoRepository = iOdontologoRepository;
    }

    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        logger.info("Odontologo guardado "+odontologo);
        return iOdontologoRepository.save(odontologo);
    }

    @Override
    public Optional<Odontologo> buscarPorId(Integer id) {
        logger.info("Odontologo encontrado con id "+id);
        return iOdontologoRepository.findById(id);
    }

    @Override
    public List<Odontologo> buscarTodos() {
        logger.info("Odontologo encontrado ");
        return iOdontologoRepository.findAll();
    }

    @Override
    public void modificarOdontologo(Odontologo odontologo) {
        logger.info("Odontologo modificado "+odontologo);
        iOdontologoRepository.save(odontologo);
    }

    @Override
    public void eliminarOdontologo(Integer id) {
        Optional<Odontologo> odontologo = iOdontologoRepository.findById(id);
        if (odontologo.isPresent()){
            iOdontologoRepository.deleteById(id);
            logger.info("Odontologo eliminado "+odontologo);
        } else {
            throw new ResourceNotFoundException("El odontologo" +id+" no fue encontrado");
        }
    }

    @Override
    public List<Odontologo> buscarPorMatriculaONombre(String matricula, String nombre) {
        return iOdontologoRepository.findByMatriculaOrNombre(matricula, nombre);
    }

    @Override
    public List<Odontologo> buscarPorApellidoExacto(String apellido) {
        return iOdontologoRepository.findByApellidoEquals(apellido);
    }
}


