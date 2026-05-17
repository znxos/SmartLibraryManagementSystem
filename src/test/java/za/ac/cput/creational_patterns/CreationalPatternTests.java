package za.ac.cput.creational_patterns;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;

import static org.junit.jupiter.api.Assertions.*;

public class CreationalPatternTests {

    @Test
    public void testSimpleFactory_CreateMember() {
        UserAccount user = UserFactory.createUser("MEMBER", "M001", "Linda Mtoba", "linda77@gmail.com", "hashedpass123");
        assertNotNull(user);
        assertEquals("MEMBER", user.getRole());
        assertTrue(user instanceof Member);
    }

    @Test
    public void testSimpleFactory_CreateLibrarian() {
        UserAccount user = UserFactory.createUser("LIBRARIAN", "L001", "Jane Smith", "jane@library.com", "hashedpass456");
        assertNotNull(user);
        assertEquals("LIBRARIAN", user.getRole());
        assertTrue(user instanceof Librarian);
    }

    @Test
    public void testSimpleFactory_InvalidType_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            UserFactory.createUser("ADMIN", "A001", "Admin User", "admin@library.com", "hashedpass789");
        });
    }

    @Test
    public void testFactoryMethod_OverdueNotification() {
        Member member = new Member("M001", "Linda Mtoba", "linda77@gmail.com", "hashedpass123");
        NotificationCreator creator = new OverdueNotificationCreator();
        Notification notification = creator.createNotification("N001", member);
        assertNotNull(notification);
        assertEquals("OVERDUE", notification.getType());
        assertEquals(member, notification.getMember());
    }

    @Test
    public void testFactoryMethod_ReservationReadyNotification() {
        Member member = new Member("M001", "Linda Mtoba", "linda77@gmail.com", "hashedpass123");
        NotificationCreator creator = new ReservationReadyNotificationCreator();
        Notification notification = creator.createNotification("N002", member);
        assertNotNull(notification);
        assertEquals("RESERVATION_READY", notification.getType());
    }

    @Test
    public void testFactoryMethod_FineReminderNotification() {
        Member member = new Member("M001", "Linda Mtoba", "linda77@gmail.com", "hashedpass123");
        NotificationCreator creator = new FineReminderNotificationCreator();
        Notification notification = creator.createNotification("N003", member);
        assertNotNull(notification);
        assertEquals("FINE_REMINDER", notification.getType());
    }

    @Test
    public void testAbstractFactory_MemberFactory_CreatesCorrectType() {
        AccountFactory factory = new MemberAccountFactory();
        UserAccount user = factory.createUser("M002", "Alice Dlamini", "alice63@gmail.com", "hashed");
        assertNotNull(user);
        assertTrue(user instanceof Member);
        assertEquals("MEMBER", user.getRole());
    }

    @Test
    public void testAbstractFactory_LibrarianFactory_CreatesCorrectType() {
        AccountFactory factory = new LibrarianAccountFactory();
        UserAccount user = factory.createUser("L002", "Bob Nkosi", "bob@library.com", "hashed");
        assertNotNull(user);
        assertTrue(user instanceof Librarian);
        assertEquals("LIBRARIAN", user.getRole());
    }

    @Test
    public void testAbstractFactory_WelcomeNotification_IsGenerated() {
        AccountFactory factory = new MemberAccountFactory();
        Member member = (Member) factory.createUser("M003", "Carol Zulu", "carol47@icloud.com", "hashed");
        Notification notification = factory.createWelcomeNotification("N004", member);
        assertNotNull(notification);
        assertEquals("WELCOME", notification.getType());
    }

    @Test
    public void testBuilder_CreatesBookWithRequiredFields() {
        Book book = new BookBuilder("B001", "Clean Code", "Robert Martin", "9784")
                .build();
        assertNotNull(book);
        assertEquals("Clean Code", book.getTitle());
        assertEquals("Robert Martin", book.getAuthor());
        assertEquals(1, book.getTotalCopies());
    }

    @Test
    public void testBuilder_CreatesBookWithAllFields() {
        Book book = new BookBuilder("B002", "Design Patterns", "GoF", "9760")
                .genre("Computer Science")
                .yearPublished(1994)
                .totalCopies(3)
                .build();
        assertNotNull(book);
        assertEquals("Computer Science", book.getGenre());
        assertEquals(1994, book.getYearPublished());
        assertEquals(3, book.getTotalCopies());
    }

    @Test
    public void testBuilder_InvalidYear_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BookBuilder("B003", "Future Book", "Unknown", "000")
                    .yearPublished(3000)
                    .build();
        });
    }

    @Test
    public void testBuilder_InvalidCopies_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BookBuilder("B004", "Zero Copy Book", "Unknown", "001")
                    .totalCopies(0)
                    .build();
        });
    }

    @Test
    public void testBuilder_NullRequiredField_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BookBuilder(null, "Test Book", "Author", "ISBN123").build();
        });
    }

    @Test
    public void testPrototype_ClonesAcademicTemplate() {
        BookPrototypeCache.loadCache();
        Book cloned = BookPrototypeCache.getClone("ACADEMIC", "B005", "Software Engineering", "Martin Fowler", "9753");
        assertNotNull(cloned);
        assertEquals("Academic", cloned.getGenre());
        assertEquals(3, cloned.getTotalCopies());
        assertEquals("Software Engineering", cloned.getTitle());
    }

    @Test
    public void testPrototype_ClonesFictionTemplate() {
        BookPrototypeCache.loadCache();
        Book cloned = BookPrototypeCache.getClone("FICTION", "B006", "The Great Gatsby", "F. Scott Fitzgerald", "9765");
        assertNotNull(cloned);
        assertEquals("Fiction", cloned.getGenre());
        assertEquals(5, cloned.getTotalCopies());
    }

    @Test
    public void testPrototype_ClonesAreIndependent() {
        BookPrototypeCache.loadCache();
        Book clone1 = BookPrototypeCache.getClone("ACADEMIC", "B007", "Book One", "Author One", "ISBN001");
        Book clone2 = BookPrototypeCache.getClone("ACADEMIC", "B008", "Book Two", "Author Two", "ISBN002");
        assertNotEquals(clone1.getTitle(), clone2.getTitle());
        assertNotEquals(clone1.getBookId(), clone2.getBookId());
    }

    @Test
    public void testPrototype_InvalidType_ThrowsException() {
        BookPrototypeCache.loadCache();
        assertThrows(IllegalArgumentException.class, () -> {
            BookPrototypeCache.getClone("UNKNOWN", "B009", "Unknown Book", "Unknown", "ISBN003");
        });
    }

    @Test
    public void testSingleton_ReturnsSameInstance() {
        DatabaseConnection instance1 = DatabaseConnection.getInstance();
        DatabaseConnection instance2 = DatabaseConnection.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    public void testSingleton_ConnectsSuccessfully() {
        DatabaseConnection db = DatabaseConnection.getInstance();
        db.connect();
        assertTrue(db.isConnected());
        db.disconnect();
    }

    @Test
    public void testSingleton_DisconnectsSuccessfully() {
        DatabaseConnection db = DatabaseConnection.getInstance();
        db.connect();
        db.disconnect();
        assertFalse(db.isConnected());
    }

    @Test
    public void testSingleton_ThreadSafety() throws InterruptedException {
        DatabaseConnection[] instances = new DatabaseConnection[2];
        Thread t1 = new Thread(() -> instances[0] = DatabaseConnection.getInstance());
        Thread t2 = new Thread(() -> instances[1] = DatabaseConnection.getInstance());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        assertSame(instances[0], instances[1]);
    }
}