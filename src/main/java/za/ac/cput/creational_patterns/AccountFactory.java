package za.ac.cput.creational_patterns;

import za.ac.cput.domain.Notification;
import za.ac.cput.domain.Member;
import za.ac.cput.domain.UserAccount;

public interface AccountFactory {
    UserAccount createUser(String userId, String fullName, String email, String passwordHash);
    Notification createWelcomeNotification(String notificationId, Member member);
}

