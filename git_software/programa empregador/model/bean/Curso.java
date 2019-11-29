
package model.bean;

public class Curso {
    
    private String nomeCurso;
    private String fornecedor;
    private int horas;
    private String certificado;

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

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public String getCertificado() {
        return certificado;
    }

    public void setCertificado(String certificado) {
        this.certificado = certificado;
    }

    @Override
    public String toString() {
        return "Curso{" + "nomeCurso=" + nomeCurso + ", fornecedor=" + fornecedor + ", horas=" + horas + ", certificado=" + certificado + '}';
    }
    
}
