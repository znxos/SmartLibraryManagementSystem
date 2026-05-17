package za.ac.cput.creational_patterns;

import za.ac.cput.domain.Member;
import za.ac.cput.domain.Notification;

public class FineReminderNotificationCreator extends NotificationCreator {
    @Override
    public Notification createNotification(String id, Member member) {
        return new Notification(id, "You have an outstanding fine on your account. Please settle it at the library.", "FINE_REMINDER", member);
    }
}
