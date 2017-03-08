/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.seinfo.screen;

import java.sql.*;
import br.com.seinfo.dal.Module_Connection;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author daniel
 */
public class Screen_OS extends javax.swing.JInternalFrame {

    
    Connection connection;
    PreparedStatement pst;
    ResultSet rs;
    
    // Create variable radioButton
    private String tipo;
    
    
    
    /**
     * Creates new form Screen_OS
     */
    public Screen_OS() {
        initComponents();
        //Start autentic BD
        connection = Module_Connection.autentic();
    }

    private void pesquisar_clientes() {
    
        String sql = "select idcli as ID, nomecli as Nome, fonecli as Telefone "
                + "from tbclientes where nomecli like ?";
        
        try {
            //PreparedStatement
            
            pst = connection.prepareStatement(sql);
            // Search like%
            pst.setString(1, lbl_search.getText()+"%");
            // ResultSet
            rs = pst.executeQuery();
            
            //Show table
            tblcliente.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    // Setar campos
    
    private void setar_campos() {
    
        int setar = tblcliente.getSelectedRow();
        
        //Get Campos
        lbl_cod.setText(tblcliente.getModel().getValueAt(setar, 0).toString());
        
    }
    
    private void emitir_os() {

        String sql = "INSERT INTO tbos(tipo,situacao,equipamento,defeito, servico,"
                + "tecnico,valor,idcli) values(?,?,?,?,?,?,?,?)";
        
        try {
            //PreparedStatement Connection
            pst = connection.prepareStatement(sql);
            //Variable tipo radioButton
            pst.setString(1, tipo);
            pst.setString(2,cbo_situation.getSelectedItem().toString() );
            pst.setString(3, lbl_equip.getText());
            pst.setString(4, lbl_def.getText());
            pst.setString(5, lbl_serv.getText());
            pst.setString(6, lbl_tec.getText());
            // Case user ',' modified '.'
            pst.setString(7, lbl_value.getText().replace(",", "."));
            pst.setString(8, lbl_cod.getText());
            
            // Validation
            
            if (lbl_cod.getText().isEmpty() || lbl_equip.getText().isEmpty()
                    || lbl_def.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");
            } else {
                
                int add = pst.executeUpdate();
                if(add > 0){
                    JOptionPane.showMessageDialog(null, "OS emitida com sucesso!",
                            "Confirmação", JOptionPane.INFORMATION_MESSAGE);
                    
                    // Erase form
                    lbl_cod.setText(null);
                    lbl_equip.setText(null);
                    lbl_def.setText(null);
                    lbl_serv.setText(null);
                    lbl_tec.setText(null);
                    lbl_value.setText(null);
                }
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    
    // Method search the OS
    
    private void pesquisar_os(){
    
        String num_os = JOptionPane.showInputDialog("Número da OS");
    
        String sql = "SELECT * FROM tbos WHERE os= "+num_os;
 
        try {
            pst = connection.prepareStatement(sql);
            rs= pst.executeQuery();
            
            // Search 
            if(rs.next()){
                lbl_numOS.setText(rs.getString(1));
                lbl_data.setText(rs.getString(2));
                // New variable for radioButton
                String rbTipo = rs.getString(3);
                //IF tipo equals text OS 
                if (rbTipo.equals("OS")) {
                    rbtordem.setSelected(true);
                    tipo = "OS";
                } else {
                    rbtorc.setSelected(true);
                    tipo = "Orçamento";
                }
                
                
            } else {
                JOptionPane.showMessageDialog(null, "OS não cadastrada!");
            }
            
                cbo_situation.setSelectedItem(rs.getString(4));
                lbl_equip.setText(rs.getString(5));
                lbl_def.setText(rs.getString(6));
                lbl_serv.setText(rs.getString(7));
                lbl_tec.setText(rs.getString(8));
                lbl_value.setText(rs.getString(9));
                lbl_cod.setText(rs.getString(10));
                
                // Evite problem
                btn_add.setEnabled(false);
                lbl_search.setEnabled(false);
                tblcliente.setVisible(false);
                
            
        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException e) {
            JOptionPane.showMessageDialog(null, "OS Inválida");
           // System.out.println(e);
        }catch (Exception e2){
            JOptionPane.showMessageDialog(null, e2);
        }
        
    }
    
    //Alter OS
    
    private void alterar() {
    
        String sql = "UPDATE tbos SET tipo=?,situacao=?,equipamento=?,defeito=?,"
                + "servico=?,tecnico=?,valor=? WHERE os=?";
         try {
            //PreparedStatement Connection
            pst = connection.prepareStatement(sql);
            //Variable tipo radioButton
            pst.setString(1, tipo);
            pst.setString(2,cbo_situation.getSelectedItem().toString() );
            pst.setString(3, lbl_equip.getText());
            pst.setString(4, lbl_def.getText());
            pst.setString(5, lbl_serv.getText());
            pst.setString(6, lbl_tec.getText());
            // Case user ',' modified '.'
            pst.setString(7, lbl_value.getText().replace(",", "."));
            pst.setString(8, lbl_numOS.getText());
            
            // Validation
            
            if (lbl_cod.getText().isEmpty() || lbl_equip.getText().isEmpty()
                    || lbl_def.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");
            } else {
                
                int add = pst.executeUpdate();
                if(add > 0){
                    JOptionPane.showMessageDialog(null, "OS alterada com sucesso!",
                            "Confirmação", JOptionPane.INFORMATION_MESSAGE);
                    
                    // Erase form
                    lbl_numOS.setText(null);
                    lbl_data.setText(null);
                    lbl_cod.setText(null);
                    lbl_equip.setText(null);
                    lbl_def.setText(null);
                    lbl_serv.setText(null);
                    lbl_tec.setText(null);
                    lbl_value.setText(null);
                    
                    // Enable object
                    btn_add.setEnabled(true);
                    lbl_search.setEnabled(true);
                    tblcliente.setVisible(true);
                    
                    
                }
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    
    }
    
    private void excluir_os() {
    
        int confim = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir OS!",
                "Confirmação", JOptionPane.YES_NO_OPTION);
        
        if (confim == JOptionPane.YES_OPTION) {
            
            String sql = "DELETE * FROM tbos WHERE os=?";
            
            try {
                pst = connection.prepareStatement(sql);
                pst.setString(1, lbl_numOS.getText());
                int apagado = pst.executeUpdate();
                
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Excluido com sucesso",
                            "Excluido", JOptionPane.YES_NO_OPTION);
                    
                      // Erase form
                    lbl_numOS.setText(null);
                    lbl_data.setText(null);
                    lbl_cod.setText(null);
                    lbl_equip.setText(null);
                    lbl_def.setText(null);
                    lbl_serv.setText(null);
                    lbl_tec.setText(null);
                    lbl_value.setText(null);
                    
                    // Enable object
                    btn_add.setEnabled(true);
                    lbl_search.setEnabled(true);
                    tblcliente.setVisible(true);
                    
                    
                    
                }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            
        } 
        
    }
    
    // Method Print
    private void imprimir() {
    
         //Generated form client
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a impressão "
                + "desse relatório na tela?","Atenção", JOptionPane.YES_NO_OPTION);
        
        if (confirma == JOptionPane.YES_OPTION) {
            // Print form clients
            try {
                // Class create filter HashMap critery for print
                HashMap filter = new HashMap();
                filter.put("os", Integer.parseInt(lbl_numOS.getText()));
                
                
                // Class JasPerPrint prepared print
                JasperPrint print = JasperFillManager.fillReport(
                        "/home/daniel/Documents/project_osinfoX/reports/os.jasper",
                        filter,connection);
                
                // View form after print
                JasperViewer.viewReport(print, false);
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl_numOS = new javax.swing.JTextField();
        lbl_data = new javax.swing.JTextField();
        rbtorc = new javax.swing.JRadioButton();
        rbtordem = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        cbo_situation = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        lbl_search = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblcliente = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        lbl_cod = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbl_equip = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        lbl_def = new javax.swing.JTextArea();
        lbl_tec = new javax.swing.JTextField();
        lbl_serv = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        lbl_value = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_add = new javax.swing.JButton();
        btn_print = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Ordem de Serviço");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("NºOS");

        jLabel2.setText("Data");

        lbl_numOS.setEditable(false);
        lbl_numOS.setEnabled(false);

        lbl_data.setEditable(false);
        lbl_data.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_data.setEnabled(false);

        buttonGroup1.add(rbtorc);
        rbtorc.setText("Orçamento");
        rbtorc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtorcActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtordem);
        rbtordem.setText("Ordem de Serviço");
        rbtordem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtordemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rbtorc)
                        .addGap(20, 20, 20)
                        .addComponent(rbtordem))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(78, 78, 78)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbl_numOS)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_data, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_numOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtorc)
                    .addComponent(rbtordem))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setText("Situação");

        cbo_situation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Na Bancada", "Entrega OK", "Orçamento Cancelado", "Aguardando Aprovação", "Aguardando Peças", "Abandonado pelo Cliente", "Retorno para Revisão" }));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        lbl_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbl_searchKeyReleased(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/seinfo/icons/1488449320_search.png"))); // NOI18N

        tblcliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Código", "Nome", "Telefone"
            }
        ));
        tblcliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblclienteMouseClicked(evt);
            }
        });
        tblcliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblclienteKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblcliente);

        jLabel5.setText("Cód");

        lbl_cod.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbl_search, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(4, 4, 4)
                        .addComponent(lbl_cod)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_cod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setText("Equipamento");

        jLabel7.setText("Defeito");

        jLabel8.setText("Serviço");

        jLabel9.setText("Técnico");

        lbl_def.setColumns(20);
        lbl_def.setRows(5);
        jScrollPane2.setViewportView(lbl_def);

        jLabel10.setText("Valor Total");

        lbl_value.setText("0");

        btn_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/seinfo/icons/1488353712_Search-Male-User.png"))); // NOI18N
        btn_search.setText("Pesquisar");
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });

        btn_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/seinfo/icons/1488353876_Edit-Male-User.png"))); // NOI18N
        btn_save.setText("Salvar");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        btn_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/seinfo/icons/1488353722_Remove-Male-User.png"))); // NOI18N
        btn_delete.setText("Remover");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/seinfo/icons/1488353700_Add-Male-User.png"))); // NOI18N
        btn_add.setText("Adicionar");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        btn_print.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/seinfo/icons/printer.png"))); // NOI18N
        btn_print.setText("Imprimir");
        btn_print.setToolTipText("imprimir OS");
        btn_print.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        btn_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_printActionPerformed(evt);
            }
        });

        jLabel11.setForeground(new java.awt.Color(225, 1, 1));
        jLabel11.setText("*");

        jLabel12.setForeground(new java.awt.Color(225, 1, 1));
        jLabel12.setText("*");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbo_situation, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 425, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_value, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lbl_equip, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                                    .addComponent(lbl_tec, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)))
                            .addComponent(lbl_serv, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 157, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(btn_search)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_save)
                                .addGap(18, 18, 18)
                                .addComponent(btn_delete)
                                .addGap(18, 18, 18)
                                .addComponent(btn_add)
                                .addGap(18, 18, 18)
                                .addComponent(btn_print)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(22, 22, 22)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbo_situation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(17, 17, 17)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(lbl_tec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(lbl_equip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(lbl_serv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_value, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_search)
                    .addComponent(btn_save)
                    .addComponent(btn_delete)
                    .addComponent(btn_add)
                    .addComponent(btn_print))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        setBounds(0, 0, 750, 660);
    }// </editor-fold>//GEN-END:initComponents

    private void tblclienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblclienteKeyReleased
     
    }//GEN-LAST:event_tblclienteKeyReleased

    private void lbl_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbl_searchKeyReleased
        
        // Digits release search client
        pesquisar_clientes();
    }//GEN-LAST:event_lbl_searchKeyReleased

    private void tblclienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblclienteMouseClicked
       //Call method setar_campos
       setar_campos();
       
    }//GEN-LAST:event_tblclienteMouseClicked

    private void rbtorcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtorcActionPerformed
        //Get text radioButton if selected
        tipo = "Orçamento";
        
    }//GEN-LAST:event_rbtorcActionPerformed

    private void rbtordemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtordemActionPerformed
        // Get text radioButton if selected
        tipo = "OS";
        
    }//GEN-LAST:event_rbtordemActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // Opened screen mark radioButton
        rbtorc.setSelected(true);
        tipo = "Orçamento";
    }//GEN-LAST:event_formInternalFrameOpened

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        //Call method
        emitir_os();
    }//GEN-LAST:event_btn_addActionPerformed

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        pesquisar_os();
    }//GEN-LAST:event_btn_searchActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        alterar();
    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
       excluir_os();
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed
        //Call method print
        imprimir();
    }//GEN-LAST:event_btn_printActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_print;
    private javax.swing.JButton btn_save;
    private javax.swing.JButton btn_search;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbo_situation;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField lbl_cod;
    private javax.swing.JTextField lbl_data;
    private javax.swing.JTextArea lbl_def;
    private javax.swing.JTextField lbl_equip;
    private javax.swing.JTextField lbl_numOS;
    private javax.swing.JTextField lbl_search;
    private javax.swing.JTextField lbl_serv;
    private javax.swing.JTextField lbl_tec;
    private javax.swing.JTextField lbl_value;
    private javax.swing.JRadioButton rbtorc;
    private javax.swing.JRadioButton rbtordem;
    private javax.swing.JTable tblcliente;
    // End of variables declaration//GEN-END:variables
}
