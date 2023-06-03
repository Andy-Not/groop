package com.groop.server.dto;

/**
 * @author joandy alejo garcia
 */
public class CommentDTO {
    private String message;
    private Long task_id;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTask_id() {
        return task_id;
    }

    public void setTask_id(Long task_id) {
        this.task_id = task_id;
    }
}
