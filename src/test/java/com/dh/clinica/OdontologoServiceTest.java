package com.dh.clinica;


class OdontologoServiceTest {
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

*/
}