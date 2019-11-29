
package model.dao;

import connection.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.TrabalhouEm;

public class TrabalhouDao {
    
    public void create(TrabalhouEm t){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO trabalhou_em (P_CPF, E_id)VALUES(?, ?)");
            stmt.setString(1, t.getCpf());
            stmt.setInt(2, t.getIdEmp());
            
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso !");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }
        
    }
     
     public void delete(TrabalhouEm t){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM trabalhou_em WHERE P_CPF = ? and E_id = ?");
            stmt.setString(1, t.getCpf());
            stmt.setInt(2, t.getIdEmp());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso !");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }
        
    }
     
     public ArrayList<Integer> procId(String cpf){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        
        ArrayList<Integer> ids = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM trabalhou_em WHERE P_CPF = ?");
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                int id;
                id = rs.getInt("E_id");
                
                ids.add(id);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        
        return ids;
        
    }
    
}
