package TestCajaBlanca;

import empresa.Empresa;


import empresa.EmpresaException;
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
      empresaTest = new Empresa();
  }
  
  public void tearDown(){
    empresaTest = new Empresa();
  }
  
  
  public void setUp03A(){
      Material mat= new Material(1, "Acero", 50.0);
      Maquina maq= new Maquina(1,"Flipper");
        try {
            maq.agregarMaterial(mat);
        } catch (EmpresaException e) {
        }
        Pedido ped= new Pedido(maq, 5, new GregorianCalendar(2016,12,12));
        ped.setEstado(Pedido.EN_EVALUACION);
        empresaTest.getPedidos().put(10, ped);
  }
  
  
}
