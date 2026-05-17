package za.ac.cput.creational_patterns;

import za.ac.cput.domain.Member;
import za.ac.cput.domain.Notification;

public class ReservationReadyNotificationCreator extends NotificationCreator {
    @Override
    public Notification createNotification(String id, Member member) {
        return new Notification(id, "Your reserved book is now available for collection.", "RESERVATION_READY", member);
    }
}
