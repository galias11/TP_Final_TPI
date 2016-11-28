package TestGUI;

import InterfazGrafica.NuevaObservacion;

import empresa.Pedido;

public class FixtureNuevaObservacion {
    private NuevaObservacion interfaz;
    
    public FixtureNuevaObservacion() {
       
    }


    public NuevaObservacion getInterfaz() {
        return interfaz;
    }

    public void setUp(){
        interfaz = new NuevaObservacion();
        interfaz.setVisible(true);
    }
                                             
    public void tearDown(){
        interfaz = null;
    }
    
    
    
    public void setUpM01_A(){
        
    }
    
    public void setUpM02_A(){
        
    }
}
