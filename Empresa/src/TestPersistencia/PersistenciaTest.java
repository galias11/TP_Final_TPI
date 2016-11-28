package TestPersistencia;

import empresa.Empleado;
import empresa.Empresa;

import empresa.Maquina;
import empresa.Material;
import empresa.Pedido;
import empresa.Sector;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.HashMap;

import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class PersistenciaTest
{
  FixturePersistencia fixture = new FixturePersistencia();
  
  public PersistenciaTest()
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
  public void testDespersistirSinArchivo()
  {
    try
    {
      fixture.setEmpresaTest(Empresa.deserializacion());
      fail("Deberia haber lanzado FileNotFoundException.");
    }
    catch (FileNotFoundException e)
    {
    }
  }
  
  @Test
  public void testCrearArchivo()
  {
    try
    {
      Empresa.serializacion(fixture.getEmpresaTest());
      File archivo = new File("empresaTest.xml");
      assertTrue("El archivo no se ha creado correctamente.",archivo.exists());
    }
    catch (FileNotFoundException e)
    {
      fail("Excepcion inesperada: " + e.getMessage());
    }
  }
  
  @Test
  public void testEmpresaVacia()
  {
    try
    {
      Empresa.serializacion(fixture.getEmpresaTest());
      Empresa empresa = Empresa.deserializacion();
      assertTrue("La coleccion inventario contiene elementos.",empresa.getInventario().isEmpty());
      assertTrue("La lista de empleados contiene elementos.",empresa.getListaEmpleados().isEmpty());
      assertTrue("La coleccion pedidos contiene elementos.",empresa.getPedidos().isEmpty());
      assertTrue("La coleccion productos contiene elementos.",empresa.getProductos().isEmpty());
      assertTrue("La coleccion sectores contiene elementos.",empresa.getSectores().isEmpty());
    }
    catch (FileNotFoundException e)
    {
      fail("Excepcion inesperada: " + e.getMessage());
    }
  }
  
  @Test
  public void testEmpresaConDatos()
  {
    fixture.setUpEmpresaConDatos();
    try
    {
      Empresa.serializacion(fixture.getEmpresaTest());
      Empresa empresa = Empresa.deserializacion();
      assertEquals("La coleccion inventario no se persistio correctamente.",fixture.getEmpresaTest().getInventario(),empresa.getInventario());
      assertEquals("La lista de empleados no se persistio correctamente.",fixture.getEmpresaTest().getListaEmpleados(),empresa.getListaEmpleados());
      assertEquals("La coleccion pedidos no se persistio correctamente.",fixture.getEmpresaTest().getPedidos(),empresa.getPedidos());
      assertEquals("La coleccion productos no se persistio correctamente.",fixture.getEmpresaTest().getProductos(),empresa.getProductos());
      assertEquals("La coleccion sectores no se persistio correctamente.",fixture.getEmpresaTest().getSectores(),empresa.getSectores());
    }
    catch (FileNotFoundException e)
    {
      fail("Excepcion inesperada: " + e.getMessage());
    }
  }
  
  
}
