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
        // Confirmar agregar Observacion
        if(e.getActionCommand().equals(InterfazNuevaObservacion.AGREGAR)){
            Pedido pedActivo = observaciones.getPedido();
            try {
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
            Pedido pedActual = princ.pedidoSeleccionado();
            if(pedActual == null)
                JOptionPane.showMessageDialog(null, "Debe seleccionar un pedido.",
                                              "GuiLeoCriasAl S.A.", JOptionPane.ERROR_MESSAGE);     
            else {
                try {
                
                    String listado = modelo.materialesNecesaarios(pedActual.getNroPedido());
                    princ.lanzarCartel(listado);
                } catch(EmpresaException ex){
                    JOptionPane.showMessageDialog(null, "Error al obtener listado de materiales: " + ex.toString(),
                                                  "GuiLeoCriasAl S.A.", JOptionPane.ERROR_MESSAGE); 
                }
            }
        }
        //Ver listado de materiales faltantes
        if(e.getActionCommand().equals(InterfazPrincipal.MATFALT)){
            Pedido pedActual = princ.pedidoSeleccionado();
            if(pedActual == null)
                JOptionPane.showMessageDialog(null, "Debe seleccionar un pedido.",
                                              "GuiLeoCriasAl S.A.", JOptionPane.ERROR_MESSAGE);
            else{
                try{
                    Iterator<Material> it = modelo.consultaFaltantes(pedActual.getNroPedido()).values().iterator();
                    princ.lanzarCartelConLista(it);
                } catch (EmpresaException ex){
                    JOptionPane.showMessageDialog(null, "Error al obtener listado de faltantes: " + ex.toString(),
                                              "GuiLeoCriasAl S.A.", JOptionPane.ERROR_MESSAGE); 
                }
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
            Material nuevoMat = maquinas.getMatStockSeleccionado();
            int codMaq = maquinas.getCodigoMaquina();
            double cant;
            try{
                String strNumber = JOptionPane.showInputDialog(null, "Ingrese la cantidad.",
                                                               "GuiLeoCrisAl S.A.", JOptionPane.INFORMATION_MESSAGE);
                if(strNumber != null && !strNumber.isEmpty()){
                    cant = Double.parseDouble(strNumber);
                    if(nuevoMat != null){
                        try{
                            modelo.agregarMaterialReceta(codMaq, nuevoMat.getCodigoMaterial(), cant);
                            maquinas.refresh();
                            JOptionPane.showMessageDialog(null, "Material agregado a la receta.",
                                                          "GuiLeoCriasAl S.A.", JOptionPane.INFORMATION_MESSAGE);
                        } catch(EmpresaException ex){
                            JOptionPane.showMessageDialog(null, "Error al agregar el material. " + ex.toString(),
                                                          "GuiLeoCriasAl S.A.", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                else
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un material del inventario para" +
                                              " agregarlo.", "GuiLeoCriasAl S.A.", JOptionPane.ERROR_MESSAGE);
            } catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Formato numerico incorrecto. " + ex.toString(), 
                                              "GuiLeoCriasAl S.A.", JOptionPane.ERROR_MESSAGE);     
            } 
                
        }
        //Eliminar material
        if(e.getActionCommand().equals(InterfazMaquina.ELIMINAR)){
            Material aEliminar = maquinas.getMatProdSeleccionado();
            if(aEliminar != null){
                int codMaq = maquinas.getCodigoMaquina();
                try{
                    modelo.eliminarMaterialReceta(codMaq, aEliminar.getCodigoMaterial());
                    maquinas.refresh();
                    JOptionPane.showMessageDialog(null, "Material eliminado de la receta.",
                                                  "GuiLeoCriasAl S.A.", JOptionPane.INFORMATION_MESSAGE);
                } catch(EmpresaException ex){
                    JOptionPane.showMessageDialog(null, "Error al eliminar material de la receta. " + ex.toString()
                                                  , "GuiLeoCriasAl S.A.", JOptionPane.ERROR_MESSAGE);            
                }
            }
            else
                JOptionPane.showMessageDialog(null, "Debe seleccionar un material de la receta para " +
                                              " eliminarlo.", "GuiLeoCriasAl S.A.", JOptionPane.ERROR_MESSAGE);
        }
        //Modificar cantidad receta
        if(e.getActionCommand().equals(InterfazMaquina.MODIFICAR)){
            Material aModificar = maquinas.getMatProdSeleccionado();
            if(aModificar != null){
                try {
                    int codMaq = maquinas.getCodigoMaquina();
                    String strNumber = JOptionPane.showInputDialog(null, "Ingrese nueva cantidad.",
                                                                               "GuiLeoCrisAl S.A.", JOptionPane.INFORMATION_MESSAGE);
                    if(strNumber != null && !strNumber.isEmpty()){
                        double cant = Double.parseDouble(strNumber);  
                        modelo.modificarCantidadReceta(codMaq, aModificar.getCodigoMaterial(), cant);
                        maquinas.refresh();
                        JOptionPane.showMessageDialog(null, "Cantidad material modificada.",
                                                  "GuiLeoCriasAl S.A.", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Formato numerico incorrecto.", 
                                                  "GuiLeoCriasAl S.A.", JOptionPane.ERROR_MESSAGE);
                } catch(EmpresaException ex){
                    JOptionPane.showMessageDialog(null, "Error al modificar la cantidad en la receta. " + ex.toString(), 
                                                  "GuiLeoCriasAl S.A.", JOptionPane.ERROR_MESSAGE);        
                }
            }
            else
            JOptionPane.showMessageDialog(null, "Debe seleccionar un material de la receta para " +
                                          " modificarlo.", "GuiLeoCriasAl S.A.", JOptionPane.ERROR_MESSAGE);    
        }
    }
    
    
}
