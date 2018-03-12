package com.crud.tasks.controller;

//@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="ID Not Found")
public class TaskNotFoundException extends Exception {

    public TaskNotFoundException() {
        super("Id Not Found");
    }
}

