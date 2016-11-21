package TestCajaNegra;

import empresa.Operacion;

public class FixtureOperacion {
    private Operacion operacionTest = new Operacion();
    
    public FixtureOperacion() {
    }
    
    public void setUp(){
        
    }
    
    public void tearDown(){
        operacionTest = new Operacion();
    }
    
    public void setUpM01A(){
        
    }
}
