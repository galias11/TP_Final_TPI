package TestCajaBlanca;

import empresa.Maquina;
import empresa.Material;

import java.util.HashMap;

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
    maquinaTest.setCodigo(15);
    maquinaTest.setDescripcion("Maq1");
    maquinaTest.setListadoMateriales(new HashMap<Integer, Material>());
    maquinaTest.getListadoMateriales().put(401, new Material(401,"Madera",500));
  }

  public void tearDown()
  {
    maquinaTest = new Maquina();
  }
  
}
