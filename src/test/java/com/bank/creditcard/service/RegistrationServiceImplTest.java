package com.bank.creditcard.service;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.bank.creditcard.constants.ApplicationConstants;
import com.bank.creditcard.dto.RegistrationRequestDto;
import com.bank.creditcard.dto.RegistrationResponseDto;
import com.bank.creditcard.entity.Card;
import com.bank.creditcard.entity.Customer;
import com.bank.creditcard.exception.UnderAgeException;
import com.bank.creditcard.repository.CardRepository;
import com.bank.creditcard.repository.CustomerRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class RegistrationServiceImplTest {
	
	@InjectMocks
	RegistrationServiceImpl registrationServiceImpl;
	
	@Mock
	CardRepository cardRepository;
	
	@Mock
	CustomerRepository customerRepository;
	
	RegistrationRequestDto registrationRequestDto=new RegistrationRequestDto();
	Card card= new Card();
	Customer customer= new Customer();
	RegistrationResponseDto registrationResponseDto= new RegistrationResponseDto();
	
	
	@Before
	public void setUp() {
		registrationRequestDto.setDateOfBirth(LocalDate.of(2018, 8, 25));
		registrationRequestDto.setCustomerEmail("hema");
		registrationRequestDto.setFirstName("test");
		registrationRequestDto.setCardBalance(546.7);
		registrationRequestDto.setGender("female");
		registrationRequestDto.setLastName("test");
		registrationRequestDto.setMobile(9876543210L);
		registrationRequestDto.setProfession("Doctor");
		registrationRequestDto.setSalary(89.9);
		customer.setCustomerId(1L);
		customer.setCustomerEmail("hema");
		customer.setPassword("555");
		card.setCardType(ApplicationConstants.TYPE);
		card.setCustomerId(customer);
		card.setCvv(655);
		card.setValidFrom(LocalDate.now());
		card.setValidTo(LocalDate.now().plusMonths(ApplicationConstants.VALID_MONTHS));
		registrationResponseDto.setCustomerId(customer.getCustomerEmail());
		registrationResponseDto.setPassword(customer.getPassword());
	}
	
	@Test(expected = UnderAgeException.class)
	public void testRegisterNegative() throws UnderAgeException {
		registrationServiceImpl.register(registrationRequestDto);
		
	}
	
}
