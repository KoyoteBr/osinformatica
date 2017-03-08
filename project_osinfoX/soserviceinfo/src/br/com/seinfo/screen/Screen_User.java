/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.seinfo.screen;

/**
 *
 * @author daniel
 */
import br.com.seinfo.dal.Module_Connection;
import java.sql.*;
import javax.swing.JOptionPane;

public class Screen_User extends javax.swing.JInternalFrame {

    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form Screen_User
     */
    public Screen_User() {
        initComponents();
        // Instance for Module_Connection autentic
        connection = Module_Connection.autentic();

    }

    // Methods for CRUD
    private void consultar() {
        String sql = "SELECT * FROM tbusuarios WHERE iduser=?";
        try {
            // PreparedStatement
            pst = connection.prepareStatement(sql);
            // indicator label 
            pst.setString(1, lbl_cod.getText());
            // Execute query
            rs = pst.executeQuery();
            // If next search 
            if (rs.next()) {
                // Collumn DB 2(Nome)
                lbl_nome.setText(rs.getString(2));
                lbl_fone.setText(rs.getString(3));
                lbl_login.setText(rs.getString(4));
                lbl_passwd.setText(rs.getString(5));
                cbox_perfil.setSelectedItem(rs.getString(6));

            } else {
                JOptionPane.showMessageDialog(null, "Usuário não encontrado", "Não encontrado",
                        JOptionPane.INFORMATION_MESSAGE);
                // Erase form 
                lbl_nome.setText(null);
                lbl_fone.setText(null);
                lbl_login.setText(null);
                lbl_passwd.setText(null);
                cbox_perfil.setSelectedItem(null);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Isert in Db
    public void adicionar() {

        if ((lbl_cod.getText().isEmpty()) || (lbl_nome.getText().isEmpty())
                || (lbl_login.getText().isEmpty()) || (lbl_passwd.getText().isEmpty())
                || (cbox_perfil.getSelectedItem() == null)) {

            JOptionPane.showMessageDialog(null, "Erro ao adicionar usuário \n"
                    + "Respeite os campos obrigatórios * !");

        } else {

            String sql = "INSERT INTO tbusuarios(iduser,usuario,fone,login,senha,perfil) "
                    + "values(?,?,?,?,?,?)";
            try {
                //PreparedStatement and Column
                pst = connection.prepareStatement(sql);
                pst.setString(1, lbl_cod.getText());
                pst.setString(2, lbl_nome.getText());
                pst.setString(3, lbl_fone.getText());
                pst.setString(4, lbl_login.getText());
                pst.setString(5, lbl_passwd.getText());
                pst.setString(6, cbox_perfil.getSelectedItem().toString());
                // Execute update
                int add = pst.executeUpdate();

                if (add > 0) {
                    JOptionPane.showMessageDialog(null, "Cadastro atualizado", "Confirmação",
                            JOptionPane.PLAIN_MESSAGE);
                }

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, e);
                // Erase form
                lbl_nome.setText(null);
                lbl_fone.setText(null);
                lbl_login.setText(null);
                lbl_passwd.setText(null);
                cbox_perfil.setSelectedItem(null);
            }

        }

    }

    public void editar() {

        if ((lbl_cod.getText().isEmpty()) || (lbl_nome.getText().isEmpty())
                || (lbl_login.getText().isEmpty()) || (lbl_passwd.getText().isEmpty())
                || (cbox_perfil.getSelectedItem() == null)) {

            JOptionPane.showMessageDialog(null, "Erro ao editar usuário \n"
                    + "Respeite os campos obrigatórios * !");

        } else {

            String sql = "UPDATE tbusuarios set usuario=?,fone=?,login=?,senha=?,perfil=? WHERE iduser=?";

            try {
                //PreparedStatement
                pst = connection.prepareStatement(sql);

                //Config Label for execute
                pst.setString(1, lbl_nome.getText());
                pst.setString(2, lbl_fone.getText());
                pst.setString(3, lbl_login.getText());
                pst.setString(4, lbl_passwd.getText());
                pst.setString(5, cbox_perfil.getSelectedItem().toString());
                pst.setString(6, lbl_cod.getText());

                // Execute update
                int add = pst.executeUpdate();

                if (add > 0) {
                    JOptionPane.showMessageDialog(null, "Cadastro atualizado", "Confirmação",
                            JOptionPane.PLAIN_MESSAGE);
                    // Erase form
                    lbl_nome.setText(null);
                    lbl_fone.setText(null);
                    lbl_login.setText(null);
                    lbl_passwd.setText(null);
                    cbox_perfil.setSelectedItem(null);
                    
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);

            }
        }

    }

    public void excluir() {

        int confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente remover usuário?",
                "Remover Usuário", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM tbusuarios WHERE iduser=?";

            try {
                pst = connection.prepareStatement(sql);

                pst.setString(1, lbl_cod.getText());

                // Execute Query
                int excluiu = pst.executeUpdate();

                if (excluiu > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário excluido com sucesso!",
                            "Excluido", JOptionPane.INFORMATION_MESSAGE);

                    // Erase form
                    lbl_nome.setText(null);
                    lbl_fone.setText(null);
                    lbl_login.setText(null);
                    lbl_passwd.setText(null);
                    cbox_perfil.setSelectedItem(null);
                    
                }

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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbl_cod = new javax.swing.JTextField();
        lbl_nome = new javax.swing.JTextField();
        lbl_login = new javax.swing.JTextField();
        lbl_passwd = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cbox_perfil = new javax.swing.JComboBox<>();
        lbl_fone = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btn_add = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_search = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Usuários");
        setPreferredSize(new java.awt.Dimension(750, 660));

        jLabel1.setText("Código");

        jLabel2.setText("Nome");

        jLabel3.setText("Login");

        jLabel4.setText("Senha");

        jLabel5.setText("Perfil");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Cuprum", 3, 32)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(37, 105, 255));
        jLabel6.setText("Cadastro de Usuários");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(229, 229, 229)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel6)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        cbox_perfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "user", " " }));
        cbox_perfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbox_perfilActionPerformed(evt);
            }
        });

        jLabel7.setText("Telefone");

        btn_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/seinfo/icons/1488353700_Add-Male-User.png"))); // NOI18N
        btn_add.setText("Adicionar");
        btn_add.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btn_add.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        btn_excluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/seinfo/icons/1488353722_Remove-Male-User.png"))); // NOI18N
        btn_excluir.setText("Remover");
        btn_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirActionPerformed(evt);
            }
        });

        btn_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/seinfo/icons/1488353876_Edit-Male-User.png"))); // NOI18N
        btn_edit.setText("Salvar");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        btn_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/seinfo/icons/1488353712_Search-Male-User.png"))); // NOI18N
        btn_search.setText("Pesquisar");
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Cantarell", 2, 10)); // NOI18N
        jLabel8.setText("Pesquise o cadastro usando o código");

        jLabel9.setForeground(new java.awt.Color(208, 19, 8));
        jLabel9.setText("*");

        jLabel10.setForeground(new java.awt.Color(208, 19, 8));
        jLabel10.setText("*");

        jLabel11.setForeground(new java.awt.Color(208, 19, 8));
        jLabel11.setText("*");

        jLabel12.setForeground(new java.awt.Color(208, 19, 8));
        jLabel12.setText("*");

        jLabel13.setForeground(new java.awt.Color(208, 19, 8));
        jLabel13.setText("*");

        jLabel14.setFont(new java.awt.Font("Cantarell", 0, 10)); // NOI18N
        jLabel14.setText("Campos Obrigatórios-");

        jLabel15.setForeground(new java.awt.Color(208, 19, 8));
        jLabel15.setText("*");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbl_cod, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel8))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbl_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabel10))))
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(lbl_fone, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn_search)
                                        .addGap(18, 18, 18))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(41, 41, 41)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbl_passwd, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbl_login, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(cbox_perfil, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(3, 3, 3)
                                                .addComponent(jLabel13)))
                                        .addGap(7, 7, 7)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btn_edit)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_excluir)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel14)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel15))
                                            .addComponent(btn_add)))))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(33, 33, 33))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbl_cod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel8))
                                .addGap(24, 24, 24))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(42, 42, 42)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(238, 238, 238)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_fone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lbl_login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel11)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(lbl_passwd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel12)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbox_perfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btn_search)
                    .addComponent(btn_excluir)
                    .addComponent(btn_edit)
                    .addComponent(btn_add))
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbox_perfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbox_perfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbox_perfilActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        adicionar();
    }//GEN-LAST:event_btn_addActionPerformed

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        consultar();
    }//GEN-LAST:event_btn_searchActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        editar();
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        excluir();
    }//GEN-LAST:event_btn_excluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_excluir;
    private javax.swing.JButton btn_search;
    private javax.swing.JComboBox<String> cbox_perfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField lbl_cod;
    private javax.swing.JTextField lbl_fone;
    private javax.swing.JTextField lbl_login;
    private javax.swing.JTextField lbl_nome;
    private javax.swing.JTextField lbl_passwd;
    // End of variables declaration//GEN-END:variables
}
