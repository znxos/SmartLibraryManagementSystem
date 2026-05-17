package za.ac.cput.domain;

import java.util.ArrayList;
import java.util.List;

public class Member extends UserAccount {
    private String fullName;
    private double fineBalance;
    private String accountStatus;
    private List<Loan> loans;
    private List<Reservation> reservations;

    public Member(String userId, String fullName, String email, String passwordHash) {
        super(userId, email, passwordHash, "MEMBER");
        this.fullName = fullName;
        this.fineBalance = 0.0;
        this.accountStatus = "ACTIVE";
        this.loans = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    public void register() {
        this.accountStatus = "ACTIVE";
        System.out.println("Member registered: " + fullName);
    }

    public String login(String passwordHash) {
        if (authenticate(passwordHash)) {
            return generateToken();
        }
        return null;
    }

    public List<Book> searchBooks(String query) {
        System.out.println("Searching for: " + query);
        return new ArrayList<>();
    }

    public void reserveBook(String bookId) {
        if (!accountStatus.equals("SUSPENDED")) {
            System.out.println("Reservation placed for book: " + bookId);
        } else {
            System.out.println("Cannot reserve. Account is suspended.");
        }
    }

    public List<Loan> viewBorrowingHistory() {
        return loans;
    }

    public double viewFines() {
        return fineBalance;
    }

    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public String getFullName() {
        return fullName; }

    public void setFullName(String fullName) {
        this.fullName = fullName; }

    public double getFineBalance() {
        return fineBalance; }

    public void setFineBalance(double fineBalance) {
        this.fineBalance = fineBalance; }

    public String getAccountStatus() {
        return accountStatus; }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus; }

    public List<Loan> getLoans() {
        return loans; }

    public List<Reservation> getReservations() {
        return reservations; }
}