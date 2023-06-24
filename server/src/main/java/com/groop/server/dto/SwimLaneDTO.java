package com.groop.server.dto;

import lombok.Data;

import java.util.List;

/**
 * @author joandy alejo garcia
 */
@Data
public class SwimLaneDTO {
    private Long id;

    private String title;

    private List<TaskDTO> tasks;
}
