# Smart Library Management System

## Overview

The Smart Library Management System is a web application I'm developing to assist libraries manage their daily operations more efficiently. The concept started from noticing how frustrating it may be to visit a library only to discover that the book you need is already borrowed, with no convenient method to find out or reserve it ahead of time. This system will allow members to search for books and make reservations, while librarians handle loans, returns, and penalties using a simple web interface. The project will be constructed in phases during the semester, starting with the core foundation and gradually adding functionality until a fully functional system is delivered.

---

## Key Features

- **User Authentication** — Secure login and registration for Members and Librarians
- **Book Catalogue** — Search, browse, add, edit, and remove books
- **Borrow & Return** — Librarians can issue and process book loans
- **Reservations** — Members can reserve books that are currently borrowed
- **Fine Calculation** — Overdue fines are automatically calculated and displayed on screen
- **Admin Summary** — Simple dashboard showing total books, active loans, and members

---

## Language Choice and Key Design Decisions

The system is implemented in Java using Spring Boot for the backend. Java was chosen because it is the language I am most comfortable with and Spring Boot makes it straightforward to build REST APIs with built-in security and JPA support.

**Key design decisions in the code:**

- `UserAccount` is an abstract base class that both `Member` and `Librarian` extend, keeping authentication logic in one place
- `Fine` is composed within `Loan` because a fine cannot exist without the loan that generated it
- The `DatabaseConnection` Singleton ensures only one database connection exists at any time
- The `BookBuilder` provides a clean, validated way to construct Book objects with many optional fields

---

## Creational Pattern Rationales

| Pattern          | Class                 | Rationale                                                                                             |
| ---------------- | --------------------- | ----------------------------------------------------------------------------------------------------- |
| Simple Factory   | `UserFactory`         | Centralises user creation so all Member and Librarian objects are created in one place                |
| Factory Method   | `NotificationCreator` | Different notification types are created by specific subclasses without changing the base interface   |
| Abstract Factory | `AccountFactory`      | Ensures Member and Librarian accounts are always created with compatible welcome notifications        |
| Builder          | `BookBuilder`         | Books have many optional fields; the Builder allows step-by-step construction with validation         |
| Prototype        | `BookPrototypeCache`  | Common book templates can be cloned and customised instead of being configured from scratch each time |
| Singleton        | `DatabaseConnection`  | Only one database connection should exist throughout the application to prevent conflicts             |

---

## Project Structure

```
/src/main/java/domain               — Core class implementations
/src/main/java/creational_patterns  — All six creational design patterns
/src/test/java/tests                — Unit tests for all patterns
```

---

## Project Documentation

| Document                                                   | Description                                                                                                                     |
| ---------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------- |
| [SPECIFICATION.md](./SPECIFICATION.md)                     | Full system specification including domain, problem statement, scope, functional and non-functional requirements, and use cases |
| [ARCHITECTURE.md](./ARCHITECTURE.md)                       | C4 architectural diagrams covering Context, Container, Component, and Deployment views                                          |
| [STAKEHOLDERS.md](./STAKEHOLDERS.md)                       | Stakeholder analysis including roles, concerns, pain points, and success metrics                                                |
| [REQUIREMENTS.md](./REQUIREMENTS.md)                       | Full System Requirements Document with functional and non-functional requirements                                               |
| [USE-CASE-DIAGRAM.md](./USE-CASE-DIAGRAM.md)               | UML use case diagram with written explanation of actors and relationships                                                       |
| [USE-CASE-SPECIFICATIONS.md](./USE-CASE-SPECIFICATIONS.md) | Detailed specifications for 8 critical use cases                                                                                |
| [TEST-CASES.md](./TEST-CASES.md)                           | Functional and non-functional test cases traceable to requirements                                                              |
| [USER-STORIES.md](./USER-STORIES.md)                       | User stories derived from functional requirements and use cases                                                                 |
| [PRODUCT-BACKLOG.md](./PRODUCT-BACKLOG.md)                 | Prioritized product backlog using MoSCoW with effort estimates                                                                  |
| [SPRINT-PLAN.md](./SPRINT-PLAN.md)                         | Sprint 1 plan including sprint goal, selected stories, and task breakdown                                                       |
| [TEMPLATE-ANALYSIS.md](./TEMPLATE-ANALYSIS.md)             | Comparison of GitHub project templates and justification for chosen template                                                    |
| [KANBAN-EXPLANATION.md](./KANBAN-EXPLANATION.md)           | Definition and explanation of the Kanban board, WIP limits, and Agile alignment                                                 |
| [STATE-DIAGRAMS.md](./STATE-DIAGRAMS.md)                   | State transition diagrams for 8 key system objects                                                                              |
| [ACTIVITY-DIAGRAMS.md](./ACTIVITY-DIAGRAMS.md)             | Activity diagrams for 8 key system workflows                                                                                    |
| [DOMAIN-MODEL.md](./DOMAIN-MODEL.md)                       | Domain model describing key entities, attributes, methods, and business rules                                                   |
| [CLASS-DIAGRAM.md](./CLASS-DIAGRAM.md)                     | Full UML class diagram with relationships, multiplicity, and design decisions                                                   |
| [CHANGELOG.md](./CHANGELOG.md)                             | Change log tracking all updates                                                                                                 |
| [REFLECTION.md](./REFLECTION.md)                           | Latest assignment reflection                                                                                                    |

---

## Kanban Board Customisation

The project board uses the Automated Kanban template with two additional columns:

| Column  | Purpose                                                 |
| ------- | ------------------------------------------------------- |
| Testing | Features being validated against acceptance criteria    |
| Blocked | Work that cannot proceed until a dependency is resolved |

---

## Technology Stack

| Layer    | Technology            |
| -------- | --------------------- |
| Frontend | React.js              |
| Backend  | Java + Spring Boot    |
| Database | MySQL                 |
| Auth     | JWT (JSON Web Tokens) |
| Hosting  | Railway.app           |

---

## Author

Student: Thato Anikie Mabilo
Student number: 222148349
Lecturer: Dr. Boniface Kabaso
Assignment 10: Smart Library Management System
