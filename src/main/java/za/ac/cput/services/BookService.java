package za.ac.cput.services;

import org.springframework.stereotype.Service;
import za.ac.cput.domain.Book;
import za.ac.cput.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        if (book.getBookId() == null || book.getTitle() == null || book.getISBN() == null) {
            throw new IllegalArgumentException("Book ID, title, and ISBN are required.");
        }
        if (bookRepository.findById(book.getBookId()).isPresent()) {
            throw new IllegalStateException("A book with ID " + book.getBookId() + " already exists.");
        }
        bookRepository.save(book);
        return book;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(String bookId) {
        return bookRepository.findById(bookId);
    }

    public Book updateBook(String bookId, String title, String author) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found: " + bookId));
        book.updateDetails(title, author);
        bookRepository.save(book);
        return book;
    }

    public void deleteBook(String bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found: " + bookId));
        if (book.getAvailableCopies() < book.getTotalCopies()) {
            throw new IllegalStateException("Cannot delete a book that has active loans.");
        }
        bookRepository.delete(bookId);
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.findAvailable();
    }

    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> getBooksByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }
}