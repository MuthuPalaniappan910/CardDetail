package com.bank.creditcard.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.creditcard.dto.TransactionResponseDto;
import com.bank.creditcard.dto.TransactionSummary;
import com.bank.creditcard.entity.Card;
import com.bank.creditcard.entity.Transaction;
import com.bank.creditcard.exception.CardException;
import com.bank.creditcard.exception.NoTransactionException;
import com.bank.creditcard.repository.CardRepository;
import com.bank.creditcard.repository.TransactionRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	CardRepository cardRepository;

	@Autowired
	TransactionRepository transactionRepository;

	/**
	 * This method is used to get monthly transactions of the credit card for a particular year
	 * @author chethana
	 * @param month is of integer dataType
	 * @param year is of integer dataType
	 * @param creditCardId is of long dataType
	 * @return TransactionResponseDto returns a list of transaction summary
	 * 
	 */
	public TransactionResponseDto getMonthlyStatement(int month, int year, Long creditCardId) {
		log.info("Entering into getMonthlyStatement() of TransactionServiceImpl");
		Optional<Card> creditCardResponse = cardRepository.findById(creditCardId);

		if (!creditCardResponse.isPresent()) {
			log.error("Exception Occured in getMonthlyStatement");
			throw new CardException("Invalid credit card");
		}
		Optional<List<Transaction>> transactionResponse = transactionRepository
				.findByCardNumber(creditCardResponse.get());

		if (!transactionResponse.isPresent()) {
			log.error("No Transactions Found");
			throw new NoTransactionException("No Transactions Found");
		}

		List<TransactionSummary> transactionSummaryList = new ArrayList<>();

		transactionResponse.get().forEach(transaction -> {
			if (transaction.getTransactionTime().getMonthValue() == month
					&& transaction.getTransactionTime().getYear() == year) {
				TransactionSummary transactionSummary = new TransactionSummary();
				BeanUtils.copyProperties(transaction, transactionSummary);
				transactionSummaryList.add(transactionSummary);
			}
		});

		TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
		transactionResponseDto.setTransactionList(transactionSummaryList);
		return transactionResponseDto;
	}

}
