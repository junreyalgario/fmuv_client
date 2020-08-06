package fmuv.client.ui;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    public static List<Task> createTaskList() {
        List<Task> tasks = new ArrayList<>();

        tasks.add(new Task("Cook breakfast.", false, 1));
        tasks.add(new Task("Take a bath.", false, 3));
        tasks.add(new Task("Eat breakfast.", false, 2));
        tasks.add(new Task("Go to work.", false, 4));

        return tasks;
    }

}
