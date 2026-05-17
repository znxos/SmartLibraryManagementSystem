package za.ac.cput.creational_patterns;

import za.ac.cput.domain.Librarian;
import za.ac.cput.domain.Notification;
import za.ac.cput.domain.Member;

public class LibrarianAccountFactory implements AccountFactory {
    @Override
    public Librarian createUser(String userId, String fullName, String email, String passwordHash) {
        return new Librarian(userId, fullName, email, passwordHash);
    }

    @Override
    public Notification createWelcomeNotification(String notificationId, Member member) {
        return new Notification(notificationId, "Your librarian account is ready. You can now manage loans and the catalogue.", "WELCOME", member);
    }
}
