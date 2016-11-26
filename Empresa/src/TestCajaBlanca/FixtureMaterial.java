package TestCajaBlanca;

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
    materialTest.setCodigoMaterial(401);
    materialTest.setDescripcion("Madera");
    materialTest.setCantidad(500.0);
  }

  public void tearDown()
  {
    materialTest = new Material();
  }
}
