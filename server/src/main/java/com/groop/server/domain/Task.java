package com.groop.server.domain;
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
    private String status;
    private String title;
    private String description;
    @ManyToOne(optional = false)
    private User user;

    @OneToOne
    private Kanban kanban;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Kanban getBoard() {
        return kanban;
    }

    public void setBoard(Kanban kanban) {
        this.kanban = kanban;
    }

    @OneToMany
    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getMember() {
        return user;
    }

    public void setMember(User assignedTo) {
        this.user = assignedTo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
