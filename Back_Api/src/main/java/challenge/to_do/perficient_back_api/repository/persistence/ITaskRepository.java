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

    @Query("SELECT t FROM Task t WHERE t.user.userName = :user_name AND DAY(t.endtask) = DAY(CURRENT_DATE) AND MONTH(t.endtask) = MONTH(CURRENT_DATE) AND YEAR(t.endtask) = YEAR(CURRENT_DATE) ORDER BY t.beginTask DESC")
    Optional<List<Task>> findTasksByDay(@Param("user_name") String user_name);
    @Query("SELECT t FROM Task t WHERE t.user.userName = :user_name AND  EXTRACT(YEAR FROM t.endtask) = EXTRACT(YEAR FROM CURRENT_DATE) AND EXTRACT(WEEK FROM t.endtask) = EXTRACT(WEEK FROM CURRENT_DATE) ORDER BY t.beginTask DESC")
    Optional<List<Task>> findTasksByWeek(@Param("user_name")String user_name);

    @Query("SELECT t FROM Task t WHERE t.user.userName = :user_name AND MONTH(t.endtask) = MONTH(CURRENT_DATE) AND YEAR(t.endtask) = YEAR(CURRENT_DATE) ORDER BY t.beginTask DESC")
    Optional<List<Task>> findTaskByMonth(@Param("user_name")String user_name);

    @Query("SELECT t FROM Task t JOIN Status s ON t.status.id = s.id JOIN Category c ON t.category.id = c.id WHERE t.user.userName = :user_name AND (s.id = 1 or s.id = 2) and c.id = :category ORDER BY t.beginTask asc ")
    Optional<List<Task>> findPendingTaskByCategory(@Param("category")Long category,@Param("user_name")String user_name);

    @Query("SELECT t FROM Task t JOIN Status s ON t.status.id = s.id WHERE t.user.userName =:user_name AND s.id = :status ORDER BY t.beginTask asc")
    Optional<List<Task>> findPendingTaskByStatus(@Param("status")Long status, @Param("user_name")String user_name);

    @Query("SELECT t FROM Task t JOIN Status s ON s.id = t.status.id WHERE t.user.userName = :user_name and t.status.id = :status_id")
    Optional<List<Task>> findTaskByStatus(@Param("status_id") Long status_id, @Param("user_name") String user_name);

    @Query("SELECT t FROM Task t WHERE t.user.userName =:user_name AND t.endtask BETWEEN :startDate AND :endDate")
    List<Task> findTasksDueInRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate,@Param("user_name") String user_name);

    @Query("SELECT t FROM Task t WHERE t.title = :title and t.user.userName =:user_name")
    Optional<Task> findTaskByTitle(@Param("title") String title,@Param("user_name") String user_name);

    @Query("SELECT t FROM Task t WHERE t.user.userName =:user_name AND LOWER(t.information) LIKE LOWER(CONCAT('%', :description, '%'))")
    Optional<List<Task>> findTaskByDescription(@Param("description") String description, @Param("user_name") String user_name);

    @Query("SELECT t FROM Task t WHERE t.beginTask = :date OR t.endtask =:date AND t.user.userName =:user_name")
    Optional<List<Task>> findTaskByDate(@Param("date")Date date, @Param("user_name") String user_name);

    @Query("SELECT t FROM Task t JOIN Status s ON t.status.id = s.id WHERE  s.id = 1 OR s.id =2 ORDER BY t.beginTask asc")
    Optional<List<Task>> findPendingTask();

    @Query("SELECT t FROM Task t WHERE t.user.userName =:user_name")
    Iterable<Task> findUserTasks(@Param("user_name") String user_name);

}
