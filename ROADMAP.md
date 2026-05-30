# ROADMAP.md — Smart Library Management System

This document outlines the planned features and improvements for the Smart Library Management System. It's intended to give you a clear picture of where the project is headed and what areas need work.

---

## Current State

The system currently has:

- Domain model with core entities (Book, Member, Loan, Reservation, Fine)
- In-memory repository layer with full CRUD support
- Service layer with business logic enforcement
- REST API for Book, Member, and Loan management
- Swagger UI documentation
- CI/CD pipeline with automated testing and JAR artifact generation

---

## Planned Features

### Phase 1 — Database Integration

- Replace in-memory repositories with MySQL-backed implementations using Spring Data JPA
- Add database migration scripts using Flyway
- Connect to a hosted MySQL instance on Railway.app

### Phase 2 — Authentication and Security

- Implement JWT-based authentication
- Add role-based access control (Member vs Librarian vs Admin)
- Secure all API endpoints with Spring Security

### Phase 3 — Reservation and Fine API

- Expose REST endpoints for the Reservation entity
- Expose REST endpoints for the Fine entity
- Add endpoint to view a member's fine balance

### Phase 4 — Frontend

- Build a React.js frontend that consumes the REST API
- Member dashboard showing borrowing history and fines
- Librarian dashboard for managing loans and catalogue

### Phase 5 — Notifications

- Add email notifications using Spring Mail
- Send due date reminders 3 days before a loan expires
- Notify members when their reservation becomes ready

### Phase 6 — Reporting and Analytics

- Admin dashboard with borrowing statistics
- Most borrowed books report
- Overdue loan summary report

---

## Known Issues

- Loan and Member objects contain circular references which can cause JSON serialisation issues — tracked in issue #27
- Domain classes need to be refactored to work with Spring Data JPA annotations for database integration

---

## How to Contribute

Check the issues tab for tasks labelled `good-first-issue` or `feature-request`. See [CONTRIBUTING.md](./CONTRIBUTING.md) for setup instructions.
