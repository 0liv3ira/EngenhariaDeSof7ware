
package model.bean;

public class TrabalhouEm {
    
    private String cpf;
    private int idEmp;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(int idEmp) {
        this.idEmp = idEmp;
    }

    @Override
    public String toString() {
        return "TrabalhouEm{" + "cpf=" + cpf + ", idEmp=" + idEmp + '}';
    }
    
}
