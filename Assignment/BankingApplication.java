package com.wt;

import java.util.ArrayList;

abstract class Account {
 private int accountNumber;
 private String accountHolderName;
 protected double balance;

 public Account(int accountNumber, String accountHolderName, double initialBalance) {
     this.accountNumber = accountNumber;
     this.accountHolderName = accountHolderName;
     this.balance = initialBalance;
 }

 public int getAccountNumber() {
     return accountNumber;
 }

 public String getAccountHolderName() {
     return accountHolderName;
 }

 public double getBalance() {
     return balance;
 }

 public abstract void withdraw(double amount);

 public void deposit(double amount) {
     if (amount > 0) {
         balance += amount;
         System.out.println(amount + " deposited successfully. New balance: " + balance);
     } else {
         System.out.println("Deposit amount must be greater than zero.");
     }
 }

 public void transferFunds(Account recipient, double amount) {
     if (balance >= amount) {
         this.withdraw(amount);
         recipient.deposit(amount);
         System.out.println("Transferred " + amount + " to " + recipient.getAccountHolderName());
     } else {
         System.out.println("Insufficient balance for transfer.");
     }
 }

 @Override
 public String toString() {
     return "Account Number: " + accountNumber + ", Holder: " + accountHolderName + ", Balance: " + balance;
 }
}

class SavingsAccount extends Account {
 private double interestRate;

 public SavingsAccount(int accountNumber, String accountHolderName, double initialBalance, double interestRate) {
     super(accountNumber, accountHolderName, initialBalance);
     this.interestRate = interestRate;
 }

 public void addInterest() {
     double interest = balance * (interestRate / 100);
     balance += interest;
     System.out.println("Interest of " + interest + " added. New balance: " + balance);
 }

 @Override
 public void withdraw(double amount) {
     if (amount > 0 && balance >= amount) {
         balance -= amount;
         System.out.println(amount + " withdrawn successfully. Remaining balance: " + balance);
     } else {
         System.out.println("Insufficient funds or invalid withdrawal amount.");
     }
 }

 @Override
 public String toString() {
     return super.toString() + ", Account Type: Savings, Interest Rate: " + interestRate + "%";
 }
}

class CurrentAccount extends Account {
 private double overdraftLimit;

 public CurrentAccount(int accountNumber, String accountHolderName, double initialBalance, double overdraftLimit) {
     super(accountNumber, accountHolderName, initialBalance);
     this.overdraftLimit = overdraftLimit;
 }

 @Override
 public void withdraw(double amount) {
     if (amount > 0 && (balance + overdraftLimit >= amount)) {
         balance -= amount;
         System.out.println(amount + " withdrawn successfully. Remaining balance: " + balance);
     } else {
         System.out.println("Withdrawal amount exceeds overdraft limit.");
     }
 }

 @Override
 public String toString() {
     return super.toString() + ", Account Type: Current, Overdraft Limit: " + overdraftLimit;
 }
}

//Transaction Class
class Transaction {
 private int transactionId;
 private int accountNumber;
 private String type; // "Deposit", "Withdraw", "Transfer"
 private double amount;

 public Transaction(int transactionId, int accountNumber, String type, double amount) {
     this.transactionId = transactionId;
     this.accountNumber = accountNumber;
     this.type = type;
     this.amount = amount;
 }

 @Override
 public String toString() {
     return "Transaction ID: " + transactionId + ", Account: " + accountNumber + ", Type: " + type + ", Amount: " + amount;
 }
}

//Bank Class to Manage Accounts and Transactions
class Bank {
 private ArrayList<Account> accounts;
 private ArrayList<Transaction> transactions;

 public Bank() {
     accounts = new ArrayList<>();
     transactions = new ArrayList<>();
 }

 public void addAccount(Account account) {
     accounts.add(account);
     System.out.println("Account created successfully for " + account.getAccountHolderName());
 }

 public void deposit(int accountNumber, double amount) {
     Account account = findAccount(accountNumber);
     if (account != null) {
         account.deposit(amount);
         transactions.add(new Transaction(transactions.size() + 1, accountNumber, "Deposit", amount));
     } else {
         System.out.println("Account not found.");
     }
 }

 public void withdraw(int accountNumber, double amount) {
     Account account = findAccount(accountNumber);
     if (account != null) {
         account.withdraw(amount);
         transactions.add(new Transaction(transactions.size() + 1, accountNumber, "Withdraw", amount));
     } else {
         System.out.println("Account not found.");
     }
 }

 public void transferFunds(int fromAccountNumber, int toAccountNumber, double amount) {
     Account sender = findAccount(fromAccountNumber);
     Account recipient = findAccount(toAccountNumber);
     if (sender != null && recipient != null) {
         sender.transferFunds(recipient, amount);
         transactions.add(new Transaction(transactions.size() + 1, fromAccountNumber, "Transfer", amount));
     } else {
         System.out.println("Sender or recipient account not found.");
     }
 }

 public void displayAccounts() {
     System.out.println("Accounts:");
     for (Account account : accounts) {
         System.out.println(account);
     }
 }

 public void displayTransactions() {
     System.out.println("Transactions:");
     for (Transaction transaction : transactions) {
         System.out.println(transaction);
     }
 }

 private Account findAccount(int accountNumber) {
     for (Account account : accounts) {
         if (account.getAccountNumber() == accountNumber) {
             return account;
         }
     }
     return null;
 }
}

//Main Class
public class BankingApplication {
 public static void main(String[] args) {
     Bank bank = new Bank();

     // Creating accounts
     SavingsAccount savings = new SavingsAccount(101, "Alice", 5000, 3.5);
     CurrentAccount current = new CurrentAccount(102, "Bob", 10000, 2000);

     bank.addAccount(savings);
     bank.addAccount(current);

     // Performing transactions
     bank.deposit(101, 2000);
     bank.withdraw(101, 1000);
     bank.transferFunds(101, 102, 1500);

     // Adding interest to savings account
     savings.addInterest();

     // Display accounts and transactions
     bank.displayAccounts();
     bank.displayTransactions();
 }
}

