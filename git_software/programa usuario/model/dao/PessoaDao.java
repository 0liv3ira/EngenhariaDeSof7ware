
package model.dao;

import connection.ConnectionBD;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.JOptionPane;
import model.bean.Pessoa;

public class PessoaDao {
    
    //ADICIONA NO BANCO
    public void create(Pessoa p){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO pessoa (CPF, Nome, Sobrenome, Sexo, DataNascimento, Email, Telefone, Endereco, Escolaridade, Nivelingles, Descricao, Foto)VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, p.getCpf());
            stmt.setString(2, p.getNome());
            stmt.setString(3, p.getSobrenome());
            stmt.setString(4, p.getSexo());
            stmt.setString(5, p.getDataNascimento());
            stmt.setString(6, p.getEmail());
            stmt.setString(7, p.getTelefone());
            stmt.setString(8, p.getEndereco());
            stmt.setString(9, p.getEscolaridade());
            stmt.setString(10, p.getNivelIngles());
            stmt.setString(11, p.getDescriçao());
            
            //Adicionando Blob no BD
            Blob blob = new SerialBlob(p.getFoto());
            //stmt.setString(12, p.getFoto());
            stmt.setBlob(12, blob);
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso !");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }
        
    }
    
    //BUSCA TODOS NO BANCO
    public ArrayList<Pessoa> read(){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        
        ArrayList<Pessoa> pessoas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM pessoa");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Pessoa p = new Pessoa();
                
                p.setCpf(rs.getString("CPF"));
                p.setNome(rs.getString("Nome"));
                p.setSobrenome(rs.getString("Sobrenome"));
                p.setSexo(rs.getString("Sexo"));
                p.setDataNascimento(rs.getString("DataNascimento"));
                p.setEmail(rs.getString("Email"));
                p.setTelefone(rs.getString("Telefone"));
                p.setEndereco(rs.getString("Endereco"));
                p.setEscolaridade(rs.getString("Escolaridade"));
                p.setNivelIngles(rs.getString("Nivelingles"));
                p.setDescriçao(rs.getString("Descricao"));
                p.setFoto(rs.getBytes("Foto"));
                
                pessoas.add(p);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        
        return pessoas;
        
    }
    
    public ArrayList<String> readNomes(){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        
        ArrayList<String> pessoas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM pessoa");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                            
                pessoas.add(rs.getString("Nome")+" "+rs.getString("Sobrenome")); //Concatena nome e sobrenome
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        
        return pessoas;
        
    }
    
    //ATUALIZA O BANCO
    public void update(Pessoa p){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE pessoa SET CPF = ?, Nome = ?, Sobrenome = ?, Sexo = ?, DataNascimento = ?, Email = ?, Telefone = ?, Endereco = ?, Escolaridade = ?, Nivelingles = ?, Descricao = ?, Foto = ? WHERE CPF = ?");
            stmt.setString(1, p.getCpf());
            stmt.setString(2, p.getNome());
            stmt.setString(3, p.getSobrenome());
            stmt.setString(4, p.getSexo());
            stmt.setString(5, p.getDataNascimento());
            stmt.setString(6, p.getEmail());
            stmt.setString(7, p.getTelefone());
            stmt.setString(8, p.getEndereco());
            stmt.setString(9, p.getEscolaridade());
            stmt.setString(10, p.getNivelIngles());
            stmt.setString(11, p.getDescriçao());
           // stmt.setString(12, p.getFoto());
            stmt.setString(13, p.getCpf());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso !");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }
        
    }
    
    //REMOVE DO BANCO
    public void delete(Pessoa p){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM pessoa WHERE CPF = ?");
            stmt.setString(1, p.getCpf());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso !");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }
        
    }
    
    //BUSCA POR CPF
    public ArrayList<Pessoa> procTudoC(String cpf){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        
        ArrayList<Pessoa> pessoas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM pessoa WHERE CPF = ?");
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Pessoa p = new Pessoa();
                
                p.setCpf(rs.getString("CPF"));
                p.setNome(rs.getString("Nome"));
                p.setSobrenome(rs.getString("Sobrenome"));
                p.setSexo(rs.getString("Sexo"));
                p.setDataNascimento(rs.getString("DataNascimento"));
                p.setEmail(rs.getString("Email"));
                p.setTelefone(rs.getString("Telefone"));
                p.setEndereco(rs.getString("Endereco"));
                p.setEscolaridade(rs.getString("Escolaridade"));
                p.setNivelIngles(rs.getString("Nivelingles"));
                p.setDescriçao(rs.getString("Descricao"));
                p.setFoto(rs.getBytes("Foto")); 
                
                pessoas.add(p);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        
        return pessoas;
    }
    
    //Procura CPF
    public Pessoa procN(String nome, String sobr){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        
        Pessoa p = new Pessoa();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM pessoa WHERE Nome = ? and Sobrenome = ?");
            stmt.setString(1, nome);
            stmt.setString(2, sobr);
            rs = stmt.executeQuery();
            rs.next();
                    
            p.setCpf(rs.getString("CPF"));
            p.setNome(rs.getString("Nome"));
            p.setSobrenome(rs.getString("Sobrenome"));
            p.setSexo(rs.getString("Sexo"));
            p.setDataNascimento(rs.getString("DataNascimento"));
            p.setEmail(rs.getString("Email"));
            p.setTelefone(rs.getString("Telefone"));
            p.setEndereco(rs.getString("Endereco"));
            p.setEscolaridade(rs.getString("Escolaridade"));
            p.setNivelIngles(rs.getString("Nivelingles"));
            p.setDescriçao(rs.getString("Descricao"));
            p.setFoto(rs.getBytes("Foto"));

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        
        return p;
    }
    
    //pesquisa que já fez estágio
    public ArrayList<String> estagio(){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        
        ArrayList<String> e = new ArrayList();
        
        try {
            stmt = con.prepareStatement("SELECT p.Nome, p.Sobrenome, p.Nivelingles, e.Cargo, e.NomeEmpresa, e.Tempo "
                    + "FROM pessoa p, trabalhou_em t, emprego e "
                    + "WHERE p.CPF = t.P_CPF AND t.E_id = e.id_emprego AND e.Cargo = 'Estagiário' order by p.Nome, p.Sobrenome");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                String s1 = rs.getString("p.Nome");
                String s2 = rs.getString("p.Sobrenome");
                String s3 = rs.getString("p.Nivelingles");
                String s4 = rs.getString("e.Cargo");
                String s5 = rs.getString("e.NomeEmpresa");
                String s6 = rs.getString("e.Tempo");
            
                e.add(s1);
                e.add(s2);
                e.add(s3);
                e.add(s4);
                e.add(s5);
                e.add(s6);
            
            }
 
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        
        return e;
        
    }
    
     public ArrayList<String> excel(){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        
        ArrayList<String> e = new ArrayList();
        
        try {
            stmt = con.prepareStatement("select  p.Nome, p.Sobrenome, p.CPF , c.NomeCurso, c.Fornecedor, c.Horas, c.TemCertificado "
                    + "from pessoa p, fez f, curso c "
                    + "where p.CPF = f.P_CPF and f.C_nomeCurso = c.NomeCurso and f.C_fornecedor = c.Fornecedor and f.C_nomeCurso like '%Excel%' order by p.Nome, p.Sobrenome");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                String s1 = rs.getString("p.Nome");
                String s2 = rs.getString("p.Sobrenome");
                String s3 = rs.getString("c.NomeCurso");
                String s4 = rs.getString("c.Fornecedor");
                String s5 = Integer.toString(rs.getInt("c.Horas"));
                String s6 = rs.getString("c.TemCertificado");
                String s7 = rs.getString("p.CPF");
                
                if(e.contains(s1+""+s2+" CPF :"+s7)) continue;
                e.add(s1+""+s2+" CPF :"+s7); //alteração pra gerar nomes e cpf
            
            }
 
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        
        return e;
        
    }
    
    //pesquisa quem já fez algum curso de Excel (curso contém Excel no nome)
    public ArrayList<String> PesquisaIngles(String c){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        
        ArrayList<String> e = new ArrayList();
        
        try {
            stmt = con.prepareStatement("select p.Nome, p.Sobrenome, p.CPF "
                    + "from pessoa p "
                    + "where p.Nivelingles = ?");
            stmt.setString(1, c);
            rs = stmt.executeQuery();
            //JOptionPane.showMessageDialog(null, c);
            while(rs.next()){
            
                String s1 = rs.getString("p.Nome");
                String s2 = rs.getString("p.Sobrenome");
                String s7 = rs.getString("p.CPF");
            
                e.add(s1+" "+s2+" CPF :"+s7); //alteração pra gerar nomes e cpf
                //System.out.println(" "+e);
            }
 
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        
        return e;
        
    }
    
    //pesquisa cursos e trabalhos de uma pessoa pelo nome (com duplicata)
    public ArrayList<String> cursoTrab(String nome, String sobr){
        
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        
        ArrayList<String> e = new ArrayList();
        
        try {
            stmt = con.prepareStatement("SELECT p.Nome, p.Sobrenome, p.Sexo, p.Nivelingles, c.NomeCurso, c.Fornecedor, c.Horas, c.TemCertificado, e.NomeEmpresa, e.Cargo, e.Tempo "
                    + "FROM pessoa p, curso c, emprego e, fez f, trabalhou_em t "
                    + "WHERE p.Nome = ? AND p.Sobrenome = ? AND p.CPF = t.P_CPF AND t.E_id = e.id_emprego AND p.CPF = f.P_CPF AND f.C_nomeCurso = c.NomeCurso AND f.C_fornecedor = c.Fornecedor");
            stmt.setString(1, nome);
            stmt.setString(2, sobr);
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                String s1 = rs.getString("p.Nome");
                String s2 = rs.getString("p.Sobrenome");
                String s3 = rs.getString("p.Nivelingles");
                String s4 = rs.getString("c.NomeCurso");
                String s5 = rs.getString("c.Fornecedor");
                String s6 = Integer.toString(rs.getInt("c.Horas"));
                String s7 = rs.getString("c.TemCertificado");
                String s8 = rs.getString("e.NomeEmpresa");
                String s9 = rs.getString("e.Cargo");
                String s10 = rs.getString("e.Tempo");
            
                e.add(s1);
                e.add(s2);
                e.add(s3);
                e.add(s4);
                e.add(s5);
                e.add(s6);
                e.add(s7);
                e.add(s8);
                e.add(s9);
                e.add(s10);
            
            }
 
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        
        return e;
        
    }
    
}
