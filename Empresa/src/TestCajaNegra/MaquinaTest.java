package TestCajaNegra;

import empresa.EmpresaException;
import empresa.Maquina;
import empresa.Material;

import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Test;

public class MaquinaTest {
    private FixtureMaquina fixture = new FixtureMaquina();
    
    public MaquinaTest() {
    }
    
    @Before
    public void setUp(){
        fixture.setUp();
    }
    
    @After
    public void tearDown(){
        fixture.tearDown();
    }
    
    /*
     * A continuacion se codifican los test de unidad para la clase Maquina.
     * Para ver correlación metodo, clases testeadas, fixture ver archivo de tabla
     * fixtures.
     */
    @Test
    public void testMAQ01A(){
        fixture.setUpM01_A();
        try{
            fixture.getMaquinaTest().agregarMaterial(new Material(3, "Mat3", 10.0));
            assertTrue("MAQ01A: Tamaño listado materiales incorrecto",
                       fixture.getMaquinaTest().getListadoMateriales().size() == 3);
            assertTrue("MAQ01A: Elementos listado incorrectos",
                       fixture.getMaquinaTest().getListadoMateriales().containsKey(1) &&
                       fixture.getMaquinaTest().getListadoMateriales().containsKey(2) &&
                       fixture.getMaquinaTest().getListadoMateriales().containsKey(3));
            assertTrue("MAQ01A: Cantidades listado incorrectas",
                       fixture.getMaquinaTest().getListadoMateriales().get(1).getCantidad() == 35.0 &&
                       fixture.getMaquinaTest().getListadoMateriales().get(2).getCantidad() == 15.0 &&
                       fixture.getMaquinaTest().getListadoMateriales().get(3).getCantidad() == 10.0);
        } catch(EmpresaException ex){
            fail("MAQ01A: Exception inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testMAQ01B(){
        fixture.setUpM01_A();
        boolean assertError = false;
        try{
            fixture.getMaquinaTest().agregarMaterial(null);
        } catch(EmpresaException ex){
            fail("MAQ01B: Exception inesperada: " + ex.toString());
        } catch(AssertionError e){
            assertError = true;
            assertTrue("MAQ01B: Tamaño listado materiales incorrecto",
                       fixture.getMaquinaTest().getListadoMateriales().size() == 2);
            assertTrue("MAQ01B: Elementos listado incorrectos",
                       fixture.getMaquinaTest().getListadoMateriales().containsKey(1) &&
                       fixture.getMaquinaTest().getListadoMateriales().containsKey(2));
            assertTrue("MAQ01B: Cantidades listado incorrectas",
                       fixture.getMaquinaTest().getListadoMateriales().get(1).getCantidad() == 35.0 &&
                       fixture.getMaquinaTest().getListadoMateriales().get(2).getCantidad() == 15.0);
        }
        if(!assertError)
            fail("MAQ01B: Se esperaba error de assercion");
    }
    
    @Test
    public void testMAQ01C(){
        fixture.setUpM01_A();
        try{
            fixture.getMaquinaTest().agregarMaterial(new Material(2, "Mat2", 10.0));
            fail("MAQ01C: Se esperaba excepcion");
        } catch(EmpresaException ex){
            assertTrue("MAQ01C: Tamaño listado materiales incorrecto",
                       fixture.getMaquinaTest().getListadoMateriales().size() == 2);
            assertTrue("MAQ01C: Elementos listado incorrectos",
                       fixture.getMaquinaTest().getListadoMateriales().containsKey(1) &&
                       fixture.getMaquinaTest().getListadoMateriales().containsKey(2));
            assertTrue("MAQ01C: Cantidades listado incorrectas",
                       fixture.getMaquinaTest().getListadoMateriales().get(1).getCantidad() == 35.0 &&
                       fixture.getMaquinaTest().getListadoMateriales().get(2).getCantidad() == 15.0);
        }
    }
    
    @Test
    public void testMAQ01D(){
        fixture.setUpM01_B();
        try{
            fixture.getMaquinaTest().agregarMaterial(new Material(3, "Mat3", 10.0));
            assertTrue("MAQ01D: Tamaño listado materiales incorrecto",
                       fixture.getMaquinaTest().getListadoMateriales().size() == 1);
            assertTrue("MAQ01D: Elementos listado incorrectos",
                       fixture.getMaquinaTest().getListadoMateriales().containsKey(3));
            assertTrue("MAQ01D: Cantidades listado incorrectas",
                       fixture.getMaquinaTest().getListadoMateriales().get(3).getCantidad() == 10.0);
        } catch(EmpresaException ex){
            fail("MAQ01D: Exception inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testMAQ01E(){
        fixture.setUpM01_C();
        try{
            fixture.getMaquinaTest().agregarMaterial(new Material(2, "Mat2", 10.0));
            fail("MAQ01E: Se esperaba excepcion");
        } catch(EmpresaException ex){
            assertTrue("MAQ01E: Tamaño listado materiales incorrecto",
                       fixture.getMaquinaTest().getListadoMateriales().size() == 1);
            assertTrue("MAQ01E: Elementos listado incorrectos",
                       fixture.getMaquinaTest().getListadoMateriales().containsKey(2));
            assertTrue("MAQ01E: Cantidades listado incorrectas",
                       fixture.getMaquinaTest().getListadoMateriales().get(2).getCantidad() == 15.0);
        }
    }
    
    @Test
    public void testMAQ02A(){
        fixture.setUpM02_A();
        Maquina maq = new Maquina(200001, "Prueba");
        assertTrue("MAQ02A: No se registro codigo correctamente",
                   maq.getCodigo() == 200001);
        assertEquals("MAQ02A: No se registro descricion correctamente",
                     "Prueba", maq.getDescripcion());
        assertNotNull("MAQ02A: Receta nula",
                      maq.getListadoMateriales());
        assertTrue("MAQ02A: Receta no se inicializa correctamente",
                   maq.getListadoMateriales().size() == 0);
    }
    
    @Test
    public void testMAQ02B(){
        fixture.setUpM02_A();
        Maquina maq = new Maquina(1, "Prueba");
        assertTrue("MAQ02B: No se registro codigo correctamente",
                   maq.getCodigo() == 1);
        assertEquals("MAQ02B: No se registro descricion correctamente",
                     "Prueba", maq.getDescripcion());
        assertNotNull("MAQ02B: Receta nula",
                      maq.getListadoMateriales());
        assertTrue("MAQ02B: Receta no se inicializa correctamente",
                   maq.getListadoMateriales().size() == 0);
    }
    
    @Test
    public void testMAQ02C(){
        fixture.setUpM02_A();
        Maquina maq = new Maquina(999999, "Prueba");
        assertTrue("MAQ02C: No se registro codigo correctamente",
                   maq.getCodigo() == 999999);
        assertEquals("MAQ02C: No se registro descricion correctamente",
                     "Prueba", maq.getDescripcion());
        assertNotNull("MAQ02C: Receta nula",
                      maq.getListadoMateriales());
        assertTrue("MAQ02C: Receta no se inicializa correctamente",
                   maq.getListadoMateriales().size() == 0);
    }
    
    @Test
    public void testMAQ02D(){
        fixture.setUpM02_A();
        Maquina maq = new Maquina(200001, "P");
        assertTrue("MAQ02D: No se registro codigo correctamente",
                   maq.getCodigo() == 200001);
        assertEquals("MAQ02D: No se registro descricion correctamente",
                     "P", maq.getDescripcion());
        assertNotNull("MAQ02D: Receta nula",
                      maq.getListadoMateriales());
        assertTrue("MAQ02D: Receta no se inicializa correctamente",
                   maq.getListadoMateriales().size() == 0);
    }
    
    @Test
    public void testMAQ02E(){
        fixture.setUpM02_A();
        String desc = "";
        for(int i = 0; i < 100; i++)
            desc += "P";
        Maquina maq = new Maquina(200001, desc);
        assertTrue("MAQ02E: No se registro codigo correctamente",
                   maq.getCodigo() == 200001);
        assertEquals("MAQ02E: No se registro descricion correctamente",
                     desc, maq.getDescripcion());
        assertNotNull("MAQ02E: Receta nula",
                      maq.getListadoMateriales());
        assertTrue("MAQ02E: Receta no se inicializa correctamente",
                   maq.getListadoMateriales().size() == 0);
    }
    
    @Test
    public void testMAQ02F(){
        fixture.setUpM02_A();
        boolean assertError = false;
        try{
            Maquina maq = new Maquina(-11, "Prueba");
        } catch(AssertionError e){
            assertError = true;
        }
        if(!assertError)
            fail("MAQ02F: Se esperaba un error de assercion");
    }
    
    @Test
    public void testMAQ02G(){
        fixture.setUpM02_A();
        boolean assertError = false;
        try{
            Maquina maq = new Maquina(0, "Prueba");
        } catch(AssertionError e){
            assertError = true;
        }
        if(!assertError)
            fail("MAQ02G: Se esperaba un error de assercion");
    }
    
    @Test
    public void testMAQ02H(){
        fixture.setUpM02_A();
        boolean assertError = false;
        try{
            Maquina maq = new Maquina(2000010, "Prueba");
        } catch(AssertionError e){
            assertError = true;
        }
        if(!assertError)
            fail("MAQ02H: Se esperaba un error de assercion");
    }
    
    @Test
    public void testMAQ02I(){
        fixture.setUpM02_A();
        boolean assertError = false;
        try{
            Maquina maq = new Maquina(1000000, "Prueba");
        } catch(AssertionError e){
            assertError = true;
        }
        if(!assertError)
            fail("MAQ02I: Se esperaba un error de assercion");
    }
    
    @Test
    public void testMAQ02J(){
        fixture.setUpM02_A();
        boolean assertError = false;
        try{
            Maquina maq = new Maquina(200001, "");
        } catch(AssertionError e){
            assertError = true;
        }
        if(!assertError)
            fail("MAQ02J: Se esperaba un error de assercion");
    }
    
    @Test
    public void testMAQ02K(){
        fixture.setUpM02_A();
        String obs = "";
        for(int i = 0; i < 120; i++)
            obs += "P";
        boolean assertError = false;
        try{
            Maquina maq = new Maquina(200001, obs);
        } catch(AssertionError e){
            assertError = true;
        }
        if(!assertError)
            fail("MAQ02K: Se esperaba un error de assercion");
    }
    
    @Test
    public void testMAQ02L(){
        fixture.setUpM02_A();
        String obs = "";
        for(int i = 0; i < 101; i++)
            obs += "P";
        boolean assertError = false;
        try{
            Maquina maq = new Maquina(200001, obs);
        } catch(AssertionError e){
            assertError = true;
        }
        if(!assertError)
            fail("MAQ02L: Se esperaba un error de assercion");
    }
    
    @Test
    public void testMAQ02M(){
        fixture.setUpM02_A();
        boolean assertError = false;
        try{
            Maquina maq = new Maquina(200001, null);
        } catch(AssertionError e){
            assertError = true;
        }
        if(!assertError)
            fail("MAQ02M: Se esperaba un error de assercion");
    }
    
    @Test
    public void testMAQ03A(){
        fixture.setUpM03_A();
        try{
            fixture.getMaquinaTest().eliminarMaterial(3);
            assertTrue("MAQ03A: Cantidad elementos en receta incorrecta",
                       fixture.getMaquinaTest().getListadoMateriales().size() == 1);
            assertTrue("MAQ03A: Contenido receta incorrecto",
                       fixture.getMaquinaTest().getListadoMateriales().containsKey(1));
            assertTrue("MAQ03A: Cantidades incorrectas",
                       fixture.getMaquinaTest().getListadoMateriales().get(1).getCantidad() == 25.0);
        } catch(EmpresaException ex){
            fail("MAQ03A: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testMAQ03B(){
        fixture.setUpM03_A();
        try{
            fixture.getMaquinaTest().eliminarMaterial(2);
            fail("MAQ03B: Se esperaba excepcion");
        } catch(EmpresaException ex){
            assertTrue("MAQ03B: Cantidad elementos en receta incorrecta",
                       fixture.getMaquinaTest().getListadoMateriales().size() == 2);
            assertTrue("MAQ03B: Contenido receta incorrecto",
                       fixture.getMaquinaTest().getListadoMateriales().containsKey(1) &&
                       fixture.getMaquinaTest().getListadoMateriales().containsKey(3));
            assertTrue("MAQ03B: Cantidades incorrectas",
                       fixture.getMaquinaTest().getListadoMateriales().get(1).getCantidad() == 25.0 &&
                       fixture.getMaquinaTest().getListadoMateriales().get(3).getCantidad() == 40.0);
        }
    }
    
    @Test
    public void testMAQ03C(){
        fixture.setUpM03_B();
        try{
            fixture.getMaquinaTest().eliminarMaterial(3);
            assertTrue("MAQ03C: Cantidad elementos en receta incorrecta",
                       fixture.getMaquinaTest().getListadoMateriales().size() == 0);
        } catch(EmpresaException ex){
            fail("MAQ03C: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testMAQ04A(){
        fixture.setUpM04_A();
        try{
            fixture.getMaquinaTest().modificarCantidadMaterial(3, 20.0);
            assertTrue("MAQ04A: Cantidad de elementos receta incorrecta",
                       fixture.getMaquinaTest().getListadoMateriales().size() == 2);
            assertTrue("MAQ04A: Contenido receta incorrecto",
                       fixture.getMaquinaTest().getListadoMateriales().containsKey(2) &&
                       fixture.getMaquinaTest().getListadoMateriales().containsKey(3));
            assertTrue("MAQ04A: Cantidades receta incorrectas",
                       fixture.getMaquinaTest().getListadoMateriales().get(2).getCantidad() == 22.0 &&
                       fixture.getMaquinaTest().getListadoMateriales().get(3).getCantidad() == 20.0);
        } catch(EmpresaException ex){
            fail("MAQ04A: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testMAQ04B(){
        fixture.setUpM04_A();
        try{
            fixture.getMaquinaTest().modificarCantidadMaterial(3, 0.001);
            assertTrue("MAQ04B: Cantidad de elementos receta incorrecta",
                       fixture.getMaquinaTest().getListadoMateriales().size() == 2);
            assertTrue("MAQ04B: Contenido receta incorrecto",
                       fixture.getMaquinaTest().getListadoMateriales().containsKey(2) &&
                       fixture.getMaquinaTest().getListadoMateriales().containsKey(3));
            assertTrue("MAQ04B: Cantidades receta incorrectas",
                       fixture.getMaquinaTest().getListadoMateriales().get(2).getCantidad() == 22.0 &&
                       fixture.getMaquinaTest().getListadoMateriales().get(3).getCantidad() == 0.001);
        } catch(EmpresaException ex){
            fail("MAQ04A: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testMAQ04C(){
        fixture.setUpM04_A();
        try{
            fixture.getMaquinaTest().modificarCantidadMaterial(1, 20.0);
            fail("MAQ04C: Se esperaba una excepcion");
        } catch(EmpresaException ex){
            assertTrue("MAQ04C: Cantidad de elementos receta incorrecta",
                       fixture.getMaquinaTest().getListadoMateriales().size() == 2);
            assertTrue("MAQ04C: Contenido receta incorrecto",
                       fixture.getMaquinaTest().getListadoMateriales().containsKey(2) &&
                       fixture.getMaquinaTest().getListadoMateriales().containsKey(3));
            assertTrue("MAQ04C: Cantidades receta incorrectas",
                       fixture.getMaquinaTest().getListadoMateriales().get(2).getCantidad() == 22.0 &&
                       fixture.getMaquinaTest().getListadoMateriales().get(3).getCantidad() == 60.0);
        }
    }
    
    @Test
    public void testMAQ04D(){
        fixture.setUpM04_A();
        boolean assertError = false;
        try{
            fixture.getMaquinaTest().modificarCantidadMaterial(1, -20.0);
        } catch(EmpresaException ex){
            fail("MAQ04D: Excepcion inesperada: " + ex.toString());
        } catch(AssertionError e){
            assertError = true;
            assertTrue("MAQ04D: Cantidad de elementos receta incorrecta",
                       fixture.getMaquinaTest().getListadoMateriales().size() == 2);
            assertTrue("MAQ04D: Contenido receta incorrecto",
                       fixture.getMaquinaTest().getListadoMateriales().containsKey(2) &&
                       fixture.getMaquinaTest().getListadoMateriales().containsKey(3));
            assertTrue("MAQ04D: Cantidades receta incorrectas",
                       fixture.getMaquinaTest().getListadoMateriales().get(2).getCantidad() == 22.0 &&
                       fixture.getMaquinaTest().getListadoMateriales().get(3).getCantidad() == 60.0);
        }
        if(!assertError)
            fail("MAQ04D: Se esperaba un error de assercion");
    }
    
    @Test
    public void testMAQ04E(){
        fixture.setUpM04_A();
        boolean assertError = false;
        try{
            fixture.getMaquinaTest().modificarCantidadMaterial(1, 0.0);
        } catch(EmpresaException ex){
            fail("MAQ04E: Excepcion inesperada: " + ex.toString());
        } catch(AssertionError e){
            assertError = true;
            assertTrue("MAQ04E: Cantidad de elementos receta incorrecta",
                       fixture.getMaquinaTest().getListadoMateriales().size() == 2);
            assertTrue("MAQ04E: Contenido receta incorrecto",
                       fixture.getMaquinaTest().getListadoMateriales().containsKey(2) &&
                       fixture.getMaquinaTest().getListadoMateriales().containsKey(3));
            assertTrue("MAQ04E: Cantidades receta incorrectas",
                       fixture.getMaquinaTest().getListadoMateriales().get(2).getCantidad() == 22.0 &&
                       fixture.getMaquinaTest().getListadoMateriales().get(3).getCantidad() == 60.0);
        }
        if(!assertError)
            fail("MAQ04E: Se esperaba un error de assercion");
    }
    
    @Test
    public void testMAQ04F(){
        fixture.setUpM04_B();
        try{
            fixture.getMaquinaTest().modificarCantidadMaterial(3, 20.0);
            assertTrue("MAQ04F: Cantidad de elementos receta incorrecta",
                       fixture.getMaquinaTest().getListadoMateriales().size() == 2);
            assertTrue("MAQ04F: Contenido receta incorrecto",
                       fixture.getMaquinaTest().getListadoMateriales().containsKey(2) &&
                       fixture.getMaquinaTest().getListadoMateriales().containsKey(3));
            assertTrue("MAQ04F: Cantidades receta incorrectas",
                       fixture.getMaquinaTest().getListadoMateriales().get(2).getCantidad() == 22.0 &&
                       fixture.getMaquinaTest().getListadoMateriales().get(3).getCantidad() == 20.0);
        } catch(EmpresaException ex){
            fail("MAQ04F: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testMAQ04G(){
        fixture.setUpM04_C();
        try{
            fixture.getMaquinaTest().modificarCantidadMaterial(3, 20.0);
            assertTrue("MAQ04G: Cantidad de elementos receta incorrecta",
                       fixture.getMaquinaTest().getListadoMateriales().size() == 1);
            assertTrue("MAQ04G: Contenido receta incorrecto",
                       fixture.getMaquinaTest().getListadoMateriales().containsKey(3));
            assertTrue("MAQ04G: Cantidades receta incorrectas",
                       fixture.getMaquinaTest().getListadoMateriales().get(3).getCantidad() == 20.0);
        } catch(EmpresaException ex){
            fail("MAQ04G: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testMAQ04H(){
        fixture.setUpM04_D();
        try{
            fixture.getMaquinaTest().modificarCantidadMaterial(1, 20.0);
            fail("MAQ04H: Se esperaba una excepcion");
        } catch(EmpresaException ex){
            assertTrue("MAQ04H: Cantidad de elementos receta incorrecta",
                       fixture.getMaquinaTest().getListadoMateriales().size() == 0);
        }
    }
}
