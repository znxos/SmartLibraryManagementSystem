package za.ac.cput.creational_patterns;

import za.ac.cput.domain.Book;
import java.util.HashMap;
import java.util.Map;

public class BookPrototypeCache {
    private static Map<String, Book> cache = new HashMap<>();

    public static void loadCache() {
        Book academicTemplate = new Book(
                "TEMPLATE-001",
                "Academic Textbook Template",
                "Unknown Author",
                "000",
                "Academic",
                2024,
                3
        );
        cache.put("ACADEMIC", academicTemplate);

        Book fictionTemplate = new Book(
                "TEMPLATE-002",
                "Fiction Novel Template",
                "Unknown Author",
                "001",
                "Fiction",
                2024,
                5
        );
        cache.put("FICTION", fictionTemplate);

        Book referenceTemplate = new Book(
                "TEMPLATE-003",
                "Reference Book Template",
                "Unknown Author",
                "002",
                "Reference",
                2024,
                2
        );
        cache.put("REFERENCE", referenceTemplate);
    }

    public static Book getClone(String type, String newBookId, String newTitle, String newAuthor, String newISBN) {
        Book template = cache.get(type.toUpperCase());
        if (template == null) {
            throw new IllegalArgumentException("Oops, no template found for type: " + type);
        }

        return new Book(
                newBookId,
                newTitle,
                newAuthor,
                newISBN,
                template.getGenre(),
                template.getYearPublished(),
                template.getTotalCopies()
        );
    }
}