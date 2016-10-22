package Controladora;

import InterfazGrafica.NuevaObservacion;
import InterfazGrafica.NuevoPedido;
import InterfazGrafica.PantallaLogin;

import InterfazGrafica.Principal;

import InterfazGrafica.VentanaObservaciones;

import empresa.Empleado;
import empresa.Empresa;

import empresa.EmpresaException;

import empresa.Material;
import empresa.Observacion;
import empresa.Pedido;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Calendar;

import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import InterfazGrafica.VentanaMateriales;


public class Controladora implements ActionListener{
    private InterfazLogin login;
    private InterfazPrincipal princ;
    private InterfazNuevoPed nuevoPed;
    private InterfazObservaciones observaciones;
    private InterfazNuevaObservacion nuevaObservac;
    private InterfazMaquina maquinas;
    private Empresa modelo;
    private InterfazOptionPane optionPane;
    
    public Controladora(){
        modelo = new Empresa();
        login = new PantallaLogin();
        login.setControlador(this);
        login.mostrar();
    }
    
    public void actionPerformed(ActionEvent e){
        //Login -- Inicio de sesion.
        if(e.getActionCommand().equals(InterfazLogin.LOGIN)){
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
        //Desconectarse del sistema
        if(e.getActionCommand().equals(InterfazPrincipal.DESLOG)){
            princ.cerrar();
            modelo.deslog();
            login.mostrar();
        }
        //Iniciar nuevo pedido
        if(e.getActionCommand().equals(InterfazPrincipal.NPED)){
            princ.ocultar();
            nuevoPed = new NuevoPedido(modelo.getProductos());
            nuevoPed.setControlador(this);
            nuevoPed.mostrar();
        }
        //Cancelar nuevo pedido
        if(e.getActionCommand().equals(InterfazNuevoPed.VOLVER)){
            nuevoPed.cerrar();
            princ.mostrar();
            princ.refresh();
        }
        //IniciarPedido: cargar datos y generar.
        if(e.getActionCommand().equals(InterfazNuevoPed.AGREGAR)){
            try {
                int cantidad = nuevoPed.getCantidad();
                Calendar fecha = nuevoPed.getFecha();
                int codMaquina = nuevoPed.getCodigoMaquina();
                modelo.iniciarPedido(codMaquina, cantidad, fecha);
                JOptionPane.showMessageDialog(null, "Pedido iniciado.",
                                              "GuiLeoCrisAl S.A.", JOptionPane.INFORMATION_MESSAGE);
                nuevoPed.cerrar();
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
        //Aceptar pedido
        if(e.getActionCommand().equals(InterfazPrincipal.APED)){

            try{
                Pedido pedSeleccionado = princ.pedidoSeleccionado();
                Calendar fechaPropuesta = princ.getFecha();
                modelo.aceptarPedido(pedSeleccionado.getNroPedido(), fechaPropuesta);
                JOptionPane.showMessageDialog(null, String.format("PED%06d ha sido aceptado", pedSeleccionado.getNroPedido()),
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
        //Generar lote
        if(e.getActionCommand().equals(InterfazPrincipal.GLOT)){
            try{
                Pedido pedSeleccionado = princ.pedidoSeleccionado();
                Calendar fechaDefinitiva = princ.getFecha();
                modelo.generarLote(pedSeleccionado.getNroPedido(), fechaDefinitiva);
                JOptionPane.showMessageDialog(null, String.format("Se ha asignado un lote y fecha definitiva a PED%06d ", pedSeleccionado.getNroPedido()),
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
        //Cancelar pedido iniciado o en evaluacion
        if(e.getActionCommand().equals(InterfazPrincipal.CANC)){
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
        //Abrir Ventana observaciones
        if(e.getActionCommand().equals(InterfazPrincipal.OBS)){
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
        //Cerrar ventana observaciones
        if(e.getActionCommand().equals(InterfazObservaciones.VOLVER)){
            observaciones.ocultar();
            princ.mostrar();
            princ.refresh();
        }
        //Menu para agregar nueva observacion
        if(e.getActionCommand().equals(InterfazObservaciones.AGREGAR)){
            observaciones.ocultar();
            nuevaObservac = new NuevaObservacion();
            nuevaObservac.setControlador(this);
            nuevaObservac.mostrar();
        }
        //Cancelar nueva observacion
        if(e.getActionCommand().equals(InterfazNuevaObservacion.CANCELAR)){
            nuevaObservac.cerrar();
            observaciones.mostrar();
            observaciones.refresh();
        }
        // Confirmar agregar Observacion
        if(e.getActionCommand().equals(InterfazNuevaObservacion.AGREGAR)){
            try {
                Pedido pedActivo = observaciones.getPedido();
                Observacion auxObs = new Observacion(nuevaObservac.getTema(), modelo.getUser().getLegajo(), nuevaObservac.getObservacion());
                pedActivo.insertarObservacion(auxObs);
                JOptionPane.showMessageDialog(null, "Observacion agregada al pedido.",
                                              "GuiLeoCriasAl S.A.", JOptionPane.INFORMATION_MESSAGE);
                nuevaObservac.cerrar();
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
        //Ver listado materiales necesarios
        if(e.getActionCommand().equals(InterfazPrincipal.MATNEC)){
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
        //Ver listado de materiales faltantes
        if(e.getActionCommand().equals(InterfazPrincipal.MATFALT)){
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
        //Abrir menu maquinas - recetas
        if(e.getActionCommand().equals(InterfazPrincipal.APROD)){
            princ.ocultar();
            maquinas = new VentanaMateriales(modelo.getProductos(), modelo.getInventario());
            maquinas.mostrar();
            maquinas.refresh();
            maquinas.setControlador(this);
        }
        //Cerrar menu maquinas - recetas
        if(e.getActionCommand().equals(InterfazMaquina.VOLVER)){
            maquinas.cerrar();
            princ.mostrar();
            princ.refresh();
        }
        //Agregar nuevo material a receta
        if(e.getActionCommand().equals(InterfazMaquina.AGREGAR)){
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
                JOptionPane.showMessageDialog(null, "Error: " + ex.toString()
                                              , "GuiLeoCriasAl S.A.", JOptionPane.ERROR_MESSAGE);
            } catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Formato numerico incorrecto. " + ex.toString(), 
                                              "GuiLeoCriasAl S.A.", JOptionPane.ERROR_MESSAGE);     
            } 
                
        }
        //Eliminar material
        if(e.getActionCommand().equals(InterfazMaquina.ELIMINAR)){
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
        //Modificar cantidad receta
        if(e.getActionCommand().equals(InterfazMaquina.MODIFICAR)){
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
            } catch(InterfazException ex){
                    JOptionPane.showMessageDialog(null, "Error: " + ex.toString(), 
                                                  "GuiLeoCriasAl S.A.", JOptionPane.ERROR_MESSAGE);        
            }
        }    
        
    }
    
    
}
