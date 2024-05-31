package br.com.orangex.api.model;

public enum UserRole {
    ADMIN("ADMIN"),
    USER("USER");

    private String userRole;

    private UserRole(String userRole){
        this.userRole = userRole;
    }

    public String getUserRole() {
        return userRole;
    }
}
