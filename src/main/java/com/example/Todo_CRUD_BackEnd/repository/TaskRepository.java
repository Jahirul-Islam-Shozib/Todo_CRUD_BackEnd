package com.example.Todo_CRUD_BackEnd.repository;

import com.example.Todo_CRUD_BackEnd.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {



    /////////////////////////////Create Operation//////////////////////////////////
    @Modifying
    @Transactional
    @Query(value="INSERT INTO task_table (task_name,task_date,task_shift,task_duration,task_progress) values (?1, ?2, ?3, ?4, ?5)",nativeQuery=true)
    void saveCustomTask(String taskName,
                        String taskDate,
                        String taskShift,
                        String taskDuration,
                        String taskProgress);



    ///////////////////////////////Search Or Read Query////////////////////////////////////
    @Query(value = "SELECT * FROM task_table where task_name=:n",nativeQuery = true)
    public List<Task> searchByName(@Param("n") String name);


    @Query(value="SELECT * FROM task_table",nativeQuery=true)
    public List<Task> getTaskCustomQuery();


//    @Query(value = "SELECT task_duration FROM task_table where task_name=:n",nativeQuery = true)
//    public List<Task> getTaskDurationCustom(@Param("n") String name);

    @Query(value = "SELECT * FROM task_table WHERE task_no=?1", nativeQuery = true)
    Task getTaskByTaskNoCustom(Long taskNo);


    //////////////////////////////////////Update Task//////////////////////////////////////////
    @Modifying
    @Transactional
    @Query(value ="UPDATE task_table SET task_name=?1,task_date=?2,task_shift=?3,task_duration=?4,task_progress=?5 WHERE task_no=?6",nativeQuery = true)
    int updateTaskCustomQuery(
            String taskName,
            String taskDate,
            String taskShift,
            String taskDuration,
            String taskProgress,
            Long task_no
    );

    ////////////////////////////////////Delete Task////////////////////////////////////////////

    @Modifying
    @Transactional
    @Query(value="DELETE FROM task_table WHERE task_no=?1",nativeQuery = true)
    void deleteTaskCustomQuery(Long taskNo);


}
