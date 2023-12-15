package challenge.to_do.perficient_back_api.repository.persistence;

import challenge.to_do.perficient_back_api.repository.model.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ITaskRepository extends CrudRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE DAY(t.endtask) = DAY(CURRENT_DATE) AND MONTH(t.endtask) = MONTH(CURRENT_DATE) AND YEAR(t.endtask) = YEAR(CURRENT_DATE) ORDER BY t.beginTask DESC")
    Optional<List<Task>> findTasksByDay();

    @Query("SELECT t FROM Task t WHERE YEARWEEK(t.endtask) = YEARWEEK(CURRENT_DATE) ORDER BY t.beginTask DESC")
    Optional<List<Task>> findTasksByWeek();

    @Query("SELECT t FROM Task t WHERE MONTH(t.endtask) = MONTH(CURRENT_DATE) AND YEAR(t.endtask) = YEAR(CURRENT_DATE) ORDER BY t.beginTask DESC")
    Optional<List<Task>> findTaskByMonth();

    @Query("SELECT t FROM Task t JOIN Status s ON t.status.id = s.id JOIN Category c ON t.category.id = c.id WHERE t.status.id = 1 or t.status.id = 2 ORDER BY t.beginTask asc ")
    Optional<List<Task>> findPendingTaskByCategory();

    @Query("SELECT t FROM Task t JOIN Status s ON t.status.id = s.id WHERE t.status.id = 1 or t.status.id = 2 ORDER BY t.beginTask asc")
    Optional<List<Task>> findPendingTaskByStatus();

    @Query("SELECT t FROM Task t WHERE t.status.id = :status_id")
    Optional<List<Task>> findTaskByStatus(@Param("status_id") Long status_id);

    @Query("SELECT t FROM Task t WHERE t.endtask BETWEEN :startDate AND :endDate")
    List<Task> findTasksDueInRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
