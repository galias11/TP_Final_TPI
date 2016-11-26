package TestCajaBlanca;

import org.junit.After;
import org.junit.Before;

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
}
