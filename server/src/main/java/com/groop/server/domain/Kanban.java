package com.groop.server.domain;

import com.sun.istack.NotNull;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author joandy alejo garcia
 */
@Entity
public class Kanban {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String title;
//    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
//    @JoinColumn(name = "kanban_id")
//    private List<Task> tasks;

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

//    public List<Task> getTasks() {
//        return tasks;
//    }

//    public void setTasks(List<Task> tasks) {
//        this.tasks = tasks;
//    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

//    public void addTask(Task task) {
//
//        if (Objects.isNull(tasks)) {
//            tasks = new ArrayList<>();
//        }
//        tasks.add(task);
//    }
}
