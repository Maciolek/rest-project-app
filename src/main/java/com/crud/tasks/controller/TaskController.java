package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class TaskController {

    @Autowired
    private DbService service;
    @Autowired
    private TaskMapper taskMapper;

    @RequestMapping(method = RequestMethod.GET, value = "tasks")
    public List<TaskDto> getTasks() {
        return taskMapper.mapToTaskDtoList(service.getAllTasks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "tasks/")
    @ResponseBody
    public TaskDto getTask(@RequestParam Long taskId) throws TaskNotFoundException {
        return taskMapper.mapToTaskDto(service.getTask(taskId).orElseThrow(TaskNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "tasks")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody()
    public void deleteTask(@RequestParam Long taskId) throws Exception {
        if (service.getTask(taskId).isPresent()) service.deleteTask(taskId);
        else
            throw new TaskNotFoundException();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "tasks/")
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        return taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(taskDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "tasks/", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createTask(@RequestBody TaskDto taskDto) {
        service.saveTask(taskMapper.mapToTask(taskDto));
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Location", "http://localhost:8080/tasks");
        final ResponseEntity<Void> entity = new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        return entity;
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<Object> showMessage(TaskNotFoundException taskNotFoundException) {
        return new ResponseEntity<>(taskNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }
}
