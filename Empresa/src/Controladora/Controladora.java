package Controladora;

import InterfazGrafica.NuevaObservacion;
import InterfazGrafica.NuevoPedido;
import InterfazGrafica.PantallaLogin;

import InterfazGrafica.Principal;

import InterfazGrafica.VentanaObservaciones;

import empresa.Empleado;
import empresa.Empresa;

import empresa.EmpresaException;

import empresa.Observacion;
import empresa.Pedido;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


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
            login.mostrar();
        }
        if(e.getActionCommand().equals(InterfazPrincipal.NPED)){
            princ.ocultar();
            nuevoPed = new NuevoPedido();
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
            int codMaquina = 0;
            int cantidad = 0;
            Calendar fecha = null;
            try {
                codMaquina = nuevoPed.getCodigoMaquina();
            } catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Formato numerico tipo maquina incorrecto.",
                                          "GuiLeoCrisAl S.A.", JOptionPane.ERROR_MESSAGE);
            }
            try {
                cantidad = nuevoPed.getCantidad();
                fecha = nuevoPed.getFecha();
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
            }
        }
        //Aceptar pedido
        if(e.getActionCommand().equals(InterfazPrincipal.APED)){
            Pedido pedSeleccionado = princ.pedidoSeleccionado();
            if(pedSeleccionado == null)
                JOptionPane.showMessageDialog(null, "Debe seleccionar un pedido del listado de pedidos.",
                                              "GuiLeoCrisAl S.A.", JOptionPane.ERROR_MESSAGE);    
            else {
                Calendar fechaPropuesta = null;
                String str = JOptionPane.showInputDialog(null, "Ingrese la fecha propuesta de producción. (Formato: AAAA/MM/DD)",
                                                         "GuiLeoCrisAl S.A.", JOptionPane.INFORMATION_MESSAGE);
                try{
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                    fechaPropuesta = Calendar.getInstance();
                    fechaPropuesta.setTime(sdf.parse(str));
                } catch(ParseException ex){
                    JOptionPane.showMessageDialog(null, "Error en formato de fecha.",
                                                  "GuiLeoCrisAl S.A.", JOptionPane.ERROR_MESSAGE);
                }
                try{
                    modelo.aceptarPedido(pedSeleccionado.getNroPedido(), fechaPropuesta);
                    JOptionPane.showMessageDialog(null, String.format("PED%06d ha sido aceptado", pedSeleccionado.getNroPedido()),
                                                  "GuiLeoCrisAl S.A.", JOptionPane.INFORMATION_MESSAGE);
                    princ.refresh();
                } catch(EmpresaException ex){
                    JOptionPane.showMessageDialog(null, "Error al regitrar aceptacion del pedido: " + ex.toString(),
                                                  "GuiLeoCrisAl S.A.", JOptionPane.ERROR_MESSAGE);    
                }                    
            }
        }
        //Generar lote
        if(e.getActionCommand().equals(InterfazPrincipal.GLOT)){
            Pedido pedSeleccionado = princ.pedidoSeleccionado();
            if(pedSeleccionado == null)
                JOptionPane.showMessageDialog(null, "Debe seleccionar un pedido del listado de pedidos.",
                                              "GuiLeoCrisAl S.A.", JOptionPane.ERROR_MESSAGE);
            else {
                Calendar fechaDefinitiva = null;
                String str = JOptionPane.showInputDialog(null, "Ingrese la fecha programada de producción. (Formato: AAAA/MM/DD)",
                                                         "GuiLeoCrisAl S.A.", JOptionPane.INFORMATION_MESSAGE);
                try{
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                    fechaDefinitiva = Calendar.getInstance();
                    fechaDefinitiva.setTime(sdf.parse(str));
                } catch(ParseException ex){
                    JOptionPane.showMessageDialog(null, "Error en formato de fecha.",
                                                  "GuiLeoCrisAl S.A.", JOptionPane.ERROR_MESSAGE);
                }
                try{
                    modelo.generarLote(pedSeleccionado.getNroPedido(), fechaDefinitiva);
                    JOptionPane.showMessageDialog(null, String.format("Se ha asignado un lote y fecha definitiva a PED%06d ", pedSeleccionado.getNroPedido()),
                                                  "GuiLeoCrisAl S.A.", JOptionPane.INFORMATION_MESSAGE);
                    princ.refresh();
                } catch(EmpresaException ex){
                    JOptionPane.showMessageDialog(null, "Error al regitrar generación de lote: " + ex.toString(),
                                                  "GuiLeoCrisAl S.A.", JOptionPane.ERROR_MESSAGE);    
                }
            }
        }
        //Abrir Ventana observaciones
        if(e.getActionCommand().equals(InterfazPrincipal.OBS)){
            Pedido pedSeleccionado = princ.pedidoSeleccionado();
            if(pedSeleccionado == null)
                JOptionPane.showMessageDialog(null, "Debe seleccionar un pedido.",
                                              "GuiLeoCriasAl S.A.", JOptionPane.ERROR_MESSAGE);
            else{
                princ.ocultar();
                observaciones = new VentanaObservaciones(pedSeleccionado);
                observaciones.setControlador(this);
                observaciones.mostrar();
                observaciones.refresh();
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
        // Confirmar agregar informacion
        if(e.getActionCommand().equals(InterfazNuevaObservacion.AGREGAR)){
            Pedido pedActivo = observaciones.getPedido();
            Observacion auxObs = new Observacion(nuevaObservac.getTema(), modelo.getUser().getLegajo(), nuevaObservac.getObservacion());
            try {
                pedActivo.insertarObservacion(auxObs);
                JOptionPane.showMessageDialog(null, "Observacion agregada al pedido.",
                                              "GuiLeoCriasAl S.A.", JOptionPane.INFORMATION_MESSAGE);
                nuevaObservac.cerrar();
                observaciones.mostrar();
                observaciones.refresh();
            } catch (EmpresaException ex){
                JOptionPane.showMessageDialog(null, "Error al insertar la observacion: " + ex.toString(),
                                              "GuiLeoCriasAl S.A.", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    
}
