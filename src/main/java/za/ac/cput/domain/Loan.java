package za.ac.cput.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan {
    private String loanId;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String status;
    private double fineAmount;
    private Member member;
    private Book book;

    public Loan(String loanId, Member member, Book book) {
        this.loanId = loanId;
        this.member = member;
        this.book = book;
        this.issueDate = LocalDate.now();
        this.dueDate = issueDate.plusDays(14);
        this.status = "ACTIVE";
        this.fineAmount = 0.0;
    }

    public void issueLoan() {
        book.decrementAvailableCopies();
        member.addLoan(this);
        System.out.println("Loan issued. Due date: " + dueDate);
    }

    public void processReturn() {
        this.returnDate = LocalDate.now();
        if (returnDate.isAfter(dueDate)) {
            this.fineAmount = calculateFine();
            this.status = "RETURNED_WITH_FINE";
        } else {
            this.status = "RETURNED";
        }
        book.incrementAvailableCopies();
        System.out.println("Return processed. Status: " + status);
    }

    public double calculateFine() {
        if (returnDate != null && returnDate.isAfter(dueDate)) {
            long daysOverdue = ChronoUnit.DAYS.between(dueDate, returnDate);
            return daysOverdue * 2.0;
        }
        return 0.0;
    }

    public void markAsClosed() {
        this.status = "CLOSED";
        System.out.println("Loan closed: " + loanId);
    }

    public String getLoanId() {
        return loanId; }

    public LocalDate getIssueDate() {
        return issueDate; }

    public LocalDate getDueDate() {
        return dueDate; }

    public LocalDate getReturnDate() {
        return returnDate; }

    public String getStatus() {
        return status; }

    public void setStatus(String status) {
        this.status = status; }

    public double getFineAmount() {
        return fineAmount; }

    public Member getMember() {
        return member; }

    public Book getBook() {
        return book; }
}