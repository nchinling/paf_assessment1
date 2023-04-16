package sg.edu.nus.iss.paf_mocktest1.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.paf_mocktest1.exception.TransferException;
import sg.edu.nus.iss.paf_mocktest1.models.Account;
import sg.edu.nus.iss.paf_mocktest1.models.Transaction;
import sg.edu.nus.iss.paf_mocktest1.repository.AccountsRepository;

@Service
public class FundsTransferService {

    @Autowired
    private AccountsRepository acctRepo;
    
    public List<Account> getAllAccounts(){
        return acctRepo.getAllAccounts();
    }

    public Account getAccountById(String accountId){
        return acctRepo.getAccountById(accountId);
    }

    // public static void createTransaction(Transaction transaction){
    //     acctRepo.createTransaction(transaction);
    // }

    
    @Transactional(rollbackFor = TransferException.class)
    public void createTransaction(Transaction transaction) throws TransferException{

        //create order id. 
        UUID transactionId = UUID.randomUUID();
        String transactionId8Char = transactionId.toString().substring(0, 8);

        transaction.setTransactionId(transactionId8Char);

        //insert transaction into the two tables.
        acctRepo.withdraw(transaction);
        acctRepo.deposit(transaction);

        if (!(acctRepo.withdraw(transaction) && acctRepo.deposit(transaction))){
            throw new TransferException("Transaction was not successful");
        }

    }
}
