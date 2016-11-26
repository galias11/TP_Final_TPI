package TestCajaBlanca;

import empresa.Empresa;


import empresa.Maquina;
import empresa.Material;

import empresa.Observacion;
import empresa.Pedido;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

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
  
  public void setUp(){
    
  }
  
  public void tearDown(){
    empresaTest = new Empresa();
  }
}
