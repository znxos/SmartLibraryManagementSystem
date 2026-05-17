package za.ac.cput.factories;

import za.ac.cput.domain.Book;
import za.ac.cput.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

class DatabaseBookRepository implements BookRepository {
    @Override
    public void save(Book book) {
        throw new UnsupportedOperationException("Database implementation coming in a future sprint.");
    }

    @Override
    public Optional<Book> findById(String id) {
        throw new UnsupportedOperationException("Database implementation coming in a future sprint.");
    }

    @Override
    public List<Book> findAll() {
        throw new UnsupportedOperationException("Database implementation coming in a future sprint.");
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Database implementation coming in a future sprint.");
    }

    @Override
    public List<Book> findByAuthor(String author) {
        throw new UnsupportedOperationException("Database implementation coming in a future sprint.");
    }

    @Override
    public List<Book> findByGenre(String genre) {
        throw new UnsupportedOperationException("Database implementation coming in a future sprint.");
    }

    @Override
    public List<Book> findAvailable() {
        throw new UnsupportedOperationException("Database implementation coming in a future sprint.");
    }
}

