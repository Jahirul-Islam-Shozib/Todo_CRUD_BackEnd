package com.example.Todo_CRUD_BackEnd.controller;

import com.example.Todo_CRUD_BackEnd.entity.Task;
import com.example.Todo_CRUD_BackEnd.service.TaskServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {


    private final TaskServiceImpl taskServiceImpl;

    public TaskController(TaskServiceImpl taskServiceImpl) {
        this.taskServiceImpl = taskServiceImpl;
    }

    ///////////////////////////////////Create Task///////////////////////////////////////
    @PostMapping("/saveTask")
    public ResponseEntity<Task> saveTask(@RequestBody Task task)
    {
        return new ResponseEntity<Task>(taskServiceImpl.saveTask(task), HttpStatus.CREATED);
    }

    @PostMapping("/saveCustomTask")
    public ResponseEntity<Task> saveTaskCustomQuery(@RequestBody Task task){
            taskServiceImpl.saveTaskCustomQuery(task);
             return ResponseEntity.ok().build();

    }

    ////////////////////////////////////Read Task///////////////////////////////////////
    @GetMapping("/search/{taskName}")
    public List<Task> searchN(@PathVariable("taskName") String TaskName)
    {
        return taskServiceImpl.SearchByName(TaskName);
    }
    @GetMapping("/getTask")
    public List<Task> getAllTask(){
        return taskServiceImpl.getAllTask();
    }

    @GetMapping("/getCustomTask")
    public List<Task>getCustomTask(){
        return taskServiceImpl.getAllTaskCustomQuery();
    }

//    @GetMapping("/getCustomDuration/{taskName}")
//    public List<Task>getDuration(@PathVariable("taskName") String taskName){
//        return taskServiceImpl.getDurationCustomQuery(taskName);
//    }

    @GetMapping("/getCustomId/{taskNo}")
    public Task getCustomNo(@PathVariable("taskNo") Long taskNo){
        return taskServiceImpl.getCustomNo(taskNo);
    }

    ////////////////////////////////////Update Task//////////////////////////////////////

    @PutMapping("/update/{taskNo}")
    public ResponseEntity<Task> updateTask(@PathVariable("taskNo")Long taskNo, @RequestBody Task task){
        return new ResponseEntity<Task>(taskServiceImpl.updateTask(task,taskNo), HttpStatus.OK);
    }

    @PutMapping("/customUpdate/{taskNo}")
    public ResponseEntity<Task> updateCustomQuery(@PathVariable("taskNo")Long taskNo, @RequestBody Task task){
        taskServiceImpl.updateCustomQuery(task,taskNo);
        return ResponseEntity.ok().build();
}

////////////////////////////////////////Delete Task/////////////////////////////////////
    @DeleteMapping("/delete/{taskNo}")
    public ResponseEntity<String> deleteTask(@PathVariable("taskNo")Long taskNo){
        taskServiceImpl.deleteTask(taskNo);
        return ResponseEntity.ok("Delete Task Successfully");
    }

    @DeleteMapping("/deleteCustom/{taskNo}")
    public ResponseEntity<String> deleteCustomTask(@PathVariable("taskNo")Long taskNo){
        taskServiceImpl.deleteCustomTask(taskNo);
        return ResponseEntity.ok("Delete Task Successfully");
    }
}
