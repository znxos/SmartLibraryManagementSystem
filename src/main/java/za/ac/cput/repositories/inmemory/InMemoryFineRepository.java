package za.ac.cput.repositories.inmemory;

import za.ac.cput.domain.Fine;
import za.ac.cput.repositories.FineRepository;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryFineRepository implements FineRepository {

    private final Map<String, Fine> storage = new HashMap<>();

    @Override
    public void save(Fine fine) {
        storage.put(fine.getFineId(), fine);
    }

    @Override
    public Optional<Fine> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Fine> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void delete(String id) {
        storage.remove(id);
    }

    @Override
    public List<Fine> findByStatus(String status) {
        return storage.values().stream()
                .filter(fine -> fine.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

    @Override
    public List<Fine> findByLoanId(String loanId) {
        return storage.values().stream()
                .filter(fine -> fine.getLoan().getLoanId().equals(loanId))
                .collect(Collectors.toList());
    }
}