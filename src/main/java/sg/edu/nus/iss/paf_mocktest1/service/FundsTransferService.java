package sg.edu.nus.iss.paf_mocktest1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.paf_mocktest1.models.Account;
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
}
