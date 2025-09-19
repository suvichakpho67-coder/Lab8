package com.example.myapplication;

import java.util.ArrayList;
import java.util.Date;

public class            CheckListNote
        extends Note {
    private ArrayList<String> items;

    public CheckListNote(String title, Date createdDate) {
        super(title, createdDate);
        this.items = new ArrayList<>();
    }

    // Encapsulation
    public void addItem(String item) {
        items.add(item);
    }

    public ArrayList<String> getItems() {
        return items;
    }

    // Polymorphism
    @Override
    public String display() {
        StringBuilder sb = new StringBuilder();
        sb.append("ChecklistNote: ").append(getTitle()).append(" (").append(getCreatedDate()).append(")\n");
        for (int i = 0; i < items.size(); i++) {
            sb.append("  - ").append(items.get(i)).append("\n");
        }
        return sb.toString();
    }
}
