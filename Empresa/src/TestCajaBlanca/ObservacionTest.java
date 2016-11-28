package TestCajaBlanca;

import empresa.EmpresaException;
import empresa.Observacion;
import empresa.Operacion;

import java.util.GregorianCalendar;

import org.junit.After;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public class ObservacionTest
{
  private FixtureObservacion fixture = new FixtureObservacion();

  public ObservacionTest()
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
  public void testOBS01A(){
        int comparacion=0;
        comparacion=fixture.getObservacionTest().compareTo("Nueva observacion");
        assertTrue("No se comparo correctamente",
             comparacion==0);
  }
  
  
  @Test
  public void testOBS01B(){
      int comparacion=0;
      Observacion o=new Observacion(Observacion.TEMA_OTROS,1,"Faltan insumos");
      comparacion=fixture.getObservacionTest().compareTo(o);
      assertTrue("No se comparo correctamente",
           comparacion==-1);
  }
  
    @Test
    public void testOBS01C(){
        fixture.setUp01C();
        int comparacion=0;
        Observacion o=new Observacion(Observacion.TEMA_INSUMOS,1,"Faltan insumos");
        o.setFecha(new GregorianCalendar(2016,12,12));
        comparacion=fixture.getObservacionTest().compareTo(o);
        assertTrue("No se comparo correctamente",
             comparacion==0);
    }
  
  
}
