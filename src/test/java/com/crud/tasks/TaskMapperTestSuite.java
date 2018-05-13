package com.crud.tasks;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTestSuite {

    @InjectMocks
    TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //given
        TaskDto taskDto = new TaskDto(1L, "task to do", "learn TDD");

        //when
        Task task = taskMapper.mapToTask(taskDto);

        //then
        Assert.assertNotNull(task);
        Assert.assertEquals(1l, task.getId(), 0);
        Assert.assertEquals("task to do", task.getTitle());
        Assert.assertEquals("learn TDD", task.getContent());
    }

    @Test
    public void testMapToTaskDto() {
        //given
        Task task = new Task(2L, "task to do ASAP", "learn JAVA");

        //when
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //then
        Assert.assertNotNull(task);
        Assert.assertEquals(2l, taskDto.getId(), 0);
        Assert.assertEquals("task to do ASAP", taskDto.getTitle());
        Assert.assertEquals("learn JAVA", taskDto.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        //given
        Task task = new Task(3L, "task to do", "learn SPRING");
        List<Task> taskList = Arrays.asList(task);

        //when
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);

        //then
        Assert.assertNotNull(taskDtoList);
        Assert.assertEquals(1, taskDtoList.size());
        taskDtoList.forEach(taskDto -> {
            assertEquals(3L, taskDto.getId(), 0);
            assertEquals("task to do", taskDto.getTitle());
            assertEquals("learn SPRING", taskDto.getContent());
        });
    }
}
