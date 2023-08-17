package com.groop.server.repository;

import com.groop.server.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author joandy alejo garcia
 */
public interface TaskRepository extends JpaRepository<Task, Long> {


}
