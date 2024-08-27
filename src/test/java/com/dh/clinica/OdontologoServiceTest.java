package com.dh.clinica;


import com.dh.clinica.dao.impl.OdontologoDaoH2;
import com.dh.clinica.model.Odontologo;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.service.OdontologoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;


class OdontologoServiceTest {
    static final Logger logger = LoggerFactory.getLogger(OdontologoServiceTest.class);
    OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());
    @BeforeAll
    static void crearTablas(){
        Connection connection = null;
        try{
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./clinica;INIT=RUNSCRIPT FROM 'create.sql'", "sa","sa");
        }catch (Exception e){
            logger.error(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }
        }
    }


    @Test
    @DisplayName("Testear que un odontologo se guarde")
    void caso1(){

        Odontologo odontologo = new Odontologo("A632","JOSE","MARTINEZ");
        Odontologo odontologo1 = odontologoService.guardar(odontologo);
        assertNotNull(odontologo1.getId());
    }

    @Test
    @DisplayName("Testear que un odontologo pueda ser obtenido cuando se envia el id")
    void caso3(){
        //dado
        Integer id = 1;
        // cuando
        Odontologo odontologo = odontologoService.buscarPorId(id);
        // entonces
        assertEquals(id, odontologo.getId());
    }

    @Test
    @DisplayName("Testear que traiga todos los odontologos guardados en la memoria")
    void caso2(){

        List<Odontologo> odontologos = new ArrayList<>();
        odontologos = odontologoService.buscarTodos();
        assertFalse(odontologos.isEmpty());


    }


}