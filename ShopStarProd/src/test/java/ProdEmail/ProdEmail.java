package ProdEmail;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.commons.mail.*;

public class ProdEmail {
	
private static void ProdEmail() throws EmailException {
		// Create the attachment
		  EmailAttachment attachment = new EmailAttachment();
		  attachment.setPath("C:\\Users\\NTimilsi\\git\\ShopStarProd\\target\\surefire-reports\\emailable-report.html");
		  attachment.setDisposition(EmailAttachment.ATTACHMENT);
		
		Email email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("hbsqatester@gmail.com","Halo1234"));
		email.setSSLOnConnect(true);
		email.setFrom("hbsqatester@gmail.com");
		email.setSubject("Automation Test Failure Report");
		email.setMsg("This test automation is failed.");
		email.addTo("nitesh.timilsina@halo.com");
		//email.attachment(attachment);
		email.send();
		
		
	}
}
