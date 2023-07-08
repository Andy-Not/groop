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

    public Kanban getKanban() {
        return kanban;
    }

    public void setKanban(Kanban kanban) {
        this.kanban = kanban;
    }

    public KanbanSwimLane() {
    }
}
