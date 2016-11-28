package TestCajaBlanca;

import empresa.EmpresaException;
import empresa.Operacion;
import empresa.Sector;

import java.util.HashMap;

public class FixtureSector
{
  private Sector sectorTest = new Sector();

  public FixtureSector()
  {
    super();
  }

  public Sector getSectorTest()
  {
    return sectorTest;
  }

  public void setUp()
  {
      sectorTest = new Sector("Produccion");
  }

  public void tearDown()
  {
    sectorTest = new Sector();
  }
  
  
    public void setUp01B(){
        Operacion op = new Operacion(1, "Iniciar pedido.");
        try {
            sectorTest.agregarPermiso(op);
        } catch (EmpresaException e) {
        }
    }
  
  
}
