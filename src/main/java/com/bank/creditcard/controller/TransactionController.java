package com.bank.creditcard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.creditcard.service.TransactionService;

import lombok.extern.slf4j.Slf4j;

/*
 * Used for adding transaction and getting transaction history
 * 
 */
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RestController
@RequestMapping("/transactions")
@Slf4j
public class TransactionController {
	@Autowired
	TransactionService transactionService;

	/**
	 * @author Muthu
	 * 
	 *         Method is used to add transaction based on card number
	 * 
	 * @param cardNumber
	 * @param price
	 * @return
	 */
	@PostMapping
	public Boolean addTransaction(@RequestParam Long cardNumber, @RequestParam Double price) {
		log.info("Adding transaction");
		Boolean response = transactionService.addTransaction(cardNumber, price);
		return response;
	}
}
