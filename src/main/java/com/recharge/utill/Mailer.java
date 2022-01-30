package com.recharge.utill;

import java.util.Properties;    
import javax.mail.*;    
import javax.mail.internet.*;

import org.apache.log4j.Logger;
 

public class Mailer {
	private static final Logger logger = Logger.getLogger(Mailer.class);
    public static void send(){  
    	
        //Get properties object    
    	Properties props = new Properties();
         props.put("mail.smtp.host", "true");
         props.put("mail.smtp.host", "smtp.zoho.com");
         props.put("mail.smtp.port", "587");
         props.put("mail.smtp.auth", "true");
         props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
         props.put("mail.smtp.socketFactory.fallback", "false");
         props.put("mail.smtp.socketFactory.port", "465");
         props.put("mail.smtp.starttls.enable", "true");
	

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("admin@mobecoin.io", "Mobe7890$");
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("admin@mobecoin.io"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("admin@mobecoin.io"));
			message.setSubject("WelCome Letter");
			message.setText("Dear Mail Crawler,"+ "\n\n No spam to my email, please!");

			Transport.send(message);

			System.out.println("Done");
		} catch (MessagingException e) {
			System.out.println("Unable to send an email" + e);
			throw new RuntimeException(e);
			
		}
           
  } 
    public static void main(String[] args) {    
    	   //from,password,to,subject,message  
    	  // Mailer.send("prateetisingha21@gmail.com","S9046785738","prateetisingha21@gmail.com","hello javatpoint","How r u?");  
    	   //change from, password and to  
    	}    


	

	
}  

