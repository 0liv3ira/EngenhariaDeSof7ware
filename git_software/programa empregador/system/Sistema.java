
package system;

import java.util.ArrayList;
import model.bean.Curso;
import model.bean.Emprego;
import model.bean.Fez;
import model.bean.Pessoa;
import model.dao.CursoDao;
import model.dao.EmpregoDao;
import model.dao.FezDao;
import model.dao.PessoaDao;
import model.dao.TrabalhouDao;

public class Sistema {
    
    public ArrayList<String> infoPessoa(String nome, String sobr){
        
        ArrayList<String> dados = new ArrayList<>();
        PessoaDao pd = new PessoaDao();
        TrabalhouDao td = new TrabalhouDao();
        EmpregoDao ed = new EmpregoDao();
        FezDao fd = new FezDao();
        CursoDao cd = new CursoDao();
        int i;
        
        Pessoa p = pd.procN(nome, sobr);
        dados.add(p.getCpf());
        dados.add(p.getNome());
        dados.add(p.getSobrenome());
        dados.add(p.getSexo());
        dados.add(p.getDataNascimento());
        dados.add(p.getEmail());
        dados.add(p.getTelefone());
        dados.add(p.getEndereco());
        dados.add(p.getEscolaridade());
        dados.add(p.getNivelIngles());
        dados.add(p.getDescriçao());
        //dados.add(p.getFoto());
        dados.add("teste");
        //dados.add("&");
        
        ArrayList<Fez> fez = fd.procTudo(p.getCpf());
        Curso curso = new Curso();
        for(i=0; i<fez.size(); i++){
            curso = cd.procTudoNC(fez.get(i).getNomeCurso(), fez.get(i).getFornecedor());
            dados.add(curso.getNomeCurso());
            dados.add(curso.getFornecedor());
            dados.add(Integer.toString(curso.getHoras()));
            dados.add(curso.getCertificado());
        }
        dados.add("&");
        
        ArrayList<Integer> ids = td.procId(p.getCpf());
        Emprego emp = new Emprego();
        for(i=0; i<ids.size(); i++){
            emp = ed.procTudo(ids.get(i));
            dados.add(Integer.toString(emp.getIdEmprego()));
            dados.add(emp.getNomeEmpresa());
            dados.add(emp.getCargo());
            dados.add(emp.getTempo());
        }
        
        return dados;
    }
    
    public byte[] BuscarFoto(String nome, String sobr){
        PessoaDao pd = new PessoaDao();
            Pessoa p = pd.procN(nome, sobr);
            byte[]  foto = p.getFoto();
                    return foto; 
            
    }
    
    
    
}
