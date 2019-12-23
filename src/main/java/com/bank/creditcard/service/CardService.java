package com.bank.creditcard.service;

import com.bank.creditcard.exception.DateInvalidException;

public interface CardService {

	Boolean getCardDetails(Long cardNumber, Integer cvv,Double price) throws DateInvalidException;

}
