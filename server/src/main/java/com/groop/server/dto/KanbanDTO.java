package com.groop.server.dto;

/**
 * @author joandy alejo garcia
 */
public class KanbanDTO {
    private long owner_id;
    private String title;

    public String getTitle() {
        return title;
    }

    public long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(long owner_id) {
        this.owner_id = owner_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
