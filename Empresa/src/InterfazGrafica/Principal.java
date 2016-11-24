
package InterfazGrafica;

import Controladora.Controladora;
import Controladora.InterfazException;
import Controladora.InterfazPrincipal;
import empresa.Empleado;
import empresa.Empresa;
import empresa.Material;
import empresa.Pedido;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * clase Principal
 * Interfaz principal del programa donde pueden acceder
 * todos los empleados y visualizar los diferentes pedidos
 * y su estado. Ademas ofrece todas las operaciones a realizar,
 * restringiendo el acceso a las mismas de acuerdo con la
 * autorizacion de cada usuario.
 */
public class Principal 
  extends javax.swing.JFrame implements InterfazPrincipal
{
    private Empresa empresa;
    private Empleado usuario;
       

    public Principal(Empleado usuario, Empresa empresa){
        assert (usuario != null) : ("Usuario nulo");
        assert (empresa != null) : ("Empresa nula");
        this.usuario = usuario;
        this.empresa = empresa;
        initComponents();
        setLocationRelativeTo(null);
        inicializar();
        refresh();
    }
    
    /*
     * Getters para eventual test de GUI
     */


    public JButton getAceptPedido() {
        return aceptPedido;
    }

    public JButton getAdmProductos() {
        return admProductos;
    }

    public JTextField getAyn() {
        return ayn;
    }

    public JButton getCancelar() {
        return cancelar;
    }

    public JTextArea getDescPedido() {
        return descPedido;
    }

    public JButton getDeslog() {
        return deslog;
    }

    public JButton getFaltantes() {
        return faltantes;
    }

    public JCheckBox getFiltro_aceptado() {
        return filtro_aceptado;
    }

    public JCheckBox getFiltro_cancelado() {
        return filtro_cancelado;
    }

    public JCheckBox getFiltro_evaluacion() {
        return filtro_evaluacion;
    }

    public JCheckBox getFiltro_iniciado() {
        return filtro_iniciado;
    }

    public JButton getGenLote() {
        return genLote;
    }

    public JButton getMatNecesarios() {
        return matNecesarios;
    }

    public JTextField getNLeg() {
        return nLeg;
    }

    public JButton getNuevoPedido() {
        return nuevoPedido;
    }

    public JButton getObservaciones() {
        return observaciones;
    }

    public JTextField getSector() {
        return sector;
    }

    public JTable getTablaPedidos() {
        return tablaPedidos;
    }

    public JTable getTablaStock() {
        return tablaStock;
    }

    /*
     * **************************
     */
    
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
        assert(c != null) : ("Contoladora nula");
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
    public Calendar getFecha()
        throws InterfazException, ParseException
    {
        String str = JOptionPane.showInputDialog(null, "Ingrese la fecha propuesta de producción. (Formato: AAAA/MM/DD)",
                                                 "GuiLeoCrisAl S.A.", JOptionPane.INFORMATION_MESSAGE);
        if(str == null)
            throw new InterfazException("CANCEL");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar fecha = Calendar.getInstance();
        fecha.setTime(sdf.parse(str));
        if(fecha.before(GregorianCalendar.getInstance()))
            throw new InterfazException("Fecha en el pasado.");
        return fecha;
    }
    
    @Override
    public Pedido pedidoSeleccionado()
        throws InterfazException
    {
        int row = tablaPedidos.getSelectedRow();
        if(row == -1)
            throw new InterfazException("No se ha seleccionado ningun pedido.");
        return (Pedido) tablaPedidos.getValueAt(row, 0);
    }
    
    /**
     * metodo inicializar
     * Inicializa los diferentes componentes de la interfaz.
     * De acuerdo a los servicios que tiene autorizado el usuario
     * habilita o no los diferentes botones de la interfaz de
     * usuario.
     */
    private void inicializar(){
        inicializarTablas();
        deslog.setActionCommand(InterfazPrincipal.DESLOG);
        nuevoPedido.setActionCommand(InterfazPrincipal.INIC_PED);
        aceptPedido.setActionCommand(InterfazPrincipal.EVAL_PED);
        genLote.setActionCommand(InterfazPrincipal.ACEPT_PED);
        observaciones.setActionCommand(InterfazPrincipal.OBS);
        admProductos.setActionCommand(InterfazPrincipal.ADM_PROD);
        faltantes.setActionCommand(InterfazPrincipal.MAT_FALT);
        matNecesarios.setActionCommand(InterfazPrincipal.MAT_NEC);
        cancelar.setActionCommand(InterfazPrincipal.CANC);
        nuevoPedido.setEnabled(false);
        aceptPedido.setEnabled(false);
        genLote.setEnabled(false);
        observaciones.setEnabled(false);
        admProductos.setEnabled(false);
        matNecesarios.setEnabled(false);
        faltantes.setEnabled(false);
        cancelar.setEnabled(false);
        if(usuario.autorizaOperacion(Empresa.OP_INIC_PED))
            nuevoPedido.setEnabled(true);
        if(usuario.autorizaOperacion(Empresa.OP_ACEPT_PED))
            aceptPedido.setEnabled(true);
        if(usuario.autorizaOperacion(Empresa.OP_GEN_LOTE))
            genLote.setEnabled(true);
        if(usuario.autorizaOperacion(Empresa.OP_OBSERVAR))
            observaciones.setEnabled(true);
        if(usuario.autorizaOperacion(Empresa.OP_MOD_RECETA))
            admProductos.setEnabled(true);
        if(usuario.autorizaOperacion(Empresa.OP_MAT_NECEC))
            matNecesarios.setEnabled(true);
        if(usuario.autorizaOperacion(Empresa.OP_FALTANTES))
            faltantes.setEnabled(true);
        if(usuario.autorizaOperacion(Empresa.OP_CANCELAR))
            cancelar.setEnabled(true);
        filtro_iniciado.setSelected(true);
        filtro_evaluacion.setSelected(true);
        filtro_aceptado.setSelected(true);
        filtro_cancelado.setSelected(true);
        descPedido.setText("");
    }
    
    /**
     * metodo inicializarTablas
     * Inicializa el formato de las tablas de la interfaz.
     */
    private void inicializarTablas(){
        tablaPedidos.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaPedidos.getColumnModel().getColumn(0).setMinWidth(0);
        tablaPedidos.getColumnModel().getColumn(0).setPreferredWidth(0);
        tablaStock.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaStock.getColumnModel().getColumn(0).setMinWidth(0);
        tablaStock.getColumnModel().getColumn(0).setPreferredWidth(0);
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);
        tablaPedidos.getColumnModel().getColumn(1).setCellRenderer(render);
        tablaPedidos.getColumnModel().getColumn(2).setCellRenderer(render);
        tablaPedidos.getColumnModel().getColumn(3).setCellRenderer(render);
        tablaPedidos.getColumnModel().getColumn(4).setCellRenderer(render);
        tablaPedidos.getColumnModel().getColumn(5).setCellRenderer(render);
        tablaPedidos.getColumnModel().getColumn(6).setCellRenderer(render);
        tablaPedidos.getColumnModel().getColumn(7).setCellRenderer(render);
        tablaPedidos.getColumnModel().getColumn(8).setCellRenderer(render);
        tablaPedidos.getColumnModel().getColumn(10).setCellRenderer(render);
        tablaStock.getColumnModel().getColumn(1).setCellRenderer(render);
        tablaStock.getColumnModel().getColumn(3).setCellRenderer(render);
    }
    
    public void refresh(){ 
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
        assert(str != null) : ("Cadena mensaje nula.");
        JTextArea comp = new JTextArea();
        comp.setText("Materiales necesarios: " + System.lineSeparator() + str);
        comp.setEditable(false);
        JOptionPane.showMessageDialog(null, comp,
                                      "GuiLeoCrisAl S.A.", JOptionPane.INFORMATION_MESSAGE);
    }
    
    @Override
    public void lanzarCartelConLista(Iterator it){
        assert(it != null) : ("Iterador lista nulo.");
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
    
    @Override
    public String motivoCancelacion()
        throws InterfazException
    {
        String motivo = JOptionPane.showInputDialog(null, "Ingrese el motivo de la cancelación (hasta 475 caracters)",
                                                    "GuiLeoCrisAl S.A.", JOptionPane.INFORMATION_MESSAGE);
        if(motivo == null)
            throw new InterfazException("CANCEL");
        if(motivo.isEmpty())
            throw new InterfazException("Motivo no valido (vacio).");
        if(motivo.length() > 475)
            throw new InterfazException("Motivo excede limite.");
        return "[MOTIVO CANCELACION]: " + motivo;
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
        descPedido.setText("");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        tablaPedidos.removeAll();
        DefaultTableModel model = (DefaultTableModel) tablaPedidos.getModel();
        model.setRowCount(0);
        Iterator<Pedido> itPed = empresa.getPedidos().values().iterator();
        while(itPed.hasNext()){
            Object row[] = new Object[11];
            Pedido auxP = itPed.next();
            if((filtro_iniciado.isSelected() && auxP.getEstado() == Pedido.INICIADO) ||
                (filtro_evaluacion.isSelected() && auxP.getEstado() == Pedido.EN_EVALUACION) ||
                (filtro_aceptado.isSelected() && auxP.getEstado() == Pedido.ACEPTADO) ||
                (filtro_cancelado.isSelected() && auxP.getEstado() == Pedido.CANCELADO)){
                row[0] = auxP;
                row[1] = String.format("PED%06d", auxP.getNroPedido());
                row[2] = sdf.format(auxP.getFechaPedido().getTime());
                row[3] = String.format("TIP%06d", auxP.getMaquina().getCodigo());
                row[4] = String.format("%03d", auxP.getCantidad());
                row[5] = sdf.format(auxP.getFechaEntrega().getTime());
                row[6] = auxP.getFechaPropProduccion() == null ? "" : sdf.format(auxP.getFechaPropProduccion().getTime());
                row[7] = auxP.getFechaDefinitiva() == null ? "" : sdf.format(auxP.getFechaDefinitiva().getTime());
                row[8] = auxP.getFechaAceptacion() == null ? "" : sdf.format(auxP.getFechaAceptacion().getTime());
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
                    case Pedido.CANCELADO:
                        estado = "CANCELADO";
                        break;
                    default:
                        break;
                }
                row[9] = estado;
                row[10] = "" + (auxP.getNroLote() == -1 ? "" : String.format("LOT%06d", auxP.getNroLote()));
                model.addRow(row);
            }
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
        filtro_iniciado = new javax.swing.JCheckBox();
        filtro_evaluacion = new javax.swing.JCheckBox();
        filtro_aceptado = new javax.swing.JCheckBox();
        filtro_cancelado = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        descPedido = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaStock = new javax.swing.JTable();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GuiLeoCrisAl S.A");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion de usuario"));

        deslog.setText("Cerrar Sesion");

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

        aceptPedido.setText("Iniciar evaluacion pedido");

        genLote.setText("Aceptar pedido y Generar lote");

        observaciones.setText("Adm. Observaciones pedido");

        admProductos.setText("Administrar productos");

        faltantes.setText("Consultar faltantes");

        matNecesarios.setText("Consultar materiales pedido");

        cancelar.setText("Cancelar pedido");

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
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Sel", "Numero", "F. Inic", "Tipo Maq.", "Cant.", "F. Ent. Ventas", "F. Ent. Prod.", "F. Definitiva", "F. Acept.", "Estado", "Nro. Lote"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaPedidos.setGridColor(java.awt.SystemColor.activeCaption);
        tablaPedidos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaPedidos.setShowVerticalLines(false);
        tablaPedidos.getTableHeader().setReorderingAllowed(false);
        tablaPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaPedidosMouseReleased(evt);
            }
        });
        tablaPedidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaPedidosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaPedidos);
        if (tablaPedidos.getColumnModel().getColumnCount() > 0) {
            tablaPedidos.getColumnModel().getColumn(0).setResizable(false);
            tablaPedidos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tablaPedidos.getColumnModel().getColumn(0).setHeaderValue("Sel");
            tablaPedidos.getColumnModel().getColumn(1).setResizable(false);
            tablaPedidos.getColumnModel().getColumn(1).setPreferredWidth(75);
            tablaPedidos.getColumnModel().getColumn(1).setHeaderValue("Numero");
            tablaPedidos.getColumnModel().getColumn(2).setResizable(false);
            tablaPedidos.getColumnModel().getColumn(2).setPreferredWidth(80);
            tablaPedidos.getColumnModel().getColumn(2).setHeaderValue("F. Inic");
            tablaPedidos.getColumnModel().getColumn(3).setResizable(false);
            tablaPedidos.getColumnModel().getColumn(3).setPreferredWidth(75);
            tablaPedidos.getColumnModel().getColumn(3).setHeaderValue("Tipo Maq.");
            tablaPedidos.getColumnModel().getColumn(4).setResizable(false);
            tablaPedidos.getColumnModel().getColumn(4).setPreferredWidth(75);
            tablaPedidos.getColumnModel().getColumn(4).setHeaderValue("Cant.");
            tablaPedidos.getColumnModel().getColumn(5).setResizable(false);
            tablaPedidos.getColumnModel().getColumn(5).setPreferredWidth(80);
            tablaPedidos.getColumnModel().getColumn(5).setHeaderValue("F. Ent. Ventas");
            tablaPedidos.getColumnModel().getColumn(6).setResizable(false);
            tablaPedidos.getColumnModel().getColumn(6).setPreferredWidth(80);
            tablaPedidos.getColumnModel().getColumn(6).setHeaderValue("F. Ent. Prod.");
            tablaPedidos.getColumnModel().getColumn(7).setResizable(false);
            tablaPedidos.getColumnModel().getColumn(7).setPreferredWidth(80);
            tablaPedidos.getColumnModel().getColumn(7).setHeaderValue("F. Definitiva");
            tablaPedidos.getColumnModel().getColumn(8).setResizable(false);
            tablaPedidos.getColumnModel().getColumn(8).setPreferredWidth(80);
            tablaPedidos.getColumnModel().getColumn(8).setHeaderValue("F. Acept.");
            tablaPedidos.getColumnModel().getColumn(9).setResizable(false);
            tablaPedidos.getColumnModel().getColumn(9).setPreferredWidth(100);
            tablaPedidos.getColumnModel().getColumn(9).setHeaderValue("Estado");
            tablaPedidos.getColumnModel().getColumn(10).setResizable(false);
            tablaPedidos.getColumnModel().getColumn(10).setPreferredWidth(75);
            tablaPedidos.getColumnModel().getColumn(10).setHeaderValue("Nro. Lote");
        }

        filtro_iniciado.setText("Iniciado");
        filtro_iniciado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtro_iniciadoActionPerformed(evt);
            }
        });

        filtro_evaluacion.setText("En evaluación");
        filtro_evaluacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtro_evaluacionActionPerformed(evt);
            }
        });

        filtro_aceptado.setText("Aceptado");
        filtro_aceptado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtro_aceptadoActionPerformed(evt);
            }
        });

        filtro_cancelado.setText("Cancelado");
        filtro_cancelado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtro_canceladoActionPerformed(evt);
            }
        });

        jLabel5.setText("Filtro estado:");

        descPedido.setEditable(false);
        descPedido.setColumns(20);
        descPedido.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        descPedido.setRows(5);
        jScrollPane3.setViewportView(descPedido);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(filtro_iniciado)
                .addGap(34, 34, 34)
                .addComponent(filtro_evaluacion)
                .addGap(29, 29, 29)
                .addComponent(filtro_aceptado)
                .addGap(35, 35, 35)
                .addComponent(filtro_cancelado)
                .addGap(156, 156, 156))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 993, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filtro_iniciado)
                    .addComponent(filtro_evaluacion)
                    .addComponent(filtro_aceptado)
                    .addComponent(filtro_cancelado)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        tablaStock.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaStock.setShowVerticalLines(false);
        jScrollPane2.setViewportView(tablaStock);
        if (tablaStock.getColumnModel().getColumnCount() > 0) {
            tablaStock.getColumnModel().getColumn(0).setResizable(false);
            tablaStock.getColumnModel().getColumn(0).setPreferredWidth(0);
            tablaStock.getColumnModel().getColumn(0).setHeaderValue("Sel");
            tablaStock.getColumnModel().getColumn(1).setResizable(false);
            tablaStock.getColumnModel().getColumn(1).setPreferredWidth(100);
            tablaStock.getColumnModel().getColumn(1).setHeaderValue("Codigo");
            tablaStock.getColumnModel().getColumn(2).setResizable(false);
            tablaStock.getColumnModel().getColumn(2).setPreferredWidth(600);
            tablaStock.getColumnModel().getColumn(2).setHeaderValue("Descripcion");
            tablaStock.getColumnModel().getColumn(3).setResizable(false);
            tablaStock.getColumnModel().getColumn(3).setPreferredWidth(100);
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
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

    /**
     * metodo interno de la interfaz, al seleccionar un pedido
     * en la tabla de pedidos permite visualizar el detalle del
     * mismo en un area de texto.
     */
    private void tablaPedidosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPedidosMouseReleased
        // TODO add your handling code here:
        int row = tablaPedidos.getSelectedRow();
        if(row != -1)
            descPedido.setText(((Pedido)tablaPedidos.getValueAt(row, 0)).toString());
    }//GEN-LAST:event_tablaPedidosMouseReleased

    /**
     * metodo interno de la interfaz, ante la seleccion o 
     * deseleccion del checkbox hace que se refresquen las
     * tablas de la interfaz.
     */
    private void filtro_iniciadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtro_iniciadoActionPerformed
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_filtro_iniciadoActionPerformed

    /**
     * metodo interno de la interfaz, ante la seleccion o 
     * deseleccion del checkbox hace que se refresquen las
     * tablas de la interfaz.
     */
    private void filtro_evaluacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtro_evaluacionActionPerformed
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_filtro_evaluacionActionPerformed

    /**
     * metodo interno de la interfaz, ante la seleccion o 
     * deseleccion del checkbox hace que se refresquen las
     * tablas de la interfaz.
     */
    private void filtro_aceptadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtro_aceptadoActionPerformed
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_filtro_aceptadoActionPerformed

    /**
     * metodo interno de la interfaz, ante la seleccion o 
     * deseleccion del checkbox hace que se refresquen las
     * tablas de la interfaz.
     */
    private void filtro_canceladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtro_canceladoActionPerformed
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_filtro_canceladoActionPerformed

    /**
     * metodo interno de la interfaz, al seleccionar un pedido
     * en la tabla de pedidos permite visualizar el detalle del
     * mismo en un area de texto. En este caso se programa el 
     * evento cuando se cambia la seleccion con el teclado.
     */
  private void tablaPedidosKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_tablaPedidosKeyReleased
  {//GEN-HEADEREND:event_tablaPedidosKeyReleased
    // TODO add your handling code here:
    int row = tablaPedidos.getSelectedRow();
    if(row != -1)
        descPedido.setText(((Pedido)tablaPedidos.getValueAt(row, 0)).toString());
  }//GEN-LAST:event_tablaPedidosKeyReleased

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
    private javax.swing.JTextArea descPedido;
    private javax.swing.JButton deslog;
    private javax.swing.JButton faltantes;
    private javax.swing.JCheckBox filtro_aceptado;
    private javax.swing.JCheckBox filtro_cancelado;
    private javax.swing.JCheckBox filtro_evaluacion;
    private javax.swing.JCheckBox filtro_iniciado;
    private javax.swing.JButton genLote;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton matNecesarios;
    private javax.swing.JTextField nLeg;
    private javax.swing.JButton nuevoPedido;
    private javax.swing.JButton observaciones;
    private javax.swing.JTextField sector;
    private javax.swing.JTable tablaPedidos;
    private javax.swing.JTable tablaStock;
    // End of variables declaration//GEN-END:variables

}
