
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
import model.bean.Fez;

public class FezDao {
    
    public void create(Fez f){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO fez (P_CPF, C_nomeCurso, C_fornecedor)VALUES(?, ?, ?)");
            stmt.setString(1, f.getCpf());
            stmt.setString(2, f.getNomeCurso());
            stmt.setString(3, f.getFornecedor());
            
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso !");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }
        
    }
     
     public void delete(Fez f){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM fez WHERE P_CPF = ? and C_nomeCurso = ? and C_fornecedor = ?");
            stmt.setString(1, f.getCpf());
            stmt.setString(2, f.getNomeCurso());
            stmt.setString(3, f.getFornecedor());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso !");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }
        
    }
     
     public ArrayList<Fez> procTudo(String cpf){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        
        ArrayList<Fez> fez = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM fez WHERE P_CPF = ?");
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Fez f = new Fez();
                
                f.setCpf(rs.getString("P_CPF"));
                f.setNomeCurso(rs.getString("C_nomeCurso"));
                f.setFornecedor(rs.getString("C_fornecedor"));
                
                fez.add(f);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        
        return fez;
        
    }
    
}
