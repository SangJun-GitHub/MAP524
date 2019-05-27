package com.example.todolistdb_time;

public class ToDoItem {
    private String description;
    private boolean isComplete;
    private long id;
    private String timestamp;

    public ToDoItem(String description, boolean isComplete, String timestamp){
        this(description, isComplete, timestamp, -1);
    }

    public ToDoItem(String description, boolean isComplete, String timestamp, long id){
        this.description = description;
        this.isComplete = isComplete;
        this.id = id;
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public long getId() {
        return id;
    }

    public String getDescription(){
        return description;
    }

    public boolean isComplete(){
        return isComplete;
    }

    public void toggleComplete(){
        isComplete = !isComplete;
    }

    @Override
    public String toString(){
        return getDescription();
    }

}
