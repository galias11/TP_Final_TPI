package TestCajaBlanca;

import empresa.Empresa;
import empresa.Maquina;

public class FixtureMaquina
{
  private Maquina maquinaTest = new Maquina();

  public FixtureMaquina()
  {
    super();
  }

  public Maquina getMaquinaTest()
  {
    return maquinaTest;
  }

  public void setUp()
  {

  }

  public void tearDown()
  {
    maquinaTest = new Maquina();
  }
}
