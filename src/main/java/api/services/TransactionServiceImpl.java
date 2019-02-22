package api.services;

import api.models.Transaction;
import api.repositories.CustomerRepository;
import api.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TransactionServiceImpl implements TransactionService {

    /**
     * The transaction repository implementation
     */
    private TransactionRepository transactionRepo;

    /**
     * The customer repository implementation
     */
    private CustomerRepository customerRepo;

    /**
     * Constructor
     *
     * @param transactionRepo The transaction repository implementation
     * @param customerRepo The customer repository implementation
     */
    public TransactionServiceImpl(TransactionRepository transactionRepo, CustomerRepository customerRepo) {
        this.transactionRepo = transactionRepo;
        this.customerRepo = customerRepo;
    }

    /**
     * Get transaction
     *
     * @param transactionId The transaction Id to be fetched.
     * @return The transaction entity fetched from persistent storage.
     */
    @Override
    public Transaction getTransaction(int transactionId) {
        var transaction = transactionRepo.findById(transactionId);
        return transaction.orElse(null);
    }

    /**
     * Get transactions by customer id
     *
     * @param customerId The customer Id.
     * @return The transaction entities fetched from persistent storage.
     */
    @Override
    public Set<Transaction> getTransactionsByCustomerId(int customerId) {
        return transactionRepo.findTransactionsByCustomerId(customerId);
    }

    /**
     * Create transaction
     *
     * @param transactionDto The transaction entity to be persisted.
     * @return The newly persisted transaction entity.
     */
    @Override
    public Transaction createTransaction(Transaction transactionDto) {
        var customerRecord = customerRepo.findById(transactionDto.getCustomerId());
        if (customerRecord.isPresent()) {
            transactionRepo.save(transactionDto);
            transactionDto.setCustomer(customerRecord.get());
            return transactionDto;
        } else {
            return null;
        }
    }

    /**
     * Update transaction
     *
     * @param transactionId The transaction Id to be updated.
     * @param transactionDto The updated transaction entity to be persisted.
     * @return The newly updated transaction entity.
     */
    @Override
    public Transaction updateTransaction(int transactionId, Transaction transactionDto) {
        var transactionRecord = transactionRepo.findById(transactionId);
        if (transactionRecord.isPresent()) {
            var customerRecord = customerRepo.findById(transactionDto.getCustomerId());
            if (customerRecord.isPresent()) {
                var tran = transactionRecord.get();
                tran.setTransactionDate(transactionDto.getTransactionDate());
                tran.setTransactionSubTotal(transactionDto.getTransactionSubTotal());
                tran.setTransactionTax(transactionDto.getTransactionTax());
                tran.setTransactionTotal(transactionDto.getTransactionTotal());
                tran.setCustomerId(transactionDto.getCustomerId());
                transactionRepo.save(tran);
                tran.setCustomer(customerRecord.get());
                return tran;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Delete transaction
     *
     * @param transactionId The transaction Id to be deleted.
     */
    @Override
    public void deleteTransaction(int transactionId) {
        var transaction = transactionRepo.findById(transactionId);
        transaction.ifPresent(x -> transactionRepo.delete(x));
    }
}
