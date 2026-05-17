package za.ac.cput.domain;

import java.time.LocalDate;

public class Notification {
    private String notificationId;
    private String message;
    private String type;
    private String status;
    private LocalDate createdDate;
    private Member member;

    public Notification(String notificationId, String message, String type, Member member) {
        this.notificationId = notificationId;
        this.message = message;
        this.type = type;
        this.member = member;
        this.createdDate = LocalDate.now();
        this.status = "GENERATED";
    }

    public void generate() {
        this.status = "GENERATED";
        System.out.println("Notification generated: " + message);
    }

    public void deliver() {
        this.status = "DELIVERED";
        System.out.println("Notification delivered to: " + member.getFullName());
    }

    public void markAsRead() {
        this.status = "READ";
        System.out.println("Notification marked as read.");
    }

    public void dismiss() {
        this.status = "DISMISSED";
        System.out.println("Notification dismissed.");
    }

    public void archive() {
        this.status = "ARCHIVED";
        System.out.println("Notification archived.");
    }

    public String getNotificationId() {
        return notificationId; }

    public String getMessage() {
        return message; }

    public String getType() {
        return type; }

    public String getStatus() {
        return status; }

    public Member getMember() {
        return member; }
}