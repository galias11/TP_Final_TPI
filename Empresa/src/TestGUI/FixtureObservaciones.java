package TestGUI;

import InterfazGrafica.VentanaObservaciones;

import empresa.Maquina;
import empresa.Observacion;
import empresa.Pedido;

import java.util.GregorianCalendar;

public class FixtureObservaciones {
    private VentanaObservaciones interfaz;
    private Pedido modelo;
    
    public FixtureObservaciones() {
        
    }

    public VentanaObservaciones getInterfaz() {
        return interfaz;
    }

    public Pedido getModelo() {
        return modelo;
    }
    
    public void setUp(){
        modelo = new Pedido(new Maquina(1, "M1"), 5, new GregorianCalendar(2017, 11, 31));
        interfaz = new VentanaObservaciones(modelo);
        interfaz.setVisible(true);
    }
    
    public void tearDown(){
        modelo = null;
        interfaz = null;
    }
    
    public void setUpM01_A(){
        
    }
    
    public void setUpM01_B(){
        Observacion obs1 = new Observacion("OTROS", 1, "P");
        obs1.setFecha(new GregorianCalendar(2017, 11, 31));
        modelo.getListaObservaciones().add(obs1);
    }
    
    public void setUpM01_C(){
        Observacion obs1 = new Observacion("OTROS", 1, "P");
        Observacion obs2 = new Observacion("INSUMOS", 2, "P");
        Observacion obs3 = new Observacion("OTROS", 2, "P");
        Observacion obs4 = new Observacion("FECHAS", 10, "P");
        obs1.setFecha(new GregorianCalendar(2017, 11, 31));
        obs2.setFecha(new GregorianCalendar(2016, 11, 31));
        obs3.setFecha(new GregorianCalendar(2016, 11, 31));
        obs4.setFecha(new GregorianCalendar(2019, 11, 31));
        modelo.getListaObservaciones().add(obs1);
        modelo.getListaObservaciones().add(obs2);
        modelo.getListaObservaciones().add(obs3);
        modelo.getListaObservaciones().add(obs4);
    }
    
    public void setUpM02_A(){
        Observacion obs1 = new Observacion("OTROS", 12, "PO1");
        Observacion obs2 = new Observacion("INSUMOS", 3, "PI1");
        Observacion obs3 = new Observacion("INSUMOS", 5, "PI2");
        Observacion obs4 = new Observacion("FECHAS", 3, "PF1");
        Observacion obs5 = new Observacion("INSUMOS", 3, "PI3");
        obs1.setFecha(new GregorianCalendar(2017, 05, 30));
        obs2.setFecha(new GregorianCalendar(2017, 05, 30));
        obs3.setFecha(new GregorianCalendar(2017, 06, 31));
        obs4.setFecha(new GregorianCalendar(2017, 05, 30));
        obs5.setFecha(new GregorianCalendar(2017, 11, 31));
        modelo.getListaObservaciones().add(obs1);
        modelo.getListaObservaciones().add(obs2);
        modelo.getListaObservaciones().add(obs3);
        modelo.getListaObservaciones().add(obs4);
        modelo.getListaObservaciones().add(obs5);
        interfaz.refresh();
    }
}
