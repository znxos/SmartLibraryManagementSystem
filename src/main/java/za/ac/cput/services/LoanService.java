package za.ac.cput.services;

import org.springframework.stereotype.Service;
import za.ac.cput.domain.*;
import za.ac.cput.repositories.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final FineRepository fineRepository;

    public LoanService(LoanRepository loanRepository,
                       BookRepository bookRepository,
                       MemberRepository memberRepository,
                       FineRepository fineRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.fineRepository = fineRepository;
    }

    public Loan issueLoan(String memberId, String bookId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found: " + memberId));

        if (member.getAccountStatus().equals("SUSPENDED")) {
            throw new IllegalStateException("Member account is suspended. Cannot issue loan.");
        }

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found: " + bookId));

        if (!book.checkAvailability()) {
            throw new IllegalStateException("No available copies for book: " + bookId);
        }

        long activeLoanCount = loanRepository.findByMemberId(memberId).stream()
                .filter(l -> l.getStatus().equals("ACTIVE"))
                .count();
        if (activeLoanCount >= 5) {
            throw new IllegalStateException("Member has reached the maximum of 5 active loans.");
        }

        String loanId = "L-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Loan loan = new Loan(loanId, member, book);
        loan.issueLoan();
        loanRepository.save(loan);
        return loan;
    }

    public Loan processReturn(String loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found: " + loanId));

        loan.processReturn();

        if (loan.getFineAmount() > 0) {
            String fineId = "F-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            Fine fine = new Fine(fineId, loan, loan.getFineAmount());
            fineRepository.save(fine);
            loan.getMember().setFineBalance(loan.getMember().getFineBalance() + loan.getFineAmount());
            memberRepository.save(loan.getMember());
        }

        loanRepository.save(loan);
        return loan;
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Optional<Loan> getLoanById(String loanId) {
        return loanRepository.findById(loanId);
    }

    public List<Loan> getLoansByMember(String memberId) {
        return loanRepository.findByMemberId(memberId);
    }

    public List<Loan> getOverdueLoans() {
        return loanRepository.findOverdue();
    }

    public List<Loan> getLoansByStatus(String status) {
        return loanRepository.findByStatus(status);
    }
}