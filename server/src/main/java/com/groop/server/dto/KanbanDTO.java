package com.groop.server.dto;

import com.groop.server.model.KanbanSwimLane;
import com.groop.server.service.KanbanService;

import java.util.List;

/**
 * @author joandy alejo garcia
 */
public class KanbanDTO {
    private long owner_id;

    private String title;

    private List<SwimLaneDTO> swimLanes;

    public long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(long owner_id) {
        this.owner_id = owner_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SwimLaneDTO> getSwimLanes() {
        return swimLanes;
    }

    public void setSwimLanes(List<SwimLaneDTO> swimLanes) {
        this.swimLanes = swimLanes;
    }
}
