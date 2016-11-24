package TestCajaNegra;

import empresa.EmpresaException;
import empresa.Maquina;
import empresa.Material;
import empresa.Observacion;
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
    pedidoTest = new Pedido(new Maquina(100004, "Flipper"), 500, new GregorianCalendar(2017, 11, 15));
  }

  public void tearDown()
  {
    pedidoTest = new Pedido();
  }

  public void setUpM01()
  {
    pedidoTest.setUltPedido(104);
  }

  public void setUpM02A()
  {

  }

  public void setUpM02B()
  {
    try
    {
      pedidoTest.getMaquina().agregarMaterial(new Material(401, "Madera", 2));
    }
    catch (EmpresaException e)
    {
    }
  }

  public void setUpM02C()
  {
    try
    {
      pedidoTest.getMaquina().agregarMaterial(new Material(401, "Madera", 2));
      pedidoTest.getMaquina().agregarMaterial(new Material(402, "Madera", 3));
      pedidoTest.getMaquina().agregarMaterial(new Material(403, "Madera", 50));
    }
    catch (EmpresaException e)
    {
    }
  }

  public void setUpM03()
  {

  }

  public void setUpM03C()
  {
    pedidoTest.setFechaPropProduccion(new GregorianCalendar(2017, 11, 25));
    pedidoTest.setEstado(Pedido.EN_EVALUACION);
  }

  public void setUpM04()
  {
    pedidoTest.setFechaPropProduccion(new GregorianCalendar(2017, 11, 25));
    pedidoTest.setEstado(Pedido.EN_EVALUACION);
    pedidoTest.setUltLote(5);
  }

  public void setUpM04C()
  {
    pedidoTest.setUltLote(5);
  }

  public void setUpM05A()
  {
     
  }

  public void setUpM05B()
  {
    pedidoTest.setFechaPropProduccion(new GregorianCalendar(2017, 11, 25));
    pedidoTest.setEstado(Pedido.EN_EVALUACION);
  }

  public void setUpM05C()
  {
    pedidoTest.setFechaPropProduccion(new GregorianCalendar(2017, 11, 25));
    pedidoTest.setFechaDefinitiva(new GregorianCalendar(2017, 11, 25));
    pedidoTest.setFechaAceptacion(new GregorianCalendar(2017, 11, 25));
    pedidoTest.setUltLote(5);
    pedidoTest.setEstado(Pedido.ACEPTADO);
  }
  
  public void setUpM05D(){
    Observacion obs = new Observacion("OTROS", 2, "Prueba");
    pedidoTest.setEstado(Pedido.CANCELADO);
    pedidoTest.getListaObservaciones().add(obs);
  }

  public void setUpM06A()
  {
    pedidoTest.setFechaPropProduccion(new GregorianCalendar(2017, 11, 25));
    pedidoTest.setEstado(Pedido.EN_EVALUACION);
    pedidoTest.getListaObservaciones().add(new Observacion("INSUMOS", 1, "Observacion insumos"));
    pedidoTest.getListaObservaciones().add(new Observacion("OTROS", 1, "Otra observacion"));
  }

  public void setUpM06B()
  {
    pedidoTest.setFechaPropProduccion(new GregorianCalendar(2017, 11, 25));
    pedidoTest.setFechaDefinitiva(new GregorianCalendar(2017, 11, 25));
    pedidoTest.setFechaAceptacion(new GregorianCalendar(2017, 11, 25));
    pedidoTest.setUltLote(5);
    pedidoTest.setEstado(Pedido.ACEPTADO);
  }

  public void setUpM06C()
  {
    pedidoTest.setFechaPropProduccion(new GregorianCalendar(2017, 11, 25));
    pedidoTest.setEstado(Pedido.EN_EVALUACION);
    pedidoTest.getListaObservaciones().add(new Observacion("INSUMOS", 1, "Observacion insumos"));
    pedidoTest.getListaObservaciones().add(new Observacion("OTROS", 1, "Otra observacion"));
  }

  public void setUpM06D()
  {
    pedidoTest.setFechaPropProduccion(new GregorianCalendar(2017, 11, 25));
    pedidoTest.setEstado(Pedido.EN_EVALUACION);
    pedidoTest.getListaObservaciones().add(new Observacion("INSUMOS", 1, "Observacion insumos"));
    pedidoTest.getListaObservaciones().add(new Observacion("OTROS", 1, "Otra observacion"));
    pedidoTest.getListaObservaciones().add(new Observacion("FECHAS", 1, "Modificar fecha"));
  }
  

    public void setUpM07A()
    {

    }

    public void setUpM07B()
    {
        pedidoTest.getMaquina().getListadoMateriales().put(401, new Material(401, "Madera", 2));
    }

    public void setUpM07C()
    {
        pedidoTest.getMaquina().getListadoMateriales().put(401, new Material(401, "Madera", 2));
        pedidoTest.getMaquina().getListadoMateriales().put(402, new Material(402, "Metal", 3));
        pedidoTest.getMaquina().getListadoMateriales().put(403, new Material(403, "Clavos", 50));
    }
}
