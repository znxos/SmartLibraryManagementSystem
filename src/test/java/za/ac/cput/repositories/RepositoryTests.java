package za.ac.cput.repositories;

import za.ac.cput.factories.RepositoryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;
import za.ac.cput.repositories.inmemory.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class RepositoryTests {

    private BookRepository bookRepo;
    private MemberRepository memberRepo;
    private LoanRepository loanRepo;
    private ReservationRepository reservationRepo;
    private FineRepository fineRepo;

    private Book testBook;
    private Member testMember;

    @BeforeEach
    public void setUp() {
        bookRepo = new InMemoryBookRepository();
        memberRepo = new InMemoryMemberRepository();
        loanRepo = new InMemoryLoanRepository();
        reservationRepo = new InMemoryReservationRepository();
        fineRepo = new InMemoryFineRepository();

        testBook = new Book("B001", "Clean Code", "Robert Martin", "9784", "Computer Science", 2008, 3);
        testMember = new Member("M001", "Linda Mtoba", "linda77@gmail.com", "hashedpass123");
    }

    @Test
    public void testBookRepository_Save_And_FindById() {
        bookRepo.save(testBook);
        Optional<Book> found = bookRepo.findById("B001");
        assertTrue(found.isPresent());
        assertEquals("Clean Code", found.get().getTitle());
    }

    @Test
    public void testBookRepository_FindAll() {
        Book book2 = new Book("B002", "Design Patterns", "GoF", "9760", "Computer Science", 1994, 2);
        bookRepo.save(testBook);
        bookRepo.save(book2);
        List<Book> all = bookRepo.findAll();
        assertEquals(2, all.size());
    }

    @Test
    public void testBookRepository_Delete() {
        bookRepo.save(testBook);
        bookRepo.delete("B001");
        Optional<Book> found = bookRepo.findById("B001");
        assertFalse(found.isPresent());
    }

    @Test
    public void testBookRepository_FindByAuthor() {
        bookRepo.save(testBook);
        List<Book> found = bookRepo.findByAuthor("Robert Martin");
        assertEquals(1, found.size());
        assertEquals("Clean Code", found.get(0).getTitle());
    }

    @Test
    public void testBookRepository_FindByGenre() {
        bookRepo.save(testBook);
        List<Book> found = bookRepo.findByGenre("Computer Science");
        assertEquals(1, found.size());
    }

    @Test
    public void testBookRepository_FindAvailable() {
        bookRepo.save(testBook);
        Book unavailable = new Book("B003", "Unavailable Book", "Author", "ISBN003", "Fiction", 2020, 1);
        unavailable.decrementAvailableCopies();
        bookRepo.save(unavailable);
        List<Book> available = bookRepo.findAvailable();
        assertEquals(1, available.size());
        assertEquals("Clean Code", available.get(0).getTitle());
    }

    @Test
    public void testBookRepository_FindById_NotFound() {
        Optional<Book> found = bookRepo.findById("NONEXISTENT");
        assertFalse(found.isPresent());
    }

    @Test
    public void testBookRepository_Update_ExistingBook() {
        bookRepo.save(testBook);
        testBook.setTitle("Clean Code Updated");
        bookRepo.save(testBook);
        Optional<Book> found = bookRepo.findById("B001");
        assertEquals("Clean Code Updated", found.get().getTitle());
    }

    @Test
    public void testMemberRepository_Save_And_FindById() {
        memberRepo.save(testMember);
        Optional<Member> found = memberRepo.findById("M001");
        assertTrue(found.isPresent());
        assertEquals("Linda Mtoba", found.get().getFullName());
    }

    @Test
    public void testMemberRepository_FindByEmail() {
        memberRepo.save(testMember);
        Optional<Member> found = memberRepo.findByEmail("linda77@gmail.com");
        assertTrue(found.isPresent());
        assertEquals("M001", found.get().getUserId());
    }

    @Test
    public void testMemberRepository_FindByAccountStatus() {
        memberRepo.save(testMember);
        List<Member> active = memberRepo.findByAccountStatus("ACTIVE");
        assertEquals(1, active.size());
    }

    @Test
    public void testMemberRepository_Delete() {
        memberRepo.save(testMember);
        memberRepo.delete("M001");
        assertFalse(memberRepo.findById("M001").isPresent());
    }

    @Test
    public void testMemberRepository_FindByEmail_NotFound() {
        Optional<Member> found = memberRepo.findByEmail("nonexistent@email.com");
        assertFalse(found.isPresent());
    }

    @Test
    public void testLoanRepository_Save_And_FindById() {
        Loan loan = new Loan("L001", testMember, testBook);
        loanRepo.save(loan);
        Optional<Loan> found = loanRepo.findById("L001");
        assertTrue(found.isPresent());
        assertEquals("ACTIVE", found.get().getStatus());
    }

    @Test
    public void testLoanRepository_FindByMemberId() {
        Loan loan = new Loan("L001", testMember, testBook);
        loanRepo.save(loan);
        List<Loan> found = loanRepo.findByMemberId("M001");
        assertEquals(1, found.size());
    }

    @Test
    public void testLoanRepository_FindByStatus() {
        Loan loan = new Loan("L001", testMember, testBook);
        loanRepo.save(loan);
        List<Loan> active = loanRepo.findByStatus("ACTIVE");
        assertEquals(1, active.size());
    }

    @Test
    public void testLoanRepository_Delete() {
        Loan loan = new Loan("L001", testMember, testBook);
        loanRepo.save(loan);
        loanRepo.delete("L001");
        assertFalse(loanRepo.findById("L001").isPresent());
    }

    @Test
    public void testReservationRepository_Save_And_FindById() {
        Reservation reservation = new Reservation("R001", testMember, testBook, 1);
        reservationRepo.save(reservation);
        Optional<Reservation> found = reservationRepo.findById("R001");
        assertTrue(found.isPresent());
        assertEquals("PENDING", found.get().getStatus());
    }

    @Test
    public void testReservationRepository_FindByMemberId() {
        Reservation reservation = new Reservation("R001", testMember, testBook, 1);
        reservationRepo.save(reservation);
        List<Reservation> found = reservationRepo.findByMemberId("M001");
        assertEquals(1, found.size());
    }

    @Test
    public void testReservationRepository_FindByStatus() {
        Reservation reservation = new Reservation("R001", testMember, testBook, 1);
        reservationRepo.save(reservation);
        List<Reservation> pending = reservationRepo.findByStatus("PENDING");
        assertEquals(1, pending.size());
    }

    @Test
    public void testReservationRepository_Delete() {
        Reservation reservation = new Reservation("R001", testMember, testBook, 1);
        reservationRepo.save(reservation);
        reservationRepo.delete("R001");
        assertFalse(reservationRepo.findById("R001").isPresent());
    }

    @Test
    public void testFineRepository_Save_And_FindById() {
        Loan loan = new Loan("L001", testMember, testBook);
        Fine fine = new Fine("F001", loan, 10.0);
        fineRepo.save(fine);
        Optional<Fine> found = fineRepo.findById("F001");
        assertTrue(found.isPresent());
        assertEquals(10.0, found.get().getAmount());
    }

    @Test
    public void testFineRepository_FindByStatus() {
        Loan loan = new Loan("L001", testMember, testBook);
        Fine fine = new Fine("F001", loan, 10.0);
        fineRepo.save(fine);
        List<Fine> outstanding = fineRepo.findByStatus("OUTSTANDING");
        assertEquals(1, outstanding.size());
    }

    @Test
    public void testFineRepository_Delete() {
        Loan loan = new Loan("L001", testMember, testBook);
        Fine fine = new Fine("F001", loan, 10.0);
        fineRepo.save(fine);
        fineRepo.delete("F001");
        assertFalse(fineRepo.findById("F001").isPresent());
    }

    @Test
    public void testRepositoryFactory_ReturnsInMemoryBookRepository() {
        BookRepository repo = RepositoryFactory.getBookRepository("MEMORY");
        assertNotNull(repo);
        assertTrue(repo instanceof InMemoryBookRepository);
    }

    @Test
    public void testRepositoryFactory_InvalidStorageType_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            RepositoryFactory.getBookRepository("INVALID");
        });
    }

    @Test
    public void testRepositoryFactory_DatabaseBookRepository_ThrowsUnsupportedOperation() {
        BookRepository repo = RepositoryFactory.getBookRepository("DATABASE");
        assertThrows(UnsupportedOperationException.class, () -> {
            repo.findAll();
        });
    }
}