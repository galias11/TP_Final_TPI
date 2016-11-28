
package InterfazGrafica;

import Controladora.Controladora;
import Controladora.InterfazException;
import Controladora.InterfazNuevoPed;
import Controladora.InterfazObservaciones;

import empresa.Empleado;
import empresa.Empresa;
import empresa.Observacion;
import empresa.Pedido;

import java.text.SimpleDateFormat;

import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * clase VentanaObservaciones.
 * Ventana donde se pueden ver y administrar las observaciones
 * para un determinado pedido.
 */
public class VentanaObservaciones
  extends javax.swing.JFrame implements InterfazObservaciones
{
    private Pedido pedido;
    
    /**
     * Constructor de la ventana observaciones.
     * PreCondicion:
     * pedido no nulo.
     * PostCondicion:
     *
     * @param pedido
     * Pedido: pedido activo de la ventana.
    */
    public VentanaObservaciones(Pedido pedido)
    {
        assert(pedido != null) : ("Pedido nulo.");
        initComponents();
        setLocationRelativeTo(null);
        this.pedido = pedido;
        inicializarComponentes();
    }
  
    private void inicializarComponentes(){
        agregar.setActionCommand(InterfazObservaciones.AGREGAR);
        volver.setActionCommand(InterfazObservaciones.VOLVER);
    }
    
    /*
     * Getters para test de GUI
     */


    public JButton getAgregar() {
        return agregar;
    }

    public JTextField getJTextField1() {
        return jTextField1;
    }

    public JTextField getJTextField10() {
        return jTextField10;
    }

    public JTextField getJTextField2() {
        return jTextField2;
    }

    public JTextField getJTextField3() {
        return jTextField3;
    }

    public JTextField getJTextField4() {
        return jTextField4;
    }

    public JTextField getJTextField5() {
        return jTextField5;
    }

    public JTextField getJTextField6() {
        return jTextField6;
    }

    public JTextField getJTextField7() {
        return jTextField7;
    }

    public JTextField getJTextField8() {
        return jTextField8;
    }

    public JTextField getJTextField9() {
        return jTextField9;
    }

    public JTable getTablaObservaciones() {
        return tablaObservaciones;
    }

    public JButton getVolver() {
        return volver;
    }
    
    /*
     * **********************************
     */

    @Override
    public void refresh(){
        cargarPedido();
        mostrarObservaciones();
    }
    
    @Override
    public void mostrar(){
        this.setVisible(true);
    }
    
    @Override
    public void ocultar(){
        this.setVisible(false);
    }
    
    @Override
    public void cerrar(){
        this.dispose();
    }
    
    @Override
    public void setControlador(Controladora c){
        assert(c != null) : ("Controladora nula.");
        agregar.addActionListener(c);
        volver.addActionListener(c);
    }
    
    @Override
    public Observacion getObsSeleccionada()
        throws InterfazException
    {
        int row = tablaObservaciones.getSelectedRow();
        Observacion obs = null;
        if(row != -1)
            obs = (Observacion) tablaObservaciones.getValueAt(row, 0);
        else
            throw new InterfazException("No se ha seleccionado ninguna observacion");
        return obs;
    }
    
    @Override
    public Pedido getPedido(){
        return pedido;
    }
  
    /**
     * metodo cargarPedido.
     * Coloca a los diferentes campos de la interfaz los valores
     * correspondientes al pedido activo.
     */
    private void cargarPedido(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        jTextField1.setText(String.format("PED%06d", pedido.getNroPedido()));
        jTextField2.setText(sdf.format(pedido.getFechaPedido().getTime()));
        jTextField3.setText(String.format("Cod: %d - %s", pedido.getMaquina().getCodigo(), pedido.getMaquina().getDescripcion()));
        jTextField4.setText("" + pedido.getCantidad());
        jTextField5.setText(sdf.format(pedido.getFechaEntrega().getTime()));
        jTextField6.setText(pedido.getFechaPropProduccion() == null ? "" : sdf.format(pedido.getFechaPropProduccion().getTime()));
        jTextField7.setText(pedido.getFechaDefinitiva() == null ? "" : sdf.format(pedido.getFechaDefinitiva().getTime()));
        jTextField8.setText(pedido.getFechaAceptacion() == null ? "" : sdf.format(pedido.getFechaAceptacion().getTime()));
        String estado = "";
        switch(pedido.getEstado()){
            case Pedido.ACEPTADO:
                estado = "ACEPTADO";
                break;
            case Pedido.EN_EVALUACION:
                estado = "EN EVALUACION";
                break;
            case Pedido.INICIADO:
                estado = "INICIADO";
                break;
            case Pedido.CANCELADO:
                estado = "CANCELADO";
                break;
            default:
                break;
        }
        jTextField9.setText(estado);
        jTextField10.setText("" + (pedido.getNroLote() == -1 ? "" : String.format("LOT%06d", pedido.getNroLote())));
        
    }
    
    /**
     * Metodo: mostrarObservaciones
     * Muestra el listado ordenado por tema cronologicamente de las 
     * observaciones para el pedido.
     * PreCondicion:
     * Pedido no nulo.
     * PostCondicion:
     * Se visualiza el listado con las observaciones realizadas. 
     */
    private void mostrarObservaciones(){
        assert (pedido != null) : ("Pedido nula.");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        tablaObservaciones.removeAll();
        DefaultTableModel model = (DefaultTableModel) tablaObservaciones.getModel();
        model.setRowCount(0);
        Iterator<Observacion> itObs = pedido.getListaObservaciones().iterator();
        while(itObs.hasNext()){
            Object row[] = new Object[4];
            Observacion auxO = itObs.next();
            row[0] = auxO;
            row[1] = auxO.getTema();
            row[2] = sdf.format(auxO.getFecha().getTime());
            row[3] = String.format("LEG%06d", auxO.getNLegCreador());
            model.addRow(row);
        }
    }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    private void initComponents() {//GEN-BEGIN:initComponents

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaObservaciones = new javax.swing.JTable();
        volver = new javax.swing.JButton();
        agregar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GuiLeoCrisAl S.A.");
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedido"));

        jLabel2.setText("Numero de pedido:");

        jTextField1.setEditable(false);

        jLabel3.setText("Fecha de pedido:");

        jTextField2.setEditable(false);

        jLabel4.setText("Tipo de maquina:");

        jTextField3.setEditable(false);

        jTextField4.setEditable(false);

        jLabel5.setText("Cantidad de maquinas:");

        jLabel6.setText("Fecha solicitada por Ventas:");

        jTextField5.setEditable(false);

        jTextField6.setEditable(false);

        jLabel7.setText("Fecha propuesta por Produccion:");

        jTextField7.setEditable(false);

        jLabel8.setText("Fecha definitiva:");

        jTextField8.setEditable(false);

        jLabel9.setText("Pedido aceptado:");

        jLabel10.setText("Estado:");

        jTextField9.setEditable(false);

        jLabel11.setText("Numero de lote:");

        jTextField10.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField9)
                    .addComponent(jTextField8)
                    .addComponent(jTextField7)
                    .addComponent(jTextField1)
                    .addComponent(jTextField2)
                    .addComponent(jTextField3)
                    .addComponent(jTextField4)
                    .addComponent(jTextField6)
                    .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addComponent(jTextField10))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Observaciones"));

        tablaObservaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Sel", "Tema", "Fecha", "Empleado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaObservaciones.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaObservaciones.setShowVerticalLines(false);
        tablaObservaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaObservacionesMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaObservaciones);
        if (tablaObservaciones.getColumnModel().getColumnCount() > 0) {
            tablaObservaciones.getColumnModel().getColumn(0).setMinWidth(0);
            tablaObservaciones.getColumnModel().getColumn(0).setPreferredWidth(0);
            tablaObservaciones.getColumnModel().getColumn(0).setMaxWidth(0);
            tablaObservaciones.getColumnModel().getColumn(0).setHeaderValue("Sel");
            tablaObservaciones.getColumnModel().getColumn(1).setPreferredWidth(10);
            tablaObservaciones.getColumnModel().getColumn(1).setHeaderValue("Tema");
            tablaObservaciones.getColumnModel().getColumn(2).setPreferredWidth(10);
            tablaObservaciones.getColumnModel().getColumn(2).setHeaderValue("Fecha");
            tablaObservaciones.getColumnModel().getColumn(3).setPreferredWidth(10);
            tablaObservaciones.getColumnModel().getColumn(3).setHeaderValue("Empleado");
        }

        volver.setText("Volver");

        agregar.setText("Agregar observacion");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(agregar)
                        .addGap(28, 28, 28)
                        .addComponent(volver, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(volver)
                    .addComponent(agregar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }//GEN-END:initComponents

    private void tablaObservacionesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaObservacionesMouseReleased
        // TODO add your handling code here:
        int row = tablaObservaciones.getSelectedRow();
        if(row != -1){
            Observacion obs = (Observacion) tablaObservaciones.getValueAt(row, 0);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String msg = String.format("TEMA: %s           EMISOR: LEG%06d        FECHA: %s" 
                                       + System.lineSeparator() + "OBSERVACION: " + System.lineSeparator() + "%s"  , obs.getTema(), obs.getNLegCreador(),
                                       sdf.format(obs.getFecha().getTime()), obs.getObservacion());
            JOptionPane.showMessageDialog(null, msg, "GuiLeoCrisAl S.A.", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_tablaObservacionesMouseReleased

  /**
   * @param args the command line arguments
   */
  public static void main(String args[])
  {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
    try
    {
      for (javax.swing.UIManager.LookAndFeelInfo info: javax.swing.UIManager.getInstalledLookAndFeels())
      {
        if ("Nimbus".equals(info.getName()))
        {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    }
    catch (ClassNotFoundException ex)
    {
      java.util.logging.Logger.getLogger(VentanaObservaciones.class.getName()).log(java.util.logging.Level.SEVERE, null,
                                                                                   ex);
    }
    catch (InstantiationException ex)
    {
      java.util.logging.Logger.getLogger(VentanaObservaciones.class.getName()).log(java.util.logging.Level.SEVERE, null,
                                                                                   ex);
    }
    catch (IllegalAccessException ex)
    {
      java.util.logging.Logger.getLogger(VentanaObservaciones.class.getName()).log(java.util.logging.Level.SEVERE, null,
                                                                                   ex);
    }
    catch (javax.swing.UnsupportedLookAndFeelException ex)
    {
      java.util.logging.Logger.getLogger(VentanaObservaciones.class.getName()).log(java.util.logging.Level.SEVERE, null,
                                                                                   ex);
    }
    //</editor-fold>

  }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTable tablaObservaciones;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables

}
