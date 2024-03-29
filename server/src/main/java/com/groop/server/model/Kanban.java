package com.groop.server.model;

import lombok.Data;

import javax.persistence.*;
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

    public void addUser(User user) {
        if (Objects.isNull(users)){
            users = new ArrayList<>();
        }
        users.add(user);
    }
}
