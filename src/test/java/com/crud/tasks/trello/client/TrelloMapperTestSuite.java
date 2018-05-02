package com.crud.tasks.trello.client;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTestSuite {

    @InjectMocks
    TrelloMapper trelloMapper;

    @Test
    public void testMapToBoardDto() {
        //given
        TrelloList trelloList1 = new TrelloList("11", "List1", false);
        TrelloList trelloList2 = new TrelloList("22", "List2", true);
        List<TrelloList> trelloLists = Arrays.asList(trelloList1, trelloList2);
        TrelloBoard trelloBoard = new TrelloBoard("1", "Board1", trelloLists);
        List<TrelloBoard> trelloBoardList = Arrays.asList(trelloBoard);

        //when
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);

        //then
        Assert.assertNotEquals(trelloBoardList,trelloBoardDtoList);
        Assert.assertEquals(1, trelloBoardDtoList.size());
        Assert.assertEquals(2, trelloBoardDtoList.get(0).getLists().size());
        Assert.assertEquals("11", trelloBoardDtoList.get(0).getLists().get(0).getId());
        Assert.assertEquals("List1", trelloBoardDtoList.get(0).getLists().get(0).getName());
        Assert.assertTrue(trelloBoardDtoList.get(0).getLists().get(1).isClosed());
    }

    @Test
    public void testMapToBoard() {
        TrelloListDto trelloListDto1 = new TrelloListDto("33", "List11", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("44", "List22", false);
        List<TrelloListDto> trelloLists = Arrays.asList(trelloListDto1, trelloListDto2);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "Board1", trelloLists);
        List<TrelloBoardDto> trelloBoardListDto = Arrays.asList(trelloBoardDto);

        //when
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardListDto);

        //then
        Assert.assertNotEquals(trelloBoardListDto,trelloBoardList);
        Assert.assertEquals(1, trelloBoardList.size());
        Assert.assertEquals(2, trelloBoardList.get(0).getLists().size());
        Assert.assertEquals("33", trelloBoardList.get(0).getLists().get(0).getId());
        Assert.assertEquals("List11", trelloBoardList.get(0).getLists().get(0).getName());
        Assert.assertFalse(trelloBoardList.get(0).getLists().get(1).isClosed());
    }

    @Test
    public void testMapToList() {
        //given
        TrelloListDto trelloListDto1 = new TrelloListDto("33", "List11", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("44", "List22", false);
        List<TrelloListDto> trelloListsDto = Arrays.asList(trelloListDto1, trelloListDto2);

        //when
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListsDto);

        //then
        Assert.assertNotEquals(trelloListsDto,trelloLists);
        Assert.assertEquals(2, trelloLists.size());
        Assert.assertEquals("33", trelloLists.get(0).getId());
        Assert.assertEquals("List22", trelloLists.get(1).getName());
        Assert.assertFalse(trelloLists.get(1).isClosed());
    }

    @Test
    public void testMapToListDto() {
        //given
        TrelloList trelloList1 = new TrelloList("33", "List11", true);
        TrelloList trelloList2 = new TrelloList("44", "List22", false);
        List<TrelloList> trelloLists = Arrays.asList(trelloList1, trelloList2);

        //when
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToListDto(trelloLists);

        //then
        Assert.assertNotEquals(trelloLists,trelloListsDto);
        Assert.assertEquals(2, trelloListsDto.size());
        Assert.assertEquals("33", trelloListsDto.get(0).getId());
        Assert.assertEquals("List22", trelloListsDto.get(1).getName());
        Assert.assertFalse(trelloListsDto.get(1).isClosed());
    }

    @Test
    public void testMapToCard() {
        //given
        TrelloCardDto trelloCardDto1 = new TrelloCardDto("Card1", "description1", "1", "11");

        //when
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto1);

        //then
        Assert.assertEquals("Card1", trelloCard.getName());
        Assert.assertEquals("description1", trelloCard.getDescription());
        Assert.assertEquals("1", trelloCard.getPos());
        Assert.assertEquals("11", trelloCard.getListId());
    }

    @Test
    public void testMapToCardDto() {
        //given
        TrelloCard trelloCard1 = new TrelloCard("Card1", "description1", "1", "11");

        //when
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard1);

        //then
        Assert.assertEquals("Card1", trelloCardDto.getName());
        Assert.assertEquals("description1", trelloCardDto.getDescription());
        Assert.assertEquals("1", trelloCardDto.getPos());
        Assert.assertEquals("11", trelloCardDto.getListId());
    }
}
