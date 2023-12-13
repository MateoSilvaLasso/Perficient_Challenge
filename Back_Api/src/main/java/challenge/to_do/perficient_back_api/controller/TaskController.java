package challenge.to_do.perficient_back_api.controller;

import challenge.to_do.perficient_back_api.repository.model.Task;
import challenge.to_do.perficient_back_api.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.Optional;
import java.util.OptionalInt;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskService service;

    @PostMapping("/{category_id}/{status_id}")
    public Optional<Task> createTask(@RequestBody Task task, @PathVariable Long category_id, @PathVariable Long status_id) {
        if (task.getRecurrenceStartDate() != null) {
            LocalDate localDate = task.getRecurrenceStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            DayOfWeek dayOfWeek = localDate.getDayOfWeek();
             return this.service.save(task, category_id, status_id, dayOfWeek);
        }

        return this.service.save(task, category_id,status_id,null);
    }

    @PutMapping("/{id}")
    public Optional<Task> editTask(@PathVariable Long id, @RequestBody Task task) {
        return this.service.edit(id, task);
    }

    @DeleteMapping("")
    public void DeleteTask(Task task) {
        this.service.delete(task);
    }

    @GetMapping
    //@PreAuthorize( value="permitAll()")
    public Iterable<Task> getAllTasks() {
        return this.service.getAll();
    }

    @GetMapping("/{id}")
    public String findTaskByStatus(@PathVariable Long id) {
        return this.service.findTaskByStatus(id);
    }

    @GetMapping("/days")
    public Optional<List<Task>> findTaskByDay() {
        return this.service.findTasksByDay();
    }

    @GetMapping("/weeks")
    public Optional<List<Task>> findTaskByWeek() {
        return this.service.findTasksByWeek();
    }

    @GetMapping("/months")
    public Optional<List<Task>> findTaskByMonth() {
        return this.service.findTaskByMonth();
    }

    @GetMapping("/category/{id}")
    public Optional<List<Task>> findPendingTasksByCategory(@PathVariable Long id) {
        return this.service.findPendingTaskByCategory(id);
    }

    @GetMapping("/status/{id}")
    public Optional<List<Task>> findPendingTaskByStatus(@PathVariable Long id) {
        return this.service.findPendingTaskByStatus(id);
    }

    @GetMapping("/pending/{date}")
    public Optional<List<Task>> findTaskInRange(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        return this.service.findTasksDueInRange(date);
    }


    @GetMapping("/title/{title}")
    public Optional<Task> findTaskByTitle(@PathVariable String title) {
        return this.service.findTaskByTitle(title);
    }


    @GetMapping("/description/{description}")
    public Optional<List<Task>> findTaskByDescription(@PathVariable String description) {
        return this.service.findTaskByDescription(description);
    }


    @GetMapping("/date/{date}")
    public Optional<List<Task>> findTaskByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date){
        return this.service.findTaskByDate(date);
    }

}
