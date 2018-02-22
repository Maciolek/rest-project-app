package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import org.apache.coyote.Response;
import org.springframework.beans.factory.parsing.Location;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

@RestController
public class TaskController {

    @RequestMapping(method = RequestMethod.GET, value = "tasks")
    public List<TaskDto> getTasks() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "tasks/{id}")
    @ResponseBody
    public TaskDto getTask(@PathVariable("id") String taskId) {
        return new TaskDto((long) 1, "test title", "test_content");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "tasks/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteTask(@PathVariable("id") String taskId) {
    }

    @RequestMapping(method = RequestMethod.PUT, value = "tasks")
    public TaskDto updateTask(TaskDto taskDto) {
        return new TaskDto((long) 1, "edited test title", "test_content");
    }

    @RequestMapping(method = RequestMethod.POST, value = "tasks")
    public ResponseEntity createTask(TaskDto taskDto) {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Location", "http://localhost:8080/tasks");
        final ResponseEntity<Void> entity = new ResponseEntity<Void>(headers,
                HttpStatus.CREATED);
        return entity;
    }
}
