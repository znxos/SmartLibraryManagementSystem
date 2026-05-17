package za.ac.cput.repositories.inmemory;

import za.ac.cput.domain.Loan;
import za.ac.cput.repositories.LoanRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class InMemoryLoanRepository implements LoanRepository {

    private final Map<String, Loan> storage = new HashMap<>();

    @Override
    public void save(Loan loan) {
        storage.put(loan.getLoanId(), loan);
    }

    @Override
    public Optional<Loan> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Loan> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void delete(String id) {
        storage.remove(id);
    }

    @Override
    public List<Loan> findByMemberId(String memberId) {
        return storage.values().stream()
                .filter(loan -> loan.getMember().getUserId().equals(memberId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Loan> findByBookId(String bookId) {
        return storage.values().stream()
                .filter(loan -> loan.getBook().getBookId().equals(bookId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Loan> findOverdue() {
        return storage.values().stream()
                .filter(loan -> loan.getStatus().equals("ACTIVE") &&
                        loan.getDueDate().isBefore(LocalDate.now()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Loan> findByStatus(String status) {
        return storage.values().stream()
                .filter(loan -> loan.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }
}