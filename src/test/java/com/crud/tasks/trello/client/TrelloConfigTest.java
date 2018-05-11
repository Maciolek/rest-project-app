package com.crud.tasks.trello.client;

import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TrelloConfigTest {

    @Mock
    TrelloConfig trelloConfig;

    @Test
    public void testGetTrelloApiEndpoint() {
        //given
        String trelloApiEndpoint = "https://api.trello.com/1";
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn(trelloApiEndpoint);
        //when
        String excpectApiEndpoint = trelloConfig.getTrelloApiEndpoint();
        //then
        Assert.assertEquals(trelloApiEndpoint, excpectApiEndpoint);
    }

    @Test
    public void testGetENDPOINT() {
        //given
        String trelloApiEndpoint = "https://api.trello.com/1";
        when(trelloConfig.getENDPOINT()).thenReturn(trelloApiEndpoint);
        //when
        String excpectApiEndpoint = trelloConfig.getENDPOINT();
        //then
        Assert.assertEquals(trelloApiEndpoint, excpectApiEndpoint);
    }

    @Test
    public void testGetTrelloAppKey() {
        //given
        String trelloAppKey = "8012c65e5626b1f74cff27d7ab6626f0";
        when(trelloConfig.getTrelloAppKey()).thenReturn(trelloAppKey);
        //when
        String excpectTrelloAppKey = trelloConfig.getTrelloAppKey();
        //then
        Assert.assertEquals(trelloAppKey, excpectTrelloAppKey);
    }

    @Test
    public void testGetTrelloAppToken() {
        //given
        String trelloAppToken = "4de19f6c9dbfbcc66a90e4f0ea17b17da85077eb6c2f9068cf540fef4e987e67";
        when(trelloConfig.getTrelloAppToken()).thenReturn(trelloAppToken);
        //when
        String excpectTrelloAppToken = trelloConfig.getTrelloAppToken();
        //then
        Assert.assertEquals(trelloAppToken, excpectTrelloAppToken);
    }

    @Test
    public void testGetTrelloUsername() {
        //given
        String trelloUsername = "maciolek.dariusz@gmail.com";
        when(trelloConfig.getTrelloUsername()).thenReturn(trelloUsername);
        //when
        String excpectTrelloUsername = trelloConfig.getTrelloUsername();
        //then
        Assert.assertEquals(trelloUsername, excpectTrelloUsername);
    }
}
