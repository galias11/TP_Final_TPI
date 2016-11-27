package TestCajaBlanca;

import empresa.EmpresaException;
import empresa.Maquina;
import empresa.Material;
import empresa.Pedido;

import java.util.GregorianCalendar;

public class FixturePedido
{
  private Pedido pedidoTest = new Pedido();

  public FixturePedido()
  {
    super();
  }

  public Pedido getPedidoTest()
  {
    return pedidoTest;
  }

  public void setUp()
  {
        Maquina maq= new Maquina(01,"Flipper");
        pedidoTest= new Pedido(maq, 5, new GregorianCalendar(2016,12,12));
  }

  public void tearDown()
  {
    pedidoTest = new Pedido();
  }
  
  public void setUp02B(){
      Material material= new Material(1, "Acero", 50.0);
        try {
            pedidoTest.getMaquina().agregarMaterial(material);
        } catch (EmpresaException e) {
        }
    }
  
  
  
}
