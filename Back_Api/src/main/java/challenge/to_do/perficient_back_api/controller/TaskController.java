package challenge.to_do.perficient_back_api.controller;

import challenge.to_do.perficient_back_api.repository.model.Task;
import challenge.to_do.perficient_back_api.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.Optional;
import java.util.OptionalInt;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskService service;

    @PostMapping("")
    public Optional<Task> createTask(@RequestBody Task task){
        return this.service.save(task);
    }

    @PutMapping("/{id}")
    public Optional<Task> editTask(@PathVariable Long id, @RequestBody Task task){
        return this.service.edit(id,task);
    }

    @DeleteMapping("")
    public void DeleteTask(Task task){
        this.service.delete(task);
    }

    @GetMapping("")
    public Iterable<Task> getAllTasks(){
        return this.service.getAll();
    }

    @GetMapping("/{id}")
    public String findTaskByStatus(@PathVariable Long id){
        return this.service.findTaskByStatus(id);
    }

    @GetMapping("/days")
    public Optional<List<Task>> findTaskByDay(){
        return this.service.findTasksByDay();
    }

    @GetMapping("/weeks")
    public Optional<List<Task>> findTaskByWeek(){
        return this.service.findTasksByWeek();
    }

    @GetMapping("/months")
    public Optional<List<Task>> findTaskByMonth(){
        return this.service.findTaskByMonth();
    }

    @GetMapping("/category/{id}")
    public Optional<List<Task>> findPendingTasksByCategory(@PathVariable Long id){
        return this.service.findPendingTaskByCategory(id);
    }

    @GetMapping("/status")
    public Optional<List<Task>> findPendingTaskByStatus(){
        return this.service.findPendingTaskByStatus();
    }

    @GetMapping("/pending/{date}")
    public Optional<List<Task>> findTaskInRange(@PathVariable Date current){
        return this.service.findTasksDueInRange(current);
    }

}
