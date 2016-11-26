package TestCajaBlanca;

import org.junit.After;
import org.junit.Before;

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
}
