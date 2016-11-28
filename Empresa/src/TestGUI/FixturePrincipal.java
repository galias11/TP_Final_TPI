package TestGUI;

import InterfazGrafica.Principal;

import empresa.Empresa;
import empresa.Pedido;

import java.util.GregorianCalendar;

public class FixturePrincipal {
    private Principal interfaz;
    private Empresa modelo;
    
    public FixturePrincipal() {
        super();
    }
    
    public void setUp(){
        modelo = new Empresa();
    }
    
    public void tearDown(){
        interfaz = null;
        Pedido.setUltLote(0);
        Pedido.setUltPedido(0);
    }


    public Principal getInterfaz() {
        return interfaz;
    }

    public Empresa getModelo() {
        return modelo;
    }

    public void setUpM01_A(){
        modelo.setUser(modelo.getListaEmpleados().get(1));
        interfaz = new Principal(modelo.getUser(), modelo);
        interfaz.setVisible(true);
    }
    
    public void setUpM01_B(){
        modelo.setUser(modelo.getListaEmpleados().get(2));
        interfaz = new Principal(modelo.getUser(), modelo);
        interfaz.setVisible(true);
    }
    
    public void setUpM01_C(){
        modelo.setUser(modelo.getListaEmpleados().get(5));
        interfaz = new Principal(modelo.getUser(), modelo);
        interfaz.setVisible(true);
    }
    
    public void setUpM01_D(){
        modelo.setUser(modelo.getListaEmpleados().get(3));
        interfaz = new Principal(modelo.getUser(), modelo);
        interfaz.setVisible(true);
    }
    
    public void setUpM01_E(){
        modelo.setUser(modelo.getListaEmpleados().get(4));
        interfaz = new Principal(modelo.getUser(), modelo);
        interfaz.setVisible(true);
    }
    
    public void setUpM01_F(){
        modelo.setUser(modelo.getListaEmpleados().get(9999));
        interfaz = new Principal(modelo.getUser(), modelo);
        interfaz.setVisible(true);
    }
    
    public void setUpM02_A(){
        modelo.setUser(modelo.getListaEmpleados().get(9999));
        interfaz = new Principal(modelo.getUser(), modelo);
        interfaz.setVisible(true);
    }
    
    public void setUpM03_A(){
        modelo.setUser(modelo.getListaEmpleados().get(9999));
        Pedido p1 = new Pedido(modelo.getProductos().get(100002), 5, new GregorianCalendar(2017, 12, 31));
        Pedido p2 = new Pedido(modelo.getProductos().get(100003), 15, new GregorianCalendar(2017, 12, 31));
        Pedido p3 = new Pedido(modelo.getProductos().get(100001), 25, new GregorianCalendar(2017, 12, 31));
        Pedido p4 = new Pedido(modelo.getProductos().get(100001), 5, new GregorianCalendar(2017, 12, 31));
        Pedido p5 = new Pedido(modelo.getProductos().get(100004), 10, new GregorianCalendar(2017, 12, 31));
        modelo.getPedidos().put(p1.getNroPedido(), p1);
        modelo.getPedidos().put(p2.getNroPedido(), p2);
        modelo.getPedidos().put(p3.getNroPedido(), p3);
        modelo.getPedidos().put(p4.getNroPedido(), p4);
        modelo.getPedidos().put(p5.getNroPedido(), p5);
        interfaz = new Principal(modelo.getUser(), modelo);
    }
    
    public void setUpM04_A(){
        modelo.setUser(modelo.getListaEmpleados().get(9999));
        interfaz = new Principal(modelo.getUser(), modelo);
        interfaz.setVisible(true);
    }
    
    public void setUpM05_A(){
        modelo.setUser(modelo.getListaEmpleados().get(9999));
        interfaz = new Principal(modelo.getUser(), modelo);
        interfaz.setVisible(true);
    }
}
