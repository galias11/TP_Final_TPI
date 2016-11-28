package TestGUI;

import InterfazGrafica.NuevoPedido;

import empresa.Maquina;

import java.util.HashMap;

public class FixtureNuevoPedido {
    private NuevoPedido interfaz;
    
    public FixtureNuevoPedido() {
    }

    public NuevoPedido getInterfaz() {
        return interfaz;
    }
    
    public void setUp(){
        HashMap<Integer, Maquina> listaMaquinas = new HashMap<Integer, Maquina>();
        Maquina m1 = new Maquina(100, "Prueba");
        listaMaquinas.put(m1.getCodigo(), m1);
        interfaz = new NuevoPedido(listaMaquinas);
        interfaz.setVisible(true);
    }
    
    public void tearDown(){
        interfaz = null;
    }
    
    public void setUpM01_A(){
        
    }
    
    public void setUpM02_A(){
        
    }
    
    public void setUpM03_A(){
        
    }
}
