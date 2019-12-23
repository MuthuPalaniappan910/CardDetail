package com.bank.creditcard.controller;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.creditcard.constants.ApplicationConstants;
import com.bank.creditcard.dto.RegistrationRequestDto;
import com.bank.creditcard.dto.RegistrationResponseDto;
import com.bank.creditcard.exception.UnderAgeException;
import com.bank.creditcard.service.RegistrationService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RestController
@RequestMapping("/cards")
@Slf4j
public class CardController {

	@Autowired
	RegistrationService registrationService;

	@PostMapping
	public ResponseEntity<RegistrationResponseDto> register(@Valid @RequestBody RegistrationRequestDto registrationRequestDto) throws UnderAgeException {
		log.info("Entering into registerCustomer method of registrationService ");
		RegistrationResponseDto registrationResponseDto = registrationService.register(registrationRequestDto);
		if(Objects.isNull(registrationResponseDto)) {
			RegistrationResponseDto registrationResponse= new RegistrationResponseDto();
			registrationResponse.setMessage(ApplicationConstants.FAILURE_MSG);
			registrationResponse.setStatusCode(ApplicationConstants.FAILURE_CODE);
			return new ResponseEntity<>(registrationResponseDto, HttpStatus.NOT_FOUND);
			
		}
		registrationResponseDto.setMessage(ApplicationConstants.SUCCESS_MSG);
		registrationResponseDto.setStatusCode(ApplicationConstants.SUCCESS_CODE);
		return new ResponseEntity<>(registrationResponseDto, HttpStatus.OK);
	}

}
