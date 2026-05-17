package za.ac.cput.domain;

public class Book {
    private String bookId;
    private String title;
    private String author;
    private String ISBN;
    private String genre;
    private int yearPublished;
    private int totalCopies;
    private int availableCopies;

    public Book(String bookId, String title, String author, String ISBN, String genre, int yearPublished, int totalCopies) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.genre = genre;
        this.yearPublished = yearPublished;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }

    public boolean checkAvailability() {
        return availableCopies > 0;
    }

    public void addToCatalogue() {
        System.out.println("Book added to catalogue: " + title);
    }

    public void updateDetails(String title, String author) {
        this.title = title;
        this.author = author;
        System.out.println("Book details updated.");
    }

    public void removeFromCatalogue() {
        System.out.println("Book removed from catalogue: " + title);
    }

    public void decrementAvailableCopies() {
        if (availableCopies > 0) availableCopies--;
    }

    public void incrementAvailableCopies() {
        if (availableCopies < totalCopies) availableCopies++;
    }

    public String getBookId() {
        return bookId; }

    public String getTitle() {
        return title; }

    public void setTitle(String title) {
        this.title = title; }

    public String getAuthor() {
        return author; }

    public void setAuthor(String author) {
        this.author = author; }

    public String getISBN() {
        return ISBN; }

    public String getGenre() {
        return genre; }

    public int getYearPublished() {
        return yearPublished; }

    public int getTotalCopies() {
        return totalCopies; }

    public int getAvailableCopies() {
        return availableCopies; }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies; }
}