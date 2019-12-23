package com.bank.creditcard.service;

import com.bank.creditcard.dto.OTPRequestDto;
import com.bank.creditcard.dto.OTPResponseDto;

public interface MailService {

	void sendEmail(String emailId, String message, String subject);

	OTPResponseDto sendOtp(OTPRequestDto OTPRequestDto);

}
