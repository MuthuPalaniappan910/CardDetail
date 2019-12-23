package com.bank.creditcard.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.creditcard.constants.ApplicationConstants;
import com.bank.creditcard.dto.LoginRequestDto;
import com.bank.creditcard.dto.LoginResponseDto;
import com.bank.creditcard.entity.Customer;
import com.bank.creditcard.exception.UserException;
import com.bank.creditcard.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

	@Autowired
	CustomerRepository customerRepository;

	/**
	 * @author Chethana
	 * @Description This method is used for user to login with valid credentials
	 * @param loginRequestdto
	 * @return LoginResponsedto
	 * @exception LOGIN_ERROR
	 */
	public LoginResponseDto login(LoginRequestDto loginRequestdto) throws UserException {
		log.info("Entering into login() method of LoginServiceImpl");
		Optional<Customer> customerResponse = customerRepository
				.findByCustomerEmailAndPassword(loginRequestdto.getCustomerEmail(), loginRequestdto.getPassword());
		if (!customerResponse.isPresent()) {
			log.error("Exception occured in login() method of LoginServiceImpl");
			throw new UserException(ApplicationConstants.LOGIN_FAILURE_MSG);
		}
		
		LoginResponseDto loginResponsedto = new LoginResponseDto();
		loginResponsedto.setCustomerID(customerResponse.get().getCustomerId());
		loginResponsedto.setCustomerName(customerResponse.get().getFirstName().concat(" ").concat(customerResponse.get().getLastName()));
		loginResponsedto.setCreditCardId(1000000001L);
		return loginResponsedto;
	}
}