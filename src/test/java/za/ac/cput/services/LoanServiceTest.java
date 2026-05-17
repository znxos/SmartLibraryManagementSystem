package za.ac.cput.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Book;
import za.ac.cput.domain.Loan;
import za.ac.cput.domain.Member;
import za.ac.cput.repositories.inmemory.*;
import za.ac.cput.services.LoanService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LoanServiceTest {

    private LoanService loanService;
    private InMemoryLoanRepository loanRepo;
    private InMemoryBookRepository bookRepo;
    private InMemoryMemberRepository memberRepo;
    private InMemoryFineRepository fineRepo;

    private Book testBook;
    private Member testMember;

    @BeforeEach
    public void setUp() {
        loanRepo = new InMemoryLoanRepository();
        bookRepo = new InMemoryBookRepository();
        memberRepo = new InMemoryMemberRepository();
        fineRepo = new InMemoryFineRepository();
        loanService = new LoanService(loanRepo, bookRepo, memberRepo, fineRepo);

        testBook = new Book("B001", "Clean Code", "Robert Martin", "9784", "Computer Science", 2008, 3);
        testMember = new Member("M001", "Linda Mtoba", "linda77@gmail.com", "hashedpass123");

        bookRepo.save(testBook);
        memberRepo.save(testMember);
    }

    @Test
    public void testIssueLoan_Success() {
        Loan loan = loanService.issueLoan("M001", "B001");
        assertNotNull(loan);
        assertEquals("ACTIVE", loan.getStatus());
    }

    @Test
    public void testIssueLoan_MemberNotFound_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                loanService.issueLoan("NONEXISTENT", "B001"));
    }

    @Test
    public void testIssueLoan_BookNotFound_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                loanService.issueLoan("M001", "NONEXISTENT"));
    }

    @Test
    public void testIssueLoan_SuspendedMember_ThrowsException() {
        testMember.setAccountStatus("SUSPENDED");
        memberRepo.save(testMember);
        assertThrows(IllegalStateException.class, () ->
                loanService.issueLoan("M001", "B001"));
    }

    @Test
    public void testIssueLoan_BookUnavailable_ThrowsException() {
        testBook.setAvailableCopies(0);
        bookRepo.save(testBook);
        assertThrows(IllegalStateException.class, () ->
                loanService.issueLoan("M001", "B001"));
    }

    @Test
    public void testIssueLoan_MaxLoansReached_ThrowsException() {
        Book b1 = new Book("B002", "Book2", "A", "I2", "G", 2020, 1);
        Book b2 = new Book("B003", "Book3", "A", "I3", "G", 2020, 1);
        Book b3 = new Book("B004", "Book4", "A", "I4", "G", 2020, 1);
        Book b4 = new Book("B005", "Book5", "A", "I5", "G", 2020, 1);
        bookRepo.save(b1); bookRepo.save(b2); bookRepo.save(b3); bookRepo.save(b4);
        loanService.issueLoan("M001", "B001");
        loanService.issueLoan("M001", "B002");
        loanService.issueLoan("M001", "B003");
        loanService.issueLoan("M001", "B004");
        loanService.issueLoan("M001", "B005");
        Book b5 = new Book("B006", "Book6", "A", "I6", "G", 2020, 1);
        bookRepo.save(b5);
        assertThrows(IllegalStateException.class, () ->
                loanService.issueLoan("M001", "B006"));
    }

    @Test
    public void testProcessReturn_Success() {
        Loan loan = loanService.issueLoan("M001", "B001");
        Loan returned = loanService.processReturn(loan.getLoanId());
        assertEquals("RETURNED", returned.getStatus());
    }

    @Test
    public void testProcessReturn_NotFound_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                loanService.processReturn("NONEXISTENT"));
    }

    @Test
    public void testGetAllLoans() {
        loanService.issueLoan("M001", "B001");
        List<Loan> loans = loanService.getAllLoans();
        assertEquals(1, loans.size());
    }

    @Test
    public void testGetLoansByMember() {
        loanService.issueLoan("M001", "B001");
        List<Loan> loans = loanService.getLoansByMember("M001");
        assertEquals(1, loans.size());
    }
}