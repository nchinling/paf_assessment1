package sg.edu.nus.iss.paf_mocktest1.repository;


import static sg.edu.nus.iss.paf_mocktest1.repository.DBQueries.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import sg.edu.nus.iss.paf_mocktest1.models.Account;

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


}
