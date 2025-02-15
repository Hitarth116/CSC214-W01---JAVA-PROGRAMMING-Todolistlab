package com.example;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class TodoList {
    private HashMap<String, Boolean> tasks = new HashMap<>();
    private HashMap<String, List<String>> tags = new HashMap<>();

    public void add(String task, List<String> tagsToAdd) {
        if (task == null || task.trim().isEmpty()) {
            System.out.println("Invalid input: Task cannot be blank.");
            return;
        }
        if (tasks.containsKey(task) && !tasks.get(task)) {
            System.out.println("Task already exists and is incomplete.");
            return;
        }
        tasks.put(task, false);
        if (tagsToAdd != null && !tagsToAdd.isEmpty()) {
            tags.put(task, new ArrayList<>(tagsToAdd));
        }
    }

    public void add(String task) {
        add(task, null);
    }

    public void complete(String task) {
        if (!tasks.containsKey(task)) {
            System.out.println("Task not found.");
            return;
        }
        tasks.put(task, true);
    }

    public void all() {
        if (tasks.isEmpty()) {
            System.out.println("The to-do list is empty.");
        } else {
            tasks.forEach((task, completed) -> System.out.println(task + (completed ? " (completed)" : " (incomplete)")));
        }
    }

    public void completeTasks() {
        tasks.entrySet().stream()
            .filter(HashMap.Entry::getValue)
            .forEach(entry -> System.out.println(entry.getKey()));
    }

    public void incompleteTasks() {
        tasks.entrySet().stream()
            .filter(entry -> !entry.getValue())
            .forEach(entry -> System.out.println(entry.getKey()));
    }

    public void clear() {
        tasks.clear();
        tags.clear();
    }

    public void taggedWith(String tag) {
        List<String> filteredTasks = tags.entrySet().stream()
            .filter(entry -> entry.getValue().contains(tag))
            .map(HashMap.Entry::getKey)
            .collect(Collectors.toList());

        if (filteredTasks.isEmpty()) {
            System.out.println("No tasks found with tag: " + tag);
        } else {
            filteredTasks.forEach(System.out::println);
        }
    }
}

