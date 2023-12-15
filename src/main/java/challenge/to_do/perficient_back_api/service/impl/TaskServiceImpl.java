package challenge.to_do.perficient_back_api.service.impl;

import challenge.to_do.perficient_back_api.repository.model.*;
import challenge.to_do.perficient_back_api.repository.persistence.IMultimediaRepository;
import challenge.to_do.perficient_back_api.repository.persistence.IStatusRepository;
import challenge.to_do.perficient_back_api.repository.persistence.ITaskRepository;
import challenge.to_do.perficient_back_api.repository.persistence.IUserRepository;
import challenge.to_do.perficient_back_api.service.ICategoryService;
import challenge.to_do.perficient_back_api.service.ITaskService;
import jakarta.jws.soap.SOAPBinding;
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

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Optional<Task> save(Task task, Long category_id, Long status_id, String user_name, DayOfWeek recurrence) {
        try {
            /*
            if(task.getMultimedia()!=null){
                for(Multimedia multimedia: task.getMultimedia()){
                    multimedia.getTasks().add(task);
                    multimediaRepository.save(multimedia);
                }
            }

             */
            Optional<Category> c = categoryService.findById(category_id);
            task.setCategory(c.get());
            Optional<Status> s =  statusRepository.findById(status_id);
            task.setStatus(s.get());
            Optional<User> u = userRepository.findByUsername(user_name);
            task.setUser(u.get());
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
        calendar.add(Calendar.DAY_OF_MONTH, 1);

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
    public Optional<List<Task>> findTaskByStatus(Long id, String user_name) {
        return taskRepository.findTaskByStatus(id, user_name);
    }

    @Override
    public Optional<List<Task>> findTasksByDay(String user_name) {
        return taskRepository.findTasksByDay(user_name);
    }

    @Override
    public Optional<List<Task>> findTasksByWeek(String user_name) {
        return taskRepository.findTasksByWeek(user_name);
    }

    @Override
    public Optional<List<Task>> findTaskByMonth(String user_name) {
        return taskRepository.findTaskByMonth(user_name);
    }

    @Override
    public Optional<List<Task>> findPendingTaskByCategory(Long id, String user_name) {
        return taskRepository.findPendingTaskByCategory(id, user_name);
    }

    @Override
    public Optional<List<Task>> findPendingTaskByStatus(Long id, String user_name) {
        return taskRepository.findPendingTaskByStatus(id, user_name);
    }

    @Override
    public Optional<Task> findTaskByTitle(String title,String user_name) {
        return taskRepository.findTaskByTitle(title, user_name);
    }

    @Override
    public Optional<List<Task>> findTaskByDescription(String description, String user_name) {
        return taskRepository.findTaskByDescription(description, user_name);
    }

    @Override
    public Optional<List<Task>> findTaskByDate(Date date, String user_name) {
        return taskRepository.findTaskByDate(date, user_name);
    }

    @Override
    public Optional<List<Task>> findTasksDueInRange(Date end, String user_name) {
        Date begin = new Date();
        return Optional.ofNullable(taskRepository.findTasksDueInRange(begin, end,user_name));
    }

    @Override
    public Iterable<Task> getAll(String user_name){
        return this.taskRepository.findUserTasks(user_name);
    }

    @Override
    public Optional<List<Task>> findPendingTask() {
        return this.taskRepository.findPendingTask();
    }
}
