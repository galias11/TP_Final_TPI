package TestCajaBlanca;

import empresa.EmpresaException;
import empresa.Operacion;

import javax.swing.JOptionPane;

import org.junit.After;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public class PedidoTest
{
  private FixturePedido fixture = new FixturePedido();

  public PedidoTest()
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
  public void testPED02A(){
        String info="";
        info=fixture.getPedidoTest().listadoMateriales();
        assertTrue("No se  redacto el listado correctamente",
                 info=="");
  }
  
    @Test
    public void testPED02B(){
          String info="";
          fixture.setUp02B();
          info=fixture.getPedidoTest().listadoMateriales();
                  assertTrue("No se redacto el listado correctamente",info!="");
    }
  
  
}
