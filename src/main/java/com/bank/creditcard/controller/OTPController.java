package com.bank.creditcard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.creditcard.dto.OTPResponseDto;
import com.bank.creditcard.service.MailService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RestController
@RequestMapping("/otp")
@Slf4j
public class OTPController {
	
	@Autowired
	MailService mailService;
	
	@GetMapping("/{email}")
	public ResponseEntity<OTPResponseDto> OTPResponse(@PathVariable String email){
		OTPResponseDto otpResponseDto= mailService.sendOtp(email);
		return new ResponseEntity<>(otpResponseDto,HttpStatus.OK);
		
	}

}
