package com.groop.server.domain;

import javax.persistence.*;

/**
 * @author joandy alejo garcia
 */
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    private String message;

    public String getMessage() {
        return message;
    }

    public User getMember() {
        return user;
    }

    public void setMember(User user) {
        this.user = user;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }
}
