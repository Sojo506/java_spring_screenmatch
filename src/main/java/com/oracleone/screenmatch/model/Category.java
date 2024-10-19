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
}
