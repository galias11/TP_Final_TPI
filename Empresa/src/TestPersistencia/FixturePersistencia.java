package TestPersistencia;

import empresa.Empleado;
import empresa.Empresa;
import empresa.Maquina;
import empresa.Material;

import empresa.Pedido;

import empresa.Sector;

import java.io.File;

import java.util.HashMap;

public class FixturePersistencia
{
  private Empresa empresaTest;
  
  public FixturePersistencia()
  {
    super();
  }

  public Empresa getEmpresaTest()
  {
    return empresaTest;
  }

  public void setEmpresaTest(Empresa empresaTest)
  {
    this.empresaTest = empresaTest;
  }

  public void setUp()
  {
    empresaTest = new Empresa();
    empresaTest.setInventario(new HashMap<Integer, Material>());
    empresaTest.setListaEmpleados(new HashMap<Integer, Empleado>());
    empresaTest.setPedidos(new HashMap<Integer, Pedido>());
    empresaTest.setProductos(new HashMap<Integer, Maquina>());
    empresaTest.setSectores(new HashMap<String, Sector>());
    File archivo = new File("empresaTest.xml");
    if(archivo.exists())
      archivo.delete();
  }
  
  public void tearDown()
  {
    empresaTest.setInventario(new HashMap<Integer, Material>());
    empresaTest.setListaEmpleados(new HashMap<Integer, Empleado>());
    empresaTest.setPedidos(new HashMap<Integer, Pedido>());
    empresaTest.setProductos(new HashMap<Integer, Maquina>());
    empresaTest.setSectores(new HashMap<String, Sector>());
  }
  
  public void setUpEmpresaConDatos()
  {
    empresaTest = new Empresa();
    File archivo = new File("empresaTest.xml");
    if(archivo.exists())
      archivo.delete();
  }
  
}
