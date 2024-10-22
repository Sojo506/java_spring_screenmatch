package com.oracleone.screenmatch.model;

public enum Category {
    ACTION("Action"),
    COMEDY("Comedy"),
    CRIME("Crime"),
    ADVENTURE("Adventure"),
    ANIMATION("Animation"),
    ROMANCE("Romance"),
    DRAMA("Drama");

    private String categoryOMDB;

    Category(String categoryOMDB) {
        this.categoryOMDB = categoryOMDB;
    }

    public static Category fromString(String text) {
        for (Category category : Category.values()) {
            if (category.categoryOMDB.equalsIgnoreCase(text)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Category wasn't found: " + text);
    }
}
