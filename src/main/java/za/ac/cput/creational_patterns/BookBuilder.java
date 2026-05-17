package za.ac.cput.creational_patterns;

import za.ac.cput.domain.Book;

public class BookBuilder {
    private String bookId;
    private String title;
    private String author;
    private String ISBN;
    private String genre = "Unknown";
    private int yearPublished = 0;
    private int totalCopies = 1;

    public BookBuilder(String bookId, String title, String author, String ISBN) {
        if (bookId == null || title == null || author == null || ISBN == null) {
            throw new IllegalArgumentException("bookId, title, author, and ISBN are required.");
        }
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
    }

    public BookBuilder genre(String genre) {
        this.genre = genre;
        return this;
    }

    public BookBuilder yearPublished(int yearPublished) {
        if (yearPublished < 0 || yearPublished > java.time.Year.now().getValue()) {
            throw new IllegalArgumentException("Invalid year: " + yearPublished);
        }
        this.yearPublished = yearPublished;
        return this;
    }

    public BookBuilder totalCopies(int totalCopies) {
        if (totalCopies < 1) {
            throw new IllegalArgumentException("Total copies must be at least 1.");
        }
        this.totalCopies = totalCopies;
        return this;
    }

    public Book build() {
        return new Book(bookId, title, author, ISBN, genre, yearPublished, totalCopies);
    }
}