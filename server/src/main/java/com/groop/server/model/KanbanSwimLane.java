package com.groop.server.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

/**
 * @author joandy alejo garcia
 */
@Entity
@Data
public class KanbanSwimLane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @ManyToOne
    private Kanban kanban;

    public KanbanSwimLane() {
    }
}
