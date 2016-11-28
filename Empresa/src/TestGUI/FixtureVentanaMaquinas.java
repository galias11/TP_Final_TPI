package TestGUI;

import InterfazGrafica.VentanaMaquinas;

import empresa.Empresa;
import empresa.Maquina;
import empresa.Material;

public class FixtureVentanaMaquinas {
    private VentanaMaquinas interfaz;
    private Empresa modelo;
    
    public FixtureVentanaMaquinas() {
        
    }

    public VentanaMaquinas getInterfaz() {
        return interfaz;
    }

    public Empresa getModelo() {
        return modelo;
    }
    
    public void setUp(){
        modelo = new Empresa();
        modelo.getProductos().clear();
        interfaz = new VentanaMaquinas(modelo.getProductos(), modelo.getInventario());
        interfaz.setVisible(true);
    }
    
    public void tearDown(){
        modelo = null;
        interfaz = null;
    }
    
    public void setUpM01_A(){
        
    }
    
    public void setUpM01_B(){
        Maquina m1 = new Maquina(1, "Prueba1");
        modelo.getProductos().put(m1.getCodigo(), m1);
    }
    
    public void setUpM01_C(){
        Maquina m1 = new Maquina(1, "Prueba1");
        Maquina m2 = new Maquina(2, "Prueba2");
        Maquina m3 = new Maquina(3, "Prueba3");
        Maquina m4 = new Maquina(4, "Prueba4");
        modelo.getProductos().put(m1.getCodigo(), m1);
        modelo.getProductos().put(m2.getCodigo(), m2);
        modelo.getProductos().put(m3.getCodigo(), m3);
        modelo.getProductos().put(m4.getCodigo(), m4);
    }
    
    public void setUpM02_A(){
        Maquina m1 = new Maquina(1, "Prueba1");
        Maquina m2 = new Maquina(2, "Prueba2");
        Maquina m3 = new Maquina(3, "Prueba3");
        Maquina m4 = new Maquina(4, "Prueba4");
        Maquina m5 = new Maquina(5, "Prueba5");
        Material mat1m1 = new Material(1, "M1", 5.0);
        Material mat1m3 = new Material(1, "M1", 1.0);
        Material mat2m3 = new Material(2, "M2", 4.0);
        m1.getListadoMateriales().put(mat1m1.getCodigoMaterial(), mat1m1);
        m3.getListadoMateriales().put(mat1m3.getCodigoMaterial(), mat1m3);
        m3.getListadoMateriales().put(mat2m3.getCodigoMaterial(), mat2m3);
        modelo.getProductos().put(m1.getCodigo(), m1);
        modelo.getProductos().put(m2.getCodigo(), m2);
        modelo.getProductos().put(m3.getCodigo(), m3);
        modelo.getProductos().put(m4.getCodigo(), m4);
        modelo.getProductos().put(m5.getCodigo(), m5);
        interfaz.refresh();
    }
    
    public void setUpM03_A(){
        Maquina m1 = new Maquina(1, "P");
        Material mat1 = new Material(1, "M1", 10.0);
        Material mat2 = new Material(2, "M2", 3.0);
        Material mat3 = new Material(3, "M3", 20.0);
        Material mat4 = new Material(4, "M4", 5.0);
        Material mat5 = new Material(5, "M5", 50.0);
        m1.getListadoMateriales().put(mat1.getCodigoMaterial(), mat1);
        m1.getListadoMateriales().put(mat2.getCodigoMaterial(), mat2);
        m1.getListadoMateriales().put(mat3.getCodigoMaterial(), mat3);
        m1.getListadoMateriales().put(mat4.getCodigoMaterial(), mat4);
        m1.getListadoMateriales().put(mat5.getCodigoMaterial(), mat5);
        modelo.getProductos().put(m1.getCodigo(), m1);
        interfaz.refresh();
    }
    
    public void setUpM04_A(){
        Material mat1 = new Material(1, "M1", 10.0);
        Material mat2 = new Material(2, "M2", 3.0);
        Material mat3 = new Material(3, "M3", 20.0);
        Material mat4 = new Material(4, "M4", 5.0);
        Material mat5 = new Material(5, "M5", 50.0);
        Maquina m1 = new Maquina(1, "P");
        modelo.getInventario().clear();
        modelo.getInventario().put(mat1.getCodigoMaterial(), mat1);
        modelo.getInventario().put(mat2.getCodigoMaterial(), mat2);
        modelo.getInventario().put(mat3.getCodigoMaterial(), mat3);
        modelo.getInventario().put(mat4.getCodigoMaterial(), mat4);
        modelo.getInventario().put(mat5.getCodigoMaterial(), mat5);
        modelo.getProductos().put(m1.getCodigo(), m1);
        interfaz.refresh();
    }
    
    public void setUpM05_A(){
        Maquina m1 = new Maquina(1, "P");
        modelo.getProductos().put(m1.getCodigo(), m1);
        interfaz.refresh();
    }
    
    public void setUpM06_A(){
        Maquina m1 = new Maquina(1, "M1");
        Maquina m2 = new Maquina(2, "M2");
        Maquina m3 = new Maquina(3, "M3");
        modelo.getProductos().put(m1.getCodigo(), m1);
        modelo.getProductos().put(m2.getCodigo(), m2);
        modelo.getProductos().put(m3.getCodigo(), m3);
        interfaz.refresh();
    }
    
    public void setUpM06_B(){
        
    }
}
