package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.TrelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/trello/")
public class TrelloController {

    @Autowired
    private TrelloService trelloService;

    @RequestMapping(method = RequestMethod.GET, value = "boards")
    public List<TrelloBoardDto> getTrelloBoards() throws TrelloNotFoundException {
        return trelloService.fetchTrelloBoards();
    }

    @ExceptionHandler(TrelloNotFoundException.class)
    public ResponseEntity<Object> showMessage(TaskNotFoundException taskNotFoundException) {
        return new ResponseEntity<>(taskNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.POST, value = "cards")
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloService.createTrelloCard(trelloCardDto);
    }
}