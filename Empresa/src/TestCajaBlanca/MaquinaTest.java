package TestCajaBlanca;

import empresa.EmpresaException;

import empresa.Material;

import empresa.Operacion;

import org.junit.After;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public class MaquinaTest
{
  private FixtureMaquina fixture = new FixtureMaquina();

  public MaquinaTest()
  {
    super();
  }

  @Before
  public void setUp()
  {
    fixture.setUp();
  }

  @After
  public void tearDown()
  {
    fixture.tearDown();
  }
  
  
  @Test
  public void testMAQ01A(){
      Material material= new Material(1, "Acero", 50.0);
      try {
          fixture.getMaquinaTest().agregarMaterial(material);
      } catch (EmpresaException e) {
          fail("Tendria que agregar el material correctamente");
      }
      assertTrue("No se agrego el nuevo material",
             fixture.getMaquinaTest().getListadoMateriales().containsKey(1));
  }
  
  @Test
  public void testMAQ01B(){
      fixture.setUp01B();
      boolean assertError=false;
      Material material= new Material(1, "Acero", 50.0);
      try {
          fixture.getMaquinaTest().agregarMaterial(material);
      }
      catch(AssertionError e){
          assertError=true;
      } catch (EmpresaException e) {
          assertError=true;
      }
      if (!assertError)
              fail("Se esperaba un error de asercion");
  }
  
  
  
  
  
  
  @Test
  public void testMAQ02A()
  {
      fixture.setUp02();
    try
    {
      fixture.getMaquinaTest().modificarCantidadMaterial(401, 300);
      assertTrue("MAQ02A: No se registro la cantidad correctamente",fixture.getMaquinaTest().getListadoMateriales().get(401).getCantidad()==300);
    }
    catch (EmpresaException e)
    {
      fail("MAQ02A: Excepcion inesperada: " + e.toString());
    }
  }
  
  @Test
  public void testMAQ02B()
  {
      fixture.setUp02();
    try
    {
      fixture.getMaquinaTest().modificarCantidadMaterial(402, 300);
      fail("MAQ02B: Se esperaba una excepcion.");
    }
    catch (EmpresaException e)
    {
      assertTrue("MAQ02B: La cantidad del material ha sido modificada.",fixture.getMaquinaTest().getListadoMateriales().get(401).getCantidad()==500);
    }
  }
  
  
  @Test
  public void testMAQ03A(){
      fixture.setUp01B();
      Material material= new Material(1, "Acero", 50.0);
      try {
          fixture.getMaquinaTest().eliminarMaterial(material.getCodigoMaterial());
      } catch (EmpresaException e) {
          fail("Tendria que eliminar el material correctamente");
      }
      assertTrue("No se elimino material",
             !fixture.getMaquinaTest().getListadoMateriales().containsKey(1));
  }
  
  @Test
  public void testMAQ03B(){
      boolean assertError=false;
      Material material= new Material(1, "Acero", 50.0);
      try {
          fixture.getMaquinaTest().eliminarMaterial(material.getCodigoMaterial());
      }
      catch(AssertionError e){
          assertError=true;
      } catch (EmpresaException e) {
          assertError=true;
      }
      if (!assertError)
              fail("Se esperaba un error de asercion");
  }
  
  
  
}
