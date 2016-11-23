package TestCajaNegra;

import empresa.Empresa;
import empresa.EmpresaException;
import empresa.Operacion;
import empresa.Sector;

public class FixtureSector {
    private Sector sectorTest= new Sector();
    
    public FixtureSector() {
        super();
    }


    public Sector getSectorTest() {
        return sectorTest;
    }
    
    public void setUp(){
        sectorTest= new Sector("Ventas");
    }
    
    public void tearDown(){
        sectorTest= new Sector();
    }
    
    public void setUpM03A(){
        Operacion op = new Operacion(1, "Iniciar pedido.");
        try {
            sectorTest.agregarPermiso(op);
        } catch (EmpresaException e) {
        }
    }
    
   

}
