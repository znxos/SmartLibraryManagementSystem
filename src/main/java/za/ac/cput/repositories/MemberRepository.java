package za.ac.cput.repositories;

import za.ac.cput.domain.Member;
import java.util.Optional;

public interface MemberRepository extends Repository<Member, String> {
    Optional<Member> findByEmail(String email);
    java.util.List<Member> findByAccountStatus(String status);
}