package challenge.to_do.perficient_back_api.service.impl;

import challenge.to_do.perficient_back_api.repository.model.Notify;
import challenge.to_do.perficient_back_api.repository.model.Task;
import challenge.to_do.perficient_back_api.repository.persistence.INotifyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskNotificationService {

    @Autowired
    private TaskServiceImpl taskService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private INotifyRepository notifyRepository;

    @Scheduled(cron = "0 0 0 * * ?") // Se ejecuta diariamente a la medianoche
    private void checkAndNotifyPendingTaskssave() {
        Optional<List<Task>> pendingTasks = taskService.findPendingTask();

        LocalDate currentDatePlus3Days = LocalDate.now().plusDays(3);

        List<Task> tasksEndingSoon = pendingTasks.get().stream()
                .filter(task -> isTaskEndingSoon(task, currentDatePlus3Days))
                .toList();

        tasksEndingSoon.forEach(task -> notificationService.sendTaskNotification(task.getId()));



    }

    private boolean isTaskEndingSoon(Task task, LocalDate currentDatePlus3Days) {
        Date taskEndDate = task.getEndtask();
        LocalDate localTaskEndDate = taskEndDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localTaskEndDate.isBefore(currentDatePlus3Days);
    }

    public void checkAndNotifyPendingTasks() {
        checkAndNotifyPendingTaskssave();
        Iterable<Notify> notifications = notifyRepository.findAll();
        notifications.forEach(notification -> notificationService.sendNotificationToClients(notification.getMessage()));
    }

}
