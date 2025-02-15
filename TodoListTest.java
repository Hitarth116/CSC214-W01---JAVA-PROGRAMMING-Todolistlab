package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class TodoListTest {
    private TodoList list;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        list = new TodoList();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testAddAndRetrieveTasks() {
        list.add("Task 1");
        list.add("Task 2");
        list.all();
        assertTrue(outContent.toString().contains("Task 1 (incomplete)"));
        assertTrue(outContent.toString().contains("Task 2 (incomplete)"));
    }

    @Test
    public void testCompleteTask() {
        list.add("Task 1");
        list.complete("Task 1");
        list.completeTasks();
        assertTrue(outContent.toString().contains("Task 1"));
    }

    @Test
    public void testInvalidTaskAddition() {
        list.add("");
        assertEquals("Invalid input: Task cannot be blank." + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void testDuplicateTaskAddition() {
        list.add("Task 1");
        list.add("Task 1");
        assertTrue(outContent.toString().contains("Task already exists and is incomplete."));
    }

    @Test
    public void testTaggingAndFilterByTags() {
        ArrayList<String> tags = new ArrayList<>(); // This line initializes a new ArrayList for tags
        tags.add("urgent"); // Adding a tag to the list
        list.add("Task 1", tags); // Pass the list to the add method
        list.taggedWith("urgent");
        assertTrue(outContent.toString().contains("Task 1"));
    }

    @Test
    public void testClearList() {
        list.add("Task 1");
        list.clear();
        list.all();
        assertEquals("The to-do list is empty." + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void testNoTasksWithGivenTag() {
        list.taggedWith("nonexistent");
        assertEquals("No tasks found with tag: nonexistent" + System.lineSeparator(), outContent.toString());
    }
}
