package za.ac.cput.domain;

public abstract class UserAccount {
    private String userId;
    private String email;
    private String passwordHash;
    private String role;
    private String createdDate;

    public UserAccount(String userId, String email, String passwordHash, String role) {
        this.userId = userId;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public boolean authenticate(String passwordHash) {
        return this.passwordHash.equals(passwordHash);
    }

    public String generateToken() {
        return "JWT-" + userId + "-" + role;
    }

    public void deactivate() {
        this.role = "DEACTIVATED";
    }

    public void reactivate(String role) {
        this.role = role;
    }

    public String getUserId() {
        return userId; }

    public String getEmail() {
        return email; }

    public void setEmail(String email) {
        this.email = email; }

    public String getPasswordHash() {
        return passwordHash; }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash; }

    public String getRole() {
        return role; }

    public void setRole(String role) {
        this.role = role; }
}