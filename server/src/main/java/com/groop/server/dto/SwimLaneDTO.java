package com.groop.server.dto;

import com.groop.server.model.Task;

import java.util.List;

/**
 * @author joandy alejo garcia
 */
public class SwimLaneDTO {
    private Long id;

    private String title;

    private List<TaskDTO> tasks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }
}
