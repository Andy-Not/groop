package com.groop.server.dto;

/**
 * @author joandy alejo garcia
 */
public class TaskDTO {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String title;
    private String description;


}
