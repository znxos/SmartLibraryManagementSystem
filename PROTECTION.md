# PROTECTION.md — Smart Library Management System

---

## Branch Protection Rules

This document explains the branch protection rules I applied to the main branch of this repository and why each one is important.

---

## Rules Applied

### 1. Require Pull Request Reviews

All changes to the main branch must go through a pull request and receive at least one approval before merging.

This matters because it ensures that every change is reviewed first, before it becomes part of the main codebase. Even when working alone, it forces a deliberate review step rather than pushing changes without thinking.

### 2. Require Status Checks to Pass

The CI pipeline must run successfully before a pull request can be merged. This means all 81 unit and integration tests must pass before any code reaches main.

Without this rule, broken code could easily reach the main branch undetected. If a change breaks an existing test, the pipeline fails and the merge is blocked automatically, which means the main branch is basically always kept in a working state.

### 3. No Direct Pushes

Direct pushes to main are disabled. All changes must come through a pull request.

This is crucial because it enforces the pull request workflow consistently. Without this rule the review and testing requirements could be bypassed entirely, which defeats the whole purpose of having them.

---

## Why All These Rules Matter

Together, these three rules make sure that nothing untested or unreviewed can get into main. Without them it would be easy to accidentally push broken code or skip the review process when in a rush. Having them saves a lot of potential mistakes down the line.
