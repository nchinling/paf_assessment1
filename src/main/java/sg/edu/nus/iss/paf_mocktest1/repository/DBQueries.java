package sg.edu.nus.iss.paf_mocktest1.repository;

public class DBQueries {
    public static final String GET_ALL_ACCOUNTS = "select * from accounts";
    public static final String SELECT_ACCOUNT_BY_ID ="select * from accounts where account_id = ?";

    public static final String WITHDRAW_FROM_ACCOUNT = "update accounts set balance = balance - ? where account_id= ?";
    public static final String DEPOSIT_INTO_ACCOUNT = "update accounts set balance = balance + ? where account_id= ?";
  
}
