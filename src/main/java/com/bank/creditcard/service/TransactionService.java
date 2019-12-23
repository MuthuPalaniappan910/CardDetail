package com.bank.creditcard.service;

public interface TransactionService {

	Boolean addTransaction(Long cardNumber, Double price);

}
