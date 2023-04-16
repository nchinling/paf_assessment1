package sg.edu.nus.iss.paf_mocktest1.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import sg.edu.nus.iss.paf_mocktest1.models.Account;


public class AccountRowMapper implements RowMapper<Account>{
    
    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {

        Account account = new Account();
        
        account.setAccountId(rs.getString("account_id"));
        account.setName(rs.getString("name"));
        account.setBalance(rs.getDouble("balance"));


        return account;

    }
}
