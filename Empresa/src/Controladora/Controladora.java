package Controladora;

import InterfazGrafica.NuevoPedido;
import InterfazGrafica.PantallaLogin;

import InterfazGrafica.Principal;

import empresa.Empleado;
import empresa.Empresa;

import empresa.EmpresaException;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.text.ParseException;

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
        if(e.getActionCommand().equals("login")){
            try{
                int nLeg = login.getNroLegajo();
                modelo.login(nLeg);
                login.ocultar();
                princ = new Principal(modelo.getUser(), modelo.getPedidos().values().iterator(), modelo.getInventario().values().iterator());
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
        if(e.getActionCommand().equals(InterfazPrincipal.DESLOG)){
            princ.cerrar();
            login.mostrar();
        }
        if(e.getActionCommand().equals(InterfazPrincipal.NPED)){
            princ.ocultar();
            nuevoPed = new NuevoPedido();
        }
        if(e.getActionCommand().equals(InterfazNuevoPed.VOLVER)){
            nuevoPed.cerrar();
            princ.mostrar();
            princ.refresh();
        }
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

                JOptionPane.showMessageDialog(null, "Error al generar el pedido: " + e.toString(),
                                                  "GuiLeoCrisAl S.A.", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        
    }
    
    
}
