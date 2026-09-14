package com.todoapp.models;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

@Entity
@Table(name="todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String title;
    public String description;
    public String userId;
    public LocalDateTime createdAt;
    public LocalDateTime completedAt;
    public boolean isCompleted;
    public LocalDateTime deadline;


    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.isCompleted = false;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public void setUserID(String userId) {
        this.userId = userId;
    }

    public void setCompletedAt(LocalDateTime time){
        this.completedAt = time;
    }

    public void setIsComplete(boolean isComplete) {
        this.isCompleted = isComplete;
    }
}