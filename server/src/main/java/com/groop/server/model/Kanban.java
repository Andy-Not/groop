package com.groop.server.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
public class Kanban {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    private User owner;

    @ManyToMany
    private List<User> users;

    public Kanban() {
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        if (Objects.isNull(users)){
            users = new ArrayList<>();
        }
        users.add(user);
    }
}
