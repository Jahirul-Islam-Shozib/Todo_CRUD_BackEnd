package com.example.Todo_CRUD_BackEnd.service;

import com.example.Todo_CRUD_BackEnd.entity.Task;

import java.util.List;

public interface TaskService {
    Task saveTask(Task task);
    void saveTaskCustomQuery(Task task);

    List<Task> SearchByName(String name);
    Task getCustomNo(Long taskNo);
    List<Task> getAllTask();
    List<Task> getAllTaskCustomQuery();
//    List<Task> getDurationCustomQuery(String name);


    Task updateTask(Task task, Long taskNo);
    int updateCustomQuery(Task task,Long taskNo);

    void deleteTask(Long taskNo);

    void  deleteCustomTask(Long taskNo);

}
