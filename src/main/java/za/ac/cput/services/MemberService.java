package za.ac.cput.services;

import org.springframework.stereotype.Service;
import za.ac.cput.domain.Member;
import za.ac.cput.repositories.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member registerMember(Member member) {
        if (member.getUserId() == null || member.getEmail() == null || member.getFullName() == null) {
            throw new IllegalArgumentException("Member ID, email, and full name are required.");
        }
        if (memberRepository.findByEmail(member.getEmail()).isPresent()) {
            throw new IllegalStateException("A member with email " + member.getEmail() + " already exists.");
        }
        memberRepository.save(member);
        return member;
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> getMemberById(String memberId) {
        return memberRepository.findById(memberId);
    }

    public Optional<Member> getMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public Member updateMember(String memberId, String fullName, String email) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found: " + memberId));
        member.setFullName(fullName);
        member.setEmail(email);
        memberRepository.save(member);
        return member;
    }

    public void deleteMember(String memberId) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found: " + memberId));
        memberRepository.delete(memberId);
    }

    public Member suspendMember(String memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found: " + memberId));
        member.setAccountStatus("SUSPENDED");
        memberRepository.save(member);
        return member;
    }

    public Member activateMember(String memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found: " + memberId));
        member.setAccountStatus("ACTIVE");
        memberRepository.save(member);
        return member;
    }

    public List<Member> getMembersByStatus(String status) {
        return memberRepository.findByAccountStatus(status);
    }
}