package com.bank.creditcard.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bank.creditcard.dto.OTPRequestDto;
import com.bank.creditcard.dto.OTPResponseDto;
import com.bank.creditcard.util.SendMail;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MailServiceImpl implements MailService {
	@Autowired
	SendMail sendMail;
		
	@Override
	public void sendEmail(String emailId, String message, String subject) {
		log.info(":: Enter into LoginController--------::login()");
		sendMail.SendMailToCustomer(emailId, subject, message);
	}
	
	@Override
	public OTPResponseDto sendOtp(OTPRequestDto OTPRequestDto) {
	log.info(":: Enter into LoginController--------::login()");
	Random rand= new Random();
	sendEmail(OTPRequestDto.getEmail(), Integer.toString(rand.nextInt(9999)), "OTP");
	OTPResponseDto otpResponseDto = new OTPResponseDto();
	otpResponseDto.setMessage("Success");
	otpResponseDto.setStatusCode(HttpStatus.OK.value());
	return otpResponseDto;
	}
}
