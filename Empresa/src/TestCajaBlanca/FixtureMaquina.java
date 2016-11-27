package TestCajaBlanca;

import empresa.EmpresaException;
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
  
  public void setUp(){
      maquinaTest = new Maquina(15, "Maquina1");
  }
  
  
  public void setUp01B(){
      Material material= new Material(1, "Acero", 50.0);
        try {
            maquinaTest.agregarMaterial(material);
        } catch (EmpresaException e) {
        }
    }
  
  

  public void setUp02()
  {
    maquinaTest.setListadoMateriales(new HashMap<Integer, Material>());
    maquinaTest.getListadoMateriales().put(401, new Material(401,"Madera",500));
  }

  public void tearDown()
  {
    maquinaTest = new Maquina();
  }
  
  
  
  
  
}
