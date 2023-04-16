package sg.edu.nus.iss.paf_mocktest1.repository;


import static sg.edu.nus.iss.paf_mocktest1.repository.DBQueries.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import sg.edu.nus.iss.paf_mocktest1.models.Account;
import sg.edu.nus.iss.paf_mocktest1.models.Transaction;

@Repository
public class AccountsRepository {
    
    @Autowired 
    JdbcTemplate jdbcTemplate;

    public List<Account> getAllAccounts(){
        String query = GET_ALL_ACCOUNTS;

        return jdbcTemplate.query(query, (rs, rownum) -> {

            Account account = new Account();
            account.setAccountId(rs.getString("account_id"));
            account.setName(rs.getString("name"));
            account.setBalance(rs.getDouble("balance"));
            return account; 
        });

    }

    
    public Account getAccountById(String accountId){
        List<Account> accounts = jdbcTemplate.query(SELECT_ACCOUNT_BY_ID, 
        new AccountRowMapper() , new Object[]{accountId});
        
        return accounts.get(0);

    }

    public boolean withdraw (Transaction transaction){
        
        String query = WITHDRAW_FROM_ACCOUNT;
        
        final int rowCount= jdbcTemplate.update(query,transaction.getAmount(), 
                                                transaction.getFromAccount());
 
        return rowCount> 0;
        }
    
    public boolean deposit(Transaction transaction) {
        String query = DEPOSIT_INTO_ACCOUNT;
        final int rowCount= jdbcTemplate.update(query,transaction.getAmount(), 
                                                transaction.getToAccount());

        return rowCount> 0;
    

    }



}
