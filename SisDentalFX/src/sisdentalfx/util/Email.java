package sisdentalfx.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class Email 
{
    public static boolean enviarEmail(String destino,
            String titulo, String texto) {
        SimpleEmail email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("silviocarro@gmail.com", "pass"));
        email.setSSLOnConnect(true);
        
        try { email.setDebug(true);
            email.setFrom("silviocarro@gmail.com");
            email.setSubject(titulo);
            email.setMsg(texto);
            email.addTo(destino);
            email.send();
           
        } catch (EmailException ex) {
            System.out.println(ex);
            return false;
        }
        return true;
    }
    
}
