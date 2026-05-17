package za.ac.cput.creational_patterns;

import za.ac.cput.domain.Member;
import za.ac.cput.domain.Notification;

public class MemberAccountFactory implements AccountFactory {
    @Override
    public Member createUser(String userId, String fullName, String email, String passwordHash) {
        return new Member(userId, fullName, email, passwordHash);
    }

    @Override
    public Notification createWelcomeNotification(String notificationId, Member member) {
        return new Notification(notificationId, "Welcome to the Smart Library System! You can now search and borrow books.", "WELCOME", member);
    }
}
