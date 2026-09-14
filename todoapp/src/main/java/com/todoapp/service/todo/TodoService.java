package com.todoapp.service.todo;

import com.todoapp.dto.TodoDTO;
import com.todoapp.models.Todo;

import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
        private TodoRepository todoRepo;


        @Autowired
        public TodoService(TodoRepository todoRepo) {
            this.todoRepo = todoRepo;
        }


        public void createTodo(TodoDTO.createTodo payload) throws Exception{
            if(payload.title().isEmpty()){
                throw new Exception("title is required");
            }

            Todo todo = new Todo();
            todo.setTitle(payload.title());
            todo.setDescription(payload.description());
            todo.setDeadline(payload.deadline());
            todo.setUserID(payload.userId());


            try{
                this.todoRepo.save(todo);
            }catch(Exception e){
                throw new Exception("server error");
            }
        }
        public List<TodoDTO.todo> getTodos(TodoDTO.searchParams searchParams) throws Exception{
        //    Integer limit = (searchParams.limit() == null || searchParams.limit() == 0) ? 10 : searchParams.limit();
        //    Integer page = (searchParams.page() == null || searchParams.page() == 0) ? 1 : searchParams.page();
        //    Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
            try{
                List<Todo> todo = this.todoRepo.findAll();
                List<TodoDTO.todo> result = new ArrayList<>();
                for(Todo td : todo){
                    result.add(new TodoDTO.todo(
                        td.id,
                        td.title,
                        td.description,
                        td.userId,
                        td.deadline,
                        td.isCompleted,
                        td.completedAt));
                }
                return result;
            }catch(Exception e){
                throw new Exception("server error");
            }
        }

        public TodoDTO.todo getTodo(String id) throws Exception{
            try{
                Long parsedId = Long.parseLong(id);
                Optional<Todo> td = this.todoRepo.findById(parsedId);
                if(td.isPresent()){
                    Todo val = td.get();
                    return new TodoDTO.todo(
                        val.id,
                        val.title,
                        val.description,
                        val.userId,
                        val.deadline,
                        val.isCompleted,
                        val.completedAt
                    );
                }else{
                    throw new Exception("record not found");
                }
            }catch(Exception e){
                throw new Exception("server error");
            }
        }

        
        public void updateTodo(String id,TodoDTO.update update) throws Exception{
            try{
                Long parsedId = Long.parseLong(id);
               Todo todo = this.todoRepo.findById(parsedId).orElseThrow(()-> new Exception("todo not found"));
                if(update.title() != null && !update.title().isEmpty()){
                    todo.setTitle(update.title());
                }

                if(update.description() != null && !update.description().isEmpty()){
                    todo.setDescription(update.description());
                }

                if (update.deadline() != null){
                    todo.setDeadline(update.deadline());
                }

                this.todoRepo.save(todo);
            }catch(Exception e){
                System.out.println(e);
                throw new Exception("server error");
            }
        }

        public void markAsComplete(String id) throws Exception{
            try{
                Long parsedId = Long.parseLong(id);
                Todo td = this.todoRepo.findById(parsedId).orElseThrow(() -> new Exception("todo not found"));

                td.setCompletedAt(LocalDateTime.now());
                td.setIsComplete(true);

                this.todoRepo.save(td);
            }catch(Exception e){
                System.out.println(e);
                throw new Exception("server error");
            }
        }

        public void deleteTodo(String id) throws Exception{
            try{
                Long parsedId = Long.parseLong(id);
                this.todoRepo.deleteById(parsedId);
            }catch(Exception e){
                System.out.println(e);
                throw new Exception("server error");
            }
        }


}