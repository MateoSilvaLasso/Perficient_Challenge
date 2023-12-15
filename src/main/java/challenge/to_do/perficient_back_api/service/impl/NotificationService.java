package challenge.to_do.perficient_back_api.service.impl;

import challenge.to_do.perficient_back_api.repository.model.Notify;
import challenge.to_do.perficient_back_api.repository.model.Task;
import challenge.to_do.perficient_back_api.repository.persistence.INotifyRepository;
import challenge.to_do.perficient_back_api.repository.persistence.ITaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class NotificationService {

    @Autowired
    private INotifyRepository notifyRepository;

    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @Autowired
    private ITaskRepository taskRepository;
    public Notify sendTaskNotification(Long taskId) {
        String notificationMessage = "La tarea con ID " + taskId + " está por finalizar en 3 días.";

        Notify notification = new Notify();
        notification.setCreationTime(new Date());
        notification.setMessage(notificationMessage);


        Optional<Task> task = taskRepository.findById(taskId);
        notification.setTask(task.get());

        return notifyRepository.save(notification);
    }

    public void sendNotificationToClients(String notification) {
        emitters.forEach(emitter -> {
            try {
                emitter.send(notification, MediaType.TEXT_PLAIN);
            } catch (IOException e) {
                // Manejar excepciones si es necesario
            }
        });
    }

    public SseEmitter subscribeForNotifications() {
        SseEmitter emitter = new SseEmitter();
        emitters.add(emitter);

        // Elimina el emisor cuando se cierra la conexión
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));

        return emitter;
    }

}
