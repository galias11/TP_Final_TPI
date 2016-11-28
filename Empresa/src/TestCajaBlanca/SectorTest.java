package TestCajaBlanca;

import empresa.EmpresaException;
import empresa.Operacion;

import org.junit.After;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public class SectorTest
{
  private FixtureSector fixture = new FixtureSector();

  public SectorTest()
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
  public void testSEC01A(){
      Operacion op = new Operacion(1, "Iniciar pedido.");
          try {
              fixture.getSectorTest().agregarPermiso(op);
          } catch (EmpresaException e) {
              fail("Tendria que agregar la operacion correctamente");
          }
          assertTrue("No se agrego la nueva operacion",
                 fixture.getSectorTest().getPermisos().containsKey(1));
  }
  
  
  
  @Test
  public void testSEC01B(){
      fixture.setUp01B();
      boolean exceptionError=false;
      Operacion op = new Operacion(1, "Iniciar pedido.");
      try {
          fixture.getSectorTest().agregarPermiso(op);
          fail("Se esperaba un error de excepcion");
      } 
      catch (EmpresaException e) {
            exceptionError=true;
        }
      if (!exceptionError)
              fail("Se esperaba un error de excepcion");
  }
  
  
  
}
