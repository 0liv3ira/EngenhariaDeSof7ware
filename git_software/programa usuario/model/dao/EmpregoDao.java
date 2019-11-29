
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
import model.bean.Emprego;

public class EmpregoDao {
    
    //ADICIONA NO BANCO
    public void create(Emprego e){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO emprego (NomeEmpresa, Cargo, Tempo)VALUES(?, ?, ?)");
            stmt.setString(1, e.getNomeEmpresa());
            stmt.setString(2, e.getCargo());
            stmt.setString(3, e.getTempo());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso !");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }
        
    }
    
    public ArrayList<Emprego> read(){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        
        ArrayList<Emprego> pessoas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM emprego");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Emprego e = new Emprego();
                
                e.setIdEmprego(rs.getInt("id_emprego"));
                e.setNomeEmpresa(rs.getString("NomeEmpresa"));
                e.setCargo(rs.getString("Cargo"));
                e.setTempo(rs.getString("Tempo"));
                
                pessoas.add(e);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        
        return pessoas;
        
    }
    
     public ArrayList<String> readEmpresas(){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        
        ArrayList<String> pessoas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM emprego");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                if(!(pessoas.contains(rs.getString("NomeEmpresa"))))  // Assim não adiciona-se empresas repetidas
                pessoas.add(rs.getString("NomeEmpresa"));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        
        return pessoas;
        
    }
    
    
     public int procId(String a, String b, String c){
        // nome do emprego , cargo e tempo
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        int id = 0;
        
        try {
            stmt = con.prepareStatement("SELECT id_emprego FROM emprego WHERE NomeEmpresa = ? and Cargo = ? and Tempo = ?");
            stmt.setString(1, a);
            stmt.setString(2, b);
            stmt.setString(3, c);
            rs = stmt.executeQuery();
            rs.next();

            id = rs.getInt("id_emprego");
   
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        
        return id;
     }
     
     public ArrayList<String> Pempresa(String empresa){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        
        ArrayList<String> ep = new ArrayList();
        
        try {
            stmt = con.prepareStatement("select p.Nome, p.Sobrenome, p.CPF "+"from pessoa p, trabalhou_em t, emprego e "+ "where p.CPF = t.P_CPF and t.E_id = e.id_emprego and e.NomeEmpresa = ? order by p.Nome, p.Sobrenome");
            stmt.setString(1, empresa);
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                String s1 = rs.getString("p.Nome");
                String s2 = rs.getString("p.Sobrenome");
                String s3 = rs.getString("p.CPF");
            
                ep.add(s1+" "+s2+" CPF :"+s3);
                //ALterações pra gerar também o cpf
            
            }
 
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        
        return ep;
        
    }
    
    public void update(Emprego e){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE emprego SET NomeEmpresa = ?, Cargo = ?, Tempo = ? WHERE id_emprego = ?");
            stmt.setString(1, e.getNomeEmpresa());
            stmt.setString(2, e.getCargo());
            stmt.setString(3, e.getTempo());
            stmt.setInt(4, e.getIdEmprego());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso !");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }
        
    }
    
    public void delete(Emprego e){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM emprego WHERE id_emprego = ?");
            stmt.setInt(1, e.getIdEmprego());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso !");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }
        
    }
    
    /*public ArrayList<Emprego> procTudo(int id){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        
        ArrayList<Emprego> empregos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM emprego WHERE id_emprego = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Emprego e = new Emprego();
                
                e.setIdEmprego(rs.getInt("id_emprego"));
                e.setNomeEmpresa(rs.getString("NomeEmpresa"));
                e.setCargo(rs.getString("Cargo"));
                e.setTempo(rs.getString("Tempo"));
                
                empregos.add(e);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        
        return empregos;
        
    }*/
    
    public Emprego procTudo(int id){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        
        Emprego e = new Emprego();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM emprego WHERE id_emprego = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            rs.next();

            e.setIdEmprego(rs.getInt("id_emprego"));
            e.setNomeEmpresa(rs.getString("NomeEmpresa"));
            e.setCargo(rs.getString("Cargo"));
            e.setTempo(rs.getString("Tempo"));

            
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        
        return e;
        
    }
    
    //retorna os empregos de cada pessoa
    public ArrayList<String> emprPessoas(){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        
        ArrayList<String> ep = new ArrayList();
        
        try {
            stmt = con.prepareStatement("SELECT p.Nome, p.Sobrenome, e.NomeEmpresa, e.Cargo, e.Tempo "
                    + "FROM pessoa p, trabalhou_em t, emprego e "
                    + "WHERE p.CPF=t.P_CPF AND t.E_id = e.id_emprego order by p.Nome, p.Sobrenome");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                String s1 = rs.getString("p.Nome");
                String s2 = rs.getString("p.Sobrenome");
                String s3 = rs.getString("e.NomeEmpresa");
                String s4 = rs.getString("e.Cargo");
                String s5 = rs.getString("e.Tempo");
            
                ep.add(s1);
                ep.add(s2);
                ep.add(s3);
                ep.add(s4);
                ep.add(s5);
            
            }
 
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        
        return ep;
        
    }
    
    //pesquisa os empregos pelo nome
    public ArrayList<String> empregos(String nome, String sobr){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        
        ArrayList<String> ep = new ArrayList();
        
        try {
            stmt = con.prepareStatement("SELECT p.Nome, p.Sobrenome, e.NomeEmpresa, e.Cargo, e.Tempo "
                    + "FROM pessoa p, trabalhou_em t, emprego e "
                    + "WHERE p.CPF = t.P_CPF AND t.E_id = e.id_emprego  AND p.Nome = ? AND p.Sobrenome = ?");
            stmt.setString(1, nome);
            stmt.setString(2, sobr);
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                String s1 = rs.getString("p.Nome");
                String s2 = rs.getString("p.Sobrenome");
                String s3 = rs.getString("e.NomeEmpresa");
                String s4 = rs.getString("e.Cargo");
                String s5 = rs.getString("e.Tempo");
            
                ep.add(s1);
                ep.add(s2);
                ep.add(s3);
                ep.add(s4);
                ep.add(s5);
            
            }
 
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        
        return ep;
        
    }
    
}
