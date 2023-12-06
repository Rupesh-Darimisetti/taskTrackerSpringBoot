package com.example.taskproject.controller;

import com.example.taskproject.payload.TaskDTO;
import com.example.taskproject.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskService taskService;

    //save the task
    @PostMapping("/{userId}/tasks")
    public ResponseEntity<TaskDTO> saveTask(
            @PathVariable(name="userId") long userId,
            @RequestBody TaskDTO taskDTO){
        return new ResponseEntity<>(
                taskService.saveTask(userId,taskDTO),
                HttpStatus.CREATED);
    }
    //get the task
    @PreAuthorize(value = "ROLE_USER")
    @GetMapping("/{userId}/tasks")
    public ResponseEntity<List<TaskDTO>> getAllTask(
            @PathVariable(name="userId") long userId){
        return new ResponseEntity<>(
                taskService.getAllTasks(userId),
                HttpStatus.OK);
    }
    // get individual task
    @GetMapping("/{userId}/tasks/{taskId}")
    public ResponseEntity<TaskDTO> getTask(
            @PathVariable(name = "userId")long userId,
            @PathVariable(name = "taskId")long taskId)
        {
    return new ResponseEntity<>(
            taskService.getTask(userId,taskId),
            HttpStatus.OK);
    }
    // delete individual task
    @PreAuthorize(value = "ROLE_ADMIN")
    @DeleteMapping("/{userId}/tasks/{taskId}")
    public ResponseEntity<String> deleteTask(
            @PathVariable(name = "userId")long userId,
            @PathVariable(name = "taskId")long taskId)
    {
        taskService.deleteTask(userId,taskId);
        return new ResponseEntity<>(
                "User Task deleted successfully",
                HttpStatus.OK);
    }
}
