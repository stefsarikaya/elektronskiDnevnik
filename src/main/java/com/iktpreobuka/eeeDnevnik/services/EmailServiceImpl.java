package com.iktpreobuka.eeeDnevnik.services;



import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.iktpreobuka.eeeDnevnik.models.EmailObject;


@Service
public class EmailServiceImpl implements EmailService{
	
	@Autowired
	public JavaMailSender emailSender;

	@Override
	public void sendSimpleMessage(EmailObject object) {
		SimpleMailMessage message= new SimpleMailMessage();
		message.setTo(object.getTo());
		message.setSubject(object.getSubject());
		message.setText(object.getText());
		emailSender.send(message);
	}

	@Override
	public void sendTemplateMessage(EmailObject object) throws Exception {
		MimeMessage mail= emailSender.createMimeMessage();
		MimeMessageHelper helper= new MimeMessageHelper(mail, true);
		helper.setTo(object.getTo());
		helper.setSubject(object.getSubject());		
		String text = "<html><body><table" + "style='border:2px solid black'>" + "<tr><td>Ucebuj</td>" + "<td>Dete</td>"
				+ "<td>Ocena</td>" + "<td>Datum Ocene</td></tr>" + "<tr><td>" + object.getText()+"</td><td>"
				+ object.getText()  + "</td><td>"+"</td></tr>" + "</table></body></html>";
		helper.setText(text, true);
		emailSender.send(mail);
	}


}

