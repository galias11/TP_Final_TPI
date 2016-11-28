package TestCajaBlanca;

import empresa.Empresa;


import empresa.EmpresaException;
import empresa.Flipper;
import empresa.Maquina;
import empresa.Material;

import empresa.Observacion;
import empresa.Pedido;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class FixtureEmpresa
{
  private Empresa empresaTest = new Empresa();

  public FixtureEmpresa()
  {
    super();
  }

  public Empresa getEmpresaTest()
  {
    return empresaTest;
  }

  public void setUp()
  {
    empresaTest.setUser(empresaTest.getListaEmpleados().get(9999));
  }

  public void tearDown()
  {
    empresaTest = new Empresa();
  }
  
  
  public void setUpM01B(){
      Flipper maq = new Flipper();
      Pedido ped = new Pedido(maq, 5, new GregorianCalendar(2016, 12, 12));
      empresaTest.getPedidos().put(ped.getNroPedido(),ped);
      
  }
  
    public void setUpM01C(){
        Flipper maq = new Flipper();
        Pedido ped = new Pedido(maq, 5, new GregorianCalendar(2016, 12, 12));
        ped.setEstado(Pedido.EN_EVALUACION);
        empresaTest.getPedidos().put(ped.getNroPedido(),ped);
    }
    
    public void setUpM01D(){
        Material mat = new Material(1, "Acero", 50.0);
        Flipper maq = new Flipper();
        try
        {
          maq.agregarMaterial(mat);
        }
        catch (EmpresaException e)
        {
        }
        Pedido ped = new Pedido(maq, 5, new GregorianCalendar(2016, 12, 12));
        ped.setEstado(Pedido.EN_EVALUACION);
        empresaTest.getPedidos().put(ped.getNroPedido(),ped);
    }
    
    public void setUpM01E(){
        Material mat = new Material(1, "Acero", 50.0);
        Flipper maq = new Flipper();
        try
        {
          maq.agregarMaterial(mat);
        }
        catch (EmpresaException e)
        {
        }
        Pedido ped = new Pedido(maq, 5, new GregorianCalendar(2016, 12, 12));
        ped.setEstado(Pedido.EN_EVALUACION);
        empresaTest.getPedidos().put(ped.getNroPedido(),ped);
        Material mat1= new Material(1, "Acero", 500.0);
        empresaTest.getInventario().put(1, mat1);
    }
    
    public void setUpM01F(){
        Material mat = new Material(1, "Acero", 50.0);
        Flipper maq = new Flipper();
        try
        {
          maq.agregarMaterial(mat);
        }
        catch (EmpresaException e)
        {
        }
        Pedido ped = new Pedido(maq, 5, new GregorianCalendar(2016, 12, 12));
        ped.setEstado(Pedido.EN_EVALUACION);
        empresaTest.getPedidos().put(ped.getNroPedido(),ped);
        Material mat1= new Material(1, "Acero", 100.0);
        empresaTest.getInventario().put(1, mat1);
    }
  

  public void setUpM02B()
  {
    Pedido pedido = new Pedido();
    pedido.setUltPedido(4);
    pedido = new Pedido(new Maquina(100004,"Flipper"),5,new GregorianCalendar(2017,05,06));
    pedido.setEstado(3);
    empresaTest.getPedidos().put(pedido.getNroPedido(),pedido);
  }
  
  public void setUpM02C()
  {
    Pedido pedido = new Pedido();
    pedido.setUltPedido(4);
    empresaTest.getPedidos().put(5, new Pedido(new Maquina(100004,"Flipper"),5,new GregorianCalendar(2017,05,06)));
  }

  public void setUpM03A()
  {
    Material mat = new Material(1, "Acero", 50.0);
    Flipper maq = new Flipper();
    try
    {
      maq.agregarMaterial(mat);
    }
    catch (EmpresaException e)
    {
    }
    Pedido ped = new Pedido(maq, 5, new GregorianCalendar(2016, 12, 12));
    ped.setEstado(Pedido.EN_EVALUACION);
    empresaTest.getPedidos().put(10, ped);
  }
  
    public void setUpM03B()
    {
      Material mat = new Material(1, "Acero", 50.0);
        Flipper maq = new Flipper();
      try
      {
        maq.agregarMaterial(mat);
      }
      catch (EmpresaException e)
      {
      }
      Pedido ped = new Pedido(maq, 5, new GregorianCalendar(2016, 12, 12));
      ped.setEstado(Pedido.EN_EVALUACION);
      empresaTest.getPedidos().put(10, ped);
      Material mat1= new Material(1, "Acero", 500.0);
      empresaTest.getInventario().put(1, mat1);
    }
    
    
    public void setUpM03C()
    {
    Flipper maq = new Flipper();
      Pedido ped = new Pedido(maq, 5, new GregorianCalendar(2016, 12, 12));
      ped.setEstado(Pedido.EN_EVALUACION);
      empresaTest.getPedidos().put(10, ped);
      Material mat1= new Material(1, "Acero", 500.0);
      empresaTest.getInventario().put(1, mat1);
    }
    
    

  public void setUpM04B()
  {
    Pedido pedido = new Pedido();
    pedido.setUltPedido(4);
    pedido = new Pedido(new Maquina(100004,"Flipper"),5,new GregorianCalendar(2017,05,06));
    empresaTest.getPedidos().put(pedido.getNroPedido(),pedido);
  }
  
  public void setUpM04C()
  {
    Pedido pedido = new Pedido();
    pedido.setUltPedido(4);
    pedido = new Pedido(new Maquina(100004,"Flipper"),5,new GregorianCalendar(2017,05,06));
    pedido.getListaObservaciones().add(new Observacion(Observacion.TEMA_FECHAS,1,"obs"));
    empresaTest.getPedidos().put(pedido.getNroPedido(),pedido);
  }

}
