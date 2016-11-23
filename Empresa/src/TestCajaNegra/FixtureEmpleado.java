package TestCajaNegra;

import empresa.Empleado;
import empresa.EmpresaException;
import empresa.Operacion;
import empresa.Sector;

public class FixtureEmpleado {
    
    private Empleado empleadoTest= new Empleado();
    
    public FixtureEmpleado() {
        super();
    }


    public Empleado getEmpleadoTest() {
        return empleadoTest;
    }
    
    public void setUp(){
        Sector ventas= new Sector("Ventas");
        empleadoTest= new Empleado(1, "Empleado ventas", ventas);
    }
    
    public void tearDown(){
        empleadoTest= new Empleado();
    }
    
    
    
    
    
    
}
