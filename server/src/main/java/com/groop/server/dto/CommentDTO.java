package com.groop.server.dto;

import lombok.Data;

/**
 * @author joandy alejo garcia
 */
@Data
public class CommentDTO {
    private String message;

    private Long task_id;
}
