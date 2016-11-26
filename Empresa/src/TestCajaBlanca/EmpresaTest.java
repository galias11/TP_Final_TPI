package TestCajaBlanca;

import org.junit.After;
import org.junit.Before;

public class EmpresaTest
{
  private FixtureEmpresa fixture = new FixtureEmpresa();

  public EmpresaTest()
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
