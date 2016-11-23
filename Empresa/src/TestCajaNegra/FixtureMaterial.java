package TestCajaNegra;

import empresa.Maquina;
import empresa.Material;

import java.util.HashMap;

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
  
  public void setUp(){
    materialTest.setCodigoMaterial(401);
    materialTest.setDescripcion("Madera");
    materialTest.setCantidad(500.0);
  }
  
  public void tearDown(){
    materialTest = new Material();
  }
  
  public void setUpM01(){
      
  }
  
  public void setUpM02(){
      
  }
  
  public void setUpM03(){
      
  }
  
  public void setUpM04(){
      
  }
}
