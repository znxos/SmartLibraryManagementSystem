package za.ac.cput.domain;

import java.time.LocalDate;

public class Fine {
    private String fineId;
    private double amount;
    private String status;
    private LocalDate createdDate;
    private LocalDate paidDate;
    private Loan loan;

    public Fine(String fineId, Loan loan, double amount) {
        this.fineId = fineId;
        this.loan = loan;
        this.amount = amount;
        this.createdDate = LocalDate.now();
        this.status = "OUTSTANDING";
    }

    public double calculateAmount() {
        return loan.calculateFine();
    }

    public void markAsPaid() {
        this.status = "PAID";
        this.paidDate = LocalDate.now();
        System.out.println("Fine marked as paid: " + fineId);
    }

    public void waiveFine() {
        this.status = "WAIVED";
        System.out.println("Fine waived: " + fineId);
    }

    public String getFineId() {
        return fineId; }

    public double getAmount() {
        return amount; }

    public String getStatus() {
        return status; }

    public LocalDate getCreatedDate() {
        return createdDate; }

    public LocalDate getPaidDate() {
        return paidDate; }

    public Loan getLoan() {
        return loan; }
}