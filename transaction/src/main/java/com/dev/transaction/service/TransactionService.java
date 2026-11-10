
package com.dev.transaction.service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import com.dev.transaction.dto.TransactionRequest;
import com.dev.transaction.model.Transaction;
import com.dev.transaction.repository.CsvTransactionRepository;

@Service
public class TransactionService {

    private final CsvTransactionRepository transactionRepository;

    private static final List<String> STATUSES = List.of("Pending", "Settled", "Failed");

    public TransactionService(
            CsvTransactionRepository transactionRepository) {

        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions() {

        return transactionRepository.findAll();
    }

    public Transaction addTransaction(TransactionRequest request) {

        String status = STATUSES.get(
                ThreadLocalRandom.current().nextInt(STATUSES.size())
        );

        Transaction transaction = new Transaction(
                request.getTransactionDate(),
                request.getAccountNumber(),
                request.getAccountHolderName(),
                request.getAmount(),
                status
        );

        transactionRepository.save(transaction);

        return transaction;
    }
}
