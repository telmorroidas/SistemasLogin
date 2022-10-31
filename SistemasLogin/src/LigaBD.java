
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class LigaBD {
    
    public static Connection ligacao(){
        String url= "jdbc:mysql://localhost:3306/rhcencal?useTimezone=true&serverTimezone=UTC";
        String user= "root";
        String password= "";
        Connection liga= null;
        try{
            liga= DriverManager.getConnection(url,user,password);
        }catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("erro na tentativa de ligação à base de dados");
        }
        return liga;
    }
    public static void registaUtilizador(String nome, String email, String morada, int telefone, int nif, String login, String password) throws SQLException{
        String query= "INSERT INTO utilizador (nome,email,morada,telefone,nif,login,password) "
        + "VALUES(?,?,?,?,?,?,?)";
        Connection liga= ligacao();
        PreparedStatement ps= liga.prepareStatement(query);
        ps.setString(1, nome);
        ps.setString(2, email);
        ps.setString(3, morada);
        ps.setInt(4, telefone);
        ps.setInt(5, nif);
        ps.setString(6, login);
        ps.setString(7, password);
        ps.execute();
        
    }

    public static void atualizaUtilizador(String nome, String email, String morada, int tele , int nif, String pass, String rePass) {
        Connection conn = LigaBD.ligacao();
        String query= "UPDATE utilizador SET nome=?, email=?, morada=?, telefone=?, nif=?, login=?, password=? WHERE login = '"+Login.user+"'";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, nome);
            ps.setString(2, email);
            ps.setString(3, morada);
            ps.setInt(4, tele);
            ps.setInt(5, nif);
            ps.setString(6, Login.user);
            ps.setString(7, pass);            
            if(ps.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "atualização executada com sucesso", "erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LigaBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void removerUtilizador(String query){
        Connection conn = LigaBD.ligacao();
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(query);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(LigaBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
