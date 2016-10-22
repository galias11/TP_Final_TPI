
package InterfazGrafica;

import Controladora.Controladora;
import Controladora.InterfazPrincipal;

import empresa.Empleado;
import empresa.Empresa;

import empresa.EmpresaException;
import empresa.Material;
import empresa.Operacion;
import empresa.Pedido;

import java.awt.Dimension;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Iterator;

import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author bruno
 */
public class Principal 
  extends javax.swing.JFrame implements InterfazPrincipal
{
    Empresa empresa;
    Empleado usuario;
       

    public Principal(Empleado usuario, Empresa empresa){
        assert (usuario != null) : ("Usuario nulo");
        assert (empresa != null) : ("Empresa nula");
        this.usuario = usuario;
        this.empresa = empresa;
        initComponents();
        inicializar();
        refresh();
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
    public void setControlador(Controladora c){
        deslog.addActionListener(c);
        nuevoPedido.addActionListener(c);
        aceptPedido.addActionListener(c);
        genLote.addActionListener(c);
        observaciones.addActionListener(c);
        admProductos.addActionListener(c);
        matNecesarios.addActionListener(c);
        faltantes.addActionListener(c);
        cancelar.addActionListener(c);
    }
    
    @Override
    public Pedido pedidoSeleccionado(){
        int row = tablaPedidos.getSelectedRow();
        if(row == -1)
            return null;
        return (Pedido) tablaPedidos.getValueAt(row, 0);
    }
    
    private void inicializar(){
        inicializarTablas();
        deslog.setActionCommand(InterfazPrincipal.DESLOG);
        nuevoPedido.setActionCommand(InterfazPrincipal.NPED);
        aceptPedido.setActionCommand(InterfazPrincipal.APED);
        genLote.setActionCommand(InterfazPrincipal.GLOT);
        observaciones.setActionCommand(InterfazPrincipal.OBS);
        admProductos.setActionCommand(InterfazPrincipal.APROD);
        faltantes.setActionCommand(InterfazPrincipal.MATFALT);
        matNecesarios.setActionCommand(InterfazPrincipal.MATNEC);
        nuevoPedido.setEnabled(false);
        aceptPedido.setEnabled(false);
        genLote.setEnabled(false);
        observaciones.setEnabled(false);
        admProductos.setEnabled(false);
        matNecesarios.setEnabled(false);
        faltantes.setEnabled(false);
        cancelar.setEnabled(false);
        if(usuario.autorizaOperacion(Empresa.OP_INIPED))
            nuevoPedido.setEnabled(true);
        if(usuario.autorizaOperacion(Empresa.OP_ACEPTPED))
            aceptPedido.setEnabled(true);
        if(usuario.autorizaOperacion(Empresa.OP_GENLOTE))
            genLote.setEnabled(true);
        if(usuario.autorizaOperacion(Empresa.OP_OBSERVAR))
            observaciones.setEnabled(true);
        if(usuario.autorizaOperacion(Empresa.OP_MODREC))
            admProductos.setEnabled(true);
        if(usuario.autorizaOperacion(Empresa.OP_MATNEC))
            matNecesarios.setEnabled(true);
        if(usuario.autorizaOperacion(Empresa.OP_FALTANTES))
            faltantes.setEnabled(true);
        if(usuario.autorizaOperacion(Empresa.OP_CANCELAR))
            cancelar.setEnabled(true);
    }
    
    private void inicializarTablas(){
        tablaPedidos.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaPedidos.getColumnModel().getColumn(0).setMinWidth(0);
        tablaPedidos.getColumnModel().getColumn(0).setPreferredWidth(0);
        tablaStock.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaStock.getColumnModel().getColumn(0).setMinWidth(0);
        tablaStock.getColumnModel().getColumn(0).setPreferredWidth(0);
    }
    
    public void refresh(){
        inicializar();
        mostrarUsuario();
        mostrarPedidos();
        mostrarStock();
    }
    
    @Override
    public void cerrar(){
        this.dispose();
    }
    
    @Override
    public void lanzarCartel(String str){
        JTextArea comp = new JTextArea();
        comp.setText("Materiales necesarios: " + System.lineSeparator() + str);
        comp.setEditable(false);
        JOptionPane.showMessageDialog(null, comp,
                                      "GuiLeoCrisAl S.A.", JOptionPane.INFORMATION_MESSAGE);
    }
    
    @Override
    public void lanzarCartelConLista(Iterator it){
        Vector columns = new Vector();
        JTable tabla = new JTable();
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.setRowCount(0);
        model.addColumn("Codigo");
        model.addColumn("Descripcion");
        model.addColumn("Cantidad");
        Object row[] = new Object[3];
        while(it.hasNext()){
            Material auxM = (Material) it.next();
            row[0] = String.format("MAT%06d", auxM.getCodigoMaterial());
            row[1] = auxM.getDescripcion();
            row[2] = String.format("%4.3f", auxM.getCantidad());
            model.addRow(row);
        }
        JOptionPane.showMessageDialog(null, new JScrollPane(tabla),
                                      "GuiLeoCrisAl S.A.", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Metodo mostrarUsuario
     * Metodo privado de la ventana principal que actualiza los
     * datos del usuario logueado en el formulario.
     * PreCondicion:
     * El atributo user de la empresa no debe ser nulo.
     * PostCondicion:
     * Se actualiza el formulario con los datos del usuario activo.
     */
    private void mostrarUsuario(){
        assert(usuario != null) : ("Usuario nulo");
        nLeg.setText(String.format("LEG%06d", usuario.getLegajo()));
        ayn.setText(usuario.getAyn());
        sector.setText(usuario.getSector().getNombre());
    }
    
    /**
     * Metodo mostrarPedidos
     * Enlista en la tabla de pedidos todos los pedidos que tiene 
     * pendientes la empresa.
     * PreCondicion:
     * 
     * PostCondicion:
     * Los datos de todos los pedidos se muestran por pantalla.
     */
    private void mostrarPedidos(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        tablaPedidos.removeAll();
        DefaultTableModel model = (DefaultTableModel) tablaPedidos.getModel();
        model.setRowCount(0);
        Iterator<Pedido> itPed = empresa.getPedidos().values().iterator();
        while(itPed.hasNext()){
            Object row[] = new Object[10];
            Pedido auxP = itPed.next();
            row[0] = auxP;
            row[1] = String.format("PED%06d", auxP.getNroPedido());
            row[2] = sdf.format(auxP.getFechaPedido().getTime());
            row[3] = String.format("Cod: %d - %s", auxP.getMaquina().getCodigo(), auxP.getMaquina().getDescripcion());
            row[4] = sdf.format(auxP.getFechaEntrega().getTime());
            row[5] = auxP.getFechaPropProduccion() == null ? "" : sdf.format(auxP.getFechaPropProduccion().getTime());
            row[6] = auxP.getFechaDefinitiva() == null ? "" : sdf.format(auxP.getFechaDefinitiva().getTime());
            row[7] = auxP.getFechaAceptacion() == null ? "" : sdf.format(auxP.getFechaAceptacion().getTime());
            String estado = "";
            switch(auxP.getEstado()){
                case Pedido.ACEPTADO:
                    estado = "ACEPTADO";
                    break;
                case Pedido.EN_EVALUACION:
                    estado = "EN EVALUACION";
                    break;
                case Pedido.INICIADO:
                    estado = "INICIADO";
                    break;
                default:
                    break;
            }
            row[8] = estado;
            row[9] = "" + (auxP.getNroLote() == -1 ? "" : String.format("LOT%06d", auxP.getNroLote()));
            model.addRow(row);
        }
    }
    
    /**
     * Metodo: mostrarStock
     * Refresca el listado de Stock, que corresponde al inventario
     * disponible de la empresa.
     * PreCondicion:
     * 
     * PostCondicion:
     * Listado actualizado de acuerdo al inventario del atributo
     * empresa.
     */
    private void mostrarStock(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        tablaStock.removeAll();
        DefaultTableModel model = (DefaultTableModel) tablaStock.getModel();
        model.setRowCount(0);
        Iterator<Material> itMat = empresa.getInventario().values().iterator();
        while(itMat.hasNext()){
            Object row[] = new Object[4];
            Material auxM = itMat.next();
            row[0] = auxM;
            row[1] = String.format("MAT%05d", auxM.getCodigoMaterial());
            row[2] = auxM.getDescripcion();
            row[3] = String.format("%3.4f", auxM.getCantidad());
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

        jComboBox1 = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        deslog = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nLeg = new javax.swing.JTextField();
        ayn = new javax.swing.JTextField();
        sector = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        nuevoPedido = new javax.swing.JButton();
        aceptPedido = new javax.swing.JButton();
        genLote = new javax.swing.JButton();
        observaciones = new javax.swing.JButton();
        admProductos = new javax.swing.JButton();
        faltantes = new javax.swing.JButton();
        matNecesarios = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPedidos = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaStock = new javax.swing.JTable();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GuiLeoCrisAl S.A");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion de usuario"));

        deslog.setText("Cerrar Sesion");
        deslog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deslogActionPerformed(evt);
            }
        });

        jLabel1.setText("Legajo:");

        jLabel2.setText("Apellido y");

        jLabel3.setText("Nombre:");

        jLabel4.setText("Sector:");

        nLeg.setEditable(false);

        ayn.setEditable(false);

        sector.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deslog, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sector, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                            .addComponent(ayn)
                            .addComponent(nLeg))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nLeg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ayn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(sector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(deslog)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones"));

        nuevoPedido.setText("Nuevo Pedido");
        nuevoPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoPedidoActionPerformed(evt);
            }
        });

        aceptPedido.setText("Iniciar evaluacion pedido");
        aceptPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptPedidoActionPerformed(evt);
            }
        });

        genLote.setText("Aceptar pedido y Generar lote");
        genLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genLoteActionPerformed(evt);
            }
        });

        observaciones.setText("Adm. Observaciones pedido");
        observaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                observacionesActionPerformed(evt);
            }
        });

        admProductos.setText("Administrar productos");
        admProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admProductosActionPerformed(evt);
            }
        });

        faltantes.setText("Consultar faltantes");
        faltantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                faltantesActionPerformed(evt);
            }
        });

        matNecesarios.setText("Consultar materiales pedido");
        matNecesarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matNecesariosActionPerformed(evt);
            }
        });

        cancelar.setText("Cancelar pedido");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nuevoPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(aceptPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(genLote, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                    .addComponent(observaciones, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                    .addComponent(admProductos, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                    .addComponent(faltantes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                    .addComponent(matNecesarios, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                    .addComponent(cancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nuevoPedido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(aceptPedido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(genLote)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(observaciones)
                .addGap(76, 76, 76)
                .addComponent(matNecesarios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(faltantes)
                .addGap(36, 36, 36)
                .addComponent(admProductos)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedidos"));

        tablaPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Sel", "Numero", "Fecha", "Tipo de Maquina", "Fecha de Entrega por Ventas", "Fecha de Entrega por Produccion", "Fecha Definitiva", "Pedido Aceptadol", "Estado", "Numero de Lote"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaPedidos.getTableHeader().setReorderingAllowed(false);
        tablaPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaPedidosMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaPedidos);
        if (tablaPedidos.getColumnModel().getColumnCount() > 0) {
            tablaPedidos.getColumnModel().getColumn(0).setResizable(false);
            tablaPedidos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tablaPedidos.getColumnModel().getColumn(0).setHeaderValue("Sel");
            tablaPedidos.getColumnModel().getColumn(1).setHeaderValue("Numero");
            tablaPedidos.getColumnModel().getColumn(2).setHeaderValue("Fecha");
            tablaPedidos.getColumnModel().getColumn(3).setHeaderValue("Tipo de Maquina");
            tablaPedidos.getColumnModel().getColumn(4).setHeaderValue("Fecha de Entrega por Ventas");
            tablaPedidos.getColumnModel().getColumn(5).setHeaderValue("Fecha de Entrega por Produccion");
            tablaPedidos.getColumnModel().getColumn(6).setHeaderValue("Fecha Definitiva");
            tablaPedidos.getColumnModel().getColumn(7).setHeaderValue("Pedido Aceptadol");
            tablaPedidos.getColumnModel().getColumn(8).setHeaderValue("Estado");
            tablaPedidos.getColumnModel().getColumn(9).setHeaderValue("Numero de Lote");
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 993, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Stock de Materiales"));

        tablaStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Sel", "Codigo", "Descripcion", "Cantidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaStockMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tablaStock);
        if (tablaStock.getColumnModel().getColumnCount() > 0) {
            tablaStock.getColumnModel().getColumn(0).setResizable(false);
            tablaStock.getColumnModel().getColumn(0).setPreferredWidth(0);
            tablaStock.getColumnModel().getColumn(0).setHeaderValue("Sel");
            tablaStock.getColumnModel().getColumn(1).setHeaderValue("Codigo");
            tablaStock.getColumnModel().getColumn(2).setHeaderValue("Descripcion");
            tablaStock.getColumnModel().getColumn(3).setHeaderValue("Cantidad");
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }//GEN-END:initComponents

  private void deslogActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_deslogActionPerformed
  {//GEN-HEADEREND:event_deslogActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_deslogActionPerformed

    private void genLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genLoteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genLoteActionPerformed

    private void observacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_observacionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_observacionesActionPerformed

    private void admProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admProductosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_admProductosActionPerformed

    private void tablaPedidosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPedidosMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaPedidosMouseReleased

    private void tablaStockMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaStockMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaStockMouseReleased

    private void nuevoPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoPedidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nuevoPedidoActionPerformed

    private void aceptPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptPedidoActionPerformed
        // TODO add your handling code here:
 
    }//GEN-LAST:event_aceptPedidoActionPerformed

    private void faltantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_faltantesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_faltantesActionPerformed

    private void matNecesariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matNecesariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_matNecesariosActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelarActionPerformed

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
      java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null,
                                                                                ex);
    }
    catch (InstantiationException ex)
    {
      java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null,
                                                                                ex);
    }
    catch (IllegalAccessException ex)
    {
      java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null,
                                                                                ex);
    }
    catch (javax.swing.UnsupportedLookAndFeelException ex)
    {
      java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null,
                                                                                ex);
    }
    //</editor-fold>

  }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptPedido;
    private javax.swing.JButton admProductos;
    private javax.swing.JTextField ayn;
    private javax.swing.JButton cancelar;
    private javax.swing.JButton deslog;
    private javax.swing.JButton faltantes;
    private javax.swing.JButton genLote;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton matNecesarios;
    private javax.swing.JTextField nLeg;
    private javax.swing.JButton nuevoPedido;
    private javax.swing.JButton observaciones;
    private javax.swing.JTextField sector;
    private javax.swing.JTable tablaPedidos;
    private javax.swing.JTable tablaStock;
    // End of variables declaration//GEN-END:variables

}
