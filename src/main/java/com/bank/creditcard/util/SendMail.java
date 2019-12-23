package com.bank.creditcard.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class SendMail {
	@Autowired
	private MailSender mailSender;

	public void SendMailToCustomer(String toAddress, String subject, String msgBody) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("muthupalaniappan552@gmail.com");
		msg.setTo(toAddress);
		msg.setSubject(subject);
		msg.setText(msgBody);
		mailSender.send(msg);
	}

}
