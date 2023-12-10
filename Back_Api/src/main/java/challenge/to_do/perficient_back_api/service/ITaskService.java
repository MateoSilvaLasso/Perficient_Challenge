package challenge.to_do.perficient_back_api.service;

import challenge.to_do.perficient_back_api.repository.model.Task;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ITaskService {

    Optional<Task> save (Task task);

    Optional<Task> edit( Long id, Task task);

    void delete (Task task);

    Optional<List<Task>> findTaskByStatus(Long id);

    Optional<List<Task>> findTasksByDay();

    Optional<List<Task>> findTasksByWeek();

    Optional<List<Task>> findTaskByMonth();

    Optional<List<Task>> findPendingTaskByCategory();

    Optional<List<Task>> findPendingTaskByStatus();


    Optional<List<Task>> findTasksDueInRange(Date endDate);
}
