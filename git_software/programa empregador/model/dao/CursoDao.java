
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
import model.bean.Curso;

public class CursoDao {
    
    public void create(Curso c){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO curso (NomeCurso, Fornecedor, Horas, TemCertificado)VALUES(?, ?, ?, ?)");
            stmt.setString(1, c.getNomeCurso());
            stmt.setString(2, c.getFornecedor());
            stmt.setInt(3, c.getHoras());
            stmt.setString(4, c.getCertificado());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso !");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }
        
    }
    
  
    
    public ArrayList<Curso> read(){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        
        ArrayList<Curso> cursos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM curso");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Curso c = new Curso();
                
                c.setNomeCurso(rs.getString("NomeCurso"));
                c.setFornecedor(rs.getString("Fornecedor"));
                c.setHoras(rs.getInt("Horas"));
                c.setCertificado(rs.getString("TemCertificado"));
                
                cursos.add(c);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        
        return cursos;
        
    }
    
    public ArrayList<String> readCursos(){  // Retorna  todos os cursos no BD
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        
        ArrayList<String> cursos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM curso");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                

                cursos.add(rs.getString("NomeCurso"));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        
        return cursos;
        
    }
    
    
     public void update(Curso c){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE curso SET NomeCurso = ?, Fornecedor = ?, Horas = ?, TemCertificado = ?  WHERE NomeCurso = ? and Fornecedor = ?");
            stmt.setString(1, c.getNomeCurso());
            stmt.setString(2, c.getFornecedor());
            stmt.setInt(3, c.getHoras());
            stmt.setString(4, c.getCertificado());
            stmt.setString(5, c.getNomeCurso());
            stmt.setString(6, c.getFornecedor());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso !");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }
        
    }
     
     public void delete(Curso c){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM curso WHERE NomeCurso = ? and Fornecedor = ?");
            stmt.setString(1, c.getNomeCurso());
            stmt.setString(2, c.getFornecedor());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso !");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }
        
    }
     
     
     
     /*public ArrayList<Curso> procTudoNC(String cur, String forn){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        
        ArrayList<Curso> cursos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM curso WHERE NomeCurso = ? and Fornecedor = ?");
            stmt.setString(1, cur);
            stmt.setString(2, forn);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Curso c = new Curso();
                
                c.setNomeCurso(rs.getString("NomeCurso"));
                c.setFornecedor(rs.getString("Fornecedor"));
                c.setHoras(rs.getInt("Horas"));
                c.setCertificado(rs.getString("TemCertificado"));
                
                
                cursos.add(c);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        
        return cursos;
        
    }*/
     
     public Curso procTudoNC(String cur, String forn){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        Curso c = new Curso();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM curso WHERE NomeCurso = ? and Fornecedor = ?");
            stmt.setString(1, cur);
            stmt.setString(2, forn);
            rs = stmt.executeQuery();
            rs.next();

            c.setNomeCurso(rs.getString("NomeCurso"));
            c.setFornecedor(rs.getString("Fornecedor"));
            c.setHoras(rs.getInt("Horas"));
            c.setCertificado(rs.getString("TemCertificado"));
         
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        
        return c;
        
    }
    
    //retorna os cursos de cada pessoa
     public ArrayList<String> curPessoas(){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        
        ArrayList<String> cp = new ArrayList();
        
        try {
            stmt = con.prepareStatement("SELECT p.Nome, p.Sobrenome, c.NomeCurso, c.Fornecedor, c.Horas, c.TemCertificado "
                    + "FROM pessoa p, fez f, curso c "
                    + "WHERE p.CPF=f.P_CPF AND f.C_nomeCurso = c.NomeCurso AND f.C_fornecedor = c.Fornecedor order by p.Nome, p.Sobrenome");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                String s1 = rs.getString("p.Nome");
                String s2 = rs.getString("p.Sobrenome");
                String s3 = rs.getString("c.NomeCurso");
                String s4 = rs.getString("c.Fornecedor");
                String s5 = Integer.toString(rs.getInt("c.Horas"));
                String s6 = rs.getString("c.TemCertificado");
            
                cp.add(s1);
                cp.add(s2);
                cp.add(s3);
                cp.add(s4);
                cp.add(s5);
                cp.add(s6);
                
            }
 
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        
        return cp;
        
    }
     
     //pesquisa os cursos pelo nome
     public ArrayList<String> cursos(String nome, String sobr){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        
        ArrayList<String> cp = new ArrayList();
        
        try {
            stmt = con.prepareStatement("SELECT p.Nome, p.Sobrenome, c.NomeCurso, c.Fornecedor, c.Horas, c.TemCertificado "
                    + "FROM pessoa p, fez f, curso c "
                    + "WHERE p.CPF = f.P_CPF AND f.C_nomeCurso = c.NomeCurso AND f.C_fornecedor = c.Fornecedor AND p.Nome = ? AND p.Sobrenome = ?");
            stmt.setString(1, nome);
            stmt.setString(2, sobr);
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                String s1 = rs.getString("p.Nome");
                String s2 = rs.getString("p.Sobrenome");
                String s3 = rs.getString("c.NomeCurso");
                String s4 = rs.getString("c.Fornecedor");
                String s5 = Integer.toString(rs.getInt("c.Horas"));
                String s6 = rs.getString("c.TemCertificado");
            
                cp.add(s1);
                cp.add(s2);
                cp.add(s3);
                cp.add(s4);
                cp.add(s5);
                cp.add(s6);
            
            }
 
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        
        return cp;
        
    }
     
     //pesquisa quem já fez um curso pelo nome do curso
     public ArrayList<String> Pcursos(String curso){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        
        ArrayList<String> cp = new ArrayList();
        
        try {
            stmt = con.prepareStatement("select p.Nome, p.Sobrenome ,p.CPF "
                    + "from pessoa p, fez f "
                    + "where p.CPF = f.P_CPF and f.C_nomeCurso = ? order by p.Nome, p.Sobrenome");
            stmt.setString(1, curso);
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                String s1 = rs.getString("p.Nome");
                String s2 = rs.getString("p.Sobrenome");
                 String s3 = rs.getString("p.CPF");
                 
                 //JOptionPane.showMessageDialog(null, ""+s3);
            
                cp.add(s1+" "+s2+" CPF :"+s3);
            
            }
 
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        
        return cp;
        
    }
    
}
