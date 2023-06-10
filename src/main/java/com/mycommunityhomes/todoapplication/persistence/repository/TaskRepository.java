package com.mycommunityhomes.todoapplication.persistence.repository;

import com.mycommunityhomes.todoapplication.persistence.model.Task;
import com.mycommunityhomes.todoapplication.persistence.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    public List<Task> findAllByTaskStatus(TaskStatus taskStatus);

    @Modifying
    @Query(value = "UPDATE task SET finished = true WHERE ID=:id", nativeQuery = true)
    public void markTaskAsFinished(@Param("id") Long id);

}
