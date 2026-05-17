package za.ac.cput.repositories.inmemory;

import za.ac.cput.domain.Reservation;
import za.ac.cput.repositories.ReservationRepository;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryReservationRepository implements ReservationRepository {

    private final Map<String, Reservation> storage = new HashMap<>();

    @Override
    public void save(Reservation reservation) {
        storage.put(reservation.getReservationId(), reservation);
    }

    @Override
    public Optional<Reservation> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Reservation> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void delete(String id) {
        storage.remove(id);
    }

    @Override
    public List<Reservation> findByMemberId(String memberId) {
        return storage.values().stream()
                .filter(r -> r.getMember().getUserId().equals(memberId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findByBookId(String bookId) {
        return storage.values().stream()
                .filter(r -> r.getBook().getBookId().equals(bookId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findByStatus(String status) {
        return storage.values().stream()
                .filter(r -> r.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }
}