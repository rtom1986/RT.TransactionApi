package api.controllers;

import api.models.Transaction;
import api.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    /**
     * Transaction service implementation
     */
    private TransactionService transactionService;

    /**
     * Constructor
     *
     * @param transactionService The transaction service implementation
     */
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Get transaction
     *
     * @param id The transaction Id to be fetched.
     * @return The transaction entity fetched from persistent storage.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Transaction> getTransaction(@PathVariable int id) {
        try {
            var transactionEntity = transactionService.getTransaction(id);
            if (transactionEntity != null) {
                return new ResponseEntity<>(transactionEntity, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get transactions by customer id
     *
     * @param id The customer Id to be fetched.
     * @return The transaction entity fetched from persistent storage.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Set<Transaction>> getTransactions(@RequestParam("customerId") int id) {
        try {
            var transactionEntities = transactionService.getTransactionsByCustomerId(id);
            if (transactionEntities != null) {
                return new ResponseEntity<>(transactionEntities, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Create transaction
     *
     * @param transactionDto The transaction entity to be persisted.
     * @return The newly persisted transaction entity.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody Transaction transactionDto) {
        try {
            var transactionEntity = transactionService.createTransaction(transactionDto);
            if (transactionEntity != null) {
                return new ResponseEntity<>(transactionEntity, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update transaction
     *
     * @param id The transaction Id to be updated.
     * @param transactionDto The updated transaction entity to be persisted.
     * @return The newly persisted transaction entity.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Transaction> updateTransaction(@PathVariable int id, @Valid @RequestBody Transaction transactionDto) {
        try {
            var transactionEntity = transactionService.updateTransaction(id, transactionDto);
            if (transactionEntity != null) {
                return new ResponseEntity<>(transactionEntity, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete transaction
     *
     * @param id The transaction Id to be deleted.
     * @return No Content if the transaction was deleted.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteTransaction(@PathVariable int id) {
        try {
            transactionService.deleteTransaction(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
