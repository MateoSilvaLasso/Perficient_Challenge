package challenge.to_do.perficient_back_api.repository.persistence;

import challenge.to_do.perficient_back_api.repository.model.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ITaskRepository extends CrudRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE DAY(t.endtask) = DAY(CURRENT_DATE) AND MONTH(t.endtask) = MONTH(CURRENT_DATE) AND YEAR(t.endtask) = YEAR(CURRENT_DATE) ORDER BY T.id DESC")
    Optional<List<Task>> findTasksByDay();

    @Query("SELECT t FROM Task t WHERE YEARWEEK(t.endtask) = YEARWEEK(CURRENT_DATE) ORDER BY T.id DESC")
    Optional<List<Task>> findTasksByWeek();



}
