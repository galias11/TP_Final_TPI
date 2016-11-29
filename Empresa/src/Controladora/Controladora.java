package Controladora;

import InterfazGrafica.NuevaObservacion;
import InterfazGrafica.NuevoPedido;
import InterfazGrafica.PantallaLogin;

import InterfazGrafica.Principal;

import InterfazGrafica.VentanaObservaciones;

import empresa.Empresa;

import empresa.EmpresaException;

import empresa.Material;
import empresa.Observacion;
import empresa.Pedido;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.text.ParseException;

import java.util.Calendar;

import java.util.Iterator;

import javax.swing.JOptionPane;

import InterfazGrafica.VentanaMaquinas;

import java.io.FileNotFoundException;

/**
 * Clase Controladora.
 * Controladora del sistema, maneja el modelo (empresa) e interactua
 * con la interfaz de usuario, tanto para mostrar como para
 * solicitar informacion al usuario.
 */
public class Controladora implements ActionListener{
    private InterfazLogin login;
    private InterfazPrincipal princ;
    private InterfazNuevoPed nuevoPed;
    private InterfazObservaciones observaciones;
    private InterfazNuevaObservacion nuevaObservac;
    private InterfazMaquina maquinas;
    private Empresa modelo;
    //Para eventual test de GUI
    //private InterfazOptionPane optionPane;
    
    public Controladora(){
        try{
            modelo = Empresa.deserializacion();
        } catch(FileNotFoundException ex){
            modelo = new Empresa();
        }
        login = new PantallaLogin();
        login.setControlador(this);
        login.mostrar();
    }
    
    /**
     * metodo actionPerformed
     * Captura de las acciones realizadas en la interfaz y actua
     * en funcion de ellas sobre el modelo del sistema.
     * Precondicion:
     * e no puede ser nulo. Como es un evento interno se descarta
     * la posibilidad de que sea nulo, por lo cual no se checkea 
     * con aserto.
     * PostCondicion:
     * 
     * @param e
     * ActionEvent: evento capturado de la interfaz.
     */
    public void actionPerformed(ActionEvent e){
        switch(e.getActionCommand()){
            //Login -- Inicio de sesion.
            case InterfazLogin.LOGIN:
                actionLogin();
                break;
            //Desconectarse del sistema
            case InterfazPrincipal.DESLOG:
                actionPrincDeslog();
                break;
            //Iniciar nuevo pedido
            case InterfazPrincipal.INIC_PED:
                actionPrincInicPed();
                break;
            //Cancelar nuevo pedido
            case InterfazNuevoPed.VOLVER:
                actionNuevoPedVolver();
                break;
            //IniciarPedido: cargar datos y generar.
            case InterfazNuevoPed.AGREGAR:
                actionNuevoPedAgregar();
                break;
            //Aceptar pedido
            case InterfazPrincipal.EVAL_PED:
                actionPrincEvalPed();                    
                break;
            //Generar lote
            case InterfazPrincipal.ACEPT_PED:
                actionPrincAceptPed();
                break;
            //Cancelar pedido iniciado o en evaluacion
            case InterfazPrincipal.CANC:
                actionPrincCanc();
                break;
            //Abrir Ventana observaciones
            case InterfazPrincipal.OBS:
                actionPrincObs();
                break;
            //Cerrar ventana observaciones
            case InterfazObservaciones.VOLVER:
                actionObsVolver();
                break;
            //Menu para agregar nueva observacion
            case InterfazObservaciones.AGREGAR:
                actionObsAgregar();
                break;
            //Cancelar nueva observacion
            case InterfazNuevaObservacion.CANCELAR:
                actionNuevaObsCancelar();
                break;
            // Confirmar agregar Observacion
            case InterfazNuevaObservacion.AGREGAR:
                actionNuevaObsAgregar();
                break;
            //Ver listado materiales necesarios
            case InterfazPrincipal.MAT_NEC:
                actionPrincMatNec();
                break;
            //Ver listado de materiales faltantes
            case InterfazPrincipal.MAT_FALT:
                actionPrincMatFalt();
                break;
            //Abrir menu maquinas - recetas
            case InterfazPrincipal.ADM_PROD:
                actionPrincAdmProd();
                break;
            //Cerrar menu maquinas - recetas
            case InterfazMaquina.VOLVER:
                actionMaqVolver();
                break;
            //Agregar nuevo material a receta
            case InterfazMaquina.AGREGAR:
                actionMaqAgregar();
                break;
            //Eliminar material
            case InterfazMaquina.ELIMINAR:
                actionMaqEliminar();
                break;
            //Modificar cantidad receta
            case InterfazMaquina.MODIFICAR:
                actionMaqModificar();
                break;
            default:
                break;
        }
    }
    
