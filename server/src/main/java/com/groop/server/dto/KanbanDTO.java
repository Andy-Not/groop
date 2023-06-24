package com.groop.server.dto;

import lombok.Data;

import java.util.List;

/**
 * @author joandy alejo garcia
 */
@Data
public class KanbanDTO {
    private Long id;

    private long owner_id;

    private String title;

    private List<SwimLaneDTO> swimLanes;
}
