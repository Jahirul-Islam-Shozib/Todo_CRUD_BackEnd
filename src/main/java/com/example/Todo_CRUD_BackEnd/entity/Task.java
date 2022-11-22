package com.example.Todo_CRUD_BackEnd.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="task_table")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskNo;

    @Column(name="task_name", nullable = false)
    private String taskName;

    @Column(name="taskDate", nullable = false)
    private String taskDate;

    @Column(name="taskShift", nullable = false)
    private String taskShift;

    @Column(name="taskDuration", nullable = false)
    private String taskDuration;

    @Column(name="taskProgress", nullable = false)
    private String taskProgress;
}
