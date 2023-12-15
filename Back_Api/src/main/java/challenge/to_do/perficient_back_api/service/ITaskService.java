package challenge.to_do.perficient_back_api.service;

import challenge.to_do.perficient_back_api.repository.model.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ITaskService {
    Optional<Task> save (Task task, Long category_id, Long status_id, String user_id, DayOfWeek recurrence);
    Iterable<Task> getAll(String user_name);
    Optional<Task> edit( Long id, Task task);
    void delete (Task task);
    Optional<List<Task>> findTaskByStatus(Long id, String user_name);
    Optional<List<Task>> findTasksByDay(String user_name);
    Optional<List<Task>> findTasksByWeek(String user_name);
    Optional<List<Task>> findTaskByMonth(String user_name);
    Optional<List<Task>> findPendingTask();
    Optional<List<Task>> findPendingTaskByCategory(Long id, String user_name);
    Optional<List<Task>> findPendingTaskByStatus(Long id, String user_name);
    Optional<List<Task>> findTasksDueInRange(Date endDate, String user_name);
    Optional<Task> findTaskByTitle(String title, String user_name);
    Optional<List<Task>> findTaskByDescription(String description, String user_name);
    Optional<List<Task>> findTaskByDate(Date date, String user_name);
}
