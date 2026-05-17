package za.ac.cput.domain;

public class Librarian extends UserAccount {
    private String fullName;
    private String librarianId;

    public Librarian(String librarianId, String fullName, String email, String passwordHash) {
        super(librarianId, email, passwordHash, "LIBRARIAN");
        this.librarianId = librarianId;
        this.fullName = fullName;
    }

    public void issueLoan(String memberId, String bookId) {
        System.out.println("Loan issued to member " + memberId + " for book " + bookId);
    }

    public void processReturn(String loanId) {
        System.out.println("Return processed for loan: " + loanId);
    }

    public void manageCatalogue() {
        System.out.println("Managing catalogue...");
    }

    public void markFinePaid(String fineId) {
        System.out.println("Fine marked as paid: " + fineId);
    }

    public void viewAdminSummary() {
        System.out.println("Displaying admin summary...");
    }

    public String getFullName() {
        return fullName; }

    public String getLibrarianId() {
        return librarianId; }
}