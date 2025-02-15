package com.example;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        TodoList list = new TodoList();

        ArrayList<String> foodTags = new ArrayList<>();
        foodTags.add("food");
        list.add("Buy milk", foodTags);

        ArrayList<String> eggTags = new ArrayList<>();
        eggTags.add("food");
        list.add("Buy eggs", eggTags);

        list.add("Prepare a lesson for CSC 122");

        ArrayList<String> beetTags = new ArrayList<>();
        beetTags.add("food");
        beetTags.add("garden");
        beetTags.add("spring");
        list.add("Sow beet seeds", beetTags);

        System.out.println("All Tasks:");
        list.all();

        list.complete("Buy eggs");
        System.out.println("\nCompleted Tasks:");
        list.completeTasks();

        System.out.println("\nIncomplete Tasks:");
        list.incompleteTasks();

        System.out.println("\nTasks tagged with 'food':");
        list.taggedWith("food");

        System.out.println("\nTasks tagged with 'music':");
        list.taggedWith("music");

        list.clear();
        System.out.println("\nAfter clearing:");
        list.all();
    }
}
