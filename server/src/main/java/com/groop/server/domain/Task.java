package com.groop.server.domain;

import javax.persistence.*;

/**
 * @author joandy alejo garcia
 */
@Entity
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private String Title;
    private String Description;
    @ManyToOne(optional = false)
    private Member member;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member assignedTo) {
        this.member = assignedTo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
