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

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskService service;

    @PostMapping("/{category_id}/{status_id}/{user_name}")
    public Optional<Task> createTask(@RequestBody Task task, @PathVariable Long category_id, @PathVariable Long status_id, @PathVariable String user_name) {
        if (task.getRecurrenceStartDate() != null) {
            LocalDate localDate = task.getRecurrenceStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            DayOfWeek dayOfWeek = localDate.getDayOfWeek();
             return this.service.save(task, category_id, status_id, user_name, dayOfWeek);
        }

        return this.service.save(task, category_id,status_id,user_name,null);
    }

    @PutMapping("/{id}/{category_id}/{status_id}")
    public Optional<Task> editTask(@PathVariable Long id,@PathVariable Long category_id,@PathVariable Long status_id,  @RequestBody Task task) {
        return this.service.edit(id, task, category_id, status_id);
    }

    @DeleteMapping("")
    public void DeleteTask(Task task) {
        this.service.delete(task);
    }

    @GetMapping("/{user_name}")
    //@PreAuthorize("hasRole('noRole')")
    //@PreAuthorize( value="permitAll()")
    public Iterable<Task> getAllTasks(@PathVariable String user_name) {
        return this.service.getAll(user_name);
    }

    @GetMapping("/{id}/{user_name}")
    @PreAuthorize("hasRole('noRole')")
    public Optional<List<Task>> findTaskByStatus(@PathVariable Long id, @PathVariable String user_name) {
        return this.service.findTaskByStatus(id, user_name);
    }

    @GetMapping("/days/{user_name}")
    public Optional<List<Task>> findTaskByDay(@PathVariable String user_name) {
        return this.service.findTasksByDay(user_name);
    }

    @GetMapping("/weeks/{user_name}")
    public Optional<List<Task>> findTaskByWeek(@PathVariable String user_name) {
        return this.service.findTasksByWeek(user_name);
    }

    @GetMapping("/months/{user_name}")
    public Optional<List<Task>> findTaskByMonth(@PathVariable String user_name) {
        return this.service.findTaskByMonth(user_name);
    }

    @GetMapping("/category/{id}/{user_name}")
    public Optional<List<Task>> findPendingTasksByCategory(@PathVariable Long id, @PathVariable String user_name) {
        return this.service.findPendingTaskByCategory(id, user_name);
    }

    @GetMapping("/status/{id}/{user_name}")
    public Optional<List<Task>> findPendingTaskByStatus(@PathVariable Long id, @PathVariable String user_name) {
        return this.service.findPendingTaskByStatus(id, user_name);
    }

    @GetMapping("/pending/{date}/{user_name}")
    public Optional<List<Task>> findTaskInRange(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date, @PathVariable String user_name) {
        return this.service.findTasksDueInRange(date, user_name);
    }


    @GetMapping("/title/{title}/{user_name}")
    public Optional<Task> findTaskByTitle(@PathVariable String title, @PathVariable String user_name) {
        return this.service.findTaskByTitle(title, user_name);
    }


    @GetMapping("/description/{description}/{user_name}")
    public Optional<List<Task>> findTaskByDescription(@PathVariable String description, @PathVariable String user_name) {
        return this.service.findTaskByDescription(description, user_name);
    }


    @GetMapping("/date/{date}/{user_name}")
    public Optional<List<Task>> findTaskByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date, @PathVariable String user_name){
        return this.service.findTaskByDate(date, user_name);
    }

}
