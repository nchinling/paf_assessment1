package sg.edu.nus.iss.paf_mocktest1.models;

import java.util.ArrayList;
import java.util.List;

public class Transaction {
    
    private String fromAccount;
    private String toAccount;
    private Double amount;
    private String comment;
    private String errorMessage;
    public String transactionId;

    private Account account;
    private List<Account> accounts = new ArrayList<Account>();

    public Transaction() {
    }

    
    public Transaction(String fromAccount, String toAccount, Double amount, String comment, String errorMessage,
            String transactionId, Account account, List<Account> accounts) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.comment = comment;
        this.errorMessage = errorMessage;
        this.transactionId = transactionId;
        this.account = account;
        this.accounts = accounts;
    }


    public Transaction(String fromAccount, String toAccount, Double amount, String comment, String errorMessage) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.comment = comment;
        this.errorMessage = errorMessage;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    
    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    
    public String getTransactionId() {
        return transactionId;
    }


    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    
    
    @Override
    public String toString() {
        return "Transaction [fromAccount=" + fromAccount + ", toAccount=" + toAccount + ", amount=" + amount
                + ", comment=" + comment + ", errorMessage=" + errorMessage + ", account=" + account + "]";
    }


}
