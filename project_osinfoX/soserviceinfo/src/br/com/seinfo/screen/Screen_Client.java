/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.seinfo.screen;

import br.com.seinfo.dal.Module_Connection;
import java.sql.*;
import javax.swing.JOptionPane;
//Import rs2xml
import net.proteanit.sql.DbUtils;

/**
 *
 * @author daniel
 */
public class Screen_Client extends javax.swing.JInternalFrame {

    /**
     * Creates new form Screen_Client
     */
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    // Isert in Db
    public void adicionar() {

        // Form labels *
        if ((lbl_nome.getText().isEmpty()) || (lbl_fone.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios!",
                    "Erro ao adicionar", JOptionPane.ERROR_MESSAGE);
        } else {

            String sql = "INSERT INTO tbclientes(nomecli,endecli,fonecli,emailcli) "
                    + "values(?,?,?,?)";

            try {

                pst = connection.prepareStatement(sql);
                //PreparedStatement
                pst.setString(1, lbl_nome.getText());
                pst.setString(2, lbl_end.getText());
                pst.setString(3, lbl_fone.getText());
                pst.setString(4, lbl_email.getText());

                int add = pst.executeUpdate();

                // If add == 1 record in BD
                if (add > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso!",
                            "Adicionado", JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
    }

    // Method search clients for name
    private void pesquisar_cliente() {

        String sql = "SELECT * FROM tbclientes WHERE nomecli like ?";

        try {
            // Prepared Connection
            pst = connection.prepareStatement(sql);
            // Call for table result tbclientes
            // Caution % in continue of String

            pst.setString(1, lbl_search.getText() + "%");
            rs = pst.executeQuery();

            //Bibliotec use rs2xml
            tbl_cli.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void setar_campos() {

        int setar = tbl_cli.getSelectedRow();

        lbl_id.setText(tbl_cli.getModel().getValueAt(setar, 0).toString());
        lbl_nome.setText(tbl_cli.getModel().getValueAt(setar, 1).toString());
        lbl_end.setText(tbl_cli.getModel().getValueAt(setar, 2).toString());
        lbl_fone.setText(tbl_cli.getModel().getValueAt(setar, 3).toString());
        lbl_email.setText(tbl_cli.getModel().getValueAt(setar, 4).toString());

        // Unable set add
        btn_add.setEnabled(false);

    }

    private void alterar() {

        if ((lbl_nome.getText().isEmpty()) || (lbl_fone.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios!",
                    "Erro ao adicionar", JOptionPane.ERROR_MESSAGE);
        } else {

            String sql = "UPDATE tbclientes SET nomecli=?,endecli=?,fonecli=?,emailcli=? "
                    + "WHERE idcli=?";

            try {
                pst = connection.prepareStatement(sql);

                pst.setString(1, lbl_nome.getText());
                pst.setString(2, lbl_end.getText());
                pst.setString(3, lbl_fone.getText());
                pst.setString(4, lbl_email.getText());
                // Critery for update
                pst.setString(5, lbl_id.getText());

                int add = pst.executeUpdate();

                if (add > 0) {
                    JOptionPane.showMessageDialog(null, "Modificação salva com sucesso",
                            "Editado", JOptionPane.INFORMATION_MESSAGE);

                    // Erase form 
                    lbl_nome.setText(null);
                    lbl_end.setText(null);
                    lbl_fone.setText(null);
                    lbl_email.setText(null);

                    //Enable button add
                    btn_add.setEnabled(true);

                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
    }

    private void remover() {

        int confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir cliente",
                "Confirmação exclusão", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                String sql = "DELETE FROM tbclientes WHERE idcli=?";
                pst = connection.prepareStatement(sql);
                pst.setString(1, lbl_id.getText());
                int del = pst.executeUpdate();

                if (del > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário excluido com sucesso!",
                            "Exclusão Confirmada", JOptionPane.INFORMATION_MESSAGE);

                    // Erase form 
                    lbl_nome.setText(null);
                    lbl_end.setText(null);
                    lbl_fone.setText(null);
                    lbl_email.setText(null);

                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }

    public Screen_Client() {
        initComponents();
        //Start BD in class package DAL
        connection = Module_Connection.autentic();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl_nome = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl_fone = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lbl_email = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btn_edit = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();
        btn_add = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbl_end = new javax.swing.JTextField();
        lbl_search = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_cli = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        lbl_id = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro de Cliente");
        setPreferredSize(new java.awt.Dimension(750, 660));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Cuprum", 3, 32)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(37, 105, 255));
        jLabel6.setText("Cadastro Clientes");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(229, 229, 229)
                .addComponent(jLabel6)
                .addContainerGap(221, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel6)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jLabel2.setText("Nome");

        jLabel10.setForeground(new java.awt.Color(208, 19, 8));
        jLabel10.setText("*");

        jLabel7.setText("Telefone");

        jLabel3.setText("Email");

        jLabel11.setForeground(new java.awt.Color(208, 19, 8));
        jLabel11.setText("*");

        btn_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/seinfo/icons/1488353876_Edit-Male-User.png"))); // NOI18N
        btn_edit.setText("Salvar");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        btn_excluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/seinfo/icons/1488353722_Remove-Male-User.png"))); // NOI18N
        btn_excluir.setText("Remover");
        btn_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirActionPerformed(evt);
            }
        });

        btn_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/seinfo/icons/1488353700_Add-Male-User.png"))); // NOI18N
        btn_add.setText("Adicionar");
        btn_add.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btn_add.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Cantarell", 0, 10)); // NOI18N
        jLabel14.setText("Campos Obrigatórios-");

        jLabel15.setForeground(new java.awt.Color(208, 19, 8));
        jLabel15.setText("*");

        jLabel1.setText("Endereço");

        lbl_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbl_searchKeyReleased(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/seinfo/icons/1488449320_search.png"))); // NOI18N

        tbl_cli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "código", "nome", "endereço", "telefone", "email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_cli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_cliMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_cli);

        jLabel5.setText("Código");

        lbl_id.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel2)
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(350, 350, 350)
                                .addComponent(jLabel10))
                            .addComponent(lbl_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_id, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1)
                        .addGap(20, 20, 20)
                        .addComponent(lbl_end, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel7)
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_fone, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(190, 190, 190)
                                .addComponent(jLabel11))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel3)
                        .addGap(46, 46, 46)
                        .addComponent(lbl_email, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(339, 339, 339)
                        .addComponent(btn_edit)
                        .addGap(18, 18, 18)
                        .addComponent(btn_excluir)
                        .addGap(18, 18, 18)
                        .addComponent(btn_add))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_search, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabel4)
                                .addGap(186, 186, 186)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15)))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(jLabel15))
                    .addComponent(jLabel4))
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lbl_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(lbl_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(lbl_end, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(lbl_fone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(lbl_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_edit)
                    .addComponent(btn_excluir)
                    .addComponent(btn_add)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        alterar();
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        remover();
    }//GEN-LAST:event_btn_excluirActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        adicionar();
    }//GEN-LAST:event_btn_addActionPerformed

    private void lbl_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbl_searchKeyReleased
        //The event is type while digit..
        pesquisar_cliente();
    }//GEN-LAST:event_lbl_searchKeyReleased

    private void tbl_cliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_cliMouseClicked
        // If click mouse in table 
        setar_campos();
    }//GEN-LAST:event_tbl_cliMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_excluir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lbl_email;
    private javax.swing.JTextField lbl_end;
    private javax.swing.JTextField lbl_fone;
    private javax.swing.JTextField lbl_id;
    private javax.swing.JTextField lbl_nome;
    private javax.swing.JTextField lbl_search;
    private javax.swing.JTable tbl_cli;
    // End of variables declaration//GEN-END:variables
}
