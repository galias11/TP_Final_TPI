package TestGUI;

import InterfazGrafica.PantallaLogin;

import empresa.Empresa;

public class FixtureLogin {
    private PantallaLogin interfaz;
    
    public FixtureLogin() {
        
    }


    public PantallaLogin getInterfaz() {
        return interfaz;
    }
    
    public void setUp(){
        interfaz = new PantallaLogin();
        interfaz.setVisible(true);
    }
    
    public void tearDown(){
        interfaz = null;
    }
    
    public void setUpM01_A(){
        
    }
}
