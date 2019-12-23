package com.bank.creditcard.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.bank.creditcard.dto.LoginResponseDto;
import com.bank.creditcard.dto.RegistrationRequestDto;
import com.bank.creditcard.dto.RegistrationResponseDto;
import com.bank.creditcard.exception.UnderAgeException;
import com.bank.creditcard.service.RegistrationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(MockitoJUnitRunner.Silent.class)
public class CardControllerTest {
	
	@InjectMocks
	CardController cardController;
	
	@Mock
	RegistrationService registrationService;
	
	static RegistrationResponseDto registrationResponseDto =new RegistrationResponseDto();
	static RegistrationRequestDto registrationRequestDto=new RegistrationRequestDto();
	
	@Before
	public void setUp() {
		registrationRequestDto.setCustomerEmail("test");
		registrationResponseDto.setPassword("test");
	}
	
	@Test
	public void testRegisterPositive() throws UnderAgeException {
		log.info("testRegisterPositive");
		Mockito.when(registrationService.register(registrationRequestDto)).thenReturn(registrationResponseDto);
		Integer result = cardController.register(registrationRequestDto).getStatusCodeValue();
		assertEquals(200, result);
	}
	
	@Test
	public void testRegisterNegative() throws UnderAgeException {
		log.info("testRegisterNegative");
		Mockito.when(registrationService.register(registrationRequestDto)).thenReturn(registrationResponseDto);
		ResponseEntity<RegistrationResponseDto> registrationResponseDto = cardController.register(registrationRequestDto);
		Assert.assertNotNull(registrationResponseDto);
	}

}
