package com.example.expensesplitter.models;

public class Group {

    private int id;
    private String name;

    // Constructor
    public Group(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter for ID
    public int getId() {
        return id;
    }

    // Getter for Name
    public String getName() {
        return name;
    }

    // Optional: Setter (if needed later)
    public void setName(String name) {
        this.name = name;
    }
}