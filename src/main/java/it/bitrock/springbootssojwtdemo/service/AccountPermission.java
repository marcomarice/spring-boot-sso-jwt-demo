package it.bitrock.springbootssojwtdemo.service;

public enum AccountPermission {
    CURRICULUM_READ("curriculum:read"),
    CURRICULUM_WRITE("curriculum:write");

    private final String permission;

    AccountPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
