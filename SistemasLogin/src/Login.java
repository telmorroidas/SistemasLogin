
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

public static String user;
    public Login() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ctxUser = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        ctxPassword = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Login");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("PASSWORD");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("LOGIN");

        ctxUser.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        ctxUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ctxUserActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 204, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton1.setText("Iniciar Sessão");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        ctxPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ctxPasswordActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/trololo.jpg"))); // NOI18N

        jButton2.setBackground(new java.awt.Color(255, 255, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton2.setText("Registe-se");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(ctxPassword))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(ctxUser))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(ctxUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(113, 113, 113)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(ctxPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ctxUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ctxUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ctxUserActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        /*validação através da recolha e comparação de password e login
        1º verificar se existe ficheiro "login.txt"
        2º verificar se a password corresponde à pass que está no ficheiro
        se sim, segue para a JFrame Form MenuOpcoes
        */
        // se login e password corretos faz isto que se segue
        user = ctxUser.getText();
        String pass = ctxPassword.getText();
        if(user.equals("")|| pass.equals(""))
            JOptionPane.showMessageDialog(null, "preencha os campos de login e password", "erro", JOptionPane.ERROR_MESSAGE);
        else{  
            
            
            try {
                System.out.println("login "+ctxUser.getText()+"\n password "+ctxPassword.getText());
                String update = "SELECT * FROM utilizador WHERE login = '"+ctxUser.getText()+"' and password = '"+ctxPassword.getText()+"'";
                Connection liga= LigaBD.ligacao();
                PreparedStatement ps;
                ps = liga.prepareStatement(update);
                //ps = liga.prepareStatement(update);            
                ResultSet rs= ps.executeQuery();
                //rs.first();
                
                if(rs.next()){
                    MenuOpcoes MO = new MenuOpcoes();
                    MO.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "dados de login inválidos", "erro", JOptionPane.ERROR_MESSAGE);
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        /*File ficheiro= new File(user+".txt");
            try {
                if(ficheiro.exists()){
                FileReader fr = new FileReader(ficheiro);
                BufferedReader br= new BufferedReader(fr);
                while (br.ready()){
                    String linha = br.readLine();
                    if (linha.equals(pass)){
                    br.close();
                    fr.close();   
                    MenuOpcoes mo = new MenuOpcoes();
                    this.dispose();
                    mo.setVisible(true);
                    break;
                    }                  
                    else{
                        JOptionPane.showMessageDialog(null, "dados de login inválidos", "erro", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
                br.close();
                    fr.close();
                        }
                else{
                 JOptionPane.showMessageDialog(null, "dados de login inválidos", "erro", JOptionPane.ERROR_MESSAGE);    
                }
                }catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ctxPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ctxPasswordActionPerformed

    }//GEN-LAST:event_ctxPasswordActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        FormRegisto fr= new FormRegisto();
        this.setVisible(false);
        fr.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed


    public static void main(String args[]) {
      


        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField ctxPassword;
    private javax.swing.JTextField ctxUser;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
