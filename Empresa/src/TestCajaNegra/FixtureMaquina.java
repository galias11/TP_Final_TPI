package TestCajaNegra;

import empresa.Maquina;
import empresa.Material;

import java.util.HashMap;

public class FixtureMaquina {
    private Maquina maquinaTest = new Maquina();
    
    public FixtureMaquina() {
    }
    
    public void setUp(){
        maquinaTest.setCodigo(1);
        maquinaTest.setDescripcion("Maq1");
        maquinaTest.setListadoMateriales(new HashMap<Integer, Material>());
    }
    
    public void tearDown(){
        maquinaTest = new Maquina();
    }
    
    // setUps para metodo agregarMaterial
    public void setUpM01_A(){
        Material mat1 = new Material(1, "Mat1", 35.0);
        Material mat2 = new Material(2, "Mat2",  15.0);
        maquinaTest.getListadoMateriales().put(mat1.getCodigoMaterial(), mat1);
        maquinaTest.getListadoMateriales().put(mat2.getCodigoMaterial(), mat2);
    }
    
    public void setUpM01_B(){        
    }
    
    public void setUpM01_C(){
        Material mat2 = new Material(2, "Mat2",  15.0);
        maquinaTest.getListadoMateriales().put(mat2.getCodigoMaterial(), mat2);        
    }
    //*********************************************************************************************
    
    // setUps para metodo Constructor *************************************************************
    public void setUpM02_A(){
        
    }
    //*********************************************************************************************
    
    // setUps para metodo eliminarMaterial
    public void setUpM03_A(){
        Material mat1 = new Material(1, "Mat1", 25.0);
        Material mat3 = new Material(3, "Mat3", 40.0);
        maquinaTest.getListadoMateriales().put(mat1.getCodigoMaterial(), mat1);
        maquinaTest.getListadoMateriales().put(mat3.getCodigoMaterial(), mat3);
    }
    
    public void setUpM03_B(){
        Material mat3 = new Material(3, "Mat3", 40.0);
        maquinaTest.getListadoMateriales().put(mat3.getCodigoMaterial(), mat3);        
    }
    //*********************************************************************************************
    
    // setUps para metodo modificarCantidadMaterial
    public void setUpM04_A(){
        Material mat2 = new Material(1, "Mat2", 22.0);
        Material mat3 = new Material(3, "Mat3", 60.0);
        maquinaTest.getListadoMateriales().put(mat2.getCodigoMaterial(), mat2);
        maquinaTest.getListadoMateriales().put(mat3.getCodigoMaterial(), mat3);
    }
    
    public void setUpM04_B(){
        Material mat2 = new Material(1, "Mat2", 22.0);
        Material mat3 = new Material(3, "Mat3", 20.0);
        maquinaTest.getListadoMateriales().put(mat2.getCodigoMaterial(), mat2);
        maquinaTest.getListadoMateriales().put(mat3.getCodigoMaterial(), mat3);        
    }
    
    public void setUpM04_C(){
        Material mat3 = new Material(3, "Mat3", 60.0);
        maquinaTest.getListadoMateriales().put(mat3.getCodigoMaterial(), mat3);
    }
    
    public void setUpM04_D(){
    
    }
    
}