    /*
     * A continuacion se codifican los metodos privados de la
     * clase que definen la interaccion entree la interfaz de
     * usuario y el modelo para cada accion posible.
     */
    private void actionLogin(){
        try{
            int nLeg = login.getNroLegajo();
            modelo.login(nLeg);
            login.ocultar();
            princ = new Principal(modelo.getUser(), modelo);
            princ.setControlador(this);
            princ.mostrar();
        } catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Formato numerico incorrecto.", 
                                        "GuiLeoCrisAl S.A.", JOptionPane.ERROR_MESSAGE);
        } catch(EmpresaException ex){
            JOptionPane.showMessageDialog(null, "Empleado inexistente.",
                                          "GuiLeoCrisAl S.A.", JOptionPane.ERROR_MESSAGE);        
        }
    }
    
    private void actionPrincDeslog(){
        princ.cerrar();
        princ = null;
        modelo.deslog();
        login.mostrar();
        try{
            Empresa.serializacion(modelo);
        } catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Error al guardar estado: " + ex.toString(),
                       "GuiLeoCrisAl S.A.", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void actionPrincInicPed(){
        princ.ocultar();
        nuevoPed = new NuevoPedido(modelo.getProductos());
        nuevoPed.setControlador(this);
        nuevoPed.mostrar();
    }
    
    private void actionNuevoPedVolver(){
        nuevoPed.cerrar();
        nuevoPed = null;
        princ.mostrar();
        princ.refresh();
    }
    
    private void actionNuevoPedAgregar(){
        try {
            int cantidad = nuevoPed.getCantidad();
            Calendar fecha = nuevoPed.getFecha();
            int codMaquina = nuevoPed.getCodigoMaquina();
            modelo.iniciarPedido(codMaquina, cantidad, fecha);
            JOptionPane.showMessageDialog(null, "Pedido iniciado.",
                                          "GuiLeoCrisAl S.A.", JOptionPane.INFORMATION_MESSAGE);
            nuevoPed.cerrar();
            nuevoPed = null;
            princ.mostrar();
            princ.refresh();
        } catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Formato numerico cantidad maquinas incorrecto.",
                                  "GuiLeoCrisAl S.A.", JOptionPane.ERROR_MESSAGE);
        } catch(ParseException ex){
            JOptionPane.showMessageDialog(null, "Formato fecha incorrecto: AAAA/MM/DD.",
                                                  "GuiLeoCrisAl S.A.", JOptionPane.ERROR_MESSAGE);    
        } catch(EmpresaException ex){
            JOptionPane.showMessageDialog(null, "Error al generar el pedido: " + ex.toString(),
                                              "GuiLeoCrisAl S.A.", JOptionPane.ERROR_MESSAGE);
        } catch(InterfazException ex){
            JOptionPane.showMessageDialog(null, "Error: " + ex.toString(),
                                              "GuiLeoCrisAl S.A.", JOptionPane.ERROR_MESSAGE);        
        }
    }
    
    private void actionPrincEvalPed(){
        try{
            Pedido pedSeleccionado = princ.pedidoSeleccionado();
            Calendar fechaPropuesta = princ.getFecha();
            modelo.inciarEvaluacionPedido(pedSeleccionado.getNroPedido(), fechaPropuesta);
            JOptionPane.showMessageDialog(null, String.format("PED%06d evaluacion iniciada.", pedSeleccionado.getNroPedido()),
                                          "GuiLeoCrisAl S.A.", JOptionPane.INFORMATION_MESSAGE);
            princ.refresh();
        } catch(InterfazException ex){
            if(!ex.toString().equals("CANCEL"))
                JOptionPane.showMessageDialog(null, "Error: " + ex.toString(),
                                              "GuiLeoCrisAl S.A.", JOptionPane.ERROR_MESSAGE);          
        } catch(ParseException ex){
            JOptionPane.showMessageDialog(null, "Error en formato de fecha.",
                                          "GuiLeoCrisAl S.A.", JOptionPane.ERROR_MESSAGE);
        } catch(EmpresaException ex){
            JOptionPane.showMessageDialog(null, "Error al regitrar aceptacion del pedido: " + ex.toString(),
                                          "GuiLeoCrisAl S.A.", JOptionPane.ERROR_MESSAGE);    
        }
    }
    
    private void actionPrincAceptPed(){
        try{
            Pedido pedSeleccionado = princ.pedidoSeleccionado();
            Calendar fechaDefinitiva = princ.getFecha();
            modelo.aceptarPedido(pedSeleccionado.getNroPedido(), fechaDefinitiva);
            JOptionPane.showMessageDialog(null, String.format("PED%06d Aceptado: Se ha asignado un lote y fecha definitiva.", pedSeleccionado.getNroPedido()),
                                          "GuiLeoCrisAl S.A.", JOptionPane.INFORMATION_MESSAGE);
            princ.refresh();
        } catch(ParseException ex){
            JOptionPane.showMessageDialog(null, "Error en formato de fecha.",
                                          "GuiLeoCrisAl S.A.", JOptionPane.ERROR_MESSAGE);
        } catch(InterfazException ex){
            if(!ex.toString().equals("CANCEL"))
                JOptionPane.showMessageDialog(null, "Error: " + ex.toString(),
                                              "GuiLeoCrisAl S.A.", JOptionPane.ERROR_MESSAGE);          
        } catch(EmpresaException ex){
            JOptionPane.showMessageDialog(null, "Error al regitrar generación de lote: " + ex.toString(),
                                              "GuiLeoCrisAl S.A.", JOptionPane.ERROR_MESSAGE);    
        }
    }
    
    private void actionPrincCanc(){
        try{
            Pedido pedSel = princ.pedidoSeleccionado();
            String motivo = "[CANCELACION] " + princ.motivoCancelacion();
            modelo.cancelarPedido(pedSel.getNroPedido(), motivo);
            princ.refresh();
        } catch(EmpresaException ex){
            JOptionPane.showMessageDialog(null, "Error al cancelar pedido: " + ex.toString(),
                                              "GuiLeoCrisAl S.A.", JOptionPane.ERROR_MESSAGE);        
        } catch(InterfazException ex){
            if(!ex.toString().equals("CANCEL"))
                JOptionPane.showMessageDialog(null, "Error: " + ex.toString(),
                                                  "GuiLeoCrisAl S.A.", JOptionPane.ERROR_MESSAGE);            
        }
    }
    
    private void actionPrincObs(){
        try {
            Pedido pedSeleccionado = princ.pedidoSeleccionado();
            princ.ocultar();
            observaciones = new VentanaObservaciones(pedSeleccionado);
            observaciones.setControlador(this);
            observaciones.mostrar();
            observaciones.refresh();
        } catch(InterfazException ex){
            JOptionPane.showMessageDialog(null, "Error al obtener pedido seleccionado: " + ex.toString(),
                                          "GuiLeoCrisAl S.A.", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void actionObsVolver(){
        observaciones.cerrar();
        observaciones = null;
        princ.mostrar();
        princ.refresh();
    }
    
    private void actionObsAgregar(){
        observaciones.ocultar();
        nuevaObservac = new NuevaObservacion();
        nuevaObservac.setControlador(this);
        nuevaObservac.mostrar();
    }
    
    private void actionNuevaObsCancelar(){
        nuevaObservac.cerrar();
        nuevaObservac = null;
        observaciones.mostrar();
        observaciones.refresh();
    }
    
    private void actionNuevaObsAgregar(){
        try {
            Pedido pedActivo = observaciones.getPedido();
            Observacion auxObs = new Observacion(nuevaObservac.getTema(), modelo.getUser().getLegajo(), nuevaObservac.getObservacion());
            pedActivo.insertarObservacion(auxObs);
            JOptionPane.showMessageDialog(null, "Observacion agregada al pedido.",
                                          "GuiLeoCriasAl S.A.", JOptionPane.INFORMATION_MESSAGE);
            nuevaObservac.cerrar();
            nuevaObservac = null;
            observaciones.mostrar();
            observaciones.refresh();
        } catch (EmpresaException ex){
            JOptionPane.showMessageDialog(null, "Error al insertar la observacion: " + ex.toString(),
                                          "GuiLeoCriasAl S.A.", JOptionPane.ERROR_MESSAGE);
        } catch (InterfazException ex){
            JOptionPane.showMessageDialog(null, "Observacion mayor a 500 caracteres",
                                          "GuiLeoCriasAl S.A.", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void actionPrincMatNec(){
        try {
            Pedido pedActual = princ.pedidoSeleccionado();
            String listado = modelo.materialesNecesaarios(pedActual.getNroPedido());
            princ.lanzarCartel(listado);
        } catch(EmpresaException ex){
            JOptionPane.showMessageDialog(null, "Error al obtener listado de materiales: " + ex.toString(),
                                          "GuiLeoCriasAl S.A.", JOptionPane.ERROR_MESSAGE); 
        } catch(InterfazException ex){
            JOptionPane.showMessageDialog(null, "Error al obtener pedido seleccionado: " + ex.toString(),
                                          "GuiLeoCrisAl S.A.", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void actionPrincMatFalt(){
        try{
            Pedido pedActual = princ.pedidoSeleccionado();
            Iterator<Material> it = modelo.consultaFaltantes(pedActual.getNroPedido()).values().iterator();
            princ.lanzarCartelConLista(it);
        } catch (EmpresaException ex){
            JOptionPane.showMessageDialog(null, "Error al obtener listado de faltantes: " + ex.toString(),
                                      "GuiLeoCriasAl S.A.", JOptionPane.ERROR_MESSAGE); 
        } catch(InterfazException ex){
            JOptionPane.showMessageDialog(null, "Error al obtener pedido seleccionado: " + ex.toString(),
                                          "GuiLeoCrisAl S.A.", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void actionPrincAdmProd(){
        princ.ocultar();
        maquinas = new VentanaMaquinas(modelo.getProductos(), modelo.getInventario());
        maquinas.mostrar();
        maquinas.refresh();
        maquinas.setControlador(this);
    }
    
    private void actionMaqVolver(){
        maquinas.cerrar();
        maquinas = null;
        princ.mostrar();
        princ.refresh();
    }
    
    private void actionMaqAgregar(){
        try{
            Material nuevoMat = maquinas.getMatStockSeleccionado();
            int codMaq = maquinas.getCodigoMaquina();
            double cant = maquinas.getCantidad();
            modelo.agregarMaterialReceta(codMaq, nuevoMat.getCodigoMaterial(), cant);
            maquinas.refresh();
            JOptionPane.showMessageDialog(null, "Material agregado a la receta.",
                                         "GuiLeoCriasAl S.A.", JOptionPane.INFORMATION_MESSAGE);
        } catch(EmpresaException ex){
            JOptionPane.showMessageDialog(null, "Error al agregar el material. " + ex.toString(),
                                                      "GuiLeoCriasAl S.A.", JOptionPane.ERROR_MESSAGE);
        } catch(InterfazException ex) {
            if(!ex.toString().equals("CANCEL"))
                JOptionPane.showMessageDialog(null, "Error: " + ex.toString()
                                              , "GuiLeoCriasAl S.A.", JOptionPane.ERROR_MESSAGE);
        } catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Formato numerico incorrecto. " + ex.toString(), 
                                          "GuiLeoCriasAl S.A.", JOptionPane.ERROR_MESSAGE);     
        } 
    }
    
    private void actionMaqEliminar(){
        try{
            Material aEliminar = maquinas.getMatProdSeleccionado();
            int codMaq = maquinas.getCodigoMaquina();    
            modelo.eliminarMaterialReceta(codMaq, aEliminar.getCodigoMaterial());
            maquinas.refresh();
            JOptionPane.showMessageDialog(null, "Material eliminado de la receta.",
                                          "GuiLeoCriasAl S.A.", JOptionPane.INFORMATION_MESSAGE);
        } catch(EmpresaException ex){
            JOptionPane.showMessageDialog(null, "Error al eliminar material de la receta. " + ex.toString()
                                          , "GuiLeoCriasAl S.A.", JOptionPane.ERROR_MESSAGE);            
        } catch(InterfazException ex){
            JOptionPane.showMessageDialog(null, "Error: " + ex.toString()
                                          , "GuiLeoCriasAl S.A.", JOptionPane.ERROR_MESSAGE);                    
        }
    }
    
    private void actionMaqModificar(){
        try {
            Material aModificar = maquinas.getMatProdSeleccionado();
            int codMaq = maquinas.getCodigoMaquina();
            double cant = maquinas.getCantidad();  
            modelo.modificarCantidadReceta(codMaq, aModificar.getCodigoMaterial(), cant);
            maquinas.refresh();
            JOptionPane.showMessageDialog(null, "Cantidad material modificada.",
                                          "GuiLeoCriasAl S.A.", JOptionPane.INFORMATION_MESSAGE);
        } catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Formato numerico incorrecto.", 
                                              "GuiLeoCriasAl S.A.", JOptionPane.ERROR_MESSAGE);
        } catch(EmpresaException ex){
                JOptionPane.showMessageDialog(null, "Error al modificar la cantidad en la receta. " + ex.toString(), 
                                              "GuiLeoCriasAl S.A.", JOptionPane.ERROR_MESSAGE);        
        } catch(InterfazException ex) {
            if(!ex.toString().equals("CANCEL"))
                JOptionPane.showMessageDialog(null, "Error: " + ex.toString()
                                              , "GuiLeoCriasAl S.A.", JOptionPane.ERROR_MESSAGE);
        }
    }
}


