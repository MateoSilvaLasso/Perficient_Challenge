package challenge.to_do.perficient_back_api.model;

import challenge.to_do.perficient_back_api.repository.model.Category;
import challenge.to_do.perficient_back_api.repository.model.Status;
import challenge.to_do.perficient_back_api.repository.model.Task;
import challenge.to_do.perficient_back_api.repository.persistence.ICategoryRepository;
import challenge.to_do.perficient_back_api.repository.persistence.IStatusRepository;
import challenge.to_do.perficient_back_api.repository.persistence.ITaskRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@DataJpaTest
public class TaskTest {

    @Autowired
    private ITaskRepository taskRepository;

    @Autowired
    private IStatusRepository statusRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    @BeforeEach
    public void cleanUp(){
        taskRepository.deleteAll();
        statusRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    public void saveTaskTest(){
        Task task= new Task();
        try {
            Date begin = new SimpleDateFormat("yyyy-MM-dd").parse("2022-12-31");
            Date end = new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-02");
            task = new Task(begin, end,"Calle 14 #12-24",new Category(),new Status());
            task = taskRepository.save(task);

        }catch (ParseException ex){
            ex.printStackTrace();
        }

        assertNotNull(task.getId());
        try {
            assertEquals(task.getBeginTask(), new SimpleDateFormat("yyyy-MM-dd").parse("2022-12-31"));
        } catch (ParseException ex){
            ex.printStackTrace();
        }

    }

    @Test
    public void findTaskByIdTest(){
        Task task= new Task();
        try {
            Date begin = new SimpleDateFormat("yyyy-MM-dd").parse("2022-12-31");
            Date end = new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-02");
            task = new Task(1L,begin, end,"Calle 14 #12-24",null,null);
            Task newTask = taskRepository.save(task);

        }catch (ParseException ex){
            ex.printStackTrace();
        }
        Optional<Task> byTask = taskRepository.findById(1L);

        assertNotNull(byTask);
        assertEquals(byTask.get().getId(), task.getId());
        assertEquals(byTask.get().getBeginTask(), task.getBeginTask());
        assertEquals(byTask.get().getEndtask(), task.getEndtask());
        assertNull(byTask.get().getCategory());

    }

    @Test
    public void DeleteTaskTest(){
        Task task= new Task();
        Task deleteTask = new Task();
        try {
            Date begin = new SimpleDateFormat("yyyy-MM-dd").parse("2022-12-31");
            Date end = new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-02");
            task = new Task(begin, end,"Calle 14 #12-24",new Category(),new Status());
            deleteTask = taskRepository.save(task);

        }catch (ParseException ex){
            ex.printStackTrace();
        }

        taskRepository.deleteById(deleteTask.getId());

        Optional<Task> deletedAuthor = taskRepository.findById(deleteTask.getId());

        assertFalse(deletedAuthor.isPresent());

    }

    @Test
    public void testFindAllAuthors() {
        Task task= new Task();
        Task task1 = new Task();
        try {
            Date begin = new SimpleDateFormat("yyyy-MM-dd").parse("2022-12-31");
            Date end = new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-02");
            task = new Task(begin, end,"Calle 14 #12-24",new Category(),new Status());
            task = taskRepository.save(task);
            Date begin1 = new SimpleDateFormat("yyyy-MM-dd").parse("2003-09-02");
            Date end1 = new SimpleDateFormat("yyyy-MM-dd").parse("2007-02-02");
            task1 = new Task(begin, end,"Calle 14 #12-24",new Category(),new Status());
            task1 = taskRepository.save(task1);

        }catch (ParseException ex){
            ex.printStackTrace();
        }

        Iterable<Task> taskIterable = taskRepository.findAll();
        List<Task> tasks = StreamSupport.stream(taskIterable.spliterator(),false).
                collect(Collectors.toList());


        assertEquals(2, tasks.size());//esto debido a que en el script habian 15 cargados
    }

    @Test
    public void findByDayTest(){
        Task task= new Task();
        Task task1 = new Task();
        try {
            Date begin = new SimpleDateFormat("yyyy-MM-dd").parse("2022-12-31");
            Date end = new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-11");
            task = new Task(begin, end,"Calle 14 #12-24",new Category(),new Status());
            task = taskRepository.save(task);
            Date begin1 = new SimpleDateFormat("yyyy-MM-dd").parse("2003-09-02");
            Date end1 = new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-11");
            task1 = new Task(begin, end,"Calle 14 #12-24",new Category(),new Status());
            task1 = taskRepository.save(task1);

        }catch (ParseException ex){
            ex.printStackTrace();
        }

        //assertEquals(2,taskRepository.findTasksByDay().get().size());

    }

    @Test
    public void findByMonthTest(){
        Task task= new Task();
        Task task1 = new Task();
        try {
            Date begin = new SimpleDateFormat("yyyy-MM-dd").parse("2022-12-31");
            Date end = new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-01");
            task = new Task(begin, end,"Calle 14 #12-24",new Category(),new Status());
            task = taskRepository.save(task);
            Date begin1 = new SimpleDateFormat("yyyy-MM-dd").parse("2003-09-02");
            Date end1 = new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-11");
            task1 = new Task(begin, end,"Calle 14 #12-24",new Category(),new Status());
            task1 = taskRepository.save(task1);

        }catch (ParseException ex){
            ex.printStackTrace();
        }

        //assertEquals(2,taskRepository.findTaskByMonth().get().size());
    }

    @Test
    public void findByWeekTest(){
        Task task= new Task();
        Task task1 = new Task();
        Task task2 = new Task();
        try {
            Date begin = new SimpleDateFormat("yyyy-MM-dd").parse("2022-12-31");
            Date end = new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-15");
            task = new Task(begin, end,"Calle 14 #12-24",new Category(),new Status());
            task = taskRepository.save(task);
            Date begin1 = new SimpleDateFormat("yyyy-MM-dd").parse("2003-09-02");
            Date end1 = new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-11");
            task1 = new Task(begin, end,"Calle 14 #12-24",new Category(),new Status());
            task1 = taskRepository.save(task1);
            Date begin2 = new SimpleDateFormat("yyyy-MM-dd").parse("2003-09-02");
            Date end2 = new SimpleDateFormat("yyyy-MM-dd").parse("2023-09-11");
            task2 = new Task(begin, end,"Calle 14 #12-24",new Category(),new Status());
            task2 = taskRepository.save(task1);

        }catch (ParseException ex){
            ex.printStackTrace();
        }

        //assertEquals(2,taskRepository.findTasksByWeek().get().size());
    }

    @Test
    public void findPendingTaskByCategorytest(){
        Task task= new Task();
        Task task1 = new Task();
        Task task2 = new Task();
        try {
            Category category = new Category(1L,"Work",new Color(187,0, 255));
            Category category1 = new Category(2L,"University", new Color(23,45,197));
            Category category2 = new Category(3L, "Home Tasks", new Color(0,0,0));
            Status status = new Status(1L, "To-do");
            Status status1 = new Status(2L, "In progress");
            Status status2 =  new Status(3L, "Cancelled");
            Status status3 = new Status(4L, "finished");
            statusRepository.save(status);
            statusRepository.save(status1);
            statusRepository.save(status2);
            statusRepository.save(status3);
            categoryRepository.save(category);
            categoryRepository.save(category1);
            categoryRepository.save(category2);
            Date begin = new SimpleDateFormat("yyyy-MM-dd").parse("2022-12-31");
            Date end = new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-15");
            task = new Task(begin, end,"Calle 14 #12-24",categoryRepository.findById(1L).get(),statusRepository.findById(1L).get());
            task = taskRepository.save(task);
            Date begin1 = new SimpleDateFormat("yyyy-MM-dd").parse("2003-09-02");
            Date end1 = new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-11");
            task1 = new Task(begin, end,"Calle 14 #12-24",categoryRepository.findById(3L).get(),statusRepository.findById(3L).get());
            task1 = taskRepository.save(task1);
            Date begin2 = new SimpleDateFormat("yyyy-MM-dd").parse("2003-09-02");
            Date end2 = new SimpleDateFormat("yyyy-MM-dd").parse("2023-09-11");
            task2 = new Task(begin, end,"Calle 14 #12-24",categoryRepository.findById(1L).get(),statusRepository.findById(2L).get());
            task2 = taskRepository.save(task2);

        }catch (ParseException ex){
            ex.printStackTrace();
        }

        //assertEquals(2, taskRepository.findPendingTaskByCategory(1L).get().size());
    }

    @Test
    public void findPendingTaskByStatusTest(){
        Task task= new Task();
        Task task1 = new Task();
        Task task2 = new Task();
        try {

            Category category2 = new Category(3L, "Home Tasks", new Color(255,255,0));
            Category category1 = new Category(2L,"University", new Color(23,45,197));
            Category category = new Category(1L,"Work",new Color(255,0, 0));
            Status status = new Status(1L, "To-do");
            Status status1 = new Status(2L, "In progress");
            Status status2 =  new Status(3L, "Cancelled");
            Status status3 = new Status(4L, "finished");
            statusRepository.save(status);
            statusRepository.save(status1);
            statusRepository.save(status2);
            statusRepository.save(status3);
            categoryRepository.save(category);
            categoryRepository.save(category1);
            categoryRepository.save(category2);
            Date begin = new SimpleDateFormat("yyyy-MM-dd").parse("2022-12-31");
            Date end = new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-15");
            task = new Task(begin, end,"Calle 14 #12-24",categoryRepository.findById(1L).get(),statusRepository.findById(1L).get());
            task = taskRepository.save(task);
            Date begin1 = new SimpleDateFormat("yyyy-MM-dd").parse("2003-09-02");
            Date end1 = new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-11");
            task1 = new Task(begin, end,"Calle 14 #12-24",categoryRepository.findById(3L).get(),statusRepository.findById(1L).get());
            task1 = taskRepository.save(task1);
            Date begin2 = new SimpleDateFormat("yyyy-MM-dd").parse("2003-09-02");
            Date end2 = new SimpleDateFormat("yyyy-MM-dd").parse("2023-09-11");
            task2 = new Task(begin, end,"Calle 14 #12-24",categoryRepository.findById(1L).get(),statusRepository.findById(1L).get());
            task2 = taskRepository.save(task2);

        }catch (ParseException ex){
            ex.printStackTrace();
        }

        //assertEquals(3, taskRepository.findPendingTaskByStatus(1L).get().size());
    }

    @Test
    public void findTaskByStatus(){
        Task task= new Task();
        Task task1 = new Task();
        Task task2 = new Task();
        try {
            Category category = new Category(1L,"Work",new Color(187,0, 255));
            Category category1 = new Category(2L,"University", new Color(23,45,197));
            Category category2 = new Category(3L, "Home Tasks", new Color(0,0,0));
            Status status = new Status(1L, "To-do");
            Status status1 = new Status(2L, "In progress");
            Status status2 =  new Status(3L, "Cancelled");
            Status status3 = new Status(4L, "finished");
            statusRepository.save(status);
            statusRepository.save(status1);
            statusRepository.save(status2);
            statusRepository.save(status3);
            categoryRepository.save(category);
            categoryRepository.save(category1);
            categoryRepository.save(category2);
            Date begin = new SimpleDateFormat("yyyy-MM-dd").parse("2022-12-31");
            Date end = new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-15");
            task = new Task(begin, end,"Calle 14 #12-24",categoryRepository.findById(1L).get(),statusRepository.findById(1L).get());
            task = taskRepository.save(task);
            Date begin1 = new SimpleDateFormat("yyyy-MM-dd").parse("2003-09-02");
            Date end1 = new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-11");
            task1 = new Task(begin, end,"Calle 14 #12-24",categoryRepository.findById(3L).get(),statusRepository.findById(3L).get());
            task1 = taskRepository.save(task1);
            Date begin2 = new SimpleDateFormat("yyyy-MM-dd").parse("2003-09-02");
            Date end2 = new SimpleDateFormat("yyyy-MM-dd").parse("2023-09-11");
            task2 = new Task(begin, end,"Calle 14 #12-24",categoryRepository.findById(1L).get(),statusRepository.findById(2L).get());
            task2 = taskRepository.save(task2);

        }catch (ParseException ex){
            ex.printStackTrace();
        }

        //assertEquals("To-do", taskRepository.findTaskByStatus(1L));
    }

    @Test
    public void findTasDueInRangeTest(){
        Task task= new Task();
        Task task1 = new Task();
        Task task2 = new Task();
        try {
            Category category = new Category(1L,"Work",new Color(187,0, 255));
            Category category1 = new Category(2L,"University", new Color(23,45,197));
            Category category2 = new Category(3L, "Home Tasks", new Color(0,0,0));
            Status status = new Status(1L, "To-do");
            Status status1 = new Status(2L, "In progress");
            Status status2 =  new Status(3L, "Cancelled");
            Status status3 = new Status(4L, "finished");
            statusRepository.save(status);
            statusRepository.save(status1);
            statusRepository.save(status2);
            statusRepository.save(status3);
            categoryRepository.save(category);
            categoryRepository.save(category1);
            categoryRepository.save(category2);
            Date begin = new SimpleDateFormat("yyyy-MM-dd").parse("2022-12-31");
            Date end = new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-15");
            task = new Task(begin, end,"Calle 14 #12-24",categoryRepository.findById(1L).get(),statusRepository.findById(1L).get());
            task = taskRepository.save(task);
            Date begin1 = new SimpleDateFormat("yyyy-MM-dd").parse("2003-09-02");
            Date end1 = new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-12");
            task1 = new Task(begin1, end1,"Calle 14 #12-24",categoryRepository.findById(3L).get(),statusRepository.findById(3L).get());
            task1 = taskRepository.save(task1);
            Date begin2 = new SimpleDateFormat("yyyy-MM-dd").parse("2003-09-02");
            Date end2 = new SimpleDateFormat("yyyy-MM-dd").parse("2023-09-11");
            task2 = new Task(begin2, end2,"Calle 14 #12-24",categoryRepository.findById(1L).get(),statusRepository.findById(2L).get());
            task2 = taskRepository.save(task2);

        }catch (ParseException ex){
            ex.printStackTrace();
        }
        try {

            //assertEquals(2, taskRepository.findTasksDueInRange(new Date(), new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-16")).size());
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

}
