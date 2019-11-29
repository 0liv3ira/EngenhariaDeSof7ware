
package model.bean;

public class Fez {
    
    private String cpf;
    private String nomeCurso;
    private String fornecedor;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public String toString() {
        return "Fez{" + "cpf=" + cpf + ", nomeCurso=" + nomeCurso + ", fornecedor=" + fornecedor + '}';
    }
    
}
