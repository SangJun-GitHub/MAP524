package com.example.todolist;

public class ToDoItem {
    private String description;
    private boolean isComplete;

    public ToDoItem(String description, boolean isComplete){
        this.description = description;
        this.isComplete = isComplete;
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
