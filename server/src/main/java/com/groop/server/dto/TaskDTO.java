package com.groop.server.dto;

import com.groop.server.model.TaskStatus;
import lombok.Data;

/**
 * @author joandy alejo garcia
 */
@Data
public class TaskDTO {
    private Long id;

    private String description;

    private Long swimLaneID;

    private TaskStatus status;

    private String title;
}
