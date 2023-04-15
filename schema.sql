//create database
create database acme_bank;
use acme_bank;

//create table
create table accounts(
account_id VARCHAR(10) NOT NULL PRIMARY KEY,
name VARCHAR(50) NOT NULL,
balance DECIMAL(12,2)
);


//populate with data
insert into accounts(account_id,name, balance) VALUES
('abcde11111', 'CL Ng', 100000.45),
('abcde22222', 'Ms Seet', 999999.99),
('abcde33333', 'Woo Young Woo', 43501.27),
('abcde44444', 'Moon Dong Eun', 50000),
('abcde55555', 'Morgan Redman', 1200000);
