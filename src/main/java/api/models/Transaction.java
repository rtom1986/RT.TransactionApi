package api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "[Transaction]")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;

    @NotNull
    private Date transactionDate;

    @Min(value=1)
    private int transactionSubTotal;

    @Min(value=0)
    private int transactionTax;

    @Min(value=1)
    private int transactionTotal;

    @Min(value=1)
    private int customerId;

    @ManyToOne
    @JoinColumn(name="CustomerId", insertable = false, updatable = false)
    private Customer customer;

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getTransactionSubTotal() {
        return transactionSubTotal;
    }

    public void setTransactionSubTotal(int transactionSubTotal) {
        this.transactionSubTotal = transactionSubTotal;
    }

    public int getTransactionTax() {
        return transactionTax;
    }

    public void setTransactionTax(int transactionTax) {
        this.transactionTax = transactionTax;
    }

    public int getTransactionTotal() {
        return transactionTotal;
    }

    public void setTransactionTotal(int transactionTotal) {
        this.transactionTotal = transactionTotal;
    }

    @JsonIgnore
    public int getCustomerId() {
        return customerId;
    }

    @JsonProperty
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @JsonProperty
    public Customer getCustomer() {
        return customer;
    }

    @JsonIgnore
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
