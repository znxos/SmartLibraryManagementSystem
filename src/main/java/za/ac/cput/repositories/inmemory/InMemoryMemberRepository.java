package za.ac.cput.repositories.inmemory;

import za.ac.cput.domain.Member;
import za.ac.cput.repositories.MemberRepository;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryMemberRepository implements MemberRepository {

    private final Map<String, Member> storage = new HashMap<>();

    @Override
    public void save(Member member) {
        storage.put(member.getUserId(), member);
    }

    @Override
    public Optional<Member> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void delete(String id) {
        storage.remove(id);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return storage.values().stream()
                .filter(member -> member.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    @Override
    public List<Member> findByAccountStatus(String status) {
        return storage.values().stream()
                .filter(member -> member.getAccountStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }
}