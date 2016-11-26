package TestCajaBlanca;

import empresa.Pedido;

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

  }

  public void tearDown()
  {
    pedidoTest = new Pedido();
  }
}
