/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;
import view.Login;
import view.Resultado;
import model.dao.CursoDao;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;


/**
 *
 * @author Venom
 */
public class CadastroDeCurricolo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws EmailException {
        
        //CursoDao teste = new CursoDao();
        //System.out.println(" "+teste.readCursos()+" ");
        
        System.out.println("");
        Sistema s1 = new Sistema();

        
        //Resultado t1 =new Resultado(s1.infoPessoa("Joelma","Cruz"));
        //t1.setVisible(true);

       //Pesquisas t1 =new Pesquisas(); 
        //t1.setVisible(true);
        
        Login t3 = new Login();
        t3.setVisible(true);
        
        //Cadastro t1 = new Cadastro();
        //t1.setVisible(true);
        
        
    }
    
}
