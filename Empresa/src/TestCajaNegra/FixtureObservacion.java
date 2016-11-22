package TestCajaNegra;

import empresa.Observacion;

public class FixtureObservacion {
    private Observacion observacionTest = new Observacion();
    
    
    public FixtureObservacion() {
        super();
    }


    public Observacion getObservacionTest() {
        return observacionTest;
    }

    public void setUp(){
        observacionTest= new Observacion(Observacion.TEMA_INSUMOS, 1, "Falta material 1");
    }
    
    public void tearDown(){
        observacionTest = new Observacion();
    }
    
    
}
