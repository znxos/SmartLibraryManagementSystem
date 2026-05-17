package za.ac.cput.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Book;
import za.ac.cput.services.BookService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Books", description = "Endpoints for managing the library book catalogue")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @Operation(summary = "Get all books", description = "Returns a list of all books in the catalogue")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book by ID", description = "Returns a single book by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book found"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    public ResponseEntity<?> getBookById(@PathVariable String id) {
        return bookService.getBookById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Book not found: " + id)));
    }

    @PostMapping
    @Operation(summary = "Add a new book", description = "Adds a new book to the catalogue")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Book created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input or duplicate book ID")
    })
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        try {
            Book created = bookService.addBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a book", description = "Updates the title and author of an existing book")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book updated successfully"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    public ResponseEntity<?> updateBook(@PathVariable String id,
                                        @RequestParam String title,
                                        @RequestParam String author) {
        try {
            Book updated = bookService.updateBook(id, title, author);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book", description = "Deletes a book from the catalogue if it has no active loans")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Book has active loans"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    public ResponseEntity<?> deleteBook(@PathVariable String id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.ok(Map.of("message", "Book deleted successfully: " + id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/available")
    @Operation(summary = "Get available books", description = "Returns all books with at least one available copy")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved available books")
    public ResponseEntity<List<Book>> getAvailableBooks() {
        return ResponseEntity.ok(bookService.getAvailableBooks());
    }

    @GetMapping("/author/{author}")
    @Operation(summary = "Get books by author", description = "Returns all books by a specific author")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved books by author")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable String author) {
        return ResponseEntity.ok(bookService.getBooksByAuthor(author));
    }

    @GetMapping("/genre/{genre}")
    @Operation(summary = "Get books by genre", description = "Returns all books in a specific genre")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved books by genre")
    public ResponseEntity<List<Book>> getBooksByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(bookService.getBooksByGenre(genre));
    }
}