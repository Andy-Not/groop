package com.groop.server.model;
import javax.persistence.*;
import java.util.List;

/**
 * @author joandy alejo garcia
 */
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    @Column(nullable = false, length = 100)
    private String title;
    private int taskOrder;

    @Column(length = 500)
    private String description;

    @OneToOne
    private KanbanSwimLane kanbanSwimLane;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "task", cascade = CascadeType.ALL)
    private List<Comment> comments;

}
