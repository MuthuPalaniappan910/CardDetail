package com.bank.creditcard.service;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.creditcard.constants.ApplicationConstants;
import com.bank.creditcard.entity.Card;
import com.bank.creditcard.entity.Transaction;
import com.bank.creditcard.repository.CardRepository;
import com.bank.creditcard.repository.TransactionRepository;
/*
 * Used for adding transaction and getting transaction history
 * 
 */
@Service
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	CardRepository cardRepository;
	/**
	 * @author Muthu
	 * 
	 *         Method is used to add transaction based on card number
	 * 
	 * @param cardNumber
	 * @param price
	 * @return
	 */
	@Override
	public Boolean addTransaction(Long cardNumber, Double price) {
		Card card = cardRepository.findByCardNumber(cardNumber);
		Transaction transaction = new Transaction();
		if (!(Objects.isNull(card))) {
			Double priceUpdate = card.getCardBalance() - price;
			card.setCardBalance(priceUpdate);
			cardRepository.save(card);
			transaction.setAvailableBalance(priceUpdate);
			transaction.setCardNumber(card);
			transaction.setTransactionAmount(price);
			transaction.setTransactionComments(ApplicationConstants.TRANSACTIONCOMMENTS);
			transaction.setTransactionStatus(ApplicationConstants.SUCCESS_MSG);
			transaction.setTransactionTime(LocalDateTime.now());
			transaction.setTransactionType(ApplicationConstants.DEBIT_MESSAGE);
			transactionRepository.save(transaction);
			return true;
		}
		return false;
	}
}
