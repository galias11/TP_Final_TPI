package TestCajaBlanca;

import empresa.Observacion;

public class FixtureObservacion
{
  private Observacion observacionTest = new Observacion();

  public FixtureObservacion()
  {
    super();
  }

  public Observacion getObservacionTest()
  {
    return observacionTest;
  }

  public void setUp()
  {

  }

  public void tearDown()
  {
    observacionTest = new Observacion();
  }
}
