package util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email
{

    public static boolean enviarHotmail(String Destinatario, String Assunto, String Mensagem)
    {
        Properties props = new Properties();
        /**
         * Parâmetros de conexão com servidor Hotmail
         */
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.live.com");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("SysEmail@hotmail.com", "SenhaEmailSistema");
            }
        });
        /**
         * Ativa Debug para sessão
         */
        session.setDebug(true);
        try
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("SysDepartamento@hotmail.com")); //Remetente
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(Destinatario)); //Destinatário(s)
            message.setSubject(Assunto);//Assunto
            message.setText(Mensagem);
            /*
                Método para enviar a arquivo anexado
                ////////////////////////////////////
                Multipart multipart = new MimeMultipart();
                BodyPart messageBodyPart = new MimeBodyPart();
                // Now set the actual message
                messageBodyPart.setText("This is message body");
                // Set text message part
                multipart.addBodyPart(messageBodyPart);
                // Part two is attachment
                messageBodyPart = new MimeBodyPart();
                String filename = "C:/Users/Luish/Desktop/D&D5/D&D 5.0 - Fichas/D&D 5.0 - Ficha de Personagem (Impressão).pdf";
                DataSource source = new FileDataSource(filename);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(filename);
                multipart.addBodyPart(messageBodyPart);
                message.setContent(multipart);
                ///////////////////////////////////
             */
            Transport.send(message);
            return true;
        } catch (MessagingException e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
