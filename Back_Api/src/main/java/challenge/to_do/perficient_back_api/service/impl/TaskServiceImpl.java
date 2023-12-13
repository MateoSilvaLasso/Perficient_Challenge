package challenge.to_do.perficient_back_api.service.impl;

import challenge.to_do.perficient_back_api.repository.model.Category;
import challenge.to_do.perficient_back_api.repository.model.Multimedia;
import challenge.to_do.perficient_back_api.repository.model.Status;
import challenge.to_do.perficient_back_api.repository.model.Task;
import challenge.to_do.perficient_back_api.repository.persistence.IMultimediaRepository;
import challenge.to_do.perficient_back_api.repository.persistence.IStatusRepository;
import challenge.to_do.perficient_back_api.repository.persistence.ITaskRepository;
import challenge.to_do.perficient_back_api.service.ICategoryService;
import challenge.to_do.perficient_back_api.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.*;

@Service
public class TaskServiceImpl implements ITaskService {

    @Autowired
    private ITaskRepository taskRepository;

    @Autowired
    private IMultimediaRepository multimediaRepository;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IStatusRepository statusRepository;

    @Override
    public Optional<Task> save(Task task, Long category_id, Long status_id, DayOfWeek recurrence) {
        try {
            if(task.getMultimedia()!=null){
                for(Multimedia multimedia: task.getMultimedia()){
                    multimedia.getTasks().add(task);
                    multimediaRepository.save(multimedia);
                }
            }
            Optional<Category> c = categoryService.findById(category_id);
            task.setCategory(c.get());
            Optional<Status> s =  statusRepository.findById(status_id);
            task.setStatus(s.get());
            Optional<Task> savedTask= Optional.of(taskRepository.save(task));
            if (savedTask.isPresent()) {
                if(recurrence!=null) {
                    Task savedTask1 = savedTask.get();


                    List<Task> recurringTasks = generateRecurringTasks(savedTask1, recurrence);


                    for (Task recurringTask : recurringTasks) {
                        taskRepository.save(recurringTask);
                    }
                }else{
                    return Optional.of(task);
                }
                return Optional.of(task);
            } else {
                return Optional.empty();
            }
            //return Optional.of(taskRepository.save(task));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private List<Task> generateRecurringTasks(Task baseTask, DayOfWeek recurrenceDayOfWeek) {
        List<Task> recurringTasks = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(baseTask.getRecurrenceStartDate());

        while (calendar.get(Calendar.DAY_OF_WEEK) != recurrenceDayOfWeek.getValue()) {

            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        while (calendar.getTime().before(baseTask.getEndtask())) {
            Task recurringTask = new Task(baseTask);
            recurringTask.setId(null);
            recurringTask.setBeginTask(calendar.getTime());
            recurringTask.setRecurrenceStartDate(calendar.getTime());

            recurringTasks.add(recurringTask);

            calendar.add(Calendar.DAY_OF_MONTH, 7);
        }

        return recurringTasks;
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
    public Optional<List<Task>> findPendingTaskByStatus(Long id) {
        return taskRepository.findPendingTaskByStatus(id);
    }

    @Override
    public Optional<Task> findTaskByTitle(String title) {
        return taskRepository.findTaskByTitle(title);
    }

    @Override
    public Optional<List<Task>> findTaskByDescription(String description) {
        return taskRepository.findTaskByDescription(description);
    }

    @Override
    public Optional<List<Task>> findTaskByDate(Date date) {
        return taskRepository.findTaskByDate(date);
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
