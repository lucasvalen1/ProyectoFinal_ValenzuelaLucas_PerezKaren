package com.dh.clinica.service;


import com.dh.clinica.dao.IDao;
import com.dh.clinica.model.Odontologo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService {
    private IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {

        this.odontologoIDao = odontologoIDao;
    }

    public Odontologo guardar(Odontologo odontologo){

        return odontologoIDao.guardar(odontologo);
    }

    public List<Odontologo> buscarTodos(){

        return odontologoIDao.buscarTodos();
    }

    public Odontologo buscarPorId(Integer id){

        return odontologoIDao.buscarPorId(id);
    }
    public void modificarOdontologo(Odontologo odontologo){

        odontologoIDao.modificar(odontologo);
    }
    public void eliminarOdontologo(Integer id){

        odontologoIDao.eliminar(id);
    }
}
