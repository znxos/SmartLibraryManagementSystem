package za.ac.cput.domain;

import java.time.LocalDate;

public class Reservation {
    private String reservationId;
    private LocalDate reservationDate;
    private String status;
    private int queuePosition;
    private Member member;
    private Book book;

    public Reservation(String reservationId, Member member, Book book, int queuePosition) {
        this.reservationId = reservationId;
        this.member = member;
        this.book = book;
        this.reservationDate = LocalDate.now();
        this.status = "PENDING";
        this.queuePosition = queuePosition;
    }

    public void placeReservation() {
        if (!book.checkAvailability()) {
            member.addReservation(this);
            System.out.println("Reservation placed. Queue position: " + queuePosition);
        } else {
            System.out.println("Book is available. No reservation needed.");
        }
    }

    public void cancelReservation() {
        this.status = "CANCELLED";
        System.out.println("Reservation cancelled: " + reservationId);
    }

    public void markAsReady() {
        this.status = "READY";
        System.out.println("Reservation is ready for pickup: " + reservationId);
    }

    public void markAsFulfilled() {
        this.status = "FULFILLED";
        System.out.println("Reservation fulfilled: " + reservationId);
    }

    public String getReservationId() {
        return reservationId; }

    public String getStatus() {
        return status; }

    public int getQueuePosition() {
        return queuePosition; }

    public Member getMember() {
        return member; }

    public Book getBook() {
        return book; }
}