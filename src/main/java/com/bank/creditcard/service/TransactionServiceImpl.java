package com.bank.creditcard.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.creditcard.dto.TransactionResponseDto;
import com.bank.creditcard.dto.TransactionSummary;
import com.bank.creditcard.entity.Card;
import com.bank.creditcard.exception.CardException;
import com.bank.creditcard.repository.CardRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionServiceImpl extends TransactionService{

	@Autowired
	CardRepository cardRepository;
	
	  public TransactionResponseDto getMonthlyStatement(int Month,int year,Long creditCardId) {
		  log.info("Entering into getMonthlyStatement() of TransactionServiceImpl");
		Optional<Card> creditCardResponse=cardRepository.findById(creditCardId);
		
		if(!creditCardResponse.isPresent()) {
			log.error("Exception Occured in getMonthlyStatement");
			throw new CardException("Invalid credit card");
		}
		  List<TransactionSummary> TransactionSummary= new ArrayList<>();
		  
		  
		return null;
	  
	  }
	 
	
}
