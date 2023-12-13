package challenge.to_do.perficient_back_api.repository.persistence;

import challenge.to_do.perficient_back_api.repository.model.Category;
import challenge.to_do.perficient_back_api.repository.model.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ITaskRepository extends CrudRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE " +
            "FUNCTION('DATE_PART', 'DAY', FUNCTION('TIMEZONE', 'America/Bogota', t.endtask)) = FUNCTION('DATE_PART', 'DAY', FUNCTION('TIMEZONE', 'America/Bogota', CURRENT_DATE)) " +
            "AND FUNCTION('DATE_PART', 'MONTH', FUNCTION('TIMEZONE', 'America/Bogota', t.endtask)) = FUNCTION('DATE_PART', 'MONTH', FUNCTION('TIMEZONE', 'America/Bogota', CURRENT_DATE)) " +
            "AND FUNCTION('DATE_PART', 'YEAR', FUNCTION('TIMEZONE', 'America/Bogota', t.endtask)) = FUNCTION('DATE_PART', 'YEAR', FUNCTION('TIMEZONE', 'America/Bogota', CURRENT_DATE)) " +
            "AND FUNCTION('HOUR', t.endtask) <= 23 " +
            "ORDER BY t.beginTask DESC")
    Optional<List<Task>> findTasksByDay();
    @Query("SELECT t FROM Task t WHERE EXTRACT(YEAR FROM t.endtask) = EXTRACT(YEAR FROM CURRENT_DATE) AND EXTRACT(WEEK FROM t.endtask) = EXTRACT(WEEK FROM CURRENT_DATE) ORDER BY t.beginTask DESC")
    Optional<List<Task>> findTasksByWeek();

    @Query("SELECT t FROM Task t WHERE MONTH(t.endtask) = MONTH(CURRENT_DATE) AND YEAR(t.endtask) = YEAR(CURRENT_DATE) ORDER BY t.beginTask DESC")
    Optional<List<Task>> findTaskByMonth();

    @Query("SELECT t FROM Task t JOIN Status s ON t.status.id = s.id JOIN Category c ON t.category.id = c.id WHERE (s.id = 1 or s.id = 2) and c.id = :category ORDER BY t.beginTask asc ")
    Optional<List<Task>> findPendingTaskByCategory(@Param("category")Long category);

    @Query("SELECT t FROM Task t JOIN Status s ON t.status.id = s.id WHERE t.status.id = :status ORDER BY t.beginTask asc")
    Optional<List<Task>> findPendingTaskByStatus(@Param("status")Long status);

    @Query("SELECT s.name FROM Task t JOIN Status s ON s.id = t.status.id WHERE t.id = :status_id")
    String findTaskByStatus(@Param("status_id") Long status_id);

    @Query("SELECT t FROM Task t WHERE t.endtask BETWEEN :startDate AND :endDate")
    List<Task> findTasksDueInRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT t FROM Task t WHERE t.title = :title")
    Optional<Task> findTaskByTitle(@Param("title") String title);

    @Query("SELECT t FROM Task t WHERE LOWER(t.information) LIKE LOWER(CONCAT('%', :description, '%'))")
    Optional<List<Task>> findTaskByDescription(@Param("description") String description);

    @Query("SELECT t FROM Task t WHERE t.beginTask = :date OR t.endtask =:date")
    Optional<List<Task>> findTaskByDate(@Param("date")Date date);

}
