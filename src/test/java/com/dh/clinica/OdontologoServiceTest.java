package com.dh.clinica;
import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.service.impl.OdontologoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
class OdontologoServiceTest {
    @Autowired

    OdontologoService odontologoService;
    Odontologo odontologo;

    @BeforeEach
    void crearOdontologo(){
        odontologo= new Odontologo();
        odontologo.setApellido("Martinez");
        odontologo.setNombre("Jose");
        odontologo.setMatricula("A632");
        odontologo = odontologoService.guardarOdontologo(odontologo);

}
    @Test
    @DisplayName("Testear que un odontologo se guarde")
    void caso1(){


        assertNotNull(odontologo.getId());
    }

/*    static final Logger logger = LoggerFactory.getLogger(OdontologoServiceTest.class);
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

*/
}