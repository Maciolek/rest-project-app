package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/task/")

@Api(description = "Endpoints for tasks")
public class TaskController {

    @Autowired
    private DbService service;
    @Autowired
    private TaskMapper taskMapper;

    @RequestMapping(method = RequestMethod.GET, value = "tasks")
    @ApiOperation(value = "Returns a complete list of tasks", notes = "Retrieving collection of tasks")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "The request requires user authentication"),
            @ApiResponse(code = 403, message = "The server understood the request, but is refusing to fulfill it"),
            @ApiResponse(code = 404, message = "Page not found")})
    public List<TaskDto> getTasks() {
        return taskMapper.mapToTaskDtoList(service.getAllTasks());
    }

    @ApiOperation(value = "Returns the task by ID", notes = "Retrieving specific task")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "The request requires user authentication"),
            @ApiResponse(code = 403, message = "The server understood the request, but is refusing to fulfill it"),
            @ApiResponse(code = 404, message = "Task with given taskID does not exist")})
    @RequestMapping(method = RequestMethod.GET, value = "tasks/{taskId}")
    @ResponseBody()
    public TaskDto getTask(
            @ApiParam(required = true, name = "taskId", value = "ID of the task you want to get", defaultValue = "0")
            @PathVariable Long taskId) throws TaskNotFoundException {
        return taskMapper.mapToTaskDto(service.getTask(taskId).orElseThrow(TaskNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "tasks/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody()
    public void deleteTask(@PathVariable Long taskId) throws Exception {

        if (service.getTask(taskId).isPresent()) service.deleteTask(taskId);
        else
            throw new TaskNotFoundException();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "tasks/")
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        return taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(taskDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "tasks", consumes = APPLICATION_JSON_VALUE)
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
