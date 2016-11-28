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
    Maquina maq = new Maquina(01, "Flipper");
    pedidoTest = new Pedido(maq, 5, new GregorianCalendar(2016, 12, 12));
  }

  public void tearDown()
  {
    pedidoTest = new Pedido();
  }

  public void setUpM01A()
  {
    pedidoTest.getMaquina().getListadoMateriales().put(401, new Material(401, "Madera", 500));
  }

  public void setUpM02B()
  {
    Material material = new Material(1, "Acero", 50.0);
    try
    {
      pedidoTest.getMaquina().agregarMaterial(material);
    }
    catch (EmpresaException e)
    {
    }
  }

  public void setUpM03A()
  {
    pedidoTest.setFechaPedido(new GregorianCalendar(2016, 12, 12));
    pedidoTest.setFechaEntrega(new GregorianCalendar(2016, 12, 12));
    pedidoTest.setFechaPropProduccion(new GregorianCalendar(2016, 12, 12));
    pedidoTest.setFechaAceptacion(new GregorianCalendar(2016, 12, 12));
    pedidoTest.setFechaDefinitiva(new GregorianCalendar(2016, 12, 12));
    pedidoTest.setEstado(2);
  }

  public void setUpM03B()
  {
    pedidoTest.setFechaPedido(new GregorianCalendar(2016, 12, 12));
    pedidoTest.setFechaEntrega(new GregorianCalendar(2016, 12, 12));
    pedidoTest.setFechaPropProduccion(new GregorianCalendar(2016, 12, 12));
    pedidoTest.setFechaAceptacion(new GregorianCalendar(2016, 12, 12));
    pedidoTest.setFechaDefinitiva(new GregorianCalendar(2016, 12, 12));
    pedidoTest.setEstado(0);
  }

  public void setUpM03C()
  {
    pedidoTest.setFechaPedido(new GregorianCalendar(2016, 12, 12));
    pedidoTest.setFechaEntrega(new GregorianCalendar(2016, 12, 12));
    pedidoTest.setFechaPropProduccion(new GregorianCalendar(2016, 12, 12));
    pedidoTest.setFechaAceptacion(new GregorianCalendar(2016, 12, 12));
    pedidoTest.setFechaDefinitiva(new GregorianCalendar(2016, 12, 12));
    pedidoTest.setEstado(1);
  }

  public void setUpM03D()
  {
    pedidoTest.setFechaPedido(new GregorianCalendar(2016, 12, 12));
    pedidoTest.setFechaEntrega(new GregorianCalendar(2016, 12, 12));
    pedidoTest.setFechaPropProduccion(new GregorianCalendar(2016, 12, 12));
    pedidoTest.setFechaAceptacion(new GregorianCalendar(2016, 12, 12));
    pedidoTest.setFechaDefinitiva(new GregorianCalendar(2016, 12, 12));
    pedidoTest.setEstado(3);
  }

  public void setUpM03E()
  {
    pedidoTest.setFechaPedido(new GregorianCalendar(2016, 12, 12));
    pedidoTest.setFechaEntrega(new GregorianCalendar(2016, 12, 12));
    pedidoTest.setFechaPropProduccion(new GregorianCalendar(2016, 12, 12));
    pedidoTest.setFechaAceptacion(new GregorianCalendar(2016, 12, 12));
    pedidoTest.setFechaDefinitiva(new GregorianCalendar(2016, 12, 12));
    pedidoTest.setEstado(4);
  }

  public void setUpM03F()
  {
    pedidoTest.setFechaPedido(null);
    pedidoTest.setFechaEntrega(null);
    pedidoTest.setFechaPropProduccion(null);
    pedidoTest.setFechaAceptacion(null);
    pedidoTest.setFechaDefinitiva(null);
    pedidoTest.setEstado(2);
  }

  public void setUpM03G()
  {
    pedidoTest.setFechaPedido(null);
    pedidoTest.setFechaEntrega(null);
    pedidoTest.setFechaPropProduccion(null);
    pedidoTest.setFechaAceptacion(null);
    pedidoTest.setFechaDefinitiva(null);
    pedidoTest.setEstado(0);
  }

  public void setUpM03H()
  {
    pedidoTest.setFechaPedido(null);
    pedidoTest.setFechaEntrega(null);
    pedidoTest.setFechaPropProduccion(null);
    pedidoTest.setFechaAceptacion(null);
    pedidoTest.setFechaDefinitiva(null);
    pedidoTest.setEstado(1);
  }

  public void setUpM03I()
  {
    pedidoTest.setFechaPedido(null);
    pedidoTest.setFechaEntrega(null);
    pedidoTest.setFechaPropProduccion(null);
    pedidoTest.setFechaAceptacion(null);
    pedidoTest.setFechaDefinitiva(null);
    pedidoTest.setEstado(3);
  }

  public void setUpM03J()
  {
    pedidoTest.setFechaPedido(null);
    pedidoTest.setFechaEntrega(null);
    pedidoTest.setFechaPropProduccion(null);
    pedidoTest.setFechaAceptacion(null);
    pedidoTest.setFechaDefinitiva(null);
    pedidoTest.setEstado(4);
  }
}
