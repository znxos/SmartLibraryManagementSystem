package za.ac.cput.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Book;
import za.ac.cput.repositories.inmemory.InMemoryBookRepository;
import za.ac.cput.services.BookService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class BookServiceTest {

    private BookService bookService;
    private Book testBook;

    @BeforeEach
    public void setUp() {
        bookService = new BookService(new InMemoryBookRepository());
        testBook = new Book("B001", "Clean Code", "Robert Martin", "9784", "Computer Science", 2008, 3);
    }

    @Test
    public void testAddBook_Success() {
        Book added = bookService.addBook(testBook);
        assertNotNull(added);
        assertEquals("Clean Code", added.getTitle());
    }

    @Test
    public void testAddBook_DuplicateId_ThrowsException() {
        bookService.addBook(testBook);
        assertThrows(IllegalStateException.class, () -> bookService.addBook(testBook));
    }

    @Test
    public void testAddBook_NullTitle_ThrowsException() {
        Book invalid = new Book("B002", null, "Author", "ISBN", "Genre", 2020, 1);
        assertThrows(IllegalArgumentException.class, () -> bookService.addBook(invalid));
    }

    @Test
    public void testGetAllBooks_ReturnsAllBooks() {
        bookService.addBook(testBook);
        Book book2 = new Book("B002", "Design Patterns", "GoF", "9760", "Computer Science", 1994, 2);
        bookService.addBook(book2);
        List<Book> books = bookService.getAllBooks();
        assertEquals(2, books.size());
    }

    @Test
    public void testGetBookById_Found() {
        bookService.addBook(testBook);
        Optional<Book> found = bookService.getBookById("B001");
        assertTrue(found.isPresent());
        assertEquals("Clean Code", found.get().getTitle());
    }

    @Test
    public void testGetBookById_NotFound() {
        Optional<Book> found = bookService.getBookById("NONEXISTENT");
        assertFalse(found.isPresent());
    }

    @Test
    public void testUpdateBook_Success() {
        bookService.addBook(testBook);
        Book updated = bookService.updateBook("B001", "Clean Code 2nd Edition", "Robert Martin");
        assertEquals("Clean Code 2nd Edition", updated.getTitle());
    }

    @Test
    public void testUpdateBook_NotFound_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                bookService.updateBook("NONEXISTENT", "Title", "Author"));
    }

    @Test
    public void testDeleteBook_Success() {
        bookService.addBook(testBook);
        bookService.deleteBook("B001");
        assertFalse(bookService.getBookById("B001").isPresent());
    }

    @Test
    public void testDeleteBook_NotFound_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> bookService.deleteBook("NONEXISTENT"));
    }

    @Test
    public void testDeleteBook_WithActiveLoans_ThrowsException() {
        bookService.addBook(testBook);
        testBook.decrementAvailableCopies();
        assertThrows(IllegalStateException.class, () -> bookService.deleteBook("B001"));
    }

    @Test
    public void testGetAvailableBooks() {
        bookService.addBook(testBook);
        Book unavailable = new Book("B003", "Unavailable", "Author", "ISBN003", "Fiction", 2020, 1);
        unavailable.decrementAvailableCopies();
        bookService.addBook(unavailable);
        List<Book> available = bookService.getAvailableBooks();
        assertEquals(1, available.size());
        assertEquals("Clean Code", available.get(0).getTitle());
    }
}