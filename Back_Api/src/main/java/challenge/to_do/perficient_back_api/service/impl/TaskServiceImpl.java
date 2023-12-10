package challenge.to_do.perficient_back_api.service.impl;

import challenge.to_do.perficient_back_api.repository.model.Task;
import challenge.to_do.perficient_back_api.repository.persistence.ITaskRepository;
import challenge.to_do.perficient_back_api.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements ITaskService {

    @Autowired
    private ITaskRepository taskRepository;

    @Override
    public Optional<Task> save(Task task) {
        try {
            return Optional.of(taskRepository.save(task));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Task> edit(Long id, Task task) {
        taskRepository.deleteById(id);
        return Optional.of(taskRepository.save(task));
    }

    @Override
    public void delete(Task task) {
        taskRepository.delete(task);
    }

    @Override
    public String findTaskByStatus(Long id) {
        return taskRepository.findTaskByStatus(id);
    }

    @Override
    public Optional<List<Task>> findTasksByDay() {
        return taskRepository.findTasksByDay();
    }

    @Override
    public Optional<List<Task>> findTasksByWeek() {
        return taskRepository.findTasksByWeek();
    }

    @Override
    public Optional<List<Task>> findTaskByMonth() {
        return taskRepository.findTaskByMonth();
    }

    @Override
    public Optional<List<Task>> findPendingTaskByCategory(Long id) {
        return taskRepository.findPendingTaskByCategory(id);
    }

    @Override
    public Optional<List<Task>> findPendingTaskByStatus() {
        return taskRepository.findPendingTaskByStatus();
    }

    @Override
    public Optional<List<Task>> findTasksDueInRange(Date end) {

        Date begin = new Date();
        return Optional.ofNullable(taskRepository.findTasksDueInRange(begin, end));
    }

    @Override
    public Iterable<Task> getAll(){
        return this.taskRepository.findAll();
    }
}
