 PRAGMA foreign_keys = ON;

 CREATE TABLE IF NOT EXISTS Users (
     userId	INTEGER,
     name	varchar(50) NOT NULL,
     address	varchar(255) NOT NULL,
     PRIMARY KEY(userId AUTOINCREMENT)
 );

 CREATE TABLE IF NOT EXISTS Accounts (
     accountId	INTEGER,
     userId	INTEGER(10) NOT NULL,
     balance	decimal(50) NOT NULL CHECK(balance >= 0 AND balance <= 2000000000 AND balance = ROUND("balance", 3)),
     currency	varchar(255) NOT NULL,
     FOREIGN KEY(userId) REFERENCES Users(userId),
     PRIMARY KEY(accountId AUTOINCREMENT)
 );

 CREATE TABLE IF NOT EXISTS Transactions (
     transactionId	INTEGER,
     accountId	INTEGER(10) NOT NULL,
     amount	decimal(15) NOT NULL CHECK(amount <= 1000000000 AND amount = ROUND("amount", 3)),
     FOREIGN KEY(accountId) REFERENCES Accounts(accountId),
     PRIMARY KEY(transactionId AUTOINCREMENT)
 );