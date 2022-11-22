package com.example.Todo_CRUD_BackEnd.service;

import com.example.Todo_CRUD_BackEnd.entity.Task;
import com.example.Todo_CRUD_BackEnd.repository.TaskRepository;
import com.example.Todo_CRUD_BackEnd.repository.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    public final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    ///////////////////////////Create Task///////////////////////
    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void saveTaskCustomQuery(Task task) {
        taskRepository.saveCustomTask(
                task.getTaskName(),
                task.getTaskDate(),
                task.getTaskShift(),
                task.getTaskDuration(),
                task.getTaskProgress()
                );

    }

///////////////////////////Read Task///////////////////////////////
    @Override
    public List<Task> SearchByName(String name) {
        return taskRepository.searchByName(name);
    }


    @Override
    public List<Task> getAllTask(){
        return taskRepository.findAll();
    }
    @Override
    public List<Task> getAllTaskCustomQuery(){
        return taskRepository.getTaskCustomQuery();
    }

//    @Override
//    public List<Task> getDurationCustomQuery(String name){
//        return taskRepository.getTaskDurationCustom(name);
//    }

    @Override
    public Task getCustomNo(Long taskNo){
        return taskRepository.getTaskByTaskNoCustom(taskNo);
    }


    @Override
    public Task updateTask(Task task ,Long taskNo){
        Task existingTask=taskRepository.findById(taskNo).orElseThrow(()-> new ResourceNotFoundException("Employee","Id",taskNo));
        existingTask.setTaskName(task.getTaskName());
        existingTask.setTaskDate(task.getTaskDate());
        existingTask.setTaskShift(task.getTaskShift());
        existingTask.setTaskDuration(task.getTaskDuration());
        existingTask.setTaskProgress(task.getTaskProgress());
        taskRepository.save(existingTask);
        return null;
    }

//////////////////////////////////////Update Task//////////////////////////////
    @Override
    public int updateCustomQuery(Task task, Long taskNo) {
        Task existingCustomTask=taskRepository.getTaskByTaskNoCustom(taskNo);
        int result;
        if(existingCustomTask!=null){
            result=taskRepository.updateTaskCustomQuery(
                    task.getTaskName(),
                    task.getTaskDate(),
                    task.getTaskShift(),
                    task.getTaskDuration(),
                    task.getTaskProgress(),
                    task.getTaskNo()
               );
        }else{
            result=-1;
            System.out.println("Invalid Id given");
        }
        return result;
    }

    ////////////////////////////////////Delete task//////////////////////
    @Override
    public void deleteTask(Long taskNo){
        taskRepository.findById(taskNo).orElseThrow(()-> new ResourceNotFoundException("Task","Id",taskNo));
        taskRepository.deleteById(taskNo);
    }

    @Override
    public void deleteCustomTask(Long taskNo){

        Task selectedTask= taskRepository.getTaskByTaskNoCustom(taskNo);
        if(selectedTask !=null){
            taskRepository.deleteTaskCustomQuery(taskNo);
        }
    }


}
