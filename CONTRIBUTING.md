# CONTRIBUTING.md — Smart Library Management System

First and foremost, a massive thank you for your interest in contributing to the Smart Library Management System! This document explains how to get set up, what the coding standards are, and how to pick up issues and submit pull requests.

---

## Prerequisites

Before you can run the project locally you will need the following installed:

- **Java 21** — [You can download it here](https://adoptium.net/)
- **Maven** — comes bundled with IntelliJ IDEA if that's your IDE or [download here](https://maven.apache.org/)
- **IntelliJ IDEA** (recommended) or any Java IDE
- **Git**

---

## Setup Instructions

1. Fork this repository to your account on GitHub.
2. Clone your fork to your local machine:

```bash
git clone https://github.com/YOUR_USERNAME/SmartLibraryManagementSystem.git
```

3. Open the project in IntelliJ IDEA
4. Let Maven load all the dependencies automatically
5. Run the tests to confirm everything is working:

```bash
mvn test
```

6. Start the application:

```bash
mvn spring-boot:run
```

7. Open your browser and go to `http://localhost:8080/swagger-ui/index.html` to see the API

---

## Coding Standards

- Follow standard Java naming conventions — classes use PascalCase, methods and variables use camelCase
- Every new method should have a brief comment explaining what it does
- All new features must include unit tests in the `/src/test/java/za/ac/cput/` directory
- Tests must pass before submitting a pull request — the CI pipeline will check this automatically
- Keep methods small and focused — if a method is doing more than one thing, maybe consider splitting it

---

## How to Pick Issues and Submit PRs

1. Go to the **Issues** tab and look for issues labelled `good-first-issue`
2. Comment on the issue to let others know that you are working on it
3. Create a new branch from `main` with a descriptive name:

```bash
git checkout -b feature/your-feature-name
```

4. Make your changes and write tests
5. Commit your changes with a clear message:

```bash
git commit -m "Add feature: brief description"
```

6. Push your branch and open a pull request against `main`
7. Fill in the PR description explaining what you changed and why
8. Then wait for the CI pipeline to pass, if all 81 tests pass you are good to go and I will review your PR

---

## Project Structure

```
src/main/java/za/ac/cput/
    domain/              — Core domain classes (Book, Member, Loan etc.)
    repositories/        — Repository interfaces
    repositories/inmemory/ — In-memory implementations
    services/            — Business logic layer
    api/                 — REST API controllers
    config/              — Spring Boot configuration
src/test/java/za/ac/cput/
    services/            — Service unit tests
    repositories/        — Repository tests
```

---

## Running Tests

```bash
# Run all tests
mvn test

# Run a specific test class
mvn test -Dtest=BookServiceTest

# Generate a coverage report
mvn test && open target/site/jacoco/index.html
```

---

Thank you once again for your contribution!
