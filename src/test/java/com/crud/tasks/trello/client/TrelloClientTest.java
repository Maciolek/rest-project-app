package com.crud.tasks.trello.client;

import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloClientTest {

    @InjectMocks
    TrelloClient trelloClient;

    @Mock
    RestTemplate restTemplate

    @Mock
    TrelloConfig trelloConfig;

    @Test
    public void shouldFetchTrelloBoards(){

        //given

        //when

        //then
    }




}