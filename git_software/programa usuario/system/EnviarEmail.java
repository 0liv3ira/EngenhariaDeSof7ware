/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
/**
 *
 * @author Venom
 */
public class EnviarEmail {
    
    SimpleEmail email;
    
    public EnviarEmail() {
        System.out.println("Iniciando envio de email");
        email = new SimpleEmail();
        
    }
    
    public void Envio(String destinatario ,String remetente, String Mensagem, String Assunto, String Senha) throws EmailException {
    email.setHostName("smtp.googlemail.com");
email.setSmtpPort(465);
email.setAuthentication(remetente,Senha);
email.setSSLOnConnect(true);
email.setFrom(remetente);
email.setSubject(Assunto);
email.setMsg(Mensagem);
email.addTo(destinatario);
        System.out.println("passei");
email.send();
        // É-se necessario desbloquear nas configurações do gmail , permitindo apps menos seguros
    }
    
    
}
