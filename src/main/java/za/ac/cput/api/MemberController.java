package za.ac.cput.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Member;
import za.ac.cput.services.MemberService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/members")
@Tag(name = "Members", description = "Endpoints for managing library members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    @Operation(summary = "Get all members", description = "Returns a list of all registered members")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all members")
    public ResponseEntity<List<Member>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get member by ID", description = "Returns a single member by their ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Member found"),
            @ApiResponse(responseCode = "404", description = "Member not found")
    })
    public ResponseEntity<?> getMemberById(@PathVariable String id) {
        return memberService.getMemberById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Member not found: " + id)));
    }

    @PostMapping
    @Operation(summary = "Register a new member", description = "Registers a new library member")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Member registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input or duplicate email")
    })
    public ResponseEntity<?> registerMember(@RequestBody Member member) {
        try {
            Member created = memberService.registerMember(member);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a member", description = "Updates the full name and email of an existing member")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Member updated successfully"),
            @ApiResponse(responseCode = "404", description = "Member not found")
    })
    public ResponseEntity<?> updateMember(@PathVariable String id,
                                          @RequestParam String fullName,
                                          @RequestParam String email) {
        try {
            Member updated = memberService.updateMember(id, fullName, email);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a member", description = "Deletes a member account")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Member deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Member not found")
    })
    public ResponseEntity<?> deleteMember(@PathVariable String id) {
        try {
            memberService.deleteMember(id);
            return ResponseEntity.ok(Map.of("message", "Member deleted successfully: " + id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}/suspend")
    @Operation(summary = "Suspend a member", description = "Suspends a member account preventing further borrowing")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Member suspended successfully"),
            @ApiResponse(responseCode = "404", description = "Member not found")
    })
    public ResponseEntity<?> suspendMember(@PathVariable String id) {
        try {
            Member member = memberService.suspendMember(id);
            return ResponseEntity.ok(member);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}/activate")
    @Operation(summary = "Activate a member", description = "Reactivates a suspended member account")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Member activated successfully"),
            @ApiResponse(responseCode = "404", description = "Member not found")
    })
    public ResponseEntity<?> activateMember(@PathVariable String id) {
        try {
            Member member = memberService.activateMember(id);
            return ResponseEntity.ok(member);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }
}