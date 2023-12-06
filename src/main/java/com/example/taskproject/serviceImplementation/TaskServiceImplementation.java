package com.example.taskproject.serviceImplementation;

import com.example.taskproject.entity.Task;
import com.example.taskproject.entity.Users;
import com.example.taskproject.exception.APIException;
import com.example.taskproject.exception.TaskNotFound;
import com.example.taskproject.exception.UserNotFound;
import com.example.taskproject.payload.TaskDTO;
import com.example.taskproject.repository.TaskRepository;
import com.example.taskproject.repository.UserRepository;
import com.example.taskproject.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImplementation implements TaskService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Override
    public TaskDTO saveTask(long userId,
                            TaskDTO taskDTO) {
        Users user = userRepository.findById(userId).orElseThrow(
                ()->new UserNotFound(String.format("User Id %d not found",userId))
        );
        Task task = modelMapper.map(taskDTO,Task.class);
        task.setUsers(user);
        // After setting the user we are storing the data in DB
        Task savedTask = taskRepository.save(task);
        return modelMapper.map(savedTask,TaskDTO.class);
    }

    @Override
    public List<TaskDTO> getAllTasks(long userId) {
        userRepository.findById(userId).orElseThrow(
                ()->new UserNotFound(String.format("User Id %d not found",userId))
            );
        List<Task> tasks = taskRepository.findAllByUsersId(userId);
        return tasks.stream().map(
                task -> modelMapper.map(task,TaskDTO.class)
                ).collect(Collectors.toList());
    }

    @Override
    public TaskDTO getTask(long userId, long taskId) {
        Users users = userRepository.findById(userId).orElseThrow(
                ()->new UserNotFound(String.format("User Id %d not found",userId))
        );
        Task task = taskRepository.findById(taskId).orElseThrow(
                ()-> new TaskNotFound(String.format("Task id %d not found",taskId))
        );
        if(users.getId() != task.getUsers().getId()){
            throw new APIException(String.format("Task is %d is not belong to User id %d",taskId,userId));
        }
        return modelMapper.map(task,TaskDTO.class);
    }

    @Override
    public void deleteTask(long userId, long taskId) {
        Users users = userRepository.findById(userId).orElseThrow(
                ()->new UserNotFound(String.format("User Id %d not found",userId))
        );
        Task task = taskRepository.findById(taskId).orElseThrow(
                ()-> new TaskNotFound(String.format("Task id %d not found",taskId))
        );
        if(users.getId() != task.getUsers().getId()){
            throw new APIException(String.format("Task is %d is not belong to User id %d",taskId,userId));
        }
        taskRepository.deleteById(taskId); // delete the task
    }
}
