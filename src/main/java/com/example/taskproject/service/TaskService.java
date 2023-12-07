package com.example.taskproject.service;

import com.example.taskproject.payload.TaskDTO;

import java.util.List;

public interface TaskService {
    TaskDTO saveTask(long userId, TaskDTO taskDTO);

    List<TaskDTO> getAllTasks(long userId);

    TaskDTO getTask(long userId, long taskId);

    void deleteTask(long userId, long taskId);
}
