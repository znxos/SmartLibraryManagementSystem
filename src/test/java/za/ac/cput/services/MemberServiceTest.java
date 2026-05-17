package za.ac.cput.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Member;
import za.ac.cput.repositories.inmemory.InMemoryMemberRepository;
import za.ac.cput.services.MemberService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class MemberServiceTest {

    private MemberService memberService;
    private Member testMember;

    @BeforeEach
    public void setUp() {
        memberService = new MemberService(new InMemoryMemberRepository());
        testMember = new Member("M001", "Linda Mtoba", "linda77@gmail.com", "hashedpass123");
    }

    @Test
    public void testRegisterMember_Success() {
        Member registered = memberService.registerMember(testMember);
        assertNotNull(registered);
        assertEquals("Linda Mtoba", registered.getFullName());
    }

    @Test
    public void testRegisterMember_DuplicateEmail_ThrowsException() {
        memberService.registerMember(testMember);
        Member duplicate = new Member("M002", "Other Person", "linda77@gmail.com", "pass");
        assertThrows(IllegalStateException.class, () -> memberService.registerMember(duplicate));
    }

    @Test
    public void testRegisterMember_NullEmail_ThrowsException() {
        Member invalid = new Member("M002", "Name", null, "pass");
        assertThrows(IllegalArgumentException.class, () -> memberService.registerMember(invalid));
    }

    @Test
    public void testGetAllMembers() {
        memberService.registerMember(testMember);
        List<Member> members = memberService.getAllMembers();
        assertEquals(1, members.size());
    }

    @Test
    public void testGetMemberById_Found() {
        memberService.registerMember(testMember);
        Optional<Member> found = memberService.getMemberById("M001");
        assertTrue(found.isPresent());
    }

    @Test
    public void testGetMemberById_NotFound() {
        Optional<Member> found = memberService.getMemberById("NONEXISTENT");
        assertFalse(found.isPresent());
    }

    @Test
    public void testSuspendMember_Success() {
        memberService.registerMember(testMember);
        Member suspended = memberService.suspendMember("M001");
        assertEquals("SUSPENDED", suspended.getAccountStatus());
    }

    @Test
    public void testActivateMember_Success() {
        memberService.registerMember(testMember);
        memberService.suspendMember("M001");
        Member activated = memberService.activateMember("M001");
        assertEquals("ACTIVE", activated.getAccountStatus());
    }

    @Test
    public void testDeleteMember_Success() {
        memberService.registerMember(testMember);
        memberService.deleteMember("M001");
        assertFalse(memberService.getMemberById("M001").isPresent());
    }

    @Test
    public void testDeleteMember_NotFound_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> memberService.deleteMember("NONEXISTENT"));
    }
}