package api.services;

import api.models.Transaction;

import java.util.Set;

public interface TransactionService {

    Transaction getTransaction(int transactionId);

    Set<Transaction> getTransactionsByCustomerId(int customerId);

    Transaction createTransaction(Transaction transactionDto);

    Transaction updateTransaction(int transactionId, Transaction transactionDto);

    void deleteTransaction(int transactionId);
}
