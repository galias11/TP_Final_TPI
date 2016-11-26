package TestCajaBlanca;

import org.junit.After;
import org.junit.Before;

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
}
