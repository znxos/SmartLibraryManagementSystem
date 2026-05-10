# CHANGELOG.md — Smart Library Management System

---

## Assignment 10 — Class Implementation and Creational Patterns

### Added

**Source Code (/src/main/java/domain)**

- `UserAccount.java` — Abstract base class for Member and Librarian with shared authentication logic
- `Member.java` — Extends UserAccount; handles registration, login, book search, reservations, and fine viewing
- `Librarian.java` — Extends UserAccount; handles loan issuing, returns, catalogue management, and fine recording
- `Book.java` — Represents a book in the catalogue with availability tracking
- `Loan.java` — Represents a borrowing transaction with automatic fine calculation at R2.00 per overdue day
- `Reservation.java` — Represents a book reservation with queue position tracking
- `Fine.java` — Represents an overdue fine associated with a loan (composition relationship)
- `Notification.java` — Represents a system notification delivered to a member

**Creational Patterns (/src/main/java/creational_patterns)**

- `UserFactory.java` — Simple Factory for centralised user account creation
- `NotificationCreator.java` — Factory Method for creating different notification types
- `AccountFactory.java` — Abstract Factory for creating families of account-related objects
- `BookBuilder.java` — Builder for constructing Book objects step by step with validation
- `BookPrototypeCache.java` — Prototype for cloning pre-configured book templates
- `DatabaseConnection.java` — Singleton ensuring one database connection instance globally

**Tests (/src/test/java/tests)**

- `CreationalPatternTests.java` — Unit tests for all six creational patterns covering correct object creation, attribute initialisation, and edge cases including invalid inputs and thread safety

---

## Previous Assignments

### Assignment 9 — Domain Modeling and Class Diagrams

- Added DOMAIN-MODEL.md
- Added CLASS-DIAGRAM.md

### Assignment 8 — State and Activity Diagrams

- Added STATE-DIAGRAMS.md
- Added ACTIVITY-DIAGRAMS.md

### Assignment 7 — Kanban Board

- Added TEMPLATE-ANALYSIS.md
- Added KANBAN-EXPLANATION.md
- Customised GitHub Project board with Testing and Blocked columns

### Assignment 6 — Agile Planning

- Added USER-STORIES.md
- Added PRODUCT-BACKLOG.md
- Added SPRINT-PLAN.md
- Created 14 GitHub Issues
- Created Sprint 1 Milestone

### Assignment 5 — Use Cases and Test Cases

- Added USE-CASE-DIAGRAM.md
- Added USE-CASE-SPECIFICATIONS.md
- Added TEST-CASES.md

### Assignment 4 — Requirements

- Added STAKEHOLDERS.md
- Added REQUIREMENTS.md

### Assignment 3 — Specification and Architecture

- Added SPECIFICATION.md
- Added ARCHITECTURE.md
- Added README.md
