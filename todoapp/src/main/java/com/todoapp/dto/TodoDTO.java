package com.todoapp.dto;

import java.time.LocalDateTime;

public class TodoDTO {
    public static record createTodo(
        String title,
        String description,
        String userId,
        LocalDateTime deadline
    ){}


    public static record searchParams(
        Integer page,
        Integer limit,
        String title
    ){}


    public static  record todo(
        Long id,
        String title,
        String description,
        String userId, 
        LocalDateTime deadline,
        boolean isCompleted,
        LocalDateTime completedAt
    ){}


    public static record update(
        String title,
        String description,
        LocalDateTime deadline 
    ){}
}