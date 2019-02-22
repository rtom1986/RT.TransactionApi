package api.repositories;

import api.models.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
    Set<Transaction> findTransactionsByCustomerId(int customerId);
}
