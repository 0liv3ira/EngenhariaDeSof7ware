
package model.bean;

public class Emprego {
    
    private int idEmprego;
    private String nomeEmpresa;
    private String cargo;
    private String tempo;

    public int getIdEmprego() {
        return idEmprego;
    }

    public void setIdEmprego(int idEmprego) {
        this.idEmprego = idEmprego;
    }
    
    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    @Override
    public String toString() {
        return "Emprego{" + "idEmprego=" + idEmprego + ", nomeEmpresa=" + nomeEmpresa + ", cargo=" + cargo + ", tempo=" + tempo + '}';
    }
    
}
