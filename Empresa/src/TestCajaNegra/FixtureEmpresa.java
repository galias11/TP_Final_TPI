package TestCajaNegra;

import empresa.Empresa;

import empresa.EmpresaException;

import empresa.Maquina;
import empresa.Material;

import empresa.Observacion;
import empresa.Pedido;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class FixtureEmpresa {
    private Empresa empresaTest = new Empresa();
    private Calendar fechaAux;
    
    public FixtureEmpresa() {
    }


    public Empresa getEmpresaTest() {
        return empresaTest;
    }

    public void setUp(){
        empresaTest.setUser(empresaTest.getListaEmpleados().get(9999));   
        fechaAux = GregorianCalendar.getInstance();
        fechaAux.add(Calendar.DAY_OF_YEAR, 1);
        Pedido.setUltLote(0);
        Pedido.setUltPedido(0);
    }
    
    public void tearDown(){
        empresaTest = new Empresa();
    }
    
    // SetUps para metodo aceptarPedido ***********************************************************
    public void setUpM01_A()
    {
        empresaTest.setUser(empresaTest.getListaEmpleados().get(9999));
        Pedido ped_01 = new Pedido(empresaTest.getProductos().get(100002), 5, fechaAux);
        empresaTest.getPedidos().put(ped_01.getNroPedido(), ped_01);
        Maquina maq = empresaTest.getProductos().get(100002);
        Material mat1 = empresaTest.getInventario().get(401);
        Material mat2 = empresaTest.getInventario().get(402);
        Material mat3 = empresaTest.getInventario().get(403);
        maq.getListadoMateriales().put(401, new Material(mat1.getCodigoMaterial(), mat1.getDescripcion(), 20.0));
        maq.getListadoMateriales().put(403, new Material(mat3.getCodigoMaterial(), mat3.getDescripcion(), 15.0));
        ped_01.setEstado(Pedido.EN_EVALUACION);
        ped_01.setFechaPropProduccion(fechaAux);    
        mat1.setCantidad(300.0);
        mat2.setCantidad(150.0);
        mat3.setCantidad(400.0);
        empresaTest.getInventario().clear();
        empresaTest.getInventario().put(mat1.getCodigoMaterial(), mat1);
        empresaTest.getInventario().put(mat2.getCodigoMaterial(), mat2);
        empresaTest.getInventario().put(mat3.getCodigoMaterial(), mat3);       
    }
    
    public void setUpM01_B(){
        setUpM01_A();
        empresaTest.setUser(empresaTest.getListaEmpleados().get(5));
    }
    
    public void setUpM01_C(){
        empresaTest.setUser(empresaTest.getListaEmpleados().get(9999));
        Pedido ped_01 = new Pedido(empresaTest.getProductos().get(100002), 5, fechaAux);
        empresaTest.getPedidos().put(ped_01.getNroPedido(), ped_01);
        Maquina maq = empresaTest.getProductos().get(100002);
        Material mat1 = empresaTest.getInventario().get(401);
        Material mat2 = empresaTest.getInventario().get(402);
        Material mat3 = empresaTest.getInventario().get(403);
        maq.getListadoMateriales().put(401, new Material(mat1.getCodigoMaterial(), mat1.getDescripcion(), 20.0));
        maq.getListadoMateriales().put(403, new Material(mat3.getCodigoMaterial(), mat3.getDescripcion(), 15.0));    
        mat1.setCantidad(300.0);
        mat2.setCantidad(150.0);
        mat3.setCantidad(400.0);
        empresaTest.getInventario().clear();
        empresaTest.getInventario().put(mat1.getCodigoMaterial(), mat1);
        empresaTest.getInventario().put(mat2.getCodigoMaterial(), mat2);
        empresaTest.getInventario().put(mat3.getCodigoMaterial(), mat3);
    }
    
    public void setUpM01_D(){
        empresaTest.setUser(empresaTest.getListaEmpleados().get(9999));
        Pedido ped_01 = new Pedido(empresaTest.getProductos().get(100002), 5, fechaAux);
        empresaTest.getPedidos().put(ped_01.getNroPedido(), ped_01);
        Maquina maq = empresaTest.getProductos().get(100002);
        Material mat1 = empresaTest.getInventario().get(401);
        Material mat2 = empresaTest.getInventario().get(402);
        Material mat3 = empresaTest.getInventario().get(403);
        maq.getListadoMateriales().put(401, new Material(mat1.getCodigoMaterial(), mat1.getDescripcion(), 20.0));
        maq.getListadoMateriales().put(403, new Material(mat3.getCodigoMaterial(), mat3.getDescripcion(), 15.0));
        ped_01.setEstado(Pedido.ACEPTADO);
        ped_01.setFechaPropProduccion(fechaAux);
        ped_01.setFechaAceptacion(GregorianCalendar.getInstance());
        ped_01.setFechaDefinitiva(fechaAux);
        ped_01.setUltLote(1 + ped_01.getUltLote());
        ped_01.setNroLote(ped_01.getNroLote());
        mat1.setCantidad(300.0);
        mat2.setCantidad(150.0);
        mat3.setCantidad(400.0);
        empresaTest.getInventario().clear();
        empresaTest.getInventario().put(mat1.getCodigoMaterial(), mat1);
        empresaTest.getInventario().put(mat2.getCodigoMaterial(), mat2);
        empresaTest.getInventario().put(mat3.getCodigoMaterial(), mat3);
    }
    
    public void setUpM01_E(){
        setUpM01_A();
        Material matAux = empresaTest.getInventario().get(401);
        matAux.setCantidad(30.0);
    }
    //*********************************************************************************************
    
    // setUps para metodo agregarMaterialReceta ***************************************************
    public void setUpM02_A()
    {
        Maquina maq = empresaTest.getProductos().get(100002);
        Material mat1 = empresaTest.getInventario().get(401);
        Material mat2 = empresaTest.getInventario().get(402);
        Material mat3 = empresaTest.getInventario().get(403);
        Material mat4 = empresaTest.getInventario().get(404);
        mat1.setCantidad(1000.0);
        mat2.setCantidad(300.0);
        mat3.setCantidad(200.0);
        mat4.setCantidad(500.0);
        maq.getListadoMateriales().put(mat1.getCodigoMaterial(), new Material(mat1.getCodigoMaterial(), mat1.getDescripcion(), 35.0));
        maq.getListadoMateriales().put(mat3.getCodigoMaterial(), new Material(mat3.getCodigoMaterial(), mat3.getDescripcion(), 25.0));
        empresaTest.getInventario().clear();
        empresaTest.getInventario().put(mat1.getCodigoMaterial(), mat1);
        empresaTest.getInventario().put(mat2.getCodigoMaterial(), mat2);
        empresaTest.getInventario().put(mat3.getCodigoMaterial(), mat3);
        empresaTest.getInventario().put(mat4.getCodigoMaterial(), mat4);
    }
    
    public void setUpM02_B(){
        setUpM02_A();
        Maquina maq = empresaTest.getProductos().get(100002);
        maq.getListadoMateriales().clear();
    }
    
    public void setUpM02_C(){
        setUpM02_A();
        empresaTest.setUser(empresaTest.getListaEmpleados().get(5));
    }
    //*********************************************************************************************
    
    // setUps metodo cancelarPedido ***************************************************************
    public void setUpM03_A(){
        Pedido ped_01 = new Pedido(empresaTest.getProductos().get(100001), 5, fechaAux);
        empresaTest.getPedidos().put(ped_01.getNroPedido(), ped_01);
    }
    
    public void setUpM03_B(){
        Pedido ped_01 = new Pedido(empresaTest.getProductos().get(100001), 5, fechaAux);
        ped_01.setEstado(Pedido.EN_EVALUACION);
        ped_01.setFechaPropProduccion(fechaAux);
        empresaTest.getPedidos().put(ped_01.getNroPedido(), ped_01);
    }
    
    public void setUpM03_C(){
        setUpM03_A();
        empresaTest.setUser(empresaTest.getListaEmpleados().get(5));
    }
    
    public void setUpM03_D(){
        Pedido ped_01 = new Pedido(empresaTest.getProductos().get(100001), 5, fechaAux);
        ped_01.setEstado(Pedido.EN_EVALUACION);
        ped_01.setFechaPropProduccion(fechaAux);
        ped_01.setEstado(Pedido.ACEPTADO);
        ped_01.setFechaAceptacion(GregorianCalendar.getInstance());
        ped_01.setFechaDefinitiva(fechaAux);
        ped_01.setUltLote(1 + ped_01.getUltLote());
        ped_01.setNroLote(ped_01.getUltLote());
        empresaTest.getPedidos().put(ped_01.getNroPedido(), ped_01);
    }
    
    public void setUpM03_E(){
        Pedido ped_01 = new Pedido(empresaTest.getProductos().get(100001), 5, fechaAux);
        ped_01.setEstado(Pedido.EN_EVALUACION);
        ped_01.setFechaPropProduccion(fechaAux);
        ped_01.setEstado(Pedido.CANCELADO);
        empresaTest.getPedidos().put(ped_01.getNroPedido(), ped_01);
    }
    //*********************************************************************************************
    
    // setUps Metodo consultaFaltantes ************************************************************
    public void setUpM05_A()
    {
        Maquina maq = empresaTest.getProductos().get(100001);
        Material mat1 = empresaTest.getInventario().get(401);
        mat1.setCantidad(900.0);
        Material mat2 = empresaTest.getInventario().get(402);
        mat2.setCantidad(1000.0);
        empresaTest.getInventario().clear();
        empresaTest.getInventario().put(mat1.getCodigoMaterial(), mat1);
        empresaTest.getInventario().put(mat2.getCodigoMaterial(), mat2);
        maq.getListadoMateriales().put(mat1.getCodigoMaterial(), new Material(mat1.getCodigoMaterial(), mat1.getDescripcion(), 20.0));
        maq.getListadoMateriales().put(mat2.getCodigoMaterial(), new Material(mat2.getCodigoMaterial(), mat2.getDescripcion(), 35.0));
        Pedido ped_01 = new Pedido(empresaTest.getProductos().get(100003), 10 , fechaAux);
        empresaTest.getPedidos().put(ped_01.getNroPedido(), ped_01);
        Pedido ped_02 = new Pedido(empresaTest.getProductos().get(100001), 5, fechaAux);
        ped_02.setEstado(Pedido.EN_EVALUACION);
        ped_02.setFechaPropProduccion(fechaAux);
        empresaTest.getPedidos().put(ped_02.getNroPedido(), ped_02);
    }
    
    public void setUpM05_B(){
        Maquina maq = empresaTest.getProductos().get(100001);
        Material mat1 = empresaTest.getInventario().get(401);
        mat1.setCantidad(100.0);
        Material mat2 = empresaTest.getInventario().get(402);
        mat2.setCantidad(175.0);
        empresaTest.getInventario().clear();
        empresaTest.getInventario().put(mat1.getCodigoMaterial(), mat1);
        empresaTest.getInventario().put(mat2.getCodigoMaterial(), mat2);
        maq.getListadoMateriales().put(mat1.getCodigoMaterial(), new Material(mat1.getCodigoMaterial(), mat1.getDescripcion(), 20.0));
        maq.getListadoMateriales().put(mat2.getCodigoMaterial(), new Material(mat2.getCodigoMaterial(), mat2.getDescripcion(), 35.0));
        Pedido ped_01 = new Pedido(empresaTest.getProductos().get(100003), 10 , fechaAux);
        empresaTest.getPedidos().put(ped_01.getNroPedido(), ped_01);
        Pedido ped_02 = new Pedido(empresaTest.getProductos().get(100001), 5, fechaAux);
        ped_02.setEstado(Pedido.EN_EVALUACION);
        ped_02.setFechaPropProduccion(fechaAux);
        empresaTest.getPedidos().put(ped_02.getNroPedido(), ped_02);
    }
    
    public void setUpM05_C(){
        Maquina maq = empresaTest.getProductos().get(100001);
        Material mat1 = empresaTest.getInventario().get(401);
        mat1.setCantidad(50.0);
        Material mat2 = empresaTest.getInventario().get(402);
        mat2.setCantidad(100.0);
        empresaTest.getInventario().clear();
        empresaTest.getInventario().put(mat1.getCodigoMaterial(), mat1);
        empresaTest.getInventario().put(mat2.getCodigoMaterial(), mat2);
        maq.getListadoMateriales().put(mat1.getCodigoMaterial(), new Material(mat1.getCodigoMaterial(), mat1.getDescripcion(), 20.0));
        maq.getListadoMateriales().put(mat2.getCodigoMaterial(), new Material(mat2.getCodigoMaterial(), mat2.getDescripcion(), 35.0));
        Pedido ped_01 = new Pedido(empresaTest.getProductos().get(100003), 10 , fechaAux);
        empresaTest.getPedidos().put(ped_01.getNroPedido(), ped_01);
        Pedido ped_02 = new Pedido(empresaTest.getProductos().get(100001), 5, fechaAux);
        ped_02.setEstado(Pedido.EN_EVALUACION);
        ped_02.setFechaPropProduccion(fechaAux);
        empresaTest.getPedidos().put(ped_02.getNroPedido(), ped_02);
    }
    
    public void setUpM05_D(){
        Maquina maq = empresaTest.getProductos().get(100001);
        Material mat1 = empresaTest.getInventario().get(401);
        mat1.setCantidad(99.999);
        Material mat2 = empresaTest.getInventario().get(402);
        mat2.setCantidad(200.0);
        empresaTest.getInventario().clear();
        empresaTest.getInventario().put(mat1.getCodigoMaterial(), mat1);
        empresaTest.getInventario().put(mat2.getCodigoMaterial(), mat2);
        maq.getListadoMateriales().put(mat1.getCodigoMaterial(), new Material(mat1.getCodigoMaterial(), mat1.getDescripcion(), 20.0));
        maq.getListadoMateriales().put(mat2.getCodigoMaterial(), new Material(mat2.getCodigoMaterial(), mat2.getDescripcion(), 35.0));
        Pedido ped_01 = new Pedido(empresaTest.getProductos().get(100003), 10 , fechaAux);
        empresaTest.getPedidos().put(ped_01.getNroPedido(), ped_01);
        Pedido ped_02 = new Pedido(empresaTest.getProductos().get(100001), 5, fechaAux);
        ped_02.setEstado(Pedido.EN_EVALUACION);
        ped_02.setFechaPropProduccion(fechaAux);
        empresaTest.getPedidos().put(ped_02.getNroPedido(), ped_02);
    }
    
    public void setUpM05_E(){
        Maquina maq = empresaTest.getProductos().get(100001);
        Material mat1 = empresaTest.getInventario().get(401);
        mat1.setCantidad(99.999);
        Material mat2 = empresaTest.getInventario().get(402);
        mat2.setCantidad(200.0);
        empresaTest.getInventario().clear();
        empresaTest.getInventario().put(mat1.getCodigoMaterial(), mat1);
        empresaTest.getInventario().put(mat2.getCodigoMaterial(), mat2);
        maq.getListadoMateriales().put(mat1.getCodigoMaterial(), new Material(mat1.getCodigoMaterial(), mat1.getDescripcion(), 20.0));
        maq.getListadoMateriales().put(mat2.getCodigoMaterial(), new Material(mat2.getCodigoMaterial(), mat2.getDescripcion(), 35.0));
        Pedido ped_01 = new Pedido(empresaTest.getProductos().get(100003), 10 , fechaAux);
        empresaTest.getPedidos().put(ped_01.getNroPedido(), ped_01);
        Pedido ped_02 = new Pedido(empresaTest.getProductos().get(100001), 5, fechaAux);
        empresaTest.getPedidos().put(ped_02.getNroPedido(), ped_02);
    }
    
    public void setUpM05_F(){
        Maquina maq = empresaTest.getProductos().get(100001);
        Material mat1 = empresaTest.getInventario().get(401);
        mat1.setCantidad(99.999);
        Material mat2 = empresaTest.getInventario().get(402);
        mat2.setCantidad(200.0);
        empresaTest.getInventario().clear();
        empresaTest.getInventario().put(mat1.getCodigoMaterial(), mat1);
        empresaTest.getInventario().put(mat2.getCodigoMaterial(), mat2);
        maq.getListadoMateriales().put(mat1.getCodigoMaterial(), new Material(mat1.getCodigoMaterial(), mat1.getDescripcion(), 20.0));
        maq.getListadoMateriales().put(mat2.getCodigoMaterial(), new Material(mat2.getCodigoMaterial(), mat2.getDescripcion(), 35.0));
        Pedido ped_01 = new Pedido(empresaTest.getProductos().get(100003), 10 , fechaAux);
        empresaTest.getPedidos().put(ped_01.getNroPedido(), ped_01);
        Pedido ped_02 = new Pedido(empresaTest.getProductos().get(100001), 5, fechaAux);
        ped_02.setEstado(Pedido.EN_EVALUACION);
        ped_02.setFechaPropProduccion(fechaAux);
        ped_02.setEstado(Pedido.CANCELADO);
        empresaTest.getPedidos().put(ped_02.getNroPedido(), ped_02);
    }
    
    public void setUpM05_G(){
        setUpM05_A();
        empresaTest.setUser(empresaTest.getListaEmpleados().get(5));
    }
    //*********************************************************************************************
    
    // setUps para metodo consultaStock ***********************************************************
    public void setUpM06_A(){
        Material mat3 = empresaTest.getInventario().get(403);
        Material mat4 = empresaTest.getInventario().get(404);
        Material mat7 = empresaTest.getInventario().get(407);
        Material mat9 = empresaTest.getInventario().get(409);
        mat3.setCantidad(50.0);
        mat4.setCantidad(100.0);
        mat7.setCantidad(200.0);
        mat9.setCantidad(75.0);
        empresaTest.getInventario().clear();
        empresaTest.getInventario().put(mat3.getCodigoMaterial(), mat3);
        empresaTest.getInventario().put(mat4.getCodigoMaterial(), mat4);
        empresaTest.getInventario().put(mat7.getCodigoMaterial(), mat7);
        empresaTest.getInventario().put(mat9.getCodigoMaterial(), mat9);
    }
    
    public void setUpM06_B(){
        Material mat9 = empresaTest.getInventario().get(409);
        mat9.setCantidad(200.0);
        empresaTest.getInventario().clear();
        empresaTest.getInventario().put(mat9.getCodigoMaterial(), mat9);
    }
    
    public void setUpM06_C(){
        empresaTest.getInventario().clear();
    }
    //*********************************************************************************************
    
    // setUps para metodo deslog ******************************************************************
    public void setUpM07_A(){
        empresaTest.setUser(null);
    }
    //*********************************************************************************************
    
    // setUps para metodo eliminarMaterialReceta **************************************************
    public void setUpM08_A()
    {
        Maquina maq = empresaTest.getProductos().get(100001);
        Material mat1 = empresaTest.getInventario().get(401);
        Material mat3 = empresaTest.getInventario().get(403);
        maq.getListadoMateriales().put(mat1.getCodigoMaterial(), new Material(mat1.getCodigoMaterial(), mat1.getDescripcion(), 35.0));
        maq.getListadoMateriales().put(mat3.getCodigoMaterial(), new  Material(mat3.getCodigoMaterial(), mat3.getDescripcion(), 50.0));
    }
    
    public void setUpM08_B(){
        Maquina maq = empresaTest.getProductos().get(100001);
        Material mat3 = empresaTest.getInventario().get(403);
        maq.getListadoMateriales().put(mat3.getCodigoMaterial(), new Material(mat3.getCodigoMaterial(), mat3.getDescripcion(), 50.0));  
    }
    
    public void setUpM08_C(){
        setUpM08_A();
        empresaTest.setUser(empresaTest.getListaEmpleados().get(5));
    }
    //*********************************************************************************************
    
    // setUps para metodo iniciarEvaluacionPedido *************************************************
    public void setUpM09_A(){
        Pedido ped_01 = new Pedido(empresaTest.getProductos().get(100002), 3, fechaAux);
        Pedido ped_02 = new Pedido(empresaTest.getProductos().get(100004), 10, fechaAux);
        Pedido ped_03 = new Pedido(empresaTest.getProductos().get(100003), 5, fechaAux);
        empresaTest.getPedidos().put(ped_01.getNroPedido(), ped_01);
        empresaTest.getPedidos().put(ped_02.getNroPedido(), ped_02);
        empresaTest.getPedidos().put(ped_03.getNroPedido(), ped_03);   
    }
    
    public void setUpM09_B(){
        setUpM09_A();
        empresaTest.setUser(empresaTest.getListaEmpleados().get(5));
    }
    
    public void setUpM09_C(){
        Pedido ped_01 = new Pedido(empresaTest.getProductos().get(100002), 3, fechaAux);
        Pedido ped_02 = new Pedido(empresaTest.getProductos().get(100004), 10, fechaAux);
        Pedido ped_03 = new Pedido(empresaTest.getProductos().get(100003), 5, fechaAux);
        ped_03.setEstado(Pedido.ACEPTADO);
        ped_03.setFechaPropProduccion(fechaAux);
        ped_03.setFechaDefinitiva(fechaAux);
        ped_03.setUltLote(1 + ped_03.getUltLote());
        ped_03.setNroLote(ped_03.getUltLote());
        ped_03.setFechaAceptacion(GregorianCalendar.getInstance());
        empresaTest.getPedidos().put(ped_01.getNroPedido(), ped_01);
        empresaTest.getPedidos().put(ped_02.getNroPedido(), ped_02);
        empresaTest.getPedidos().put(ped_03.getNroPedido(), ped_03);   
    }
    
    public void setUpM09_D(){

        Pedido ped_01 = new Pedido(empresaTest.getProductos().get(100002), 3, fechaAux);
        Pedido ped_02 = new Pedido(empresaTest.getProductos().get(100004), 10, fechaAux);
        Pedido ped_03 = new Pedido(empresaTest.getProductos().get(100003), 5, fechaAux);
        ped_03.setEstado(Pedido.CANCELADO);
        ped_03.setFechaPropProduccion(fechaAux);
        ped_03.setFechaAceptacion(GregorianCalendar.getInstance());
        empresaTest.getPedidos().put(ped_01.getNroPedido(), ped_01);
        empresaTest.getPedidos().put(ped_02.getNroPedido(), ped_02);
        empresaTest.getPedidos().put(ped_03.getNroPedido(), ped_03);  
    }
    //*********************************************************************************************
    
    // setUps para metodo iniciarPedido ***********************************************************
    public void setUpM10_A(){
        
    }
    
    public void setUpM10_B(){
        empresaTest.setUser(empresaTest.getListaEmpleados().get(5));
    }
    //*********************************************************************************************
    
    // setUps para metodo listarObservaciones *****************************************************
    public void setUpM11_A(){
        Pedido ped_01 = new Pedido(empresaTest.getProductos().get(100001), 5, fechaAux);
        Pedido ped_02 = new Pedido(empresaTest.getProductos().get(100002), 10, fechaAux);
        ped_02.setEstado(Pedido.EN_EVALUACION);
        ped_02.setFechaPropProduccion(fechaAux);
        empresaTest.getPedidos().put(ped_01.getNroPedido(), ped_01);
        empresaTest.getPedidos().put(ped_02.getNroPedido(), ped_02);
    }
    
    public void setUpM11_B(){
        Pedido ped_01 = new Pedido(empresaTest.getProductos().get(100001), 5, fechaAux);
        Pedido ped_02 = new Pedido(empresaTest.getProductos().get(100002), 10, fechaAux);
        ped_02.setEstado(Pedido.EN_EVALUACION);
        ped_02.setFechaPropProduccion(fechaAux);
        empresaTest.getPedidos().put(ped_01.getNroPedido(), ped_01);
        empresaTest.getPedidos().put(ped_02.getNroPedido(), ped_02);
        Observacion obs1 = new Observacion(Observacion.TEMA_OTROS, 1, "Prueba");
        Observacion obs2 = new Observacion(Observacion.TEMA_INSUMOS, 2, "Prueba");
        Observacion obs3 = new Observacion(Observacion.TEMA_FECHAS, 1, "Prueba");
        ped_02.getListaObservaciones().add(obs1);
        ped_02.getListaObservaciones().add(obs2);
        ped_02.getListaObservaciones().add(obs3);
    }
    
    public void setUpM11_C(){
        Pedido ped_01 = new Pedido(empresaTest.getProductos().get(100001), 5, fechaAux);
        Pedido ped_02 = new Pedido(empresaTest.getProductos().get(100002), 10, fechaAux);
        ped_02.setEstado(Pedido.EN_EVALUACION);
        ped_02.setFechaPropProduccion(fechaAux);
        empresaTest.getPedidos().put(ped_01.getNroPedido(), ped_01);
        empresaTest.getPedidos().put(ped_02.getNroPedido(), ped_02);
        Observacion obs1 = new Observacion(Observacion.TEMA_OTROS, 1, "Prueba");
        ped_02.getListaObservaciones().add(obs1);
    }
    //*********************************************************************************************
    
    // setUps para metodo login *******************************************************************
    public void setUpM12_A(){
        empresaTest.setUser(null);
    }
    
    public void setUpM12_B(){
        
    }
    //*********************************************************************************************

    // setUps para metodo materialesNecesarios    
    public void setUpM13_A(){
        Pedido ped_01 = new Pedido(empresaTest.getProductos().get(100004), 8, fechaAux);
        Pedido ped_02 = new Pedido(empresaTest.getProductos().get(100003), 5, fechaAux);
        empresaTest.getPedidos().put(ped_01.getNroPedido(), ped_01);
        empresaTest.getPedidos().put(ped_02.getNroPedido(), ped_02);
        Maquina maq = empresaTest.getProductos().get(100003);
        Material mat1 = empresaTest.getInventario().get(401);
        Material mat4 = empresaTest.getInventario().get(404);
        maq.getListadoMateriales().put(mat1.getCodigoMaterial(), new Material(mat1.getCodigoMaterial(), mat1.getDescripcion(), 30.0));
        maq.getListadoMateriales().put(mat4.getCodigoMaterial(), new Material(mat4.getCodigoMaterial(), mat4.getDescripcion(), 25.0));
    }
    
    public void setUpM13_B(){
        Pedido ped_01 = new Pedido(empresaTest.getProductos().get(100004), 8, fechaAux);
        Pedido ped_02 = new Pedido(empresaTest.getProductos().get(100003), 5, fechaAux);
        empresaTest.getPedidos().put(ped_01.getNroPedido(), ped_01);
        empresaTest.getPedidos().put(ped_02.getNroPedido(), ped_02);
    }
    
    public void setUpM13_C(){
        setUpM13_A();
        empresaTest.setUser(empresaTest.getListaEmpleados().get(5));
    }
    //*********************************************************************************************
    
    // setUps para metodo modificarCantidadReceta *************************************************
    public void setUpM14_A(){
        Maquina maq = empresaTest.getProductos().get(100004);
        Material mat2 = empresaTest.getInventario().get(402);
        Material mat5 = empresaTest.getInventario().get(405);
        maq.getListadoMateriales().put(mat2.getCodigoMaterial(), new Material(mat2.getCodigoMaterial(), mat2.getDescripcion(), 30.0));
        maq.getListadoMateriales().put(mat5.getCodigoMaterial(), new Material(mat5.getCodigoMaterial(), mat5.getDescripcion(), 50.0));
    }
    
    public void setUpM14_B(){
        setUpM14_A();
        empresaTest.setUser(empresaTest.getListaEmpleados().get(5));
    }
    //*********************************************************************************************
    
    // setUps para metodo obsetvarPedido **********************************************************
    public void setUpM15_A(){
        Pedido ped_01 = new Pedido(empresaTest.getProductos().get(100003), 5, fechaAux);
        Pedido ped_02 = new Pedido(empresaTest.getProductos().get(100003), 10, fechaAux);
        Pedido ped_03 = new Pedido(empresaTest.getProductos().get(100001), 5, fechaAux);
        ped_03.setEstado(Pedido.EN_EVALUACION);
        ped_03.setFechaPropProduccion(fechaAux);
        empresaTest.getPedidos().put(ped_01.getNroPedido(), ped_01);
        empresaTest.getPedidos().put(ped_02.getNroPedido(), ped_02);
        empresaTest.getPedidos().put(ped_03.getNroPedido(), ped_03);
    }
    
    public void setUpM15_B(){
        Pedido ped_01 = new Pedido(empresaTest.getProductos().get(100003), 5, fechaAux);
        Pedido ped_02 = new Pedido(empresaTest.getProductos().get(100003), 10, fechaAux);
        Pedido ped_03 = new Pedido(empresaTest.getProductos().get(100001), 5, fechaAux);
        empresaTest.getPedidos().put(ped_01.getNroPedido(), ped_01);
        empresaTest.getPedidos().put(ped_02.getNroPedido(), ped_02);
        empresaTest.getPedidos().put(ped_03.getNroPedido(), ped_03);
    }
    
    public void setUpM15_C(){

        Pedido ped_01 = new Pedido(empresaTest.getProductos().get(100003), 5, fechaAux);
        Pedido ped_02 = new Pedido(empresaTest.getProductos().get(100003), 10, fechaAux);
        Pedido ped_03 = new Pedido(empresaTest.getProductos().get(100001), 5, fechaAux);
        ped_03.setEstado(Pedido.EN_EVALUACION);
        ped_03.setFechaPropProduccion(fechaAux);
        ped_03.setEstado(Pedido.CANCELADO);
        empresaTest.getPedidos().put(ped_01.getNroPedido(), ped_01);
        empresaTest.getPedidos().put(ped_02.getNroPedido(), ped_02);
        empresaTest.getPedidos().put(ped_03.getNroPedido(), ped_03);
    }
    //*********************************************************************************************
    
    // setUps para metodo reservarMateriales ******************************************************
    public void setUpM16_A(){
        Pedido ped_01 = new Pedido(empresaTest.getProductos().get(100003), 10, fechaAux);
        Pedido ped_02 = new Pedido(empresaTest.getProductos().get(100003), 5, fechaAux);
        ped_02.setEstado(Pedido.EN_EVALUACION);
        ped_02.setFechaPropProduccion(fechaAux);
        empresaTest.getPedidos().put(ped_01.getNroPedido(), ped_01);
        empresaTest.getPedidos().put(ped_02.getNroPedido(), ped_02);
        Maquina maq =  empresaTest.getProductos().get(100003);
        Material mat1 = empresaTest.getInventario().get(401);
        Material mat2 = empresaTest.getInventario().get(402);
        Material mat3 = empresaTest.getInventario().get(403);
        Material mat4 = empresaTest.getInventario().get(404);
        mat1.setCantidad(2500.0);
        mat2.setCantidad(400.0);
        mat3.setCantidad(500.0);
        mat4.setCantidad(250.0);
        empresaTest.getInventario().clear();
        empresaTest.getInventario().put(mat1.getCodigoMaterial(), mat1);
        empresaTest.getInventario().put(mat2.getCodigoMaterial(), mat2);
        empresaTest.getInventario().put(mat3.getCodigoMaterial(), mat3);
        empresaTest.getInventario().put(mat4.getCodigoMaterial(), mat4);
        maq.getListadoMateriales().put(mat1.getCodigoMaterial(), new Material(mat1.getCodigoMaterial(), mat1.getDescripcion(), 25.0));
        maq.getListadoMateriales().put(mat2.getCodigoMaterial(), new Material(mat2.getCodigoMaterial(), mat2.getDescripcion(), 40.0));
    }
    
    public void setUpM16_B(){
        Pedido ped_01 = new Pedido(empresaTest.getProductos().get(100003), 10, fechaAux);
        Pedido ped_02 = new Pedido(empresaTest.getProductos().get(100003), 5, fechaAux);
        ped_02.setEstado(Pedido.EN_EVALUACION);
        ped_02.setFechaPropProduccion(fechaAux);
        empresaTest.getPedidos().put(ped_01.getNroPedido(), ped_01);
        empresaTest.getPedidos().put(ped_02.getNroPedido(), ped_02);
        Maquina maq =  empresaTest.getProductos().get(100003);
        Material mat1 = empresaTest.getInventario().get(401);
        Material mat2 = empresaTest.getInventario().get(402);
        Material mat3 = empresaTest.getInventario().get(403);
        Material mat4 = empresaTest.getInventario().get(404);
        mat1.setCantidad(125.0);
        mat2.setCantidad(200.0);
        mat3.setCantidad(500.0);
        mat4.setCantidad(250.0);
        empresaTest.getInventario().clear();
        empresaTest.getInventario().put(mat1.getCodigoMaterial(), mat1);
        empresaTest.getInventario().put(mat2.getCodigoMaterial(), mat2);
        empresaTest.getInventario().put(mat3.getCodigoMaterial(), mat3);
        empresaTest.getInventario().put(mat4.getCodigoMaterial(), mat4);
        maq.getListadoMateriales().put(mat1.getCodigoMaterial(), new Material(mat1.getCodigoMaterial(), mat1.getDescripcion(), 25.0));
        maq.getListadoMateriales().put(mat2.getCodigoMaterial(), new Material(mat2.getCodigoMaterial(), mat2.getDescripcion(), 40.0));
    }
    
    public void setUpM16_C(){
        Pedido ped_01 = new Pedido(empresaTest.getProductos().get(100003), 10, fechaAux);
        Pedido ped_02 = new Pedido(empresaTest.getProductos().get(100003), 5, fechaAux);
        ped_02.setEstado(Pedido.EN_EVALUACION);
        ped_02.setFechaPropProduccion(fechaAux);
        empresaTest.getPedidos().put(ped_01.getNroPedido(), ped_01);
        empresaTest.getPedidos().put(ped_02.getNroPedido(), ped_02);
        Maquina maq =  empresaTest.getProductos().get(100003);
        Material mat1 = empresaTest.getInventario().get(401);
        Material mat2 = empresaTest.getInventario().get(402);
        Material mat3 = empresaTest.getInventario().get(403);
        Material mat4 = empresaTest.getInventario().get(404);
        mat1.setCantidad(50.0);
        mat2.setCantidad(150.0);
        mat3.setCantidad(500.0);
        mat4.setCantidad(250.0);
        empresaTest.getInventario().clear();
        empresaTest.getInventario().put(mat1.getCodigoMaterial(), mat1);
        empresaTest.getInventario().put(mat2.getCodigoMaterial(), mat2);
        empresaTest.getInventario().put(mat3.getCodigoMaterial(), mat3);
        empresaTest.getInventario().put(mat4.getCodigoMaterial(), mat4);
        maq.getListadoMateriales().put(mat1.getCodigoMaterial(), new Material(mat1.getCodigoMaterial(), mat1.getDescripcion(), 25.0));
        maq.getListadoMateriales().put(mat2.getCodigoMaterial(), new Material(mat2.getCodigoMaterial(), mat2.getDescripcion(), 40.0));
    }
    
    public void setUpM16_D(){
        Pedido ped_01 = new Pedido(empresaTest.getProductos().get(100003), 10, fechaAux);
        Pedido ped_02 = new Pedido(empresaTest.getProductos().get(100003), 5, fechaAux);
        ped_02.setEstado(Pedido.EN_EVALUACION);
        ped_02.setFechaPropProduccion(fechaAux);
        empresaTest.getPedidos().put(ped_01.getNroPedido(), ped_01);
        empresaTest.getPedidos().put(ped_02.getNroPedido(), ped_02);
        Maquina maq =  empresaTest.getProductos().get(100003);
        Material mat1 = empresaTest.getInventario().get(401);
        Material mat2 = empresaTest.getInventario().get(402);
        Material mat3 = empresaTest.getInventario().get(403);
        Material mat4 = empresaTest.getInventario().get(404);
        mat1.setCantidad(350.0);
        mat2.setCantidad(199.999);
        mat3.setCantidad(500.0);    
        mat4.setCantidad(250.0);
        empresaTest.getInventario().clear();
        empresaTest.getInventario().put(mat1.getCodigoMaterial(), mat1);
        empresaTest.getInventario().put(mat2.getCodigoMaterial(), mat2);
        empresaTest.getInventario().put(mat3.getCodigoMaterial(), mat3);
        empresaTest.getInventario().put(mat4.getCodigoMaterial(), mat4);
        maq.getListadoMateriales().put(mat1.getCodigoMaterial(), new Material(mat1.getCodigoMaterial(), mat1.getDescripcion(), 25.0));
        maq.getListadoMateriales().put(mat2.getCodigoMaterial(), new Material(mat2.getCodigoMaterial(), mat2.getDescripcion(), 40.0));
    }
}
