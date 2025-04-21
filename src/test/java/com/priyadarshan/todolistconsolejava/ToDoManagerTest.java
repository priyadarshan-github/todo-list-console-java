package com.priyadarshan.todolistconsolejava;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoManagerTest {

    private ToDoManager manager;

    @BeforeEach
    void setUp() {
        manager = new ToDoManager();
        manager.getTasks().clear();
    }

    @Test
    void addingTaskIncreasesTaskListSize() {
        int initialSize = manager.getTasks().size();
        Task task = new Task("1","Sample Task", "Description", TaskStatus.PENDING, "2022-01-01", "High");
        manager.addTask(task);
        assertEquals(initialSize + 1, manager.getTasks().size(), "Adding a task should increase the task list size by 1");
    }



}
