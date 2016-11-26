package TestCajaBlanca;

import empresa.Maquina;
import empresa.Material;

public class FixtureMaterial
{
  private Material materialTest = new Material();

  public FixtureMaterial()
  {
    super();
  }

  public Material getMaterialTest()
  {
    return materialTest;
  }

  public void setUp()
  {

  }

  public void tearDown()
  {
    materialTest = new Material();
  }
}
