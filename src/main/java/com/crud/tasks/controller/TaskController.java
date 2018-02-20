package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/task")
public class TaskController {

    @RequestMapping(method = RequestMethod.GET, value = "tasks")
    public List<TaskDto> getTasks() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "tasks")
    public TaskDto getTask(String taskId) {
        return new TaskDto((long) 1, "test title", "test_content");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "tasks")
    public void deleteTask(String taskId) {
    }

    @RequestMapping(method = RequestMethod.PUT, value = "tasks")
    public TaskDto updateTask(TaskDto taskDto) {
        return new TaskDto((long) 1, "edited test title", "test_content");
    }

    @RequestMapping(method = RequestMethod.POST, value = "tasks")
    public void createTask(TaskDto taskDto) {
    }
}
