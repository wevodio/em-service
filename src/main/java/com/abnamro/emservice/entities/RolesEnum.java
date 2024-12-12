package com.abnamro.emservice.entities;

public enum RolesEnum {
    ADMIN(1),
    USER(2),
    MANAGER(3);

    private final int id;

    RolesEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
