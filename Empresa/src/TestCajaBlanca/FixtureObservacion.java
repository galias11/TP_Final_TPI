package TestCajaBlanca;

import empresa.Observacion;

import java.util.GregorianCalendar;

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
        observacionTest = new Observacion(Observacion.TEMA_INSUMOS,1,"Faltan insumos");
  }

  public void tearDown()
  {
    observacionTest = new Observacion();
  }
  
  public void setUp01C(){
      observacionTest.setFecha(new GregorianCalendar(2016,12,12));
  }
  
}
