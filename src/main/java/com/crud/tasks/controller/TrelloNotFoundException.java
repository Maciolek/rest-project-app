package com.crud.tasks.controller;

public class TrelloNotFoundException extends Exception {

    public TrelloNotFoundException() {
        super("Boards not found");
    }
}

