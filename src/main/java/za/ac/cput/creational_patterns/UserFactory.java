package za.ac.cput.creational_patterns;

import za.ac.cput.domain.Member;
import za.ac.cput.domain.Librarian;
import za.ac.cput.domain.UserAccount;

public class UserFactory {

    public static UserAccount createUser(String type, String userId, String fullName, String email, String passwordHash) {
        switch (type.toUpperCase()) {
            case "MEMBER":
                return new Member(userId, fullName, email, passwordHash);
            case "LIBRARIAN":
                return new Librarian(userId, fullName, email, passwordHash);
            default:
                throw new IllegalArgumentException("Unknown user type: " + type);
        }
    }
}