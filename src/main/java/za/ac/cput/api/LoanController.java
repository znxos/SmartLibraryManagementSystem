package za.ac.cput.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Loan;
import za.ac.cput.services.LoanService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/loans")
@Tag(name = "Loans", description = "Endpoints for managing book loans and returns")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    @Operation(summary = "Get all loans", description = "Returns a list of all loan records")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all loans")
    public ResponseEntity<List<Loan>> getAllLoans() {
        return ResponseEntity.ok(loanService.getAllLoans());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get loan by ID", description = "Returns a single loan record by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Loan found"),
            @ApiResponse(responseCode = "404", description = "Loan not found")
    })
    public ResponseEntity<?> getLoanById(@PathVariable String id) {
        return loanService.getLoanById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Loan not found: " + id)));
    }

    @PostMapping
    @Operation(summary = "Issue a book loan", description = "Issues a book loan to a member. Member must be active and book must be available.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Loan issued successfully"),
            @ApiResponse(responseCode = "400", description = "Member suspended, book unavailable, or loan limit reached"),
            @ApiResponse(responseCode = "404", description = "Member or book not found")
    })
    public ResponseEntity<?> issueLoan(@RequestParam String memberId,
                                       @RequestParam String bookId) {
        try {
            Loan loan = loanService.issueLoan(memberId, bookId);
            return ResponseEntity.status(HttpStatus.CREATED).body(loan);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}/return")
    @Operation(summary = "Process a book return", description = "Processes the return of a borrowed book. Calculates fines if overdue.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Return processed successfully"),
            @ApiResponse(responseCode = "404", description = "Loan not found")
    })
    public ResponseEntity<?> processReturn(@PathVariable String id) {
        try {
            Loan loan = loanService.processReturn(id);
            return ResponseEntity.ok(loan);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/member/{memberId}")
    @Operation(summary = "Get loans by member", description = "Returns all loans for a specific member")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved member loans")
    public ResponseEntity<List<Loan>> getLoansByMember(@PathVariable String memberId) {
        return ResponseEntity.ok(loanService.getLoansByMember(memberId));
    }

    @GetMapping("/overdue")
    @Operation(summary = "Get overdue loans", description = "Returns all loans that are past their due date")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved overdue loans")
    public ResponseEntity<List<Loan>> getOverdueLoans() {
        return ResponseEntity.ok(loanService.getOverdueLoans());
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Get loans by status", description = "Returns all loans with a specific status")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved loans by status")
    public ResponseEntity<List<Loan>> getLoansByStatus(@PathVariable String status) {
        return ResponseEntity.ok(loanService.getLoansByStatus(status));
    }
}