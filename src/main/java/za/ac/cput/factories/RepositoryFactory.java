package za.ac.cput.factories;

import za.ac.cput.repositories.BookRepository;
import za.ac.cput.repositories.inmemory.InMemoryBookRepository;

public class RepositoryFactory {

    public static BookRepository getBookRepository(String storageType) {
        switch (storageType.toUpperCase()) {
            case "MEMORY":
                return new InMemoryBookRepository();
            case "DATABASE":
                return new DatabaseBookRepository();
            default:
                throw new IllegalArgumentException("Unknown storage type: " + storageType);
        }
    }

}