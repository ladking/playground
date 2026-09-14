package com.todoapp.service.todo;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.todoapp.models.Todo;;


@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>  {}