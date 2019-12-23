package com.bank.creditcard.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionSummary {
	
	private Long transactionId;
	private String transactionType;
	private Double transactionAmount;
	private LocalDateTime transactionTime;
	private String transactionStatus;
	private String transactionComments;
	private Double availableBalance;
}
