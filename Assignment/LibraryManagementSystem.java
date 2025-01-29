package com.Librarybooks;

import java.util.*;

//Class representing a Book
class Book {
 String id;
 String title;
 String author;
 boolean isIssued;

 public Book(String id, String title, String author) {
     this.id = id;
     this.title = title;
     this.author = author;
     this.isIssued = false;
 }

 @Override
 public String toString() {
     return "Book ID: " + id + ", Title: " + title + ", Author: " + author + ", Issued: " + isIssued;
 }
}

//Class representing a Member
class Member {
 String id;
 String name;

 public Member(String id, String name) {
     this.id = id;
     this.name = name;
 }

 @Override
 public String toString() {
     return "Member ID: " + id + ", Name: " + name;
 }
}

//Class representing a Transaction
class Transaction {
 String bookId;
 String memberId;
 Date issueDate;
 Date returnDate;

 public Transaction(String bookId, String memberId, Date issueDate) {
     this.bookId = bookId;
     this.memberId = memberId;
     this.issueDate = issueDate;
     this.returnDate = null;
 }

 public void returnBook() {
     this.returnDate = new Date();
 }

 @Override
 public String toString() {
     return "Book ID: " + bookId + ", Member ID: " + memberId + ", Issue Date: " + issueDate + ", Return Date: " + (returnDate != null ? returnDate : "Not returned");
 }
}

//Main Library Management System
public class LibraryManagementSystem {
 static List<Book> books = new ArrayList<>();
 static List<Member> members = new ArrayList<>();
 static List<Transaction> transactions = new ArrayList<>();

 // Add a book
 public static void addBook(String id, String title, String author) {
     books.add(new Book(id, title, author));
     System.out.println("Book added successfully.");
 }

 // Add a member
 public static void addMember(String id, String name) {
     members.add(new Member(id, name));
     System.out.println("Member added successfully.");
 }

 // Issue a book
 public static void issueBook(String bookId, String memberId) {
     Book book = findBookById(bookId);
     if (book == null) {
         System.out.println("Book not found.");
         return;
     }
     if (book.isIssued) {
         System.out.println("Book is already issued.");
         return;
     }

     Member member = findMemberById(memberId);
     if (member == null) {
         System.out.println("Member not found.");
         return;
     }

     book.isIssued = true;
     transactions.add(new Transaction(bookId, memberId, new Date()));
     System.out.println("Book issued successfully.");
 }

 // Return a book
 public static void returnBook(String bookId) {
     Book book = findBookById(bookId);
     if (book == null) {
         System.out.println("Book not found.");
         return;
     }
     if (!book.isIssued) {
         System.out.println("Book is not issued.");
         return;
     }

     for (Transaction transaction : transactions) {
         if (transaction.bookId.equals(bookId) && transaction.returnDate == null) {
             transaction.returnBook();
             book.isIssued = false;
             System.out.println("Book returned successfully.");
             return;
         }
     }
 }

 // Find a book by ID
 private static Book findBookById(String id) {
     for (Book book : books) {
         if (book.id.equals(id)) {
             return book;
         }
     }
     return null;
 }

 // Find a member by ID
 private static Member findMemberById(String id) {
     for (Member member : members) {
         if (member.id.equals(id)) {
             return member;
         }
     }
     return null;
 }

 // List all books
 public static void listBooks() {
     for (Book book : books) {
         System.out.println(book);
     }
 }

 // List all members
 public static void listMembers() {
     for (Member member : members) {
         System.out.println(member);
     }
 }

 // List all transactions
 public static void listTransactions() {
     for (Transaction transaction : transactions) {
         System.out.println(transaction);
     }
 }

 public static void main(String[] args) {
     addBook("B001", "The Great Gatsby", "F. Scott Fitzgerald");
     addBook("B002", "1984", "George Orwell");
     addMember("M001", "Alice");
     addMember("M002", "Bob");

     issueBook("B001", "M001");
     listTransactions();

     returnBook("B001");
     listTransactions();

     listBooks();
     listMembers();
 }
}
