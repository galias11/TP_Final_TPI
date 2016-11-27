package TestCajaBlanca;

import empresa.EmpresaException;
import empresa.Material;
import empresa.Operacion;

import java.text.SimpleDateFormat;

import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.swing.JOptionPane;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PedidoTest
{
  private FixturePedido fixture = new FixturePedido();

  public PedidoTest()
  {
    super();
  }

  @Before
  public void setUp()
  {
    fixture.setUp();
  }

  @After
  public void tearDown()
  {
    fixture.tearDown();
  }
  
  @Test
  public void testPED01A()
  {
    fixture.setUpM01A();
    HashMap<Integer, Material> necesidad = new HashMap<Integer, Material>();
    necesidad = fixture.getPedidoTest().materialesNecesarios();
    assertFalse("PED01A: No se registro el material", necesidad.isEmpty());
    assertTrue("PED01A: No se registro la cantidad del material correctamente", necesidad.get(401).getCantidad()==2500);
  }
  
  @Test
  public void testPED01B()
  {
    HashMap<Integer, Material> necesidad = new HashMap<Integer, Material>();
    necesidad = fixture.getPedidoTest().materialesNecesarios();
    assertTrue("PED01B: Se registro un material", necesidad.isEmpty());
  }
  
  @Test
  public void testPED02A()
  {
    String info = "";
    info = fixture.getPedidoTest().listadoMateriales();
    assertTrue("PED02A: No se  redacto el listado correctamente", info == "");
  }

  @Test
  public void testPED02B()
  {
    String info = "";
    fixture.setUpM02B();
    info = fixture.getPedidoTest().listadoMateriales();
    assertTrue("PED02B: No se redacto el listado correctamente", info != "");
  }
  
  @Test
  public void testPED03A()
  {
    fixture.setUpM03A();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    String strMaquina = String.format("TIP%06d  --  %s", fixture.getPedidoTest().getMaquina().getCodigo(),fixture.getPedidoTest().getMaquina().getDescripcion());
    String fecha = sdf.format((new GregorianCalendar(2016, 12, 12)).getTime());
    String strEstado = "ACEPTADO";
    String str = String.format("Numero de pedido: PED%06d        " +
        "Fecha de pedido: %s" + System.lineSeparator() +
        "Tipo de máquina: %-75.75s             Cantidad: %03d" + System.lineSeparator() +
        "Fecha de entrega solicitada por ventas: %-10.10s" + System.lineSeparator() +
        "Fecha propuesta por Producción: %-10.10s" + System.lineSeparator() +
        "Fecha aceptado: %-10.10s          Fecha definitiva: %-10.10s" + System.lineSeparator() +
        "Estado: %-15.15s" + System.lineSeparator() +
        "Número de lote: LOT%6.6s", fixture.getPedidoTest().getNroPedido(), fecha, strMaquina,
        fixture.getPedidoTest().getCantidad(), fecha, fecha, fecha, fecha, 
        strEstado, (fixture.getPedidoTest().getNroLote() == -1 ? "" : String.format("%06d", fixture.getPedidoTest().getNroLote())));
    assertTrue("PED03A: No se redacto la descripcion correctamente", str==fixture.getPedidoTest().toString());
  }

  @Test
  public void testPED03B()
  {
    fixture.setUpM03B();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    String strMaquina = String.format("TIP%06d  --  %s", fixture.getPedidoTest().getMaquina().getCodigo(),fixture.getPedidoTest().getMaquina().getDescripcion());
    String fecha = sdf.format((new GregorianCalendar(2016, 12, 12)).getTime());
    String strEstado = "INICIADO";
    String str = String.format("Numero de pedido: PED%06d        " +
        "Fecha de pedido: %s" + System.lineSeparator() +
        "Tipo de máquina: %-75.75s             Cantidad: %03d" + System.lineSeparator() +
        "Fecha de entrega solicitada por ventas: %-10.10s" + System.lineSeparator() +
        "Fecha propuesta por Producción: %-10.10s" + System.lineSeparator() +
        "Fecha aceptado: %-10.10s          Fecha definitiva: %-10.10s" + System.lineSeparator() +
        "Estado: %-15.15s" + System.lineSeparator() +
        "Número de lote: LOT%6.6s", fixture.getPedidoTest().getNroPedido(), fecha, strMaquina,
        fixture.getPedidoTest().getCantidad(), fecha, fecha, fecha, fecha, 
        strEstado, (fixture.getPedidoTest().getNroLote() == -1 ? "" : String.format("%06d", fixture.getPedidoTest().getNroLote())));
    assertTrue("PED03B: No se redacto la descripcion correctamente", str==fixture.getPedidoTest().toString());
  }
  
  @Test
  public void testPED03C()
  {
    fixture.setUpM03C();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    String strMaquina = String.format("TIP%06d  --  %s", fixture.getPedidoTest().getMaquina().getCodigo(),fixture.getPedidoTest().getMaquina().getDescripcion());
    String fecha = sdf.format((new GregorianCalendar(2016, 12, 12)).getTime());
    String strEstado = "EN EVALUACION";
    String str = String.format("Numero de pedido: PED%06d        " +
        "Fecha de pedido: %s" + System.lineSeparator() +
        "Tipo de máquina: %-75.75s             Cantidad: %03d" + System.lineSeparator() +
        "Fecha de entrega solicitada por ventas: %-10.10s" + System.lineSeparator() +
        "Fecha propuesta por Producción: %-10.10s" + System.lineSeparator() +
        "Fecha aceptado: %-10.10s          Fecha definitiva: %-10.10s" + System.lineSeparator() +
        "Estado: %-15.15s" + System.lineSeparator() +
        "Número de lote: LOT%6.6s", fixture.getPedidoTest().getNroPedido(), fecha, strMaquina,
        fixture.getPedidoTest().getCantidad(), fecha, fecha, fecha, fecha, 
        strEstado, (fixture.getPedidoTest().getNroLote() == -1 ? "" : String.format("%06d", fixture.getPedidoTest().getNroLote())));
    assertTrue("PED03C: No se redacto la descripcion correctamente", str==fixture.getPedidoTest().toString());
  }
  
  @Test
  public void testPED03D()
  {
    fixture.setUpM03D();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    String strMaquina = String.format("TIP%06d  --  %s", fixture.getPedidoTest().getMaquina().getCodigo(),fixture.getPedidoTest().getMaquina().getDescripcion());
    String fecha = sdf.format((new GregorianCalendar(2016, 12, 12)).getTime());
    String strEstado = "CANCELADO";
    String str = String.format("Numero de pedido: PED%06d        " +
        "Fecha de pedido: %s" + System.lineSeparator() +
        "Tipo de máquina: %-75.75s             Cantidad: %03d" + System.lineSeparator() +
        "Fecha de entrega solicitada por ventas: %-10.10s" + System.lineSeparator() +
        "Fecha propuesta por Producción: %-10.10s" + System.lineSeparator() +
        "Fecha aceptado: %-10.10s          Fecha definitiva: %-10.10s" + System.lineSeparator() +
        "Estado: %-15.15s" + System.lineSeparator() +
        "Número de lote: LOT%6.6s", fixture.getPedidoTest().getNroPedido(), fecha, strMaquina,
        fixture.getPedidoTest().getCantidad(), fecha, fecha, fecha, fecha, 
        strEstado, (fixture.getPedidoTest().getNroLote() == -1 ? "" : String.format("%06d", fixture.getPedidoTest().getNroLote())));
    assertTrue("PED03D: No se redacto la descripcion correctamente", str==fixture.getPedidoTest().toString());
  }
  
  @Test
  public void testPED03E()
  {
    fixture.setUpM03E();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    String strMaquina = String.format("TIP%06d  --  %s", fixture.getPedidoTest().getMaquina().getCodigo(),fixture.getPedidoTest().getMaquina().getDescripcion());
    String fecha = sdf.format((new GregorianCalendar(2016, 12, 12)).getTime());
    String strEstado = "";
    String str = String.format("Numero de pedido: PED%06d        " +
        "Fecha de pedido: %s" + System.lineSeparator() +
        "Tipo de máquina: %-75.75s             Cantidad: %03d" + System.lineSeparator() +
        "Fecha de entrega solicitada por ventas: %-10.10s" + System.lineSeparator() +
        "Fecha propuesta por Producción: %-10.10s" + System.lineSeparator() +
        "Fecha aceptado: %-10.10s          Fecha definitiva: %-10.10s" + System.lineSeparator() +
        "Estado: %-15.15s" + System.lineSeparator() +
        "Número de lote: LOT%6.6s", fixture.getPedidoTest().getNroPedido(), fecha, strMaquina,
        fixture.getPedidoTest().getCantidad(), fecha, fecha, fecha, fecha, 
        strEstado, (fixture.getPedidoTest().getNroLote() == -1 ? "" : String.format("%06d", fixture.getPedidoTest().getNroLote())));
    assertTrue("PED03E: No se redacto la descripcion correctamente", str==fixture.getPedidoTest().toString());
  }
  
  @Test
  public void testPED03F()
  {
    fixture.setUpM03F();
    String strMaquina = String.format("TIP%06d  --  %s", fixture.getPedidoTest().getMaquina().getCodigo(),fixture.getPedidoTest().getMaquina().getDescripcion());
    String fecha = "";
    String strEstado = "ACEPTADO";
    String str = String.format("Numero de pedido: PED%06d        " +
        "Fecha de pedido: %s" + System.lineSeparator() +
        "Tipo de máquina: %-75.75s             Cantidad: %03d" + System.lineSeparator() +
        "Fecha de entrega solicitada por ventas: %-10.10s" + System.lineSeparator() +
        "Fecha propuesta por Producción: %-10.10s" + System.lineSeparator() +
        "Fecha aceptado: %-10.10s          Fecha definitiva: %-10.10s" + System.lineSeparator() +
        "Estado: %-15.15s" + System.lineSeparator() +
        "Número de lote: LOT%6.6s", fixture.getPedidoTest().getNroPedido(), fecha, strMaquina,
        fixture.getPedidoTest().getCantidad(), fecha, fecha, fecha, fecha, 
        strEstado, (fixture.getPedidoTest().getNroLote() == -1 ? "" : String.format("%06d", fixture.getPedidoTest().getNroLote())));
    assertTrue("PED03F: No se redacto la descripcion correctamente", str==fixture.getPedidoTest().toString());
  }
  
  @Test
  public void testPED03G()
  {
    fixture.setUpM03G();
    String strMaquina = String.format("TIP%06d  --  %s", fixture.getPedidoTest().getMaquina().getCodigo(),fixture.getPedidoTest().getMaquina().getDescripcion());
    String fecha = "";
    String strEstado = "INICIADO";
    String str = String.format("Numero de pedido: PED%06d        " +
        "Fecha de pedido: %s" + System.lineSeparator() +
        "Tipo de máquina: %-75.75s             Cantidad: %03d" + System.lineSeparator() +
        "Fecha de entrega solicitada por ventas: %-10.10s" + System.lineSeparator() +
        "Fecha propuesta por Producción: %-10.10s" + System.lineSeparator() +
        "Fecha aceptado: %-10.10s          Fecha definitiva: %-10.10s" + System.lineSeparator() +
        "Estado: %-15.15s" + System.lineSeparator() +
        "Número de lote: LOT%6.6s", fixture.getPedidoTest().getNroPedido(), fecha, strMaquina,
        fixture.getPedidoTest().getCantidad(), fecha, fecha, fecha, fecha, 
        strEstado, (fixture.getPedidoTest().getNroLote() == -1 ? "" : String.format("%06d", fixture.getPedidoTest().getNroLote())));
    assertTrue("PED03G: No se redacto la descripcion correctamente", str==fixture.getPedidoTest().toString());
  }
  
  @Test
  public void testPED03H()
  {
    fixture.setUpM03H();
    String strMaquina = String.format("TIP%06d  --  %s", fixture.getPedidoTest().getMaquina().getCodigo(),fixture.getPedidoTest().getMaquina().getDescripcion());
    String fecha = "";
    String strEstado = "EN EVALUACION";
    String str = String.format("Numero de pedido: PED%06d        " +
        "Fecha de pedido: %s" + System.lineSeparator() +
        "Tipo de máquina: %-75.75s             Cantidad: %03d" + System.lineSeparator() +
        "Fecha de entrega solicitada por ventas: %-10.10s" + System.lineSeparator() +
        "Fecha propuesta por Producción: %-10.10s" + System.lineSeparator() +
        "Fecha aceptado: %-10.10s          Fecha definitiva: %-10.10s" + System.lineSeparator() +
        "Estado: %-15.15s" + System.lineSeparator() +
        "Número de lote: LOT%6.6s", fixture.getPedidoTest().getNroPedido(), fecha, strMaquina,
        fixture.getPedidoTest().getCantidad(), fecha, fecha, fecha, fecha, 
        strEstado, (fixture.getPedidoTest().getNroLote() == -1 ? "" : String.format("%06d", fixture.getPedidoTest().getNroLote())));
    assertTrue("PED03H: No se redacto la descripcion correctamente", str==fixture.getPedidoTest().toString());
  }
  
  @Test
  public void testPED03I()
  {
    fixture.setUpM03I();
    String strMaquina = String.format("TIP%06d  --  %s", fixture.getPedidoTest().getMaquina().getCodigo(),fixture.getPedidoTest().getMaquina().getDescripcion());
    String fecha = "";
    String strEstado = "CANCELADO";
    String str = String.format("Numero de pedido: PED%06d        " +
        "Fecha de pedido: %s" + System.lineSeparator() +
        "Tipo de máquina: %-75.75s             Cantidad: %03d" + System.lineSeparator() +
        "Fecha de entrega solicitada por ventas: %-10.10s" + System.lineSeparator() +
        "Fecha propuesta por Producción: %-10.10s" + System.lineSeparator() +
        "Fecha aceptado: %-10.10s          Fecha definitiva: %-10.10s" + System.lineSeparator() +
        "Estado: %-15.15s" + System.lineSeparator() +
        "Número de lote: LOT%6.6s", fixture.getPedidoTest().getNroPedido(), fecha, strMaquina,
        fixture.getPedidoTest().getCantidad(), fecha, fecha, fecha, fecha, 
        strEstado, (fixture.getPedidoTest().getNroLote() == -1 ? "" : String.format("%06d", fixture.getPedidoTest().getNroLote())));
    assertTrue("PED03I: No se redacto la descripcion correctamente", str==fixture.getPedidoTest().toString());
  }
  
  @Test
  public void testPED03J()
  {
    fixture.setUpM03J();
    String strMaquina = String.format("TIP%06d  --  %s", fixture.getPedidoTest().getMaquina().getCodigo(),fixture.getPedidoTest().getMaquina().getDescripcion());
    String fecha = "";
    String strEstado = "";
    String str = String.format("Numero de pedido: PED%06d        " +
        "Fecha de pedido: %s" + System.lineSeparator() +
        "Tipo de máquina: %-75.75s             Cantidad: %03d" + System.lineSeparator() +
        "Fecha de entrega solicitada por ventas: %-10.10s" + System.lineSeparator() +
        "Fecha propuesta por Producción: %-10.10s" + System.lineSeparator() +
        "Fecha aceptado: %-10.10s          Fecha definitiva: %-10.10s" + System.lineSeparator() +
        "Estado: %-15.15s" + System.lineSeparator() +
        "Número de lote: LOT%6.6s", fixture.getPedidoTest().getNroPedido(), fecha, strMaquina,
        fixture.getPedidoTest().getCantidad(), fecha, fecha, fecha, fecha, 
        strEstado, (fixture.getPedidoTest().getNroLote() == -1 ? "" : String.format("%06d", fixture.getPedidoTest().getNroLote())));
    assertTrue("PED03J: No se redacto la descripcion correctamente", str==fixture.getPedidoTest().toString());
  }
}
