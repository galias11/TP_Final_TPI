package TestCajaBlanca;

import org.junit.After;
import org.junit.Before;

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
}
